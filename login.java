import java.util.Date;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

class login extends JFrame implements ActionListener {
    JTextField t1;
    JPasswordField t2;
    JLabel l1, l2, log, l3;
    JButton b1, b2;
    Connection cn = null;
    PreparedStatement p;
    JComboBox<Integer> cmb;
    
    login() {
        super("AEMS LEAVING SOFTWARE");
        setSize(1000, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(60, 63, 65));
        add(panel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        log = new JLabel("Admin Login", SwingConstants.CENTER);
        log.setFont(new Font("Arial", Font.BOLD, 28));
        log.setForeground(new Color(173, 216, 230));
        add(log, BorderLayout.NORTH);

        l1 = new JLabel("Username:");
        l1.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(l1, gbc);

        t1 = new JTextField(15);
        gbc.gridx = 1;
        panel.add(t1, gbc);

        l2 = new JLabel("Password:");
        l2.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(l2, gbc);

        t2 = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(t2, gbc);

        l3 = new JLabel("Academic Year:");
        l3.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(l3, gbc);

        cmb = new JComboBox<>();
        gbc.gridx = 1;
        panel.add(cmb, gbc);

        Date d = new Date();
        int year = d.getYear() + 1900;
        int month = d.getMonth() + 1;
        if (month <= 3) year--;
        for (int i = 0; i < 5; i++) {
            cmb.addItem(year - i);
        }

        b1 = new JButton("Submit");
        b1.setBackground(new Color(50, 205, 50));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(b1, gbc);

        b2 = new JButton("Clear");
        b2.setBackground(new Color(220, 20, 60));
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 1;
        panel.add(b2, gbc);

        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "root");
            System.out.println("Database connected successfully.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }

        b1.addActionListener(this);
        b2.addActionListener(this);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                int newYearValue = (int) cmb.getSelectedItem();
                String sql = "UPDATE login SET year = ? WHERE user = ? AND pass = ?";
                p = cn.prepareStatement(sql);
                p.setInt(1, newYearValue);
                p.setString(2, t1.getText().toLowerCase());
                p.setString(3, new String(t2.getPassword()));
                
                int updatedRows = p.executeUpdate();
                if (updatedRows > 0) {
                    dispose();
                    new mainpage();
                } else {
                    JOptionPane.showMessageDialog(null, "Check Username and Password.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "All fields required.");
                System.out.println(ex);
            }
        } else if (e.getSource() == b2) {
            t1.setText("");
            t2.setText("");
        }
    }

    public static void main(String args[]) {
        new login();
    }
}