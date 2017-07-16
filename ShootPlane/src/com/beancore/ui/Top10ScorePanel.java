package com.beancore.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.beancore.config.Config;
import com.beancore.entity.Score;

public class Top10ScorePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JLabel top10ScoreLabel;
    private GameButton okButton;
    private GameButton[] scoreButtons;
    private int SCORE_COUNT = 10;
    public static String OK_BUTTON = "TOP_SCORE_OK_BUTTON";

    public Top10ScorePanel(MainFrame mainFrame) {
	this.initComponents(mainFrame);
    }

    private void initComponents(MainFrame mainFrame) {//初始化组件
	this.top10ScoreLabel = new JLabel("<html><font size='5'>Top 10 Scores</font></html>");
	JPanel labelPanel = new JPanel();
	labelPanel.setOpaque(false);
	labelPanel.add(top10ScoreLabel);

	JPanel scorePanel = new JPanel();
	GridLayout gridLayout = new GridLayout(12, 1, 0, 5);//网格布局
	scorePanel.setLayout(gridLayout);
	scorePanel.setOpaque(false);

	scorePanel.add(labelPanel);

	this.scoreButtons = new GameButton[SCORE_COUNT];
	for (int i = 0; i < SCORE_COUNT; i++) {
	    this.scoreButtons[i] = new GameButton();
	    scorePanel.add(this.scoreButtons[i]);
	}

	this.okButton = new GameButton("OK");
	this.okButton.setActionCommand(OK_BUTTON);
	this.okButton.addActionListener(mainFrame);
	scorePanel.add(okButton);

	Dimension d = new Dimension(Config.POP_UP_SCORE_PANEL_WIDTH, Config.POP_UP_SCORE_PANEL_HEIGHT);
	scorePanel.setSize(d);
	scorePanel.setPreferredSize(d);

	this.add(scorePanel);
	this.setOpaque(false);
    }

    public void loadScore(List<Score> sortedScoreList) {//加载分数列表
	int scoreSize = sortedScoreList.size();
	for (int i = 0; i < scoreSize; i++) {//循环获取分数
	    Score score = sortedScoreList.get(i);
	    this.scoreButtons[i].setText(score.getScore() + "");//以按钮形式显示
	}
    }
}
