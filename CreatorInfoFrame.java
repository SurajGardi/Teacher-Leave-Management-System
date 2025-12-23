import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatorInfoFrame extends JFrame {
    public CreatorInfoFrame() {
        setTitle("Designed By");
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	
		JLabel creatorLabel = new JLabel("<html><style>body{font-family:'Arial',sans-serif;background-color: #87CEFA;margin:0;padding:0;text-align:center;}h1{color:#007bff;margin-bottom:20px;}h3{color:#6c757d;margin-bottom:30px;}b{font-weight:bold;color:#495057;}</style><h1>AEMS SCHOOL</h1><h3>&copy;All Right Reserved @Anakant Education Society</h3><h3>Designed And Developed By :<b><u>Mr.Gardi Suraj</u></b></h3></html>");
        creatorLabel.setHorizontalAlignment(JLabel.CENTER);
		//creatorLabel.setFont(customFont);
        add(creatorLabel);

        setLocationRelativeTo(null); // Center the frame on the screen

        // Schedule a task to close the frame after 3 seconds
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the frame after the delay
                dispose();
            }
        });
        timer.setRepeats(false); // Make the timer execute only once
        timer.start();

        setVisible(true);
    }

    public static void main(String[] args) {
        new CreatorInfoFrame();
    }
}
