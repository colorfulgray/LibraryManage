package view;

import java.awt.*;

import javax.swing.*;

public class Search extends JPanel {
	private JTextField text = new JTextField(16);
	private JButton search = new JButton("搜索");
	private JButton show = new JButton("显示全部");
	private JPanel panel = new JPanel(new FlowLayout());
	private JPanel title = new JPanel(new FlowLayout());
	private JScrollPane scroll = new JScrollPane(panel);
	public Search(){
		panel.setSize(780, 1000);
		setLayout(new BoxLayout(getRootPane(), BoxLayout.Y_AXIS));
		title.add(text);
		title.add(search);
		title.add(show);
		add(title);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);;
		add(scroll);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame x = new JFrame();
		x.add(new Search());
		x.setSize(800, 600);
		x.setVisible(true);
		//x.setDefaultCloseOperation(EXIT_ON_CLOSE);
		x.setTitle("dddddd");
		x.setResizable(false);
		
	}

}
