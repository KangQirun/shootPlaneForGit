package com.beancore.ui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.beancore.config.Config;
import com.beancore.util.Images;

public class GameLoadingPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Image gameLoadingTextImg;
    private JLabel gameLoadingPlaneLabel;
    private JLabel gameLoadingTextLabel;
    private ImageIcon[] gameLoadingPlaneImgList;//游戏载入图片列表

    public GameLoadingPanel() {
	this.createLoadingPanel();
    }

    private void createLoadingPanel() {
	this.gameLoadingPlaneImgList = new ImageIcon[3];//分为三部分
	this.gameLoadingPlaneImgList[0] = new ImageIcon(Images.GAME_LOADING_IMG1);
	this.gameLoadingPlaneImgList[1] = new ImageIcon(Images.GAME_LOADING_IMG2);
	this.gameLoadingPlaneImgList[2] = new ImageIcon(Images.GAME_LOADING_IMG3);
	this.gameLoadingTextImg = Images.GAME_LOADING_TEXT_IMG;

	gameLoadingPlaneLabel = new JLabel();
	gameLoadingPlaneLabel.setOpaque(false);
	gameLoadingTextLabel = new JLabel(new ImageIcon(this.gameLoadingTextImg));//作为Jlabel组件显示
	gameLoadingTextLabel.setOpaque(false);//设置为默认的不透明，不允许下面的像素透视上来
	GridLayout gridLayout = new GridLayout(2, 1);//网格布局

	FlowLayout flowLayout1 = new FlowLayout(FlowLayout.CENTER);//中心流布局
	JPanel panel1 = new JPanel();
	panel1.setLayout(flowLayout1);//设置控制板的布局为中心流布局
	panel1.add(gameLoadingPlaneLabel);
	panel1.setOpaque(false);

	FlowLayout flowLayout2 = new FlowLayout(FlowLayout.CENTER);//中心流布局
	JPanel panel2 = new JPanel();
	panel2.setLayout(flowLayout2);
	panel2.add(gameLoadingTextLabel);
	panel2.setOpaque(false);

	this.setLayout(gridLayout);
	this.setOpaque(false);
	this.add(panel1);//将panel1和panel2添加进GameLoadingPanel
	this.add(panel2);
    }

    public void loadingGame() {
	int times = 3;
	for (int i = 0; i < times; i++) {//依次显示游戏加载图片
	    this.gameLoadingPlaneLabel.setIcon(this.gameLoadingPlaneImgList[i]);
	    try {
		Thread.sleep(Config.GAME_LOADING_INTERVAL);//在加载间隔使线程睡眠
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}
    }

}
