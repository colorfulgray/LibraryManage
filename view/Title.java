package view;

import java.awt.*;

import javax.swing.*;

public class Title extends JPanel {
	private JLabel title = new JLabel("图书借阅系统",JLabel.CENTER);
	private JLabel name = new JLabel("shiitt");
	public Title() {
		title.setFont(new Font("楷体", 1, 25));
		this.setLayout(new GridLayout(1,2));
		
		add(title);
		add(name);
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
