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
    private int posX;//横坐标
    private int posY;//纵坐标
    private int width;//宽度
    private int height;//高度
    private int speed;//速度
    private BulletType bulletType;//子弹类型

    private GamePlayingPanel gamePlayingPanel;
    private BulletListener listener;
    private Image bulletImage;

    public Bullet(GamePlayingPanel gamePlayingPanel, BulletType bulletType) {
	this.gamePlayingPanel = gamePlayingPanel;
	this.bulletType = bulletType;
	switch (this.bulletType) {
	case YELLOW_BULLET://若子弹为黄色子弹
	    bulletImage = Images.YELLOW_BULLET_IMG;//显示黄色子弹
	    width = ImageConstants.YELLOW_BULLET_WIDTH;//大小
	    height = ImageConstants.YELLOW_BULLET_HEIGHT;
	    speed = Config.YELLOW_BULLET_MOVE_SPEED;//黄色子弹速度
	    break;
	case BLUE_BULLET://若子弹为蓝色子弹
	    bulletImage = Images.BLUE_BULLET_IMG;
	    width = ImageConstants.YELLOW_BULLET_WIDTH;
	    height = ImageConstants.YELLOW_BULLET_HEIGHT;
	    speed = Config.BLUE_BULLET_MOVE_SPEED;
	    break;
	}
    }

    public Rectangle getRectangle() {//获取矩形区域
	return new Rectangle(posX, posY, width, height);
    }

    public void draw(Graphics g) {//绘制子弹
	Graphics2D g2d = (Graphics2D) g;
	g2d.drawImage(bulletImage, posX, posY, width, height, gamePlayingPanel);

    }

    public EnemyPlane hitEnemyPlanes() {
	List<EnemyPlane> enmeyPlanes = this.gamePlayingPanel.getEnemyPlanes();//获取敌机列表
	for (int i = 0; i < enmeyPlanes.size(); i++) {
	    EnemyPlane enemyPlane = enmeyPlanes.get(i);//逐个获取敌机
	    if (this.getRectangle().intersects(enemyPlane.getRectangle())) {
		enemyPlane.addHittedCount();//打中次数+1
		return enemyPlane;
	    }
	}
	return null;
    }

    public void addBulletListener(GamePlayingPanel gamePlayingPanel) {
	this.gamePlayingPanel = gamePlayingPanel;
    }

    public int getPosX() {//获取横坐标
	return posX;
    }

    public void setPosX(int posX) {//设置横坐标
	this.posX = posX;
    }

    public int getPosY() {//获取纵坐标
	return posY;
    }

    public void setPosY(int posY) {//设置纵坐标
	this.posY = posY;
    }

    public int getWidth() {//设置宽度
	return width;
    }

    public void setWidth(int width) {//设置宽度
	this.width = width;
    }

    public int getHeight() {//获取高度
	return height;
    }

    public void setHeight(int height) {//设置高度
	this.height = height;
    }

    public BulletType getBulletType() {//获取子弹类型
	return bulletType;
    }

    public void setBulletType(BulletType bulletType) {//设置子弹类型
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

    public int getSpeed() {//获取速度
	return speed;
    }

    public void setSpeed(int speed) {//设置速度
	this.speed = speed;
    }

}
