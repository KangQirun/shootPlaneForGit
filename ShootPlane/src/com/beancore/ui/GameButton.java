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

    public GameButton() {//���캯��1
	super();
	this.text = "";
	initButton();//��ʼ������
    }

    public GameButton(String text) {//���캯��2
	super();
	this.text = text;//���ð�������
	initButton();
    }

    @Override
    public void setText(String text) {//��������
	this.text = text;
    }

    private void initButton() {//��ʼ������
	this.buttonStatus = BUTTON_NORMAL;
	this.setBorderPainted(false);
	this.setFocusPainted(false);
	this.setContentAreaFilled(false);
	this.addMouseListener(this);
	this.setCursor(new Cursor(Cursor.HAND_CURSOR));//���ù��Ϊ��״���
	try {
	    this.buttonSoundPlayer = new SoundPlayer(Config.BUTTON_ACTION_AUDIO);//���Ű�ť��������
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Override
    protected void paintComponent(Graphics g) {//�������
	super.paintComponent(g);
	Image buttonBgImg = null;
	if (this.buttonStatus.equals(BUTTON_NORMAL)) {//������״̬Ϊ��ͨ
	    buttonBgImg = new ImageIcon(Config.BUTTON_BG_IMG).getImage();//��ȡ��ӦͼƬ
	} else if (this.buttonStatus.equals(BUTTON_HOVER)) {//������״̬Ϊ����
	    buttonBgImg = new ImageIcon(Config.BUTTON_HOVER_BG_IMG).getImage();//��ȡ��ӦͼƬ
	}
	int buttonWidth = buttonBgImg.getWidth(this);//������С
	int buttonHeight = buttonBgImg.getHeight(this);

	this.setSize(buttonWidth, buttonHeight);//���ð�����С
	this.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

	Graphics2D g2d = (Graphics2D) g;
	g2d.drawImage(buttonBgImg, 0, 0, buttonWidth, buttonHeight, this);
	FontMetrics metric = g.getFontMetrics();
	Rectangle2D rect = metric.getStringBounds(text, g);
	g2d.drawString(text, (float) (buttonWidth / 2 - rect.getWidth() / 2),
		(float) ((buttonHeight / 2) + ((metric.getAscent() + metric.getDescent()) / 2 - metric.getDescent())));
    }

    private void buttonHover() {//���ð���״̬Ϊ����
	this.buttonStatus = BUTTON_HOVER;
    }

    private void buttonNormal() {//���ð���״̬Ϊ��ͨ
	this.buttonStatus = BUTTON_NORMAL;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	buttonHover();//���������״̬��Ϊ����
    }

    @Override
    public void mousePressed(MouseEvent e) {
	buttonHover();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	buttonNormal();//�����ɿ���״̬��Ϊ��ͨ
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
