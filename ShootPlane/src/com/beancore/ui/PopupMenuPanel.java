package com.beancore.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.beancore.config.Config;
import com.beancore.util.Images;

public class PopupMenuPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JLabel logoLabel;
    //按键
    private GameButton startGameButton;//开始
    private GameButton exitGameButton;//退出游戏按键
    private GameButton top10ScoresButton;//TOP10分数按键
    private GameButton helpButton;//帮助按键

    public final static String START_GAME_BUTTON = "START_GAME_BUTTON";
    public final static String EXIT_GAME_BUTTON = "EXIT_GAME_BUTTON";
    public final static String TOP_10_SCORES_BUTTON = "TOP_10_SCORES_BUTTON";
    public final static String HELP_BUTTON = "HELP_BUTTON";

    public PopupMenuPanel(MainFrame mainFrame) {
	this.initComponents(mainFrame);
    }

    private void initComponents(MainFrame mainFrame) {//初始化组件
	this.logoLabel = new JLabel();
	this.logoLabel.setIcon(new ImageIcon(Images.MY_PLANE_IMG));//窗口logo

	this.startGameButton = new GameButton("New Game");//新游戏按键
	this.startGameButton.addActionListener(mainFrame);
	//将一个 ActionListener 添加到startGameButton按钮中
	this.startGameButton.setActionCommand(START_GAME_BUTTON);//设置此按钮的动作命令
	this.startGameButton.setOpaque(false);

	this.top10ScoresButton = new GameButton("Top 10 Scores");//TOP10分数榜按键
	this.top10ScoresButton.addActionListener(mainFrame);
	this.top10ScoresButton.setActionCommand(TOP_10_SCORES_BUTTON);
	this.top10ScoresButton.setOpaque(false);

	this.helpButton = new GameButton("Help");//帮助按键对象
	this.helpButton.addActionListener(mainFrame);
	this.helpButton.setActionCommand(HELP_BUTTON);
	this.helpButton.setOpaque(false);

	this.exitGameButton = new GameButton("Exit Game");//退出游戏按键对象
	this.exitGameButton.addActionListener(mainFrame);
	this.exitGameButton.setActionCommand(EXIT_GAME_BUTTON);
	this.exitGameButton.setOpaque(false);

	JPanel logoPanel = new JPanel();
	logoPanel.setOpaque(false);
	logoPanel.add(logoLabel);

	GridLayout gridLayout = new GridLayout(4, 1, 0, 10);//网格布局
	JPanel buttonPanel = new JPanel();
	buttonPanel.setOpaque(false);
	buttonPanel.setLayout(gridLayout);

	buttonPanel.add(startGameButton);//将所有按键加入按键组
	buttonPanel.add(top10ScoresButton);
	buttonPanel.add(helpButton);
	buttonPanel.add(exitGameButton);

	Dimension d = new Dimension(Config.POP_UP_MENU_PANEL_WIDTH, Config.POP_UP_MENU_PANEL_HEIGHT);
	buttonPanel.setSize(d);//设置大小
	buttonPanel.setPreferredSize(d);

	BorderLayout mainLayout = new BorderLayout();//采用边界布局
	mainLayout.setVgap(25);//设置组件件垂直间距为25
	JPanel mainPanel = new JPanel();
	mainPanel.setOpaque(false);
	mainPanel.setLayout(mainLayout);//边界布局
	mainPanel.add(logoPanel, BorderLayout.NORTH);
	mainPanel.add(buttonPanel, BorderLayout.CENTER);

	this.setOpaque(false);
	this.add(mainPanel);
    }

}
