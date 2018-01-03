package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;

import model.AddUser;
import model.AddBook;
import model.User;

public class Admin extends JFrame {
	// admin组件
	private JTabbedPane tab = new JTabbedPane();
	private JLabel title = new JLabel("图书借阅系统");
	private JLabel name = new JLabel();
	private User user;
	
	//signIn组件
	private JPanel signIn = new JPanel();
	private JPanel s1 = new JPanel();
	private JPanel s2 = new JPanel();
	private JPanel s3 = new JPanel();
	private JPanel s4 = new JPanel();
	private JPanel s5 = new JPanel();
	private JPanel s6 = new JPanel();
	private JLabel idL = new JLabel("输入账号：");
	private JTextField idT = new JTextField(16);
	private JLabel nameL = new JLabel("输入姓名：");
	private JTextField nameT = new JTextField(16);
	private JLabel passwordL = new JLabel("输入密码：");
	private JPasswordField passwordT= new JPasswordField(16);
	private JLabel rPasswordL = new JLabel("重复密码：");
	private JPasswordField rPasswordT = new JPasswordField(16);
	private JLabel numberL = new JLabel("借书权限：");
	private JComboBox numberC = new JComboBox();
	private JButton doSignIn = new JButton("添加");
	
	//add book 组件
	private JPanel addBook = new JPanel();
	private JPanel b1 = new JPanel();
	private JPanel b2 = new JPanel();
	private JPanel b3 = new JPanel();
	private JPanel b4 = new JPanel();
	private JPanel b5 = new JPanel();
	private JPanel b6 = new JPanel();
	private JPanel b7 = new JPanel();
	private JLabel bookIdL = new JLabel("图书号码：");
	private JTextField bookIdT = new JTextField(16);
	private JLabel bookNameL = new JLabel("图书名称：");
	private JTextField bookNameT = new JTextField(16);
	private JLabel publisherL = new JLabel("出版单位：");
	private JTextField publisherT = new JTextField(16);
	private JLabel pubTimeL = new JLabel("出版时间：");
	private JTextField pubTimeT = new JTextField(16);
	private JLabel autherL = new JLabel("图书作者：");
	private JTextField autherT = new JTextField(16);
	private JLabel numL = new JLabel("图书数量：");
	private JTextField numT = new JTextField(3);
	private Integer number = 1;
	private JButton sub = new JButton("-");
	private JButton add = new JButton("+");
	private JButton add2 = new JButton("++");
	private JButton doAddBook = new JButton("添加图书");
	
	public Admin(User user) throws SQLException {
		this.user = user;
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setTitle("图书借阅系统");
		this.setResizable(false);// 是否可自由拉伸
		name.setText(user.getName() + " ");// 用户姓名
		name.setForeground(Color.red);// 姓名颜色
		tab.addTab("添加用户", signIn);// 三个分页面
		tab.addTab("添加图书", addBook);
		//tab.addTab("历史记录", History);
		setLayout(new BorderLayout(5, 5));
		Box hbox = new Box(BoxLayout.X_AXIS);// 主面板标题栏
		add("North", hbox);
		add("Center", tab);
		title.setFont(new java.awt.Font("楷体", 1, 30));// 标题字体
		hbox.add(title);
		hbox.add(new JLabel("（管理员）"));
		hbox.add(Box.createHorizontalGlue());
		hbox.add(new JLabel("管理员： "));
		hbox.add(name);
		
		//sign in 面板
		signIn.setLayout(new GridLayout(10,1));
		signIn.add(s1);
		signIn.add(s2);
		signIn.add(s3);
		signIn.add(s4);
		//signIn.add(s5);
		signIn.add(s6);
		s1.add(idL);
		s1.add(idT);
		s2.add(nameL);
		s2.add(nameT);
		s3.add(passwordL);
		s3.add(passwordT);
		s4.add(rPasswordL);
		s4.add(rPasswordT);
		s5.add(numberL);
		s5.add(numberC);
		s6.add(doSignIn);
		doSignIn.addActionListener(new actSignIn());
		numberC.addItem(5);
		numberC.addItem(7);
		numberC.addItem(10);
		
		//add book 页面
		addBook.setLayout(new GridLayout(10,1));
		addBook.add(b1);
		addBook.add(b2);
		addBook.add(b3);
		addBook.add(b4);
		addBook.add(b5);
		addBook.add(b6);
		addBook.add(b7);
		b1.add(bookIdL);
		b1.add(bookIdT);
		b2.add(bookNameL);
		b2.add(bookNameT);
		b3.add(publisherL);
		b3.add(publisherT);
		b4.add(pubTimeL);
		b4.add(pubTimeT);
		b5.add(autherL);
		b5.add(autherT);
		b6.add(numL);
		b6.add(sub);
		b6.add(numT);
		b6.add(add);
		b6.add(add2);
		b7.add(doAddBook);
		sub.addActionListener(new numA());
		add.addActionListener(new numA());
		add2.addActionListener(new numA());
		doAddBook.addActionListener(new actAddBook());
		numT.setText(number.toString());
		numT.setEditable(false);
		
	}
	// 注册面板事件监听
	public class actSignIn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(idT.getText().equals("")||nameT.getText().equals("")||passwordT.getText().equals("")||rPasswordT.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请全部填写注册信息！", "消息", JOptionPane.ERROR_MESSAGE);
			}else {
				if(!passwordT.getText().equals(rPasswordT.getText()))
					JOptionPane.showMessageDialog(null, "两次输入的密码不一致！", "消息", JOptionPane.ERROR_MESSAGE);
				else {
					AddUser add = new AddUser();
					try {
						add.AddBook(idT.getText(), nameT.getText(), passwordT.getText());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "注册成功！");
					idT.setText("");
					nameT.setText("");
					passwordT.setText("");
					rPasswordT.setText("");
				}
			}
			
		}
		
	}
	//加减数量事件监听
	public class numA implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getActionCommand().equals("-")) {
				number--;
				if(number == 0)
					sub.setEnabled(false);
			}else if(arg0.getActionCommand().equals("+")){
				number++;
				sub.setEnabled(true);
			}else {
				number = number+5;
				sub.setEnabled(true);
			}
			numT.setText(number.toString());
		}
		
	}
	//添加图书时间监听
	public class actAddBook implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(bookIdT.getText().equals("")||bookNameT.getText().equals("")||publisherT.getText().equals("")||pubTimeT.getText().equals("")||autherT.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请全部填写注册信息！", "消息", JOptionPane.ERROR_MESSAGE);
			}else {
				AddBook ab = new AddBook();
				try {
					ab.AddBook(bookNameT.getText(), bookIdT.getText(), number, publisherT.getText(), pubTimeT.getText(), autherT.getText());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "添加成功！");
				bookIdT.setText("");
				bookNameT.setText("");
				publisherT.setText("");
				pubTimeT.setText("");
				autherT.setText("");
				number = 0;
				numT.setText(number.toString());
			}
		}
		
	}
	
	
	public static void main(String[] args) throws SQLException {
		// TODO 自动生成的方法存根
		User a = new User();
		a.setName("周二");
		a.setStudentId("08160122");
		a.setPassWord("22222222");
		new Admin(a);
	}

}
