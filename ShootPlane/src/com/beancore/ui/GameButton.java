package com.beancore.ui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.beancore.config.Config;
import com.beancore.util.SoundPlayer;

public class GameButton extends JButton implements ImageObserver, MouseListener {

    private static final long serialVersionUID = 1L;
    private String text;
    private final String BUTTON_HOVER = "BUTTON_HOVER";
    private final String BUTTON_NORMAL = "BUTTON_NORMAL";
    private String buttonStatus;
    private SoundPlayer buttonSoundPlayer;

    public GameButton() {//构造函数1
	super();
	this.text = "";
	initButton();//初始化按键
    }

    public GameButton(String text) {//构造函数2
	super();
	this.text = text;//设置按键内容
	initButton();
    }

    @Override
    public void setText(String text) {//设置内容
	this.text = text;
    }

    private void initButton() {//初始化按键
	this.buttonStatus = BUTTON_NORMAL;
	this.setBorderPainted(false);
	this.setFocusPainted(false);
	this.setContentAreaFilled(false);
	this.addMouseListener(this);
	this.setCursor(new Cursor(Cursor.HAND_CURSOR));//设置光标为手状光标
	try {
	    this.buttonSoundPlayer = new SoundPlayer(Config.BUTTON_ACTION_AUDIO);//播放按钮操作音乐
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Override
    protected void paintComponent(Graphics g) {//绘制组件
	super.paintComponent(g);
	Image buttonBgImg = null;
	if (this.buttonStatus.equals(BUTTON_NORMAL)) {//若按键状态为普通
	    buttonBgImg = new ImageIcon(Config.BUTTON_BG_IMG).getImage();//获取相应图片
	} else if (this.buttonStatus.equals(BUTTON_HOVER)) {//若按键状态为悬浮
	    buttonBgImg = new ImageIcon(Config.BUTTON_HOVER_BG_IMG).getImage();//获取相应图片
	}
	int buttonWidth = buttonBgImg.getWidth(this);//按键大小
	int buttonHeight = buttonBgImg.getHeight(this);

	this.setSize(buttonWidth, buttonHeight);//设置按键大小
	this.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

	Graphics2D g2d = (Graphics2D) g;
	g2d.drawImage(buttonBgImg, 0, 0, buttonWidth, buttonHeight, this);
	FontMetrics metric = g.getFontMetrics();
	Rectangle2D rect = metric.getStringBounds(text, g);
	g2d.drawString(text, (float) (buttonWidth / 2 - rect.getWidth() / 2),
		(float) ((buttonHeight / 2) + ((metric.getAscent() + metric.getDescent()) / 2 - metric.getDescent())));
    }

    private void buttonHover() {//设置按键状态为悬浮
	this.buttonStatus = BUTTON_HOVER;
    }

    private void buttonNormal() {//设置按键状态为普通
	this.buttonStatus = BUTTON_NORMAL;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	buttonHover();//点击按键，状态设为悬浮
    }

    @Override
    public void mousePressed(MouseEvent e) {
	buttonHover();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	buttonNormal();//按键松开，状态设为普通
    }

    @Override
    public void mouseEntered(MouseEvent e) {
	buttonHover();
	this.buttonSoundPlayer.play();
    }

    @Override
    public void mouseExited(MouseEvent e) {
	buttonNormal();
    }

}
