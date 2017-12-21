package view;

import java.awt.*;

import javax.swing.*;
import model.User;

public class Main extends JFrame {
	
	private JTabbedPane tab = new JTabbedPane();
	private JLabel title = new JLabel("图书借阅系统");
	private JLabel name = new JLabel();

	public Main(User user) {
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setTitle("图书借阅系统");
		this.setResizable(false);
		
		name.setText(user.getName());

		tab.addTab("fucker", new Search());
		tab.addTab("lover", new JPanel());
		tab.addTab("shitt", new JPanel());
		
		Box hbox = new Box(BoxLayout.X_AXIS);
		title.setFont(new java.awt.Font("楷体", 1, 30));
		hbox.add(title);
		hbox.add(Box.createHorizontalGlue());
		hbox.add(name);
		
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		add(hbox);
		add(tab);
	}
	public static void main(String[] args) {
		new Main(new User());
	}

}
