import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
class mainpage extends JFrame implements ActionListener
{
	ImageIcon i;
    JLabel l1;
	JMenuBar mbr;
	JMenu m[];
	JPanel p;
	JMenuItem mi[];
    mainpage()
	{
		super("AEMS,BARAMATI");
		setSize(1500,1500);
		setLayout(null);
		// memory
		mbr=new JMenuBar();
		m=new JMenu[8];mi=new JMenuItem[12];
		p=new JPanel();
		p.setLayout(null);
		m[1]=new JMenu("Master Entry");m[2]=new JMenu("Leave Entry");m[3]=new JMenu("Reports");m[4]=new JMenu("Exit");
		
		mi[0]=new JMenuItem("Master Form");
		mi[1]=new JMenuItem("DateWise");mi[2]=new JMenuItem("Indivisual");
		mi[3]=new JMenuItem("All Empolyee");mi[4]=new JMenuItem("Leave Typewise");
		mi[5]=new JMenuItem("Exit");mi[6]=new JMenuItem("Leave form");
		
		//add
		add(p);
		for(int k=1;k<=4;k++)
		{
			mbr.add(m[k]);
			m[k].setForeground(Color.WHITE);
			m[k].addActionListener(this);
		}
		mbr.add(m[4]);
		mi[5].setForeground(Color.RED);
		
		for(int k=0;k<=6;k++)
		{
			mi[k].setForeground(Color.BLUE);
			mi[k].addActionListener(this);
		}
		m[1].add(mi[0]);
		m[2].add(mi[6]);
		
		m[3].add(mi[1]);m[3].add(mi[2]);
		m[3].add(mi[3]);m[3].add(mi[4]);
		
		m[4].add(mi[5]);
		setJMenuBar(mbr);
		mbr.setBackground(Color.BLACK);
		//bounds
		p.setBounds(0,15,1500,1500);
		p.setBackground(Color.PINK); 
		
		
		
		ImageIcon imageIcon = new ImageIcon("tc.jpg");
		p.setLayout(null);

        JLabel label = new JLabel(imageIcon);
		label.setBounds(0,0,1500,1000);
        p.add(label);
		
		
		// setResizable(false);
		mi[5].addActionListener(this);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==mi[5])
		{
			int choice = JOptionPane.showOptionDialog
			(
				this, // Use 'this' as the parent component
				"Do you really want to close the application?",
				"Confirmation",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				new Object[]{"Yes", "No"},
				"Yes"
			);

				if (choice == JOptionPane.YES_OPTION)
				{
				// Close the application only if the user chooses "Yes"
				System.exit(0);
				}
			
		}
		if(e.getSource()==mi[0])

			 new master();
		if(e.getSource()==mi[6])
	     new leave1("AEMS LEAVING ENTRY");
		if(e.getSource()==mi[4])
	      new lev_type("LEAVE TYPEWISE");
		if(e.getSource()==mi[1])
	      new lev_date("LEAVE DATEWISE");
		if(e.getSource()==mi[3])
	       new lev_all_employee("All Empolyee");
		if(e.getSource()==mi[2])
	    	new indivisual("Indivisual");

	}
	
	public static void main(String args[])
	{
		new mainpage();
	}
}