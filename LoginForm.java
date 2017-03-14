package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JFrame{
	public static void main(String[] args) {
		LoginForm test = new LoginForm();
		JLabel lbl_login = new JLabel("Login form");
		JLabel lbl_user = new JLabel("User");
		JLabel lbl_passwd = new JLabel("Password");
		JTextField txt_userName = new JTextField(20);
		JPasswordField txt_Passwd = new JPasswordField(20);
		JButton btn_login = new JButton("Login");
		JButton btn_cancel = new JButton("Cancel");
		test.setLayout(null);
		test.setSize(400, 300);
		test.add(lbl_login).setBounds(150,20,200,20);
		test.add(lbl_user).setBounds(10,50,80,20);
		test.add(lbl_passwd).setBounds(10,100,80,20);
		test.add(txt_userName).setBounds(100,50,250,30);
		test.add(txt_Passwd).setBounds(100,100,250,30);
		test.add(btn_login).setBounds(50,180,100,20);
		test.add(btn_cancel).setBounds(200,180,100,20);
		test.setVisible(true);
		

		btn_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//					JOptionPane.showMessageDialog(null, txt_Passwd.getText());
				String url = "jdbc:mysql://localhost:3306/syslog";
				String user = txt_userName.getText();
				String passwd = txt_Passwd.getText();
				System.out.println(txt_Passwd.getText());
				String s = SQLConnect.connect(url, user, passwd);
				JOptionPane.showMessageDialog(null, s);
				try {
					ShowTable view = new ShowTable(url, user, passwd);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
			}
		});
		
	}
}
