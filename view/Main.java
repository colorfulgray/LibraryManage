package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import model.*;

public class Main extends JFrame {
	// main组件
	private JTabbedPane tab = new JTabbedPane();
	private JLabel title = new JLabel("图书借阅系统");
	private JLabel name = new JLabel();
	private User user;

	// Search面板组件
	private JPanel Search = new JPanel();
	private JTextField text = new JTextField(16);
	private JButton search = new JButton("搜索");
	private JButton showS = new JButton("显示全部");
	private JPanel panelS = new JPanel(new FlowLayout());// 滚动面板
	private JPanel titleS = new JPanel(new FlowLayout());
	private JScrollPane scrollS = new JScrollPane(panelS);

	// Return面板组件
	private JPanel Return = new JPanel();
	private JLabel returnTitle = new JLabel("已借阅图书：");
	private JPanel panelR = new JPanel(new FlowLayout(5));
	private JPanel titleR = new JPanel(new BorderLayout());
	private JScrollPane scrollR = new JScrollPane(panelR);
	private JButton refreshR = new JButton("刷新");
	
	//History面板组件
	private JPanel History = new JPanel();
	private JLabel historyTitle = new JLabel("历史借阅记录：");
	private JPanel panelH = new JPanel(new FlowLayout(5));
	private JPanel titleH = new JPanel(new BorderLayout());
	private JScrollPane scrollH = new JScrollPane(panelH);
	private JButton refreshH = new JButton("刷新");
	
	// 构造函数
	public Main(User user) throws SQLException {
		this.user = user;
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setTitle("图书借阅系统");
		this.setResizable(false);// 是否可自由拉伸
		name.setText(user.getName() + " ");// 用户姓名
		name.setForeground(Color.red);// 姓名颜色
		tab.addTab("Search", Search);// 三个分页面
		tab.addTab("Return", Return);
		tab.addTab("History", History);
		setLayout(new BorderLayout(5, 5));
		Box hbox = new Box(BoxLayout.X_AXIS);// 主面板标题栏
		add("North", hbox);
		add("Center", tab);
		title.setFont(new java.awt.Font("楷体", 1, 30));// 标题字体
		hbox.add(title);
		hbox.add(Box.createHorizontalGlue());
		hbox.add(new JLabel("欢迎 "));
		hbox.add(name);

		// Search面板
		Search.setLayout(new BorderLayout(5, 5));
		panelS.setPreferredSize(new Dimension(770, 1500));
		search.addActionListener(new Select());
		showS.addActionListener(new Select());
		titleS.add(text);
		titleS.add(search);
		titleS.add(showS);
		scrollS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Search.add("North", titleS);
		Search.add("Center", scrollS);

		// Return面板
		// returnTitle.setFont(new java.awt.Font("黑体", 1, 15));
		Return.setLayout(new BorderLayout(5, 5));
		panelR.setPreferredSize(new Dimension(770, 1500));
		titleR.add("West", returnTitle);
		refreshR.setName("R");
		refreshR.addActionListener(new actRefresh());
		titleR.add("East", refreshR);
		scrollR.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Return.add("North", titleR);
		Return.add("Center", scrollR);
		showDataR();
		
		//History面板
		History.setLayout(new BorderLayout(5, 5));
		panelH.setPreferredSize(new Dimension(770, 1500));
		titleH.add("West", historyTitle);
		refreshH.setName("H");
		refreshH.addActionListener(new actRefresh());
		titleH.add("East", refreshH);
		scrollH.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		History.add("North", titleH);
		History.add("Center", scrollH);
		showDataH();
	}

	// Search面板事件监听
	public class Select implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			FindBook fb = new FindBook();
			ResultSet result;
			panelS.removeAll();
			try {
				if (arg0.getActionCommand().equals("搜索")) {
					result = fb.FindOneBookByInput(text.getText());
					showDataS(result);

				} else {

					result = fb.FindAllBook();
					showDataS(result);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			panelS.revalidate();// 刷新界面
		}
	}

