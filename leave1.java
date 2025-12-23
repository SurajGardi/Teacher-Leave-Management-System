import javax.swing.*;
import javax.swing.event.*;
import java.awt.List;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import java.text.*;
import java.awt.print.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import java.util.Map;
import java.util.HashMap;
import java.util.Calendar;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Desktop;


class leave1 extends JFrame implements ActionListener, ItemListener{
    JLabel lbd, lnam, ldes, lstf, ltyp, lnum, lhed, lapd, ldf, lr, lltyp, lldat, lnod, lrem, llevdate, to, name_srch;
    JTextField tbd, tnam, tdes, tstf, temail, tnum, tr, tnod, name_scr,app_days;
    JPanel p1, p2, p3, p4, p5;
    JCheckBox ch1_scr, ch2_hdl;
    JComboBox lltyp_cmb, rem_cmb;
    JButton b_clr, b_sav, b_ser, b_upd, b_del, b_print, b_back, b_ext, b_bck;
    List lst;
    SimpleDateFormat sdf;
    Date date1, date2;

    Connection cn, cn2;
    PreparedStatement p;
    String sql;
    Statement s, s2;
    ResultSet rs, rs2;

    DateButton calb_app, calb_date, calb_form, calb_end;

    JTable resultTable; // JTable to display search results
    DefaultTableModel tableModel; // DefaultTableModel for the JTable
	
	 int leaveId;
	int cnt=0;
	int Data =0; 
	
/*	String path;
    OutputStream file;
    Document document;
    Paragraph p;

   PdfPTable table;
    PdfPCell c1;
    Font big, small;
    int cnt1;*/
	
