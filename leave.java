import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import java.text.*;
class leave extends JFrame implements ActionListener ,ItemListener
{
	JLabel lbd,lnam,ldes,lstf,ltyp,lnum,lhed,lapd,ldf,lr,lltyp,lldat,lnod,lrem,llevdate,to,name_srch;
	JTextField tbd,tnam,tdes,tstf,temail,tnum,tr,tnod,name_scr;
	JPanel p1,p2,p3,p4,p5;
	JCheckBox ch1_scr,ch2_hdl;
	JComboBox lltyp_cmb,rem_cmb;
	JButton b_clr,b_sav,b_ser,b_upd,b_del,b_print,b_back,b_ext,b_bck;
	List lst;
	SimpleDateFormat sdf;
	Date date1,date2;
	
		Connection cn,cn2;
		PreparedStatement p;
		String sql;
		Statement s,s2;
		ResultSet rs,rs2;
	
	
	
	 DateButton calb_app,calb_date,calb_form,calb_end;
	
    leave(String t)
	{
		super(t); 
		setSize(800,600);
		setLocation(300,100);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lhed=new JLabel("Leave Entry Form");
		add(lhed);
		lhed.setBounds(250,30,200,30);
		lhed.setFont(new Font("Arial",Font.BOLD,20));
		
		p1=new JPanel();
		add(p1);
		p1.setBounds(0,0,1500,1500);
		p1.setBackground(Color.PINK);
		p1.setLayout(null);

		p2 = new JPanel();
		p2.setBorder(BorderFactory.createTitledBorder("Employee Info"));
		p2.setLayout(null);
		p1.add(p2);
		p2.setBounds(50,80,500,180);
		p2.setBackground(Color.WHITE);
		lbd=new JLabel("Bid Code:");
		p2.add(lbd);
		lbd.setBounds(10,30,70,30);
		tbd=new JTextField();
		p2.add(tbd);
		tbd.setBounds(90,30,80,30);
		
		lnam=new JLabel("Name:");
		p2.add(lnam);
		lnam.setBounds(10,70,70,30);
		tnam=new JTextField();
		p2.add(tnam);
		tnam.setBounds(90,70,150,30);
		
		ldes=new JLabel("Designation:");
		p2.add(ldes);
		ldes.setBounds(10,110,100,30);
		tdes=new JTextField();
		p2.add(tdes);
		tdes.setBounds(90,110,150,30);
		
		ch1_scr=new JCheckBox("Search by name");
		p2.add(ch1_scr);
		ch1_scr.setBounds(180,30,130,30);
		
		lstf=new JLabel("Staff Type:");
		p2.add(lstf);
		lstf.setBounds(320,30,70,30);
		tstf=new JTextField();
		p2.add(tstf);
		tstf.setBounds(390,30,100,30);
		
		ltyp=new JLabel("Email:");
		p2.add(ltyp);
		ltyp.setBounds(320,70,70,30);
		temail=new JTextField();
		p2.add(temail);
		temail.setBounds(390,70,100,30);
		
		lnum=new JLabel("Number:");
		p2.add(lnum);
		lnum.setBounds(320,110,70,30);
		tnum=new JTextField();
		p2.add(tnum);
		tnum.setBounds(390,110,100,30);
		
		p3 = new JPanel();
		p3.setBorder(BorderFactory.createTitledBorder("Leave Entry"));
		p3.setLayout(null);
		p1.add(p3);
		p3.setBounds(50,290,500,190);
		p3.setBackground(Color.WHITE);
	
		lapd=new JLabel("Application Date:");
		p3.add(lapd);
		lapd.setBounds(10,30,100,30);
		
		 calb_app = new DateButton();
		p3.add(calb_app);
		calb_app.setBounds(120,30,120,30);
		
		
		
		//tapd=new JTextField();
		//p3.add(tapd);
		//.setBounds(140,30,120,30);
		
		/*llevdate=new JLabel("Leave Date:");
		p3.add(llevdate);
		llevdate.setBounds(10,70,100,30);
		
		calb_date = new DateButton();
		p3.add(calb_date);
		calb_date.setBounds(120,70,120,30);
		
		*/
		
		lnod=new JLabel("No Of Days:");
		p3.add(lnod);
		lnod.setBounds(10,110,100,30);
		tnod=new JTextField();
		p3.add(tnod);
		tnod.setBounds(120,110,120,30);
			
		ch2_hdl=new JCheckBox("Half Day Leave");
		p3.add(ch2_hdl);
		ch2_hdl.setBounds(250,110,130,30);
		
		
		ldf=new JLabel("Date Form:");
		p3.add(ldf);
		ldf.setBounds(10,70,120,30);
		
		
		
		calb_form = new DateButton();
		p3.add(calb_form);
		calb_form.setBounds(120,70,100,30);
		 
		 JLabel to=new JLabel("TO");
		p3.add(to);
		to.setBounds(230,70,30,30);
		
		calb_end = new DateButton();
		p3.add(calb_end);
		calb_end.setBounds(270,70,100,30);
		
		
		lr=new JLabel("Reason:");
		p3.add(lr);
		lr.setBounds(10,150,50,30);
		tr=new JTextField();
		p3.add(tr);
		tr.setBounds(70,150,200,30);
		
		lltyp=new JLabel("Leaving Type:");
		p3.add(lltyp);
		lltyp.setBounds(300,30,80,30);
		lltyp_cmb=new JComboBox();
		lltyp_cmb= new JComboBox();
		lltyp_cmb.addItem("CL");
		lltyp_cmb.addItem("DL");
		lltyp_cmb.addItem("SP");
		lltyp_cmb.addItem("SSP");
		lltyp_cmb.addItem("ML");
		lltyp_cmb.addItem("LWP");
		p3.add(lltyp_cmb);
		lltyp_cmb.setBounds(390,30,100,30);
		
		
		
		lrem=new JLabel("Remark");
		p3.add(lrem);
		lrem.setBounds(300,140,70,30);
		rem_cmb=new JComboBox();
		p3.add(rem_cmb);
		rem_cmb.addItem("Sanctioned");
		rem_cmb.addItem("Non-Sanctioned");
		rem_cmb.addItem("Cancel");
		rem_cmb.setBounds(380,140,115,30);
		
		
		p4=new JPanel();
		p1.add(p4);
		p4.setBounds(570,80,200,390);
		p4.setBackground(Color.WHITE);
		p4.setBorder(BorderFactory.createTitledBorder("OPERATIONS"));
		p4.setLayout(null);
		
		b_clr=new JButton("CLEAR");
		p4.add(b_clr);
		b_clr.setBounds(50,30,90,30);
		
		b_sav=new JButton("SAVE");
		p4.add(b_sav);
		b_sav.setBounds(50,70,90,30);
		
		b_ser=new JButton("SEARCH");
		p4.add(b_ser);
		b_ser.setBounds(50,110,90,30);
		
		b_upd=new JButton("UPDATE");
		p4.add(b_upd);
		b_upd.setBounds(50,150,90,30);
		
		b_del=new JButton("DELETE");
		p4.add(b_del);
		b_del.setBounds(50,190,90,30);
		
		b_print=new JButton("PRINT");
		p4.add(b_print);
		b_print.setBounds(50,230,90,30);
		
		b_back=new JButton("BACK");
		p4.add(b_back);
		b_back.setBounds(50,270,90,30);
		
		b_ext=new JButton("EXIT");
		p4.add(b_ext);
		b_ext.setBounds(50,310,90,30);
		
		
		
		
		
		p5= new JPanel();
			p5.setBorder(BorderFactory.createTitledBorder("SEARCH"));
			p5.setLayout(null);
			p1.add(p5);
			p5.setBounds(50,290,500,190);
			p5.setBackground(Color.YELLOW);
			
			name_srch=new JLabel("ENTER NAME TO SEARCH:");
			p5.add(name_srch);
			name_srch.setBounds(30,20,160,30);
			
			name_scr=new JTextField();
			p5.add(name_scr);
			name_scr.setBounds(200,20,180,30);
			
			b_bck=new JButton("BACK");
			p5.add(b_bck);b_bck.setBounds(390,20,100,30);
			
			
			 lst=new List();
			 p5.add(lst);
			lst.setBounds(30,60,460,120);
			p5.setVisible(false);
		
		name_scr.addKeyListener(new KeyAdapter()
        {   
            public void keyTyped(KeyEvent e)
            {
					char c = e.getKeyChar();
					if(Character.isLetter(c) || c == KeyEvent.VK_SPACE)
						super.keyTyped(e);  // Optional
					else
					{   e.consume();        // Discard the event
						Toolkit tk = Toolkit.getDefaultToolkit();
						tk.beep();          // Raise the Sound
					}					
				
				if(e.getKeyChar() == KeyEvent.VK_ENTER)
				{
			try
                    { 
					
					lst.clear();
					   cn=DriverManager.getConnection("jdbc:mysql:///login","root","root");
						s=cn.createStatement();
                        rs = s.executeQuery("select * from master where name like '%" + name_scr.getText() + "%'");
                        while(rs.next())
						lst.addItem(rs.getString(1)+") "+rs.getString(2));
					}
                    catch(Exception ee){ee.printStackTrace();}
				}
			}
		});
	
		
			try{
				tbd.addKeyListener(new KeyAdapter()
		 {   
            public void keyTyped(KeyEvent e)
            {
					char c = e.getKeyChar();
					if(Character.isDigit(c) && tbd.getText().length()<5)
						super.keyTyped(e);  // Optional
					else
					{   e.consume();        // Discard the event
						Toolkit tk = Toolkit.getDefaultToolkit();
						tk.beep();          // Raise the Sound
					}					
				if(e.getKeyChar() == KeyEvent.VK_ENTER)
				{
				try
                    {   
						cn=DriverManager.getConnection("jdbc:mysql:///login","root","root");
						s=cn.createStatement();
						rs=s.executeQuery("select * from master where bid="+tbd.getText());
							if(rs.next())
							{
							tnam.setText(rs.getString("name"));
							tdes.setText(rs.getString("desi"));
							tstf.setText(rs.getString("type"));
							temail.setText(rs.getString("email"));
							tnum.setText(rs.getString("mob"));
							}
							else
							{
								JOptionPane.showMessageDialog(null,"Such type of Entry not found");
								tbd.setText("");tnam.setText("");tdes.setText("");tstf.setText("");
								temail.setText("");tnum.setText("");
							}
							tnam.setEditable(false);tdes.setEditable(false);tstf.setEditable(false);temail.setEditable(false);tnum.setEditable(false);
					}
                    catch(Exception ee){ee.printStackTrace();}
				}
			}
		});		
		
	tnod.addKeyListener(new KeyAdapter()
		{   
            public void keyTyped(KeyEvent e)
            {					
				if(e.getKeyChar() == KeyEvent.VK_ENTER)
				{
				try
                    {  
					  sdf=new SimpleDateFormat("yyyy-MM-dd");
					date1=sdf.parse(""+calb_end.getText());
					date2=sdf.parse(""+calb_form.getText());
					
					long diffmill=date1.getTime()-date2.getTime();
					long differdays=(diffmill/(24*60*60*1000))+1;
					
					tnod.setText(Long.toString(differdays));
					//JOptionPane .showMessageDialog(null,differdays);
					}
                    catch(Exception ee){ee.printStackTrace();}
				}
			}
		});	


					
			}catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,e);
			}

		
		
		
		
		b_bck.addActionListener(this);
		b_clr.addActionListener(this);
		b_back.addActionListener(this);
		b_ext.addActionListener(this);
		b_sav.addActionListener(this);
		lst.addItemListener(this);
		ch1_scr.addActionListener(this);
		ch2_hdl.addActionListener(this);
		
	
		
		setVisible(true);
	}
	
	
	
	
	// adding listener method
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b_clr)
		{
			tbd.setEditable(true);
			tbd.setText("");tnam.setText("");tdes.setText("");tstf.setText("");
			temail.setText("");tnum.setText("");tr.setText("");tnod.setText("");
			ch1_scr.setSelected(false);ch2_hdl.setSelected(false);
		}
		if(e.getSource()==b_back)
		{
			new mainpage();
		}
		if(e.getSource()==b_bck)
		{
			p5.setVisible(false);
			p3.setVisible(true);
			ch1_scr.setSelected(false);
		}
		if(e.getSource()==b_ext)
		{
			System.exit(0);
		}
		if(e.getSource()==ch1_scr)
		{
			p3.setVisible(false);
			p5.setVisible(true);
			lst.clear();name_scr.setText("");
		}
		if(e.getSource()==ch2_hdl)
		{
			try{
			double no=Integer.parseInt(tnod.getText()); double half=no/2;
			tnod.setText(""+half);
			}catch(Exception eee)
			{JOptionPane.showMessageDialog(null,"Please press enter in front textbox");}
		}
		if(e.getSource()==b_sav)
		{
			try
			{
				if(tbd.getText().length()==0 || tnod.getText().length()==0|| tr.getText().length()==0  )
					{
						JOptionPane.showMessageDialog(null,"All Fields Required");
					}
				else{
						cn=DriverManager.getConnection("jdbc:mysql:///login","root","root");
						s=cn.createStatement();
						String s3=""+lltyp_cmb.getSelectedItem();
						String s4=""+rem_cmb.getSelectedItem();
	
						sql="insert into leavemaster (lev_app_date,lev_start_date,lev_end_date,lev_type,lev_days,lev_reason,lev_remark,bid) values('"+calb_app.getText()+"','"+calb_end.getText()+"','"+calb_form.getText()+"','"+s3+"',"+tnod.getText()+",'"+tr.getText()+"','"+s4+"',"+tbd.getText()+")";
						p=cn.prepareStatement(sql);
						p.execute();
						p.close();
						JOptionPane.showMessageDialog(null,"Data Inserted Successfully!!!!!");
						tbd.setText("");tnam.setText("");tdes.setText("");tstf.setText("");
						temail.setText("");tnum.setText("");tr.setText("");tnod.setText("");
						ch1_scr.setSelected(false);ch2_hdl.setSelected(false);
					}		
			}catch(Exception ee)
			{
				JOptionPane.showMessageDialog(null,ee);
			}
		}
	}
	
	public void itemStateChanged(ItemEvent e)
	{
		 if(e.getSource()==lst)
			{
				tbd.setEditable(false);tnam.setEditable(false);tdes.setEditable(false);tstf.setEditable(false);temail.setEditable(false);tnum.setEditable(false);
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
					//cnt++;
					s=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
					rs=s.executeQuery("select * from master where bid="+num);
					while(rs.next())
						{
							tbd.setText(rs.getString(1));
							tnam.setText(rs.getString("name"));
							tdes.setText(rs.getString("desi"));
							tstf.setText(rs.getString("type"));
							temail.setText(rs.getString("email"));
							tnum.setText(rs.getString("mob"));	
						}
				}catch(Exception ee){}
				p5.setVisible(false);
				p3.setVisible(true);
				ch1_scr.setSelected(false);
			}
	}
	public static void main(String args[])
	{
		new leave("JCalendar");
	}
}