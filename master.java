import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
// import java.util.*;
import java.util.regex.*;
class master extends JFrame implements ActionListener,ItemListener
{		
		Frame f1;
		JLabel l1,l2,l3,l4,l5,l6,l7,l8;
		JTextField  t1,t2,t3,t4,t5,t6,t7,t8;
		JPanel p1,p2,p3,p4;
		JComboBox cmb1,cmb2;
		JButton clr,sav,bac,up,de,ex,ser,p4_back;
		Connection cn;
		PreparedStatement p;
		String sql;
		Statement s;
		ResultSet rs;
		int flag=0;
		JRadioButton rb1,rb2;
		ButtonGroup grp1;
		List lst;int cnt=0;
		master()
		{
			setSize(600,500);
			setLocation(400,100);
			
			setLayout(null);
			
			
			p1=new JPanel();
			add(p1);
			p1.setBounds(0,0,600,500);
			p1.setBackground(Color.PINK);
			p1.setLayout(null);
			
			p2 = new JPanel();
			p2.setBorder(BorderFactory.createTitledBorder("Empolyee info"));
			p2.setLayout(null);

			p1.add(p2);
			p2.setBounds(50,80,500,140);
			p2.setBackground(Color.WHITE);
			l1=new JLabel("MASTER ENTRY FORM");
			p1.add(l1);
			l1.setBounds(180,20,240,50);
			l1.setFont(new Font("Arial",Font.BOLD,20));
			
			l2=new JLabel("Bid Code:");
			p2.add(l2);
			l2.setBounds(20,20,80,30);
			t1=new JTextField();
			p2.add(t1);
			t1.setBounds(90,20,80,30);
			
			l3=new JLabel("Name:");
			p2.add(l3);
			l3.setBounds(30,60,80,30);
			t2=new JTextField();
			p2.add(t2);
			t2.setBounds(90,60,150,30);
			
			l4=new JLabel("Designation:");
			p2.add(l4);
			l4.setBounds(15,100,80,30);
			cmb1=new JComboBox();
			cmb1= new JComboBox();
			cmb1.addItem("Principal");
			cmb1.addItem("Vice Principal");
			cmb1.addItem("Primary Teacher");
			cmb1.addItem("Pre-Primary Teacher");
			cmb1.addItem("Drawing Teacher");
			cmb1.addItem("Sport Teacher");
			cmb1.addItem("Music Teacher");
			cmb1.addItem("Librarian");
			cmb1.addItem("Counsellor");
			cmb1.addItem("Pre-Primary Teacher");
			cmb1.addItem("Jr.Clerk");
			cmb1.addItem("Receptionist");
			cmb1.addItem("Peon");
			cmb1.addItem("Mavashi");
			cmb1.addItem("Sport Teacher(Part Time)");
			
			p2.add(cmb1);
			cmb1.setBounds(90,100,150,30);
			
			l5=new JLabel("Mob No:");
			p2.add(l5);
			l5.setBounds(260,20,50,30);
			t3=new JTextField();
			p2.add(t3);
			t3.setBounds(320,20,150,30);
			
			l6=new JLabel("Email:");
			p2.add(l6);
			l6.setBounds(260,60,50,30);
			t4=new JTextField();
			p2.add(t4);
			t4.setBounds(320,60,150,30);
			
			l7=new JLabel("Type:");
			p2.add(l7);
			l7.setBounds(260,100,80,30);
			cmb2=new JComboBox();
			cmb2.addItem("Principal");
			cmb2.addItem("Vice Principal");
			cmb2.addItem("TGT");
			cmb2.addItem("PRT");
			cmb2.addItem("P.E.");
			cmb2.addItem("Libraian");
			cmb2.addItem("PE");
			cmb2.addItem("NTT");
			cmb2.addItem("Clerk");
			cmb2.addItem("Jr.Clerk ");
			cmb2.addItem("Receptionist ");
			cmb2.addItem(" IT Person  ");
			cmb2.addItem("Teaching");
			cmb2.addItem("Non-Teaching");
			p2.add(cmb2);
			cmb2.setBounds(320,100,150,30);
			
			
			
			p3= new JPanel();
			p3.setBorder(BorderFactory.createTitledBorder("Operations"));
			p3.setLayout(null);
			p1.add(p3);
			p3.setBounds(50,240,500,140);
			// p2.setBounds(50,80,500,140);
			p3.setBackground(Color.WHITE);
			
			
			
			p3.setVisible(true);
			
			p4= new JPanel();
			p4.setBorder(BorderFactory.createTitledBorder("SEARCH"));
			p4.setLayout(null);
			p1.add(p4);
			p4.setBounds(50,240,500,180);
			// p2.setBounds(50,80,500,140);
			p4.setBackground(Color.YELLOW);
			
			l8=new JLabel("SELECT OPTION TO SEARCH:");
			p4.add(l8);
			l8.setBounds(30,20,180,30);
			
			rb1=new JRadioButton("Bid No");rb2=new JRadioButton("Name");
			rb1.setSelected(true);
			grp1=new ButtonGroup();
			grp1.add(rb1);grp1.add(rb2);
			p4.add(rb1);p4.add(rb2);
			rb1.setBounds(210,20,80,30);rb2.setBounds(300,20,80,30);
			p4_back=new JButton("BACK");
			p4.add(p4_back);
			p4_back.setBounds(390,20,100,30);
			t8=new JTextField();
			p4.add(t8);
			t8.setBounds(30,60,460,30);
			 lst=new List();
			 p4.add(lst);
			lst.setBounds(30,95,460,80);
			
			
			
			
			p4.setVisible(false);
			
			
			
			clr=new JButton("CLEAR");
			sav=new JButton("SAVE");
			bac=new JButton("BACK");
			up=new JButton("UPDATE");
			de=new JButton("DELETE");
			ex=new JButton("EXIT");
			// ser=new JButton("SEARCH");
			
			
			p3.add(clr);
			p3.add(sav);
			p3.add(bac);
			p3.add(up);
			p3.add(de);
			p3.add(ex);
			// p3.add(ser);
			
			clr.setBounds(100,50,80,30);sav.setBounds(200,50,80,30);bac.setBounds(300,50,80,30);
			up.setBounds(100,90,80,30);de.setBounds(200,90,80,30);ex.setBounds(300,90,80,30);
			// ser.setBounds(200,130,80,30);
			try{
				cn=DriverManager.getConnection("jdbc:mysql:///login","root","root");
				s=cn.createStatement();
				rs=s.executeQuery("select * from master");
			}catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,e);
			}
			
			sav.addActionListener(this);
			clr.addActionListener(this);
			bac.addActionListener(this);
			up.addActionListener(this);
			de.addActionListener(this);
			ex.addActionListener(this);
			rb1.addActionListener(this);
			rb2.addActionListener(this);
			p4_back.addActionListener(this);
			lst.addItemListener(this);
			
			setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			
		}
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			if(e.getSource()==sav)
			{
				t1.setEditable(true);t2.setEditable(true);t3.setEditable(true);t4.setEditable(true);
				if(t1.getText().length()==0 || t2.getText().length()==0|| t3.getText().length()==0 || t4.getText().length()==0 )
					{
						JOptionPane.showMessageDialog(null,"All Fields Required - 1");
					}
				else{
					String s3=""+cmb1.getSelectedItem();
					String s4=""+cmb2.getSelectedItem();
					// int mob=Integer.parseInt(""+t3.getText());
					String s=t4.getText();
					//String s2="[a-zA-Z0-9]+[@][a-z]+[.][a-z]{2,3}";
					String s2="^[a-zA-Z0-9]+@[a-z]+\\.[a-z]{2,3}$";
					Pattern pattern=Pattern.compile(s2);
					Matcher matcher=pattern.matcher(s);
					if(matcher.matches())
					{}
					else
						JOptionPane.showMessageDialog(null,"Invalid email");
					
					if(t1.getText().length()>0 && t2.getText().length()>0 && t4.getText().length()>0 && t3.getText().length()==10 && matcher.matches())
						{
							sql="insert into master values("+t1.getText()+",'"+t2.getText()+"','"+s3+"','"+s4+"',"+t3.getText()+",'"+t4.getText()+"')";
							p=cn.prepareStatement(sql);
							p.execute();
							p.close();
							JOptionPane.showMessageDialog(null,"Data Inserted Successfully!!!!!");
							t1.setText("");t2.setText("");t3.setText("");t4.setText("");
						}
						else if(t3.getText().length()!=10)
						{
							JOptionPane.showMessageDialog(null,"Please insert 10 digit number");
						}
				}
			}
			
		}catch(Exception ee)
		{
			JOptionPane.showMessageDialog(null,"Duplicate Entry Found!!!");
			System.out.println(ee);
		}
		if(e.getSource()==clr)
		{
			t1.setEditable(true);t2.setEditable(true);t3.setEditable(true);t4.setEditable(true);
			t1.setText("");t2.setText("");t3.setText("");t4.setText("");sav.setEnabled(true);de.setEnabled(true);
			up.setEnabled(true);
			cnt=0;
		}
		if(e.getSource()==bac)
		{
			new mainpage();
		}
		if(e.getSource()==up)
		{
			t1.setEditable(true);t2.setEditable(true);t3.setEditable(true);t4.setEditable(true);
			sav.setEnabled(false);de.setEnabled(false);
			if(cnt==0)
			{
				JOptionPane.showMessageDialog(null,"search the data from database");
				p3.setVisible(false);
				p4.setVisible(true);
				lst.clear();
			}
			else{
					String s3=""+cmb1.getSelectedItem();
					String s4=""+cmb2.getSelectedItem();
						try{
									sql="update master set name='"+t2.getText()+"', mob="+t3.getText()+", email='"+t4.getText()+"',desi='"+s3+"',type='"+s4+"'  where bid="+t1.getText();
									// "update student set name='"+name+"', marks="+marks+" where roll="+roll;
									p=cn.prepareStatement(sql);
									p.execute();
									p.close();
									JOptionPane.showMessageDialog(null,"Data Updated Successfully !!!!!");
									t1.setText("");t2.setText("");t3.setText("");t4.setText("");
									sav.setEnabled(true);de.setEnabled(true);
									cnt--;
					}catch(Exception ee)
					{
						JOptionPane.showMessageDialog(null,ee);
					}
			}
		}
		if(e.getSource()==ex)
		{
			System.exit(0);
		}
		if(e.getSource()==de)
		{
			t1.setEditable(true);t2.setEditable(true);t3.setEditable(true);t4.setEditable(true);
			sav.setEnabled(false);up.setEnabled(false);
			if(cnt==0)
			{
				JOptionPane.showMessageDialog(null,"search the data from database");
				p3.setVisible(false);
				p4.setVisible(true);
				lst.clear();
				// rb1.setSelected(false);rb2.setSelected(false);
			}
			else{
				try{
							sql="delete from master where bid="+t1.getText();
							p=cn.prepareStatement(sql);
							p.execute();
							p.close();
						    JOptionPane.showMessageDialog(null,"Data Deleted!!!!!");
							t1.setText("");t2.setText("");t3.setText("");t4.setText("");
							sav.setEnabled(true);up.setEnabled(true);
									cnt--;
					}catch(Exception ee)
					{
						JOptionPane.showMessageDialog(null,ee);		// give here message like : Plz delete record 1st then delete employee
					}
			}
		}
		t8.addKeyListener(new KeyAdapter()
        {   
            public void keyTyped(KeyEvent e)
            {
				if(rb1.isSelected())
				{
					char c = e.getKeyChar();
					if(Character.isDigit(c) && t8.getText().length()<5)
						super.keyTyped(e);  // Optional
					else
					{   e.consume();        // Discard the event
						Toolkit tk = Toolkit.getDefaultToolkit();
						tk.beep();          // Raise the Sound
					}					
				}
				else if(rb2.isSelected())
				{
					char c = e.getKeyChar();
					if(Character.isLetter(c) || c == KeyEvent.VK_SPACE)
						super.keyTyped(e);  // Optional
					else
					{   e.consume();        // Discard the event
						Toolkit tk = Toolkit.getDefaultToolkit();
						tk.beep();          // Raise the Sound
					}					
				}
				
				
				if(e.getKeyChar() == KeyEvent.VK_ENTER)
				{
			try
                    {   lst.clear();
						
				if(rb1.isSelected())
                        rs = s.executeQuery("select * from master where bid like '%" + t8.getText() + "%'");
					
					else
                        rs = s.executeQuery("select * from master where name like '%" + t8.getText() + "%'");
						
                        while(rs.next())
						lst.addItem(rs.getString(1)+") "+rs.getString(2));
					}
                    catch(Exception ee){ee.printStackTrace();}
				}
			}
		});
			if(e.getSource()==p4_back)
					{
						cnt=0;
						p3.setVisible(true);
						p4.setVisible(false);
						sav.setEnabled(true);de.setEnabled(true);
						up.setEnabled(true);
					}
		
	}
	
	public void itemStateChanged(ItemEvent e)
	{
		 if(e.getSource()==lst)
			{
				t1.setEditable(false);//t2.setEditable(false);t3.setEditable(false);t4.setEditable(false);
				try{
					// int num;
					String arr = lst.getSelectedItem();
					StringBuilder num=new StringBuilder();
					for(int i=0;i<arr.length();i++)
					{
						char c=arr.charAt(i);
						if(Character.isDigit(c))
						{
							num.append(c);
						}
					}
					cnt++;
					s=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
					rs=s.executeQuery("select * from master where bid="+num);
					while(rs.next())
						{
							t1.setText(" "+rs.getString(1));
							t2.setText(" "+rs.getString("name"));
							t3.setText(" "+rs.getString("mob"));
							t4.setText(" "+rs.getString("email"));
							String selectedItem=rs.getString("desi");
							cmb1.setSelectedItem(rs.getString("desi"));
							cmb2.setSelectedItem(rs.getString("type"));
							
						}
				}catch(Exception ee){}
				
				p3.setVisible(true);
				p4.setVisible(false);
			}
				
	}
	public static void main(String args[])
	{
		new master();
	}
}
