package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import model.*;

public class Main extends JFrame {
	//main组件
	private JTabbedPane tab = new JTabbedPane();
	private JLabel title = new JLabel("图书借阅系统");
	private JLabel name = new JLabel();
	
	//Search面板组件
	private JPanel Search = new JPanel();
	private JTextField text = new JTextField(16);
	private JButton search = new JButton("搜索");
	private JButton showS = new JButton("显示全部");
	private JPanel panelS = new JPanel(new FlowLayout());//5为左对齐
	private JPanel titleS = new JPanel(new FlowLayout());
	private JScrollPane scrollS = new JScrollPane(panelS);
	private User user;
	//构造函数
	public Main(User user) {
		this.user = user;
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setTitle("图书借阅系统");
		this.setResizable(false);//是否可自由拉伸		
		name.setText(user.getName()+" ");//用户姓名
		name.setForeground(Color.red);//姓名颜色		
		tab.addTab("Search", Search);//三个分页面
		tab.addTab("lover", new JPanel());
		tab.addTab("fucker", new JPanel());
		setLayout(new BorderLayout(5,5));
		Box hbox = new Box(BoxLayout.X_AXIS);//主面板标题栏
		add("North",hbox);
		add("Center",tab);
		title.setFont(new java.awt.Font("楷体", 1, 30));//标题字体
		hbox.add(title);
		hbox.add(Box.createHorizontalGlue());
		hbox.add(new JLabel("欢迎 "));
		hbox.add(name);
		
		
		//Search面板
		Search.setLayout(new BorderLayout(5,5));
		//panel.setSize(780, 1000);
		panelS.setPreferredSize(new Dimension(770,1500));
		search.addActionListener(new Select());
		showS.addActionListener(new Select());
		titleS.add(text);
		titleS.add(search);
		titleS.add(showS);		
		scrollS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Search.add("North",titleS);
		Search.add("Center",scrollS);
		//Search Title
		JPanel oneColumn = new JPanel(new GridLayout(1,7,8,8));
		JButton oneButton = new JButton();
		oneButton.setVisible(false);
		oneColumn.setPreferredSize(new Dimension(750,20));
		oneColumn.add(new JLabel("图书名"));
		oneColumn.add(new JLabel("图书编号"));
		oneColumn.add(new JLabel("库存量"));
		oneColumn.add(new JLabel("图书出版社"));
		oneColumn.add(new JLabel("图书价格"));
		oneColumn.add(new JLabel("出版日期"));
		oneColumn.add(new JLabel("作者"));
		oneColumn.add(oneButton);
		panelS.add(oneColumn);
	
	}
	
	//Search面板事件监听
	public class Select implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			FindBook fb = new FindBook();
			if (arg0.getActionCommand().equals("搜索")) {
				text.setText("FFFFFF");
				panelS.revalidate();// 刷新界面
			} else {
				try {
					panelS.removeAll();
					ResultSet result = fb.FindAllBook();
					showData(result);
					panelS.revalidate();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
	}
	
	//借阅按钮的事件监听
	public class doBorrow implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String bookId = arg0.toString().split(" ")[2];
			doBorrow(bookId);
		}
		
	}
	
	//显示一行图书信息记录
	private void addColumn(ResultSet result) throws SQLException {
		JPanel oneColumn = new JPanel(new GridLayout(1,7,8,8));
		JButton oneButton = new JButton("借阅");
		oneColumn.setPreferredSize(new Dimension(750,20));//固定大小
		oneButton.setName(result.getString(1));
		oneColumn.add(new JLabel(result.getString(1)));
		oneColumn.add(new JLabel(result.getString(2)));
		oneColumn.add(new JLabel(result.getString(3)));
		oneColumn.add(new JLabel(result.getString(4)));
		oneColumn.add(new JLabel(result.getString(5)));
		oneColumn.add(new JLabel(result.getString(6)));
		oneColumn.add(new JLabel(result.getString(7)));
		oneColumn.add(oneButton);
		panelS.add(oneColumn);
	}
	
	//显示查询到的图书记录
	public void showData(ResultSet result) throws SQLException {
		while(result.next())
			addColumn(result);
	}
	
	//弹出借阅确定框  并执行借书
	private void doBorrow(String bookId) {
		if (JOptionPane.showConfirmDialog(null, "是否借阅 ", "确认借阅",JOptionPane.YES_NO_OPTION)
				== JOptionPane.YES_NO_OPTION) {
			BorrowBook bb = new BorrowBook();
			try {
				if (bb.Borrow(bookId, user)) {
					JOptionPane.showMessageDialog(null, "借阅成功！");
				} else {
					JOptionPane.showMessageDialog(null, "借阅失败！", "消息", JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "借阅失败！", "消息", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		User a = new User();
		a.setName("asas");
		new Main(a);
	}

}
