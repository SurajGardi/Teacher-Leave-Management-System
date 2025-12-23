import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.sql.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Desktop;
import java.io.*;

class lev_all_employee extends JFrame implements ActionListener
{
	JPanel p1,p2,p3;
	JLabel l1,l2,l3,l4;
	JButton b_show,b_back,b_exit;
	JComboBox cmb;
	 Connection cn;
    PreparedStatement prstm;
    String sql;
    Statement s;
    ResultSet rs;
	int cnt=0;
	DateButton calb_app,calb_form;
	lev_all_employee(String t)
	{	
		super(t);
		setTitle("Leave");
	    setSize(600,300);
	   
		setLocation(500,150);
		setLayout(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// memory allocation
		
		p1=new JPanel();
		add(p1);
		p1.setBounds(0,0,600,300);
		p1.setBackground(Color.PINK);
		p1.setLayout(null);
		
		l3=new JLabel("All Employee Report");
		p1.add(l3);
		l3.setBounds(150,30,500,30);
		l3.setFont(new Font("Arial",Font.BOLD,20));
		
		p2=new JPanel();
		p1.add(p2);
		p2.setBounds(70,70,500,100);
		p2.setLayout(null);
		p2.setBackground(Color.PINK);
		p2.setBorder(BorderFactory.createTitledBorder("INFORMATION"));
		l1=new JLabel("select Date =");
		p2.add(l1);
		l1.setBounds(20,30,80,30);
		
		calb_app = new DateButton();
        p2.add(calb_app);
        calb_app.setBounds(110, 30, 120, 30);
			
		l2=new JLabel("TO");
		p2.add(l2);
		l2.setBounds(240,30,20,30);
			
		calb_form= new DateButton();
        p2.add(calb_form);
        calb_form.setBounds(270, 30, 120, 30);
		
		l4=new JLabel("Select type=");
		p2.add(l4);
		l4.setBounds(20,70,80,30);
		
		cmb=new JComboBox();
		cmb.addItem("Principal");
			cmb.addItem("Vice Principal");
			cmb.addItem("TGT");
			cmb.addItem("PRT");
			cmb.addItem("P.E.");
			cmb.addItem("Libraian");
			cmb.addItem("PE");
			cmb.addItem("NTT");
			cmb.addItem("Clerk");
			cmb.addItem("Jr.Clerk ");
			cmb.addItem("Receptionist ");
			cmb.addItem(" IT Person  ");
			cmb.addItem("Teaching");
			cmb.addItem("Non-Teaching");
		p2.add(cmb);
		
		cmb.setBounds(110,70,120,25);
		
		p3=new JPanel();
		p1.add(p3);
		p3.setBounds(70,175,500,80);
		p3.setLayout(null);
		p3.setBackground(Color.PINK);
		p3.setBorder(BorderFactory.createTitledBorder("OPERATIONS"));
		
		b_show=new JButton("SHOW");
		p3.add(b_show);
		b_show.setBounds(70,30,100,30);
		
		b_back=new JButton("BACK");
		p3.add(b_back);
		b_back.setBounds(180,30,70,30);
		
		b_exit=new JButton("EXIT");
		p3.add(b_exit);
		b_exit.setBounds(260,30,70,30);
		
		b_show.addActionListener(this);
		b_back.addActionListener(this);
		b_exit.addActionListener(this);
		try{
				cn=DriverManager.getConnection("jdbc:mysql:///login","root","root");
				s=cn.createStatement();
			}catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,e);
			}
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b_show)
		{
			
			 String path;
				OutputStream file;
				Document document;
				Paragraph p;

				PdfPTable table;
				PdfPCell c1;
				Font big, small;
				
				
		// big = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        // small = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		
		 path = "lev_all_employee.pdf";
        file = null;
        document = null;
		
		  try {
            file = new FileOutputStream(new File(path));
            document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();
        } catch (Exception eq) {
            System.out.println(eq);
        }
		 try {
            p = new Paragraph("ANEKANT ENGLISH MEDIUM SCHOOL,BARAMATI");
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
			 p = new Paragraph(cmb.getSelectedItem()+" Staff -> All Empolyee Report");
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            document.add(new Paragraph(" "));
			
            p = new Paragraph("Date = " + new java.util.Date());
            p.setAlignment(Element.ALIGN_RIGHT);
            document.add(p);
            // document.add(new Paragraph(" "));
			
			 p = new Paragraph("Date= "+calb_app.getText()+" to "+calb_form.getText());
            p.setAlignment(Element.ALIGN_LEFT);
            document.add(p);
            document.add(new Paragraph(" "));
        } catch (Exception eeee) {
            System.out.println(eeee);
        }
		
		
		 float[] colsWidth = { 10,10, 15,10,20,20,15,15};
        table = new PdfPTable(colsWidth);
        table.setWidthPercentage(115);

		 c1 = new PdfPCell(new Phrase("Sr no"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
		
        c1 = new PdfPCell(new Phrase("bid"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Name"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Desi"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Lev_start"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Lev_end"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("lev_days"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Lev_remark"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);
		
		try {
            // rs = s.executeQuery("select master.bid, name,desi,lev_start_date,lev_end_date,lev_type,lev_remark from master,leavemaster where master.bid=leavemaster.bid and lev_type='"+cmb.getSelectedItem()+"' and lev_start_date='"+calb_app.getText()+"';");
				/*sql = "SELECT master.bid, name, desi, lev_start_date, lev_end_date,lev_days, lev_remark " +
             "FROM master, leavemaster " +
             "WHERE master.bid = leavemaster.bid " +
			  "AND type= ? " +
             "AND ? <= lev_start_date " +
             "AND ? >= lev_end_date";
			 */
			/* sql = "SELECT master.bid, name, desi, lev_start_date, lev_end_date, lev_days, lev_remark " +
					  "FROM master, leavemaster " +
					  "WHERE master.bid = leavemaster.bid " +
					  "AND type = ? " +
					  "AND lev_start_date >= ? " +
					 "AND lev_end_date <= ?";
	
	 sql="SELECT master.bid, name, desi, lev_start_date, lev_end_date, lev_days, lev_remark"+
          "FROM master "+
          "JOIN leavemaster ON master.bid = leavemaster.bid "+
          "WHERE  master.type=?"+
		  "and lev_start_date >= ?"+
		  "or lev_end_date >= ?" ;

				prstm= cn.prepareStatement(sql);
				prstm.setString(1, cmb.getSelectedItem().toString()); // Assuming cmb.getSelectedItem() returns a String
				prstm.setString(2, calb_app.getText());
				prstm.setString(3, calb_form.getText()); // Assuming calb_app.getText() returns a String representing a date
				rs = prstm.executeQuery();
				
			
    		sql = "SELECT master.bid, name, desi, lev_start_date, lev_end_date, lev_days, lev_remark " +
				  "FROM master " +
				  "JOIN leavemaster ON master.bid = leavemaster.bid " +
				  "WHERE master.type = ? " +
				  "AND (lev_start_date >= ? AND lev_end_date <= ?) ";

		prstm = cn.prepareStatement(sql);
		prstm.setString(1, cmb.getSelectedItem().toString()); // Assuming cmb.getSelectedItem() returns a String
		prstm.setString(2, calb_app.getText()); // Assuming calb_app.getText() returns a String representing a date
		prstm.setString(3, calb_form.getText()); // Assuming calb_form.getText() returns a String representing a date
		rs = prstm.executeQuery();
	*/
	
	sql = "SELECT master.bid, name, desi, lev_start_date, lev_end_date, lev_days, lev_remark " +
      "FROM master " +
      "JOIN leavemaster ON master.bid = leavemaster.bid " +
      "WHERE master.type = ? " +
      "AND (lev_start_date <= ? AND lev_end_date >= ?)";

prstm = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
prstm.setString(1, cmb.getSelectedItem().toString()); // Assuming cmb.getSelectedItem() returns a String
prstm.setString(2, calb_form.getText()); // Assuming calb_form.getText() returns a String representing a date
prstm.setString(3, calb_app.getText()); // Assuming calb_app.getText() returns a String representing a date
rs = prstm.executeQuery();

	
	
	
	
	
	
	
	
	
	
	
	
	
				
				if(rs.next())
				{
					rs.beforeFirst();
				while (rs.next()) {
                cnt++;

                c1 = new PdfPCell(new Phrase(Integer.toString(cnt)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(rs.getString("bid")));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
	
				c1 = new PdfPCell(new Phrase(rs.getString("name")));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(rs.getString("desi")));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
				
				c1 = new PdfPCell(new Phrase(rs.getString("lev_start_date")));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(rs.getString("lev_end_date")));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(rs.getString("lev_days")));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
				
				c1 = new PdfPCell(new Phrase(rs.getString("lev_remark")));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
				
				
            }
				  Desktop desktop = Desktop.getDesktop();
				  desktop.open(new File(path));
				}
			else
				JOptionPane.showMessageDialog(null,"No record found for that Date "+calb_app.getText()+" to "+calb_form.getText());
            // PdfPCell cell = new PdfPCell(new Paragraph("Total No. of Stud = " + cnt));
            // cell.setColspan(2);
            // cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            // table.addCell(cell);

            document.add(table);
            document.add(new Paragraph(" "));
			cnt=0;
        } catch (Exception ee) {
            System.out.println(ee);
        }
		
		
		
		 try {
            if (document != null) {
                document.close();
            }
            if (file != null) {
                file.close();
            }

            // To open the pdf file in Linux & Windows
          
        } catch (Exception eex) {
            eex.printStackTrace();
        }
		
		}
		if(e.getSource()==b_back)
		{
			dispose();
		new mainpage();
		}
	    
		if(e.getSource()==b_exit)
			System.exit(0);
	}

	public static void main(String args[])
	{
		new lev_all_employee("JCalendar");
	}
}