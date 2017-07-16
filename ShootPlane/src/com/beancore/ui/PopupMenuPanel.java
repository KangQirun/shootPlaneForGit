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
    //����
    private GameButton startGameButton;//��ʼ
    private GameButton exitGameButton;//�˳���Ϸ����
    private GameButton top10ScoresButton;//TOP10��������
    private GameButton helpButton;//��������

    public final static String START_GAME_BUTTON = "START_GAME_BUTTON";
    public final static String EXIT_GAME_BUTTON = "EXIT_GAME_BUTTON";
    public final static String TOP_10_SCORES_BUTTON = "TOP_10_SCORES_BUTTON";
    public final static String HELP_BUTTON = "HELP_BUTTON";

    public PopupMenuPanel(MainFrame mainFrame) {
	this.initComponents(mainFrame);
    }

    private void initComponents(MainFrame mainFrame) {//��ʼ�����
	this.logoLabel = new JLabel();
	this.logoLabel.setIcon(new ImageIcon(Images.MY_PLANE_IMG));//����logo

	this.startGameButton = new GameButton("New Game");//����Ϸ����
	this.startGameButton.addActionListener(mainFrame);
	//��һ�� ActionListener ��ӵ�startGameButton��ť��
	this.startGameButton.setActionCommand(START_GAME_BUTTON);//���ô˰�ť�Ķ�������
	this.startGameButton.setOpaque(false);

	this.top10ScoresButton = new GameButton("Top 10 Scores");//TOP10�����񰴼�
	this.top10ScoresButton.addActionListener(mainFrame);
	this.top10ScoresButton.setActionCommand(TOP_10_SCORES_BUTTON);
	this.top10ScoresButton.setOpaque(false);

	this.helpButton = new GameButton("Help");//������������
	this.helpButton.addActionListener(mainFrame);
	this.helpButton.setActionCommand(HELP_BUTTON);
	this.helpButton.setOpaque(false);

	this.exitGameButton = new GameButton("Exit Game");//�˳���Ϸ��������
	this.exitGameButton.addActionListener(mainFrame);
	this.exitGameButton.setActionCommand(EXIT_GAME_BUTTON);
	this.exitGameButton.setOpaque(false);

	JPanel logoPanel = new JPanel();
	logoPanel.setOpaque(false);
	logoPanel.add(logoLabel);

	GridLayout gridLayout = new GridLayout(4, 1, 0, 10);//���񲼾�
	JPanel buttonPanel = new JPanel();
	buttonPanel.setOpaque(false);
	buttonPanel.setLayout(gridLayout);

	buttonPanel.add(startGameButton);//�����а������밴����
	buttonPanel.add(top10ScoresButton);
	buttonPanel.add(helpButton);
	buttonPanel.add(exitGameButton);

	Dimension d = new Dimension(Config.POP_UP_MENU_PANEL_WIDTH, Config.POP_UP_MENU_PANEL_HEIGHT);
	buttonPanel.setSize(d);//���ô�С
	buttonPanel.setPreferredSize(d);

	BorderLayout mainLayout = new BorderLayout();//���ñ߽粼��
	mainLayout.setVgap(25);//�����������ֱ���Ϊ25
	JPanel mainPanel = new JPanel();
	mainPanel.setOpaque(false);
	mainPanel.setLayout(mainLayout);//�߽粼��
	mainPanel.add(logoPanel, BorderLayout.NORTH);
	mainPanel.add(buttonPanel, BorderLayout.CENTER);

	this.setOpaque(false);
	this.add(mainPanel);
    }

}
