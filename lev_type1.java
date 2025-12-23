import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.sql.*;
import java.util.logging.Logger; // Import the Logger class for java.util.logging

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Desktop;
import java.io.File;

class lev_type1 extends JFrame implements ActionListener {
    JPanel p1, p2, p3;
    JTextField t1, t2;
    JLabel l1, l2, l3, to;
    JComboBox cmb;
    JButton b_show, b_back, b_exit;
    
    Connection cn;
    PreparedStatement prstm;
    String sql;
    Statement s;
    ResultSet rs;
    int cnt = 1;
    DateButton calb_app, calb_form;

    private static final Logger logger = Logger.getGlobal(); // Create a logger

    lev_type1(String t) {
        super(t);
        setTitle("Leave");
        setSize(600, 400);

        setLocation(500, 150);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Memory allocation

        p1 = new JPanel();
        add(p1);
        p1.setBounds(0, 0, 600, 400);
        p1.setBackground(Color.PINK);
        p1.setLayout(null);

        l3 = new JLabel("LeaveWise All Employee Report");
        p1.add(l3);
        l3.setBounds(150, 30, 500, 30);
        //l3.setFont(new Font("Arial", Font.BOLD, 20);

        p2 = new JPanel();
        p1.add(p2);
        p2.setBounds(70, 70, 500, 150);
        p2.setLayout(null);
        p2.setBackground(Color.PINK);
        p2.setBorder(BorderFactory.createTitledBorder("INFORMATION"));
        l1 = new JLabel("select lev type =");
        p2.add(l1);
        l1.setBounds(70, 30, 100, 50);

        cmb = new JComboBox();
        cmb = new JComboBox();
        cmb.addItem("CL");
        cmb.addItem("DL");
        cmb.addItem("SP");
        cmb.addItem("SSP");
        cmb.addItem("ML");
        cmb.addItem("LWP");
        cmb.addItem("Late Mark");
        p2.add(cmb);
        cmb.setBounds(180, 30, 100, 30);

        l2 = new JLabel("Select Date =");
        p2.add(l2);
        l2.setBounds(70, 70, 100, 50);

        calb_app = new DateButton();
        p2.add(calb_app);
        calb_app.setBounds(180, 75, 110, 30);

        l2 = new JLabel("TO");
        p2.add(l2);
        l2.setBounds(300, 75, 20, 30);

        calb_form = new DateButton();
        p2.add(calb_form);
        calb_form.setBounds(330, 75, 110, 30);

        p3 = new JPanel();
        p1.add(p3);
        p3.setBounds(70, 220, 500, 80);
        p3.setLayout(null);
        p3.setBackground(Color.PINK);
        p3.setBorder(BorderFactory.createTitledBorder("OPERATIONS"));

        b_show = new JButton("SHOW");
        p3.add(b_show);
        b_show.setBounds(70, 30, 100, 30);

        b_back = new JButton("BACK");
        p3.add(b_back);
        b_back.setBounds(180, 30, 70, 30);

        b_exit = new JButton("EXIT");
        p3.add(b_exit);
        b_exit.setBounds(260, 30, 70, 30);

        b_show.addActionListener(this);
        b_back.addActionListener(this);
        b_exit.addActionListener(this);

        try {
            cn = DriverManager.getConnection("jdbc:mysql:///login", "root", "root");
            s = cn.createStatement();
        } catch (Exception e) {
            logger.severe("Database connection error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, e);
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b_show) {
            try {
                rs = s.executeQuery("SELECT * FROM leavemaster"); 
                if (rs != null) {
                    Workbook workbook = new XSSFWorkbook();
                    Sheet sheet = workbook.createSheet("Employee Data");
					
					CellStyle centerAlignment = workbook.createCellStyle();
                    centerAlignment.setAlignment(HorizontalAlignment.CENTER);

sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,7));


Row collegeNameRow = sheet.createRow(0);
Cell collegeNameCell = collegeNameRow.createCell(0);
collegeNameCell.setCellValue("ANEKANT ENGLISH MEDIUM SCHOOL,BARAMATI"); // Replace with your college name

// Set the style for the college name cell (centered and bold)
CellStyle collegeNameStyle = workbook.createCellStyle();
collegeNameStyle.setAlignment(HorizontalAlignment.CENTER);

// Create a font for the college name
org.apache.poi.ss.usermodel.Font collegeNameFont = workbook.createFont();

collegeNameFont.setFontName("Arial"); // You can specify the font name
collegeNameFont.setFontHeightInPoints((short)15); // Set the font size here

collegeNameFont.setBold(true);
collegeNameStyle.setFont(collegeNameFont);
collegeNameCell.setCellStyle(collegeNameStyle);
					
					
                    Row headerRow = sheet.createRow(3);
                    String[] headers = { "Sr no", "bid", "lev_id", "lev_reason", "Lev_start", "Lev_end", "lev_days", "Lev_remark" };
                    for (int i = 0; i < headers.length; i++) {
                        Cell cell = headerRow.createCell(i);
                        cell.setCellValue(headers[i]);
						  cell.setCellStyle(centerAlignment); 
                    }
					
                    int rowNumber = 4;
                    while (rs.next()) {
                        Row row = sheet.createRow(rowNumber++);
                        // Populate the row with data from the result set
                        row.createCell(0).setCellValue(cnt);
                        row.createCell(1).setCellValue(rs.getInt("bid"));
                        row.createCell(2).setCellValue(rs.getString("lev_id"));
                        row.createCell(3).setCellValue(rs.getString("lev_reason"));
                        row.createCell(4).setCellValue(rs.getString("lev_start_date"));
                        row.createCell(5).setCellValue(rs.getString("lev_end_date"));
                        row.createCell(6).setCellValue(rs.getString("lev_days"));
                        row.createCell(7).setCellValue(rs.getString("lev_remark"));
						
						 for (int i = 0; i < headers.length; i++) {
                            Cell dataCell = row.getCell(i);
                            dataCell.setCellStyle(centerAlignment); // Apply centered alignment to data cells
							 sheet.autoSizeColumn(i);
                        }
						
						cnt++;
                    }	
					try (FileOutputStream fileOut = new FileOutputStream("employee_data.xlsx")) {
						workbook.write(fileOut);
						
					} catch (IOException ioe) {
						logger.severe("Error saving Excel file: " + ioe.getMessage());
					}


                    // Open the Excel file
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(new File("employee_data.xlsx"));
                } 
				else {
                    logger.severe("ResultSet is null.");
                }
            } catch (Exception ee) {
                logger.severe("Error generating and opening Excel file: " + ee.getMessage());
                System.out.println(ee);
            }
        }

        if (e.getSource() == b_back) {
            dispose();
            new mainpage();
            dispose();
        }

        if (e.getSource() == b_exit) {
            System.exit(0);
        }
    }

    public static void main(String args[]) {
        new lev_type1("JCalendar");
    }
}
