package com.beancore.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;

import com.beancore.config.BulletType;
import com.beancore.config.Config;
import com.beancore.config.ImageConstants;
import com.beancore.listener.BulletListener;
import com.beancore.ui.GamePlayingPanel;
import com.beancore.util.Images;

public class Bullet {
    private int posX;//������
    private int posY;//������
    private int width;//���
    private int height;//�߶�
    private int speed;//�ٶ�
    private BulletType bulletType;//�ӵ�����

    private GamePlayingPanel gamePlayingPanel;
    private BulletListener listener;
    private Image bulletImage;

    public Bullet(GamePlayingPanel gamePlayingPanel, BulletType bulletType) {
	this.gamePlayingPanel = gamePlayingPanel;
	this.bulletType = bulletType;
	switch (this.bulletType) {
	case YELLOW_BULLET://���ӵ�Ϊ��ɫ�ӵ�
	    bulletImage = Images.YELLOW_BULLET_IMG;//��ʾ��ɫ�ӵ�
	    width = ImageConstants.YELLOW_BULLET_WIDTH;//��С
	    height = ImageConstants.YELLOW_BULLET_HEIGHT;
	    speed = Config.YELLOW_BULLET_MOVE_SPEED;//��ɫ�ӵ��ٶ�
	    break;
	case BLUE_BULLET://���ӵ�Ϊ��ɫ�ӵ�
	    bulletImage = Images.BLUE_BULLET_IMG;
	    width = ImageConstants.YELLOW_BULLET_WIDTH;
	    height = ImageConstants.YELLOW_BULLET_HEIGHT;
	    speed = Config.BLUE_BULLET_MOVE_SPEED;
	    break;
	}
    }

    public Rectangle getRectangle() {//��ȡ��������
	return new Rectangle(posX, posY, width, height);
    }

    public void draw(Graphics g) {//�����ӵ�
	Graphics2D g2d = (Graphics2D) g;
	g2d.drawImage(bulletImage, posX, posY, width, height, gamePlayingPanel);

    }

    public EnemyPlane hitEnemyPlanes() {
	List<EnemyPlane> enmeyPlanes = this.gamePlayingPanel.getEnemyPlanes();//��ȡ�л��б�
	for (int i = 0; i < enmeyPlanes.size(); i++) {
	    EnemyPlane enemyPlane = enmeyPlanes.get(i);//�����ȡ�л�
	    if (this.getRectangle().intersects(enemyPlane.getRectangle())) {
		enemyPlane.addHittedCount();//���д���+1
		return enemyPlane;
	    }
	}
	return null;
    }

    public void addBulletListener(GamePlayingPanel gamePlayingPanel) {
	this.gamePlayingPanel = gamePlayingPanel;
    }

    public int getPosX() {//��ȡ������
	return posX;
    }

    public void setPosX(int posX) {//���ú�����
	this.posX = posX;
    }

    public int getPosY() {//��ȡ������
	return posY;
    }

    public void setPosY(int posY) {//����������
	this.posY = posY;
    }

    public int getWidth() {//���ÿ��
	return width;
    }

    public void setWidth(int width) {//���ÿ��
	this.width = width;
    }

    public int getHeight() {//��ȡ�߶�
	return height;
    }

    public void setHeight(int height) {//���ø߶�
	this.height = height;
    }

    public BulletType getBulletType() {//��ȡ�ӵ�����
	return bulletType;
    }

    public void setBulletType(BulletType bulletType) {//�����ӵ�����
	this.bulletType = bulletType;
    }

    public GamePlayingPanel getGamePlayingPanel() {
	return gamePlayingPanel;
    }

    public void setGamePlayingPanel(GamePlayingPanel gamePlayingPanel) {
	this.gamePlayingPanel = gamePlayingPanel;
    }

    public BulletListener getListener() {
	return listener;
    }

    public void setListener(BulletListener listener) {
	this.listener = listener;
    }

    public Image getBulletImage() {
	return bulletImage;
    }

    public void setBulletImage(Image bulletImage) {
	this.bulletImage = bulletImage;
    }

    public int getSpeed() {//��ȡ�ٶ�
	return speed;
    }

    public void setSpeed(int speed) {//�����ٶ�
	this.speed = speed;
    }

}
