package view;

import java.awt.*;

import javax.swing.*;

public class Main extends JFrame {
	
	private JTabbedPane tab = new JTabbedPane();

	public Main() {
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setTitle("图书借阅系统");
		

		tab.addTab("fucker", new JPanel());
		tab.addTab("lover", new JPanel());
		tab.addTab("shitt", new JPanel());
		
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		add(new Title());
		add(tab);
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new Main();
	}

}
