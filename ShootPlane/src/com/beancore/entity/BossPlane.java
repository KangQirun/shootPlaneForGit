package com.beancore.entity;

import java.awt.Graphics;

import com.beancore.config.Config;
import com.beancore.config.EnemyPlaneType;
import com.beancore.config.ImageConstants;
import com.beancore.ui.GamePlayingPanel;
import com.beancore.util.Images;

public class BossPlane extends EnemyPlane {

    public BossPlane(GamePlayingPanel getPlayingPanel, EnemyPlaneType enemyType) {
	super(getPlayingPanel, enemyType);//初始化
    }

    @Override
    public void drawFighting(Graphics g) {
	new Thread(new DrawFighting(g)).start();//执行战斗绘制线程
    }

    @Override
    public void drawKilled(Graphics g) {
	new Thread(new DrawKilled(g)).start();//执行死亡绘制线程
    }

    class DrawFighting implements Runnable {
	private Graphics g;

	DrawFighting(Graphics g) {
	    this.g = g;
	}

	@Override
	public void run() {
	    drawFightingRun(g);
	}
    }

    class DrawKilled implements Runnable {
	private Graphics g;

	DrawKilled(Graphics g) {
	    this.g = g;
	}

	@Override
	public void run() {//相应的run方法
	    drawKilledRun(g);
	}

    }

    public void drawFightingRun(Graphics g) {//战斗绘制线程的run方法
	this.setPlaneImage(Images.BOSS_PLANE_FIGHTING_IMG);//设置战斗图片
	this.setWidth(ImageConstants.BOSS_PLANE_FIGHTING_WIDTH);//大小
	this.setHeight(ImageConstants.BOSS_PLANE_FIGHTING_HEIGHT);
	super.draw(g);//绘制相应图片
	try {
	    Thread.sleep(Config.BOSS_PLANE_STATUS_CHANGE_INTERVAL);//在状态改变间隔内线程睡眠
	} catch (InterruptedException e) {

	}
    }

    //绘制死亡线程的run方法，分为四步，hitted、baddlywounded,killed,ashed.
    public void drawKilledRun(Graphics g) {//死亡绘制线程的run方法
	this.setPlaneImage(Images.BOSS_PLANE_HITTED_IMG);//第一状态：被击中
	this.setWidth(ImageConstants.BOSS_PLANE_HITTED_WIDTH);//大小
	this.setHeight(ImageConstants.BOSS_PLANE_HITTED_HEIGHT);
	super.draw(g);//绘制相应图片
	try {
	    Thread.sleep(Config.BOSS_PLANE_STATUS_CHANGE_INTERVAL);
	} catch (InterruptedException e) {

	}

	this.setPlaneImage(Images.BOSS_PLANE_BADDLY_WOUNDED_IMG);//第二状态:严重损坏
	this.setWidth(ImageConstants.BOSS_PLANE_BADDLY_WOUNDED_WIDTH);//大小
	this.setHeight(ImageConstants.BOSS_PLANE_BADDLY_WOUNDED_HEIGHT);
	super.draw(g);//绘制相应图片
	try {
	    Thread.sleep(Config.BOSS_PLANE_STATUS_CHANGE_INTERVAL);
	} catch (InterruptedException e) {

	}

	this.setPlaneImage(Images.BOSS_PLANE_KILLED_IMG);//第三状态：死亡
	this.setWidth(ImageConstants.BOSS_PLANE_KILLED_WIDTH);//大小
	this.setHeight(ImageConstants.BOSS_PLANE_KILLED_HEIGHT);
	super.draw(g);//绘制相应图片
	try {
	    Thread.sleep(Config.BOSS_PLANE_STATUS_CHANGE_INTERVAL);
	} catch (InterruptedException e) {

	}

	this.setPlaneImage(Images.BOSS_PLANE_ASHED_IMG);//第四状态：击毁
	this.setWidth(ImageConstants.BOSS_PLANE_ASHED_WIDTH);//大小
	this.setHeight(ImageConstants.BOSS_PLANE_ASHED_HEIGHT);
	super.draw(g);//绘制相应图片
	try {
	    Thread.sleep(Config.BOSS_PLANE_STATUS_CHANGE_INTERVAL);
	} catch (InterruptedException e) {

	}
    }
}
