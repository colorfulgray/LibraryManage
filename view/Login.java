package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import db.Verification;
import model.User;

import javax.swing.*;

public class Login extends JFrame {
	
	private JLabel title = new JLabel("图书借阅系统",JLabel.CENTER);
	private JLabel l1 = new JLabel("学号:");
	private JLabel l2 = new JLabel("密码:");
	private JButton b = new JButton("登录");
	private JLabel r = new JLabel("");
	private JTextField id = new JTextField(16);
	private JPasswordField password = new JPasswordField(16);
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	
	public class login implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			User user = new User();
			user.setStudentId(id.getText());
			user.setPassWord(password.getText());
			try {
				if(Verification.verification(user)) {
					new Main(user);
					dispose();
				} else {
					r.setText("账号或密码错误!");
					r.setBackground(Color.red);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
	}
	
	public Login() {
		this.setSize(400, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("图书借阅系统");
		this.setResizable(false);
		//setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setLayout(new GridLayout(6,1));
		title.setFont(new java.awt.Font("楷体", 1, 20));
		b.addActionListener(new login());
		
		p1.add(l1);
		p1.add(id);
		p2.add(l2);
		p2.add(password);
		p3.add(b);
		p3.add(r);

		add(title);
		add(p1);
		add(p2);
		add(p3);
	}
	public static void main(String[] args) {
		new Login();
	}

}