	// 借阅按钮的事件监听
	public class actBorrow implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String bookId = arg0.toString().split(" ")[2];
			doBorrow(bookId);
		}

	}

	// 归还按钮的事件监听
	public class actReturn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String bookId = arg0.toString().split(" ")[2];
			doReturn(bookId);
			JButton a = (JButton) arg0.getSource();
			a.setEnabled(false);
		}

	}

	// 刷新按钮事件监听
	public class actRefresh implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String which = arg0.toString().split(" ")[2];
			
			try {
				if (which.equals("R")) {
					panelR.removeAll();
					showDataR();
					panelR.revalidate();
				} else {
					panelH.removeAll();
					showDataH();
					panelH.revalidate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

	// 显示一行图书信息记录
	private void addColumnS(ResultSet result) throws SQLException {
		JPanel oneColumn = new JPanel(new GridLayout(1, 7, 8, 8));
		JButton oneButton = new JButton("借阅");
		oneButton.addActionListener(new actBorrow());
		oneColumn.setPreferredSize(new Dimension(750, 20));// 固定大小
		oneButton.setName(result.getString("图书号"));
		oneColumn.add(new JLabel(result.getString("图书名")));
		oneColumn.add(new JLabel(result.getString("图书号")));
		oneColumn.add(new JLabel(result.getString("库存量")));
		oneColumn.add(new JLabel(result.getString("出版社")));
		oneColumn.add(new JLabel(result.getString("出版日期").split(" ")[0]));
		oneColumn.add(new JLabel(result.getString("作者")));
		oneColumn.add(oneButton);
		panelS.add(oneColumn);
	}
	// 显示查询表头
	public void showSearchTitle() {
		JPanel oneColumn = new JPanel(new GridLayout(1, 7, 8, 8));
		JButton oneButton = new JButton();
		oneButton.setVisible(false);
		oneColumn.setPreferredSize(new Dimension(750, 20));
		oneColumn.add(new JLabel("图书名"));
		oneColumn.add(new JLabel("图书编号"));
		oneColumn.add(new JLabel("库存量"));
		oneColumn.add(new JLabel("出版社"));
		oneColumn.add(new JLabel("出版日期"));
		oneColumn.add(new JLabel("作者"));
		oneColumn.add(oneButton);
		panelS.add(oneColumn);
	}
	// 显示查询到的图书记录
	public void showDataS(ResultSet result) throws SQLException {
		showSearchTitle();
		while (result.next())
			addColumnS(result);
	}

	// 显示一行未还记录
	public void addColumnR(ResultSet result) throws SQLException {
		JPanel oneColumn = new JPanel(new GridLayout(1, 4, 8, 8));
		JButton oneButton = new JButton("归还图书");
		oneButton.addActionListener(new actReturn());
		oneColumn.setPreferredSize(new Dimension(750, 20));// 固定大小
		oneButton.setName(result.getString("图书号"));
		oneColumn.add(new JLabel(result.getString("图书名")));
		oneColumn.add(new JLabel(result.getString("图书号")));
		oneColumn.add(new JLabel(result.getString("借阅日期")));
		oneColumn.add(oneButton);
		panelR.add(oneColumn);
	}
	// 显示归还图书表头
	public void showReturnTitle() {
		JPanel oneColumn = new JPanel(new GridLayout(1, 4, 8, 8));
		JButton oneButton = new JButton();
		oneButton.setVisible(false);
		oneColumn.setPreferredSize(new Dimension(750, 20));
		oneColumn.add(new JLabel("图书名"));
		oneColumn.add(new JLabel("图书编号"));
		oneColumn.add(new JLabel("借阅时间"));
		oneColumn.add(oneButton);
		panelR.add(oneColumn);
	}
	// 显示未还图书列表
	private void showDataR() throws SQLException {
		showReturnTitle();
		ReturnBook rb = new ReturnBook();
		ResultSet result = rb.FindAllBookForRet(user);
		while (result.next())
			addColumnR(result);
		panelR.revalidate();
	}
	
	//显示一行历史记录
	public void addColumnH(ResultSet result) throws SQLException {
		JPanel oneColumn = new JPanel(new GridLayout(1, 4, 8, 8));
		oneColumn.setPreferredSize(new Dimension(750, 20));// 固定大小
		oneColumn.add(new JLabel(result.getString("图书名")));
		oneColumn.add(new JLabel(result.getString("图书号")));
		oneColumn.add(new JLabel(result.getString("借阅日期")));
		oneColumn.add(new JLabel(result.getString("归还日期")));
		panelH.add(oneColumn);
	}
	//显示历史记录表头
	public void showHistoryTitle() {
		JPanel oneColumn = new JPanel(new GridLayout(1, 4, 8, 8));
		oneColumn.setPreferredSize(new Dimension(750, 20));
		oneColumn.add(new JLabel("图书名"));
		oneColumn.add(new JLabel("图书编号"));
		oneColumn.add(new JLabel("借阅时间"));
		oneColumn.add(new JLabel("归还时间"));
		panelH.add(oneColumn);
	}
	//显示历史记录列表
	private void showDataH() throws SQLException{
		showHistoryTitle();
		HistoryBook rb = new HistoryBook();
		ResultSet result = rb.FindAllBookForHis(user);
		while (result.next())
			addColumnH(result);
		panelH.revalidate();
	}
	
	// 弹出借阅确定框 并执行借书
	private void doBorrow(String bookId) {
		if (JOptionPane.showConfirmDialog(null, "是否借阅 ", "确认借阅",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
			BorrowBook bb = new BorrowBook();
			try {
				if (bb.Borrow(bookId, user)) {
					JOptionPane.showMessageDialog(null, "借阅成功！");
				} else {
					JOptionPane.showMessageDialog(null, "借阅失败！", "消息", JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 弹出确认框 并执行还书
	private void doReturn(String bookId) {
		if (JOptionPane.showConfirmDialog(null, "是否归还 ", "确认归还",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
			ReturnBook rb = new ReturnBook();
			try {
				if (rb.Return(bookId, user)) {
					JOptionPane.showMessageDialog(null, "归还成功！");
				} else {
					JOptionPane.showMessageDialog(null, "归还失败！", "消息", JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws SQLException {
		User a = new User();
		a.setName("周二");
		a.setStudentId("08160122");
		a.setPassWord("22222222");
		new Main(a);
	}

}
