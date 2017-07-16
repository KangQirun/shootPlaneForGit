package com.beancore.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import com.beancore.config.CatchableWeaponType;
import com.beancore.ui.GamePlayingPanel;

public abstract class CatchableWeapon {
	//抽象类作为父类，由BOMB和DoubleLaser两个子类继承
    private int posX;//位置
    private int posY;

    private int width;//大小
    private int height;
    private Image weaponImage;//相应图片

    private GamePlayingPanel gamePlayingPanel;
    private CatchableWeaponType weaponType;
    private boolean useAnimation;
    private boolean useAnimationDone;
    private int speed;
    private boolean weaponDisappear;

    //传递参数信息进行初始化
    public CatchableWeapon(GamePlayingPanel gamePlayingPanel, CatchableWeaponType weaponType) {
	this.gamePlayingPanel = gamePlayingPanel;
	this.weaponType = weaponType;
	this.useAnimation = false;
	this.useAnimationDone = false;
	this.weaponDisappear = false;
    }

    public Rectangle getRectangle() {//获取矩形区域，判断是否接收到武器用
	return new Rectangle(posX, posY, width, height);
    }

    public void draw(Graphics g) {//绘制相应图片
	Graphics2D g2d = (Graphics2D) g;
	g2d.drawImage(weaponImage, posX, posY, width, height, gamePlayingPanel);
    }

    //以下是一些set和get方法
    public int getPosX() {
	return posX;
    }

    public void setPosX(int posX) {//设置横纵坐标
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

    public Image getWeaponImage() {//获取武器图片
	return weaponImage;
    }

    public void setWeaponImage(Image weaponImage) {//设置武器图片
	this.weaponImage = weaponImage;
    }

    public GamePlayingPanel getGamePlayingPanel() {
	return gamePlayingPanel;
    }

    public void setGamePlayingPanel(GamePlayingPanel gamePlayingPanel) {
	this.gamePlayingPanel = gamePlayingPanel;
    }

    public CatchableWeaponType getWeaponType() {//获取武器类型
	return weaponType;
    }

    public void setWeaponType(CatchableWeaponType weaponType) {//设置武器类型
	this.weaponType = weaponType;
    }

    public int getSpeed() {//获取速度
	return speed;
    }

    public void setSpeed(int speed) {//设置速度
	this.speed = speed;
    }

    public boolean isUseAnimation() {
	return useAnimation;//获取使用动画
    }

    public void setUseAnimation(boolean useAnimation) {
	this.useAnimation = useAnimation;//设置使用动画
    }

    public boolean isUseAnimationDone() {
	return useAnimationDone;
    }

    public void setUseAnimationDone(boolean useAnimationDone) {
	this.useAnimationDone = useAnimationDone;
    }

    public boolean isWeaponDisappear() {//设置武器是否消失状态
	return weaponDisappear;
    }

    public void setWeaponDisappear(boolean weaponDisappear) {//设置武器是否消失状态
	this.weaponDisappear = weaponDisappear;
    }

}
