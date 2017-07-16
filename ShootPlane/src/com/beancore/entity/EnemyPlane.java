package com.beancore.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import com.beancore.config.EnemyPlaneType;
import com.beancore.listener.EnemyPlaneListener;
import com.beancore.ui.GamePlayingPanel;

public abstract class EnemyPlane {
	//敌机抽象类，作为父类被smallplane、bigplane、BOSSplane三个子类继承
    private int posX;//位置
    private int posY;
    private int width;//大小
    private int height;
    private int speed;//速度
    private int hittedCount;//打中统计
    private int killedCount;//杀死统计
    private int killedScore;//杀死得分
    private Image planeImage;
    private EnemyPlaneListener listener;
    private EnemyPlaneType enemyType;//敌机类型
    private GamePlayingPanel gamePlayingPanel;

    //以下为敌机的构造方法，当使用工厂类来生成一个具体的敌机时，需要提供敌机绘制的面板和敌机的类型
    public EnemyPlane(GamePlayingPanel getPlayingPanel, EnemyPlaneType enemyType) {
	this.gamePlayingPanel = getPlayingPanel;
	this.enemyType = enemyType;
	this.hittedCount = 0;//初始被击中次数
    }

    public Rectangle getRectangle() {//获取矩形区域，检测碰撞用
	return new Rectangle(posX, posY, width, height);
    }

    public void draw(Graphics g) {//用于在面板上绘制飞机
	Graphics2D g2d = (Graphics2D) g;
	g2d.drawImage(planeImage, posX, posY, width, height, gamePlayingPanel);
    }

    public void addEnemyPlaneListener(EnemyPlaneListener listener) {
    //用于添加敌机位置移动监听器
	this.listener = listener;
    }

    public void addHittedCount() {//打中统计叠加
	this.hittedCount++;
    }

    public boolean isKilled() {//判断是否被击毁
	return this.hittedCount >= this.killedCount;//当被击中数》=死亡限定
    }

    public abstract void drawFighting(Graphics g);//抽象方法，由子类实现，显示敌机收到攻击时图片

    public abstract void drawKilled(Graphics g);//抽象方法，由子类实现，显示敌机被击毁时图片

    
    //以下为一些Getter和Setter方法
    public EnemyPlaneType getEnemyType() {//获取敌机类型
	return enemyType;
    }

    public void setEnemyType(EnemyPlaneType enemyType) {//设置敌机类型
	this.enemyType = enemyType;
    }

    public GamePlayingPanel getGamePlayingPanel() {
	return gamePlayingPanel;
    }

    public void setGamePlayingPanel(GamePlayingPanel gamePlayingPanel) {
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

    public int getWidth() {//获取宽度
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

    public int getSpeed() {//获取速度
	return speed;
    }

    public void setSpeed(int speed) {//设置速度
	this.speed = speed;
    }

    public EnemyPlaneListener getListener() {
	return listener;
    }

    public void setListener(EnemyPlaneListener listener) {
	this.listener = listener;
    }

    public Image getPlaneImage() {
	return planeImage;
    }

    public void setPlaneImage(Image planeImage) {
	this.planeImage = planeImage;
    }

    public int getHittedCount() {//获取打中次数
	return hittedCount;
    }

    public void setHittedCount(int hittedCount) {//设置打中次数
	this.hittedCount = hittedCount;
    }

    public int getKilledCount() {//获取杀死统计
	return killedCount;
    }

    public void setKilledCount(int killedCount) {//设置杀死统计
	this.killedCount = killedCount;
    }

    public int getKilledScore() {
	return killedScore;
    }

    public void setKilledScore(int killedScore) {
	this.killedScore = killedScore;
    }

}