    leave1(String t) {
        super(t);
        setSize(850, 600);
        setLocation(300, 100);
        setLayout(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		
        lhed = new JLabel("Leave Entry Form");
        add(lhed);
        lhed.setBounds(250, 30, 200, 30);
        lhed.setFont(new Font("Arial", Font.BOLD, 20));

        p1 = new JPanel();
        add(p1);
        p1.setBounds(0, 0, 1500, 1500);
        p1.setBackground(Color.PINK);
        p1.setLayout(null);

        p2 = new JPanel();
        p2.setBorder(BorderFactory.createTitledBorder("Employee Info"));
        p2.setLayout(null);
        p1.add(p2);
        p2.setBounds(50, 80, 540, 180);
        p2.setBackground(Color.WHITE);
        lbd = new JLabel("Bid Code:");
        p2.add(lbd);
        lbd.setBounds(10, 30, 70, 30);
        tbd = new JTextField();
        p2.add(tbd);
        tbd.setBounds(90, 30, 80, 30);

        lnam = new JLabel("Name:");
        p2.add(lnam);
        lnam.setBounds(10, 70, 70, 30);
        tnam = new JTextField();
        p2.add(tnam);
        tnam.setBounds(90, 70, 150, 30);

        ldes = new JLabel("Designation:");
        p2.add(ldes);
        ldes.setBounds(10, 110, 100, 30);
        tdes = new JTextField();
        p2.add(tdes);
        tdes.setBounds(90, 110, 150, 30);

        ch1_scr = new JCheckBox("Search by name");
        p2.add(ch1_scr);
        ch1_scr.setBounds(180, 30, 130, 30);

        lstf = new JLabel("Staff Type:");
        p2.add(lstf);
        lstf.setBounds(320, 30, 70, 30);
        tstf = new JTextField();
        p2.add(tstf);
        tstf.setBounds(390, 30, 100, 30);

        ltyp = new JLabel("Email:");
        p2.add(ltyp);
        ltyp.setBounds(320, 70, 70, 30);
        temail = new JTextField();
        p2.add(temail);
        temail.setBounds(390, 70, 100, 30);

        lnum = new JLabel("Number:");
        p2.add(lnum);
        lnum.setBounds(320, 110, 70, 30);
        tnum = new JTextField();
        p2.add(tnum);
        tnum.setBounds(390, 110, 100, 30);

        p3 = new JPanel();
        p3.setBorder(BorderFactory.createTitledBorder("Leave Entry"));
        p3.setLayout(null);
        p1.add(p3);
        p3.setBounds(50, 290, 540, 200);
        p3.setBackground(Color.WHITE);

        lapd = new JLabel("Application Date:");
        p3.add(lapd);
        lapd.setBounds(10, 30, 100, 30);

		JButton countButton = new JButton("Count");
		p3.add(countButton);
		countButton.setBounds(250, 30, 80, 30);

        calb_app = new DateButton();
        p3.add(calb_app);
        calb_app.setBounds(120, 30, 120, 30);

        lnod = new JLabel("No Of Days:");
        p3.add(lnod);
        lnod.setBounds(10, 110, 100, 30);
        tnod = new JTextField();
        p3.add(tnod);
        tnod.setBounds(120, 110, 120, 30);

        ch2_hdl = new JCheckBox("Half Day Leave");
        p3.add(ch2_hdl);
        ch2_hdl.setBounds(250, 110, 130, 30);

        ldf = new JLabel("Date From:");
        p3.add(ldf);
        ldf.setBounds(10, 70, 100, 30);

        calb_form = new DateButton();
        p3.add(calb_form);
        calb_form.setBounds(120, 70, 100, 30);

        JLabel to = new JLabel("TO");
        p3.add(to);
        to.setBounds(230, 70, 30, 30);

        calb_end = new DateButton();
        p3.add(calb_end);
        calb_end.setBounds(270, 70, 100, 30);

        lr = new JLabel("Reason:");
        p3.add(lr);
        lr.setBounds(10, 150, 50, 30);
        tr = new JTextField();
        p3.add(tr);
        tr.setBounds(70, 150, 200, 30);
		
		app_days=new JTextField();
		p3.add(app_days);
		app_days.setBounds(340, 30, 80, 30);

        lltyp = new JLabel("Leaving Type:");
        p3.add(lltyp);
        lltyp.setBounds(430, 5, 80, 20);
        lltyp_cmb = new JComboBox();
        lltyp_cmb = new JComboBox();
        lltyp_cmb.addItem("CL");
        lltyp_cmb.addItem("DL");
        lltyp_cmb.addItem("SP");
        lltyp_cmb.addItem("SSP");
        lltyp_cmb.addItem("ML");
        lltyp_cmb.addItem("LWP");
		lltyp_cmb.addItem("Late Mark");
        p3.add(lltyp_cmb);
        lltyp_cmb.setBounds(430,30, 100, 30);

        lrem = new JLabel("Remark");
        p3.add(lrem);
        lrem.setBounds(300, 140, 70, 30);
        rem_cmb = new JComboBox();
        p3.add(rem_cmb);
        rem_cmb.addItem("Sanctioned");
        rem_cmb.addItem("Non-Sanctioned");
        rem_cmb.addItem("Cancel");
        rem_cmb.setBounds(380, 140, 115, 30);
		
        p4 = new JPanel();
        p1.add(p4);
        p4.setBounds(600, 80, 200, 390);
        p4.setBackground(Color.WHITE);
        p4.setBorder(BorderFactory.createTitledBorder("OPERATIONS"));
        p4.setLayout(null);

        b_clr = new JButton("CLEAR");
        p4.add(b_clr);
        b_clr.setBounds(50, 30, 90, 30);

        b_sav = new JButton("SAVE");
        p4.add(b_sav);
        b_sav.setBounds(50, 70, 90, 30);

        b_ser = new JButton("SEARCH");
        p4.add(b_ser);
        b_ser.setBounds(50, 110, 90, 30);

        b_upd = new JButton("UPDATE");
        p4.add(b_upd);
        b_upd.setBounds(50, 150, 90, 30);

        b_del = new JButton("DELETE");
        p4.add(b_del);
        b_del.setBounds(50, 190, 90, 30);

        b_print = new JButton("HISTROY");
        p4.add(b_print);
        b_print.setBounds(50, 230, 90, 30);

        b_back = new JButton("BACK");
        p4.add(b_back);
        b_back.setBounds(50, 270, 90, 30);

        b_ext = new JButton("EXIT");
        p4.add(b_ext);
        b_ext.setBounds(50, 310, 90, 30);

        p5 = new JPanel();
        p5.setBorder(BorderFactory.createTitledBorder("SEARCH"));
        p5.setLayout(null);
        p1.add(p5);
        p5.setBounds(50, 290, 500, 190);
        p5.setBackground(Color.YELLOW);

        name_srch = new JLabel("ENTER NAME TO SEARCH:");
        p5.add(name_srch);
        name_srch.setBounds(30, 20, 160, 30);

        name_scr = new JTextField();
        p5.add(name_scr);
        name_scr.setBounds(200, 20, 180, 30);

        b_bck = new JButton("BACK");
        p5.add(b_bck);
        b_bck.setBounds(390, 20, 100, 30);

        lst = new List();
        p5.add(lst);
        lst.setBounds(30, 60, 460, 120);
        p5.setVisible(false);
		
		
		
		
        name_scr.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c) || c == KeyEvent.VK_SPACE)
                    super.keyTyped(e);
                else {
                    e.consume();
                    Toolkit tk = Toolkit.getDefaultToolkit();
                    tk.beep();
                }

                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    try {
                        lst.clear();
                        cn = DriverManager.getConnection("jdbc:mysql:///login", "root", "root");
                        s = cn.createStatement();
                        rs = s.executeQuery("select * from master where name like '%" + name_scr.getText() + "%'");
                        while (rs.next())
                            lst.addItem(rs.getString(1) + ") " + rs.getString(2));
                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
                }
            }
        });

        try {
            tbd.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (Character.isDigit(c) && tbd.getText().length() < 5)
                        super.keyTyped(e);
                    else {
                        e.consume();
                        Toolkit tk = Toolkit.getDefaultToolkit();
                        tk.beep();
                    }
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                        try {
                            cn = DriverManager.getConnection("jdbc:mysql:///login", "root", "root");
                            s = cn.createStatement();
                            rs = s.executeQuery("select * from master where bid=" + tbd.getText());
                            if (rs.next()) {
                                tnam.setText(rs.getString("name"));
                                tdes.setText(rs.getString("desi"));
                                tstf.setText(rs.getString("type"));
                                temail.setText(rs.getString("email"));
                                tnum.setText(rs.getString("mob"));
                            } else {
                                JOptionPane.showMessageDialog(null, "Such type of Entry not found");
                                tbd.setText("");tnam.setText("");tdes.setText("");
                                tstf.setText("");temail.setText("");tnum.setText("");
                            }
                            tnam.setEditable(false);tdes.setEditable(false);tstf.setEditable(false);
                            temail.setEditable(false);tnum.setEditable(false);
							cnt=1;
                        } catch (Exception ee) {
                            ee.printStackTrace();
                        }
                    }
                }
            });

            tnod.setEditable(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
		
		 countButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						// Get the selected application date
						String selectedDate = calb_app.getText();

						// Check if the date is valid and not empty
						if (!selectedDate.isEmpty()) {
							// Connect to the database
						/*	sql = "SELECT COUNT(*) FROM leavemaster WHERE lev_app_date = ? and lev_remark='Sanctioned'";
							p = cn.prepareStatement(sql);
							p.setString(1, selectedDate);

							// Execute the query to count applications for the selected date
							rs = p.executeQuery();*/
						sql =  "SELECT COUNT(*) FROM leavemaster"+
							   " WHERE ? BETWEEN lev_start_date AND lev_end_date "+
							   "AND lev_remark='Sanctioned'";
						
				
					 p = cn.prepareStatement(sql);
					p.setString(1, selectedDate);
				rs = p.executeQuery();
							if (rs.next()) {
								int count = rs.getInt(1);
								if(count<1)
								{
									app_days.setText(""+count);
								}
								else
								{
								app_days.setText(""+count);
								String path;
				OutputStream file;
				Document document;
				Paragraph p;

				PdfPTable table;
				PdfPCell c1;
				// Font big, small;
				int cnt;
			  path = "leave_list.pdf";
			file = null;
			document = null;
			
			  try {
            file = new FileOutputStream(new File(path));
            document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();
        } catch (Exception ef) {
            System.out.println(ef);
        }
			

				try {
            p = new Paragraph("ANEKANT ENGLISH MEDIUM SCHOOL,BARAMATI");
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            document.add(new Paragraph(" "));

            p = new Paragraph("Date = " + new java.util.Date());
            p.setAlignment(Element.ALIGN_RIGHT);
            document.add(p);
            // document.add(new Paragraph(""));
			
            document.add(new Paragraph(" "));
        } catch (Exception eh) {
            System.out.println(eh);
        }
		
		 float[] colsWidth = {10,5, 20 ,15,15,15,10,15};
        table = new PdfPTable(colsWidth);
        table.setWidthPercentage(120);

        c1 = new PdfPCell(new Phrase("Sr No"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("bid"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

      
	    c1 = new PdfPCell(new Phrase("Name"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Designation"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Start date"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
		
			c1 = new PdfPCell(new Phrase("End date"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
		
			c1 = new PdfPCell(new Phrase("lev type"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
		
			c1 = new PdfPCell(new Phrase("mobile"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
		
		
		

        table.setHeaderRows(1);
		
		 cnt = 0;
        try {
            //rs = s.executeQuery("select * from leavemaster where bid=" +tbd.getText());
			rs=s.executeQuery("select master.bid,name,desi,lev_start_date,lev_end_date,lev_type,mob from leavemaster,master where master.bid=leavemaster.bid and '"+selectedDate+"' between lev_start_date and lev_end_date and lev_remark='Sanctioned'");
            while (rs.next()) {
                cnt++;

                c1 = new PdfPCell(new Phrase(Integer.toString(cnt)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(rs.getString(1)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                table.addCell(rs.getString(2));
                table.addCell(rs.getString(3));
                table.addCell(rs.getString(4));
                table.addCell(rs.getString(5));
                table.addCell(rs.getString(6));
                table.addCell(rs.getString(7));
                // table.addCell(rs.getString(2));
            }
            // rs.close();
            // cn.close();

            PdfPCell cell = new PdfPCell(new Paragraph("Total No. of Stud = " + cnt));
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            document.add(table);
            document.add(new Paragraph(" "));
			 Desktop desktop = Desktop.getDesktop();
            desktop.open(new File(path));
			 
        } catch (Exception er) {
           JOptionPane.showMessageDialog(null,er);
        }
		 try {
            if (document != null) {
                document.close();
            }
            if (file != null) {
                file.close();
            }

            // To open the pdf file in Linux & Windows
           
        } catch (Exception ew) {
            ew.printStackTrace();
        }
								
								
								
								
								
								
								
								
								
								
								
								
								
								}	
								
								
							} else {
								JOptionPane.showMessageDialog(null, "No applications found for " + selectedDate);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Please select a valid application date.");
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error counting applications: " + ex.getMessage());
					} 
				}
			});

		
		
		
		// Add this button to your existing code
JButton button_countButton = new JButton("Cal_day");
p3.add(button_countButton);
button_countButton.setBounds(400, 70, 80, 30);

// Add this ActionListener for the "Count" button
button_countButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
     
	 
	    try {
                            sdf = new SimpleDateFormat("yyyy-MM-dd");
                            date1 = sdf.parse("" + calb_form.getText());
                            date2 = sdf.parse("" + calb_end.getText());

                            long diffmill = date2.getTime() - date1.getTime();
                            long differdays = (diffmill / (24 * 60 * 60 * 1000)) + 1;
							
							if(differdays<1)
								JOptionPane.showMessageDialog(null,"Please Select Correct Dates..");
							else
                            tnod.setText(Long.toString(differdays));
                        } catch (Exception ee) {
                            ee.printStackTrace();
                        }
			}
		});

        b_bck.addActionListener(this);  //add ActionListener for name search panel(p5)
        b_clr.addActionListener(this);	// for all clear button present on p4
        b_back.addActionListener(this); // for  back button present on p4
        b_ext.addActionListener(this);	// for  Exit button present on p4
        b_sav.addActionListener(this);  // for  Save button present on p4
        b_ser.addActionListener(this);  // Add ActionListener for the Search button
        b_upd.addActionListener(this);  // for update button present on p4
        b_del.addActionListener(this);  // for Delete button present on p4
        b_print.addActionListener(this);  // for printing button present on p4
        lst.addItemListener(this);
        lst.addItemListener(this);
        ch1_scr.addActionListener(this);
        ch2_hdl.addActionListener(this);
	
		
		try{
				cn=DriverManager.getConnection("jdbc:mysql:///login","root","root");
				s=cn.createStatement();
			}catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,e);
			}
        setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b_clr) {
            tbd.setEditable(true);
            tbd.setText("");  tnam.setText("");  tdes.setText("");
            tstf.setText(""); temail.setText(""); tnum.setText("");app_days.setText("");
            tr.setText("");   tnod.setText(""); ch1_scr.setSelected(false);
			b_sav.setEnabled(true);b_upd.setEnabled(true);b_del.setEnabled(true);
            ch2_hdl.setSelected(false);
			cnt=0;
        }
        if (e.getSource() == b_back) {
			dispose();
            new mainpage();
        }
        if (e.getSource() == b_bck) {
            p5.setVisible(false);
            p3.setVisible(true);
            ch1_scr.setSelected(false);
        }
        if (e.getSource() == b_ext) {
            System.exit(0);
        }
        if (e.getSource() == ch1_scr) {
            p3.setVisible(false);
            p5.setVisible(true);
            lst.clear();
            name_scr.setText("");
			cnt=1;
        }
        if (e.getSource() == ch2_hdl) {
            try {
                double no = Double.parseDouble(tnod.getText());
                
				if(no>1)
				{
					JOptionPane.showMessageDialog(null,"Half day leave applicable only for 1 day");
					ch2_hdl.setSelected(false);
				}
				else
				{
					double half = no / 2;
                tnod.setText("" + half);
				}
            } catch (Exception eee) {
                JOptionPane.showMessageDialog(null, "Please calulate days");
            }
        }
     // Modify the actionPerformed method for the "Save" button
			if (e.getSource() == b_sav) {
				try {
							if (tbd.getText().length() == 0 || tnod.getText().length() == 0 || tr.getText().length() == 0) {
								JOptionPane.showMessageDialog(null, "All Fields Required");
							} else {
									cn = DriverManager.getConnection("jdbc:mysql:///login", "root", "root");
									s = cn.createStatement();
									String s3 = "" + lltyp_cmb.getSelectedItem();
									String s4 = "" + rem_cmb.getSelectedItem();

									// Get the selected start and end dates
									String startDate = calb_form.getText();
									String endDate = calb_end.getText();
											// Save the data to the database
											String selectedDate = calb_app.getText();
											sql = "insert into leavemaster (lev_app_date,lev_start_date,lev_end_date,lev_type,lev_days,lev_reason,lev_remark,bid) values('" + selectedDate + "','" + startDate + "','" + endDate + "','" + s3 + "'," + tnod.getText() + ",'" + tr.getText() + "','" + s4 + "'," + tbd.getText() + ")";
											p = cn.prepareStatement(sql);
											p.execute();
											p.close();
											JOptionPane.showMessageDialog(null, "Data Inserted Successfully!!!!!");
											tbd.setText(""); tnam.setText(""); tdes.setText(""); tstf.setText("");
											temail.setText(""); tnum.setText(""); tr.setText(""); tnod.setText("");
											ch1_scr.setSelected(false); ch2_hdl.setSelected(false);
										}
						} catch (Exception ee) {
							JOptionPane.showMessageDialog(null, ee);
						}
			}

		if(e.getSource()==b_del)
		{
			b_sav.setEnabled(false);b_upd.setEnabled(false);
			 if (cnt == 0) {
					JOptionPane.showMessageDialog(null, "Please first search data from the database");
			} else {
				try {
					if(Data>0)
					{
					sql = "delete from leavemaster where lev_id="+Data+";";
					p = cn.prepareStatement(sql);
					p.execute();
					p.close();
					JOptionPane.showMessageDialog(null, "Record Deleted successfully!");
					
						tbd.setText("");
					tnam.setText("");
					tdes.setText("");
					tstf.setText("");
					temail.setText("");
					tnum.setText("");
					tr.setText("");
					tnod.setText("");
					b_sav.setEnabled(true);b_upd.setEnabled(true);
					ch1_scr.setSelected(false);
					ch2_hdl.setSelected(false);
					cnt = 0;
					Data=0;
					}
					else
						JOptionPane.showMessageDialog(null, "Please Search data and select lev_id");
					} catch (Exception exxx) {
				JOptionPane.showMessageDialog(null, "Please Select lev_id.");
				exxx.printStackTrace();
				
					}
				}
		}

		if(e.getSource()==b_upd)
		{
			b_sav.setEnabled(false);b_del.setEnabled(false);
			 if (cnt == 0) {
					JOptionPane.showMessageDialog(null, "Please first search data from the database");
			} else {
        try {
            // Get the values from the form fields
					String appDate = calb_app.getText();
					String startDate = calb_form.getText();
					String endDate = calb_end.getText();
					String leaveType = (String) lltyp_cmb.getSelectedItem();
					double leaveDays = Double.parseDouble(tnod.getText());
					String leaveReason = tr.getText();
					String leaveRemark = (String) rem_cmb.getSelectedItem();

					// Update the selected record in the database
					
					sql = "UPDATE leavemaster SET lev_app_date=?, lev_start_date=?, lev_end_date=?, lev_type=?, lev_days=?, lev_reason=?, lev_remark=? WHERE lev_id=?";
					p = cn.prepareStatement(sql);
					p.setString(1, appDate);
					p.setString(2, startDate);
					p.setString(3, endDate);
					p.setString(4, leaveType);
					p.setDouble(5, leaveDays);
					p.setString(6, leaveReason);
					p.setString(7, leaveRemark);
					p.setInt(8, Data); // Use the selected row's Leave ID

					int rowsUpdated = p.executeUpdate();
					if (rowsUpdated > 0) {
						JOptionPane.showMessageDialog(null, "Record updated successfully!");
					} else {
						JOptionPane.showMessageDialog(null, "Failed to update the record.");
					}

					// Clear the form fields after the update
					tbd.setText("");
					tnam.setText("");
					tdes.setText("");
					tstf.setText("");
					temail.setText("");
					tnum.setText("");
					tr.setText("");
					tnod.setText("");
					ch1_scr.setSelected(false);
					ch2_hdl.setSelected(false);
					b_sav.setEnabled(true);b_del.setEnabled(true);
					cnt = 0;
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "All fields required.");
				ex.printStackTrace();
        }
    }
		}
		
        if (e.getSource() == b_print) {
			
			  String path;
				OutputStream file;
				Document document;
				Paragraph p;

				PdfPTable table;
				PdfPCell c1;
				// Font big, small;
				int cnt;
			  path = "Test.pdf";
			file = null;
			document = null;
			
			  try {
            file = new FileOutputStream(new File(path));
            document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();
        } catch (Exception ef) {
            System.out.println(ef);
        }
			

				try {
            p = new Paragraph("ANEKANT ENGLISH MEDIUM SCHOOL,BARAMATI");
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            document.add(new Paragraph(" "));

            p = new Paragraph("Date = " + new java.util.Date());
            p.setAlignment(Element.ALIGN_RIGHT);
            document.add(p);
            // document.add(new Paragraph(""));
			p = new Paragraph("Employee Name= " +tnam.getText().toUpperCase());
            p.setAlignment(Element.ALIGN_LEFT);
            document.add(p);
			p = new Paragraph("Designation= " +tdes.getText().toUpperCase());
            p.setAlignment(Element.ALIGN_LEFT);
            document.add(p);
            document.add(new Paragraph(" "));
        } catch (Exception eh) {
            System.out.println(eh);
        }
		
		 float[] colsWidth = {15, 10, 15 ,15,15,10,10,30};
        table = new PdfPTable(colsWidth);
        table.setWidthPercentage(120);

        c1 = new PdfPCell(new Phrase("Sr No"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("lev_id"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

      
	    c1 = new PdfPCell(new Phrase("Application date"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Start date"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
		
			c1 = new PdfPCell(new Phrase("End date"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
		
			c1 = new PdfPCell(new Phrase("lev type"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
		
			c1 = new PdfPCell(new Phrase("lev days"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("lev remark"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
		

        table.setHeaderRows(1);
		
		 cnt = 0;
        try {
            rs = s.executeQuery("select * from leavemaster where bid=" +tbd.getText());
            while (rs.next()) {
                cnt++;

                c1 = new PdfPCell(new Phrase(Integer.toString(cnt)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(rs.getString(1)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                table.addCell(rs.getString(2));
                table.addCell(rs.getString(3));
                table.addCell(rs.getString(4));
                table.addCell(rs.getString(5));
                table.addCell(rs.getString(6));
                table.addCell(rs.getString(8));
                // table.addCell(rs.getString(2));
            }
            // rs.close();
            // cn.close();

            PdfPCell cell = new PdfPCell(new Paragraph("Total No. of Stud = " + cnt));
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            document.add(table);
            document.add(new Paragraph(" "));
			 Desktop desktop = Desktop.getDesktop();
            desktop.open(new File(path));
			 tbd.setText("");  tnam.setText("");  tdes.setText("");
            tstf.setText(""); temail.setText(""); tnum.setText("");
            tr.setText("");   tnod.setText(""); ch1_scr.setSelected(false);
            ch2_hdl.setSelected(false);
        } catch (Exception er) {
           JOptionPane.showMessageDialog(null,"Please Enter bid code");
        }
		 try {
            if (document != null) {
                document.close();
            }
            if (file != null) {
                file.close();
            }

            // To open the pdf file in Linux & Windows
           
        } catch (Exception ew) {
            ew.printStackTrace();
        }
		

		}
		
		
        // Add functionality for the Search button
        if (e.getSource() == b_ser) {
            try {
                cn = DriverManager.getConnection("jdbc:mysql:///login","root","root");
                s = cn.createStatement();
                String bid = tbd.getText();
                String query = "SELECT * FROM leavemaster WHERE bid=" + bid;
                rs = s.executeQuery(query);

                // Create a new JFrame to display search results
                JFrame searchResultFrame = new JFrame("Search Results");
                searchResultFrame.setSize(600, 400);

                // Create a DefaultTableModel to hold the search results
                tableModel = new DefaultTableModel();
                resultTable = new JTable(tableModel);
                tableModel.addColumn("Leave ID");
                tableModel.addColumn("Application Date");
                tableModel.addColumn("Leave Start Date");
                tableModel.addColumn("Leave End Date");
                tableModel.addColumn("Leave Type");
                tableModel.addColumn("Leave Days");
                tableModel.addColumn("Leave Reason");
                tableModel.addColumn("Leave Remark");

                // Populate the table with search results
                while (rs.next()) {
                    tableModel.addRow(new Object[]{
                        rs.getInt("lev_id"),rs.getString("lev_app_date"),rs.getString("lev_start_date"),
                        rs.getString("lev_end_date"),rs.getString("lev_type"),rs.getDouble("lev_days"),
                        rs.getString("lev_reason"),rs.getString("lev_remark")
                    });
                }
                JScrollPane scrollPane = new JScrollPane(resultTable);
                searchResultFrame.add(scrollPane);
                searchResultFrame.setVisible(true);
				cnt=1;
            } catch (Exception ee) {
                JOptionPane.showMessageDialog(null, "Please Enter Bid code");
            }
        }
		try{
				ListSelectionModel select= resultTable.getSelectionModel();
				select.addListSelectionListener(new ListSelectionListener() 
				{  
				
					public void valueChanged(ListSelectionEvent e) 
					{  
					try{
							int[] row = resultTable.getSelectedRows();  
							int[] columns = resultTable.getSelectedColumns();  
							for (int i = 0; i < row.length; i++) 
							{  
								for (int j = 0; j < columns.length; j++) 
								{  
									Data = (int) resultTable.getValueAt(row[i], columns[j]);  
								} 
							}
						}catch(Exception ex){JOptionPane.showMessageDialog(null,"Please Select Lev_id");}				
							  
					try {
						cn = DriverManager.getConnection("jdbc:mysql:///login","root","root");
						s = cn.createStatement();
						String bid = tbd.getText();
						String query = "SELECT * FROM leavemaster WHERE lev_id=" +Data;
						rs = s.executeQuery(query);
						 if (rs.next())
							 {
								// Retrieve data from the ResultSet and display it in your fields
								calb_app.setText(rs.getString("lev_app_date"));
								calb_form.setText(rs.getString("lev_start_date"));
								calb_end.setText(rs.getString("lev_end_date"));
								lltyp_cmb.setSelectedItem(rs.getString("lev_type"));
								tnod.setText(String.valueOf(rs.getDouble("lev_days")));
								tr.setText(rs.getString("lev_reason"));
								rem_cmb.setSelectedItem(rs.getString("lev_remark"));
								cnt=1;
							}
						
					}catch(Exception exx){JOptionPane.showMessageDialog(null,exx);}
					}			
				
				}); 
		}catch(Exception er){};
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == lst) {
            tbd.setEditable(false);tnam.setEditable(false);tdes.setEditable(false);
			tstf.setEditable(false);temail.setEditable(false);tnum.setEditable(false);
            try {
                String arr = lst.getSelectedItem();
                StringBuilder num = new StringBuilder();
                for (int i = 0; i < arr.length(); i++) {
                    char c = arr.charAt(i);
                    if (Character.isDigit(c)) {
                        num.append(c);
                    }
                }
                s = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs = s.executeQuery("select * from master where bid=" + num);
                while (rs.next()) {
                    tbd.setText(rs.getString(1));
                    tnam.setText(rs.getString("name"));
                    tdes.setText(rs.getString("desi"));
                    tstf.setText(rs.getString("type"));
                    temail.setText(rs.getString("email"));
                    tnum.setText(rs.getString("mob"));
                }
            } catch (Exception ee) {
            }
            p5.setVisible(false);
            p3.setVisible(true);
            ch1_scr.setSelected(false);
        }
    }
	
	
     
    public static void main(String args[]) {
        new leave1("JCalendar");
    }
}
