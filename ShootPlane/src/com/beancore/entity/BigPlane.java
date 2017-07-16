package com.beancore.entity;

import java.awt.Graphics;

import com.beancore.config.Config;
import com.beancore.config.EnemyPlaneType;
import com.beancore.config.ImageConstants;
import com.beancore.ui.GamePlayingPanel;
import com.beancore.util.Images;

public class BigPlane extends EnemyPlane {//继承敌机父类

    public BigPlane(GamePlayingPanel getPlayingPanel, EnemyPlaneType enemyType) {
	super(getPlayingPanel, enemyType);
    }//初始化

    @Override
    public void drawFighting(Graphics g) {//重写父类抽象方法
	new Thread(new DrawFighting(g)).start();//战斗绘制线程开始执行；Java 虚拟机调用该线程的 run 方法。
    }

    @Override
    public void drawKilled(Graphics g) {//重写父类抽象方法
	new Thread(new DrawKilled(g)).start();//击毁绘制线程开始执行
    }

    class DrawFighting implements Runnable {
	private Graphics g;

	DrawFighting(Graphics g) {
	    this.g = g;
	}

	@Override
	public void run() {
	    drawFightingRun(g);//调用相应run方法
	}
    }

    class DrawKilled implements Runnable {
	private Graphics g;

	DrawKilled(Graphics g) {
	    this.g = g;
	}

	@Override
	public void run() {
	    drawKilledRun(g);
	}

    }

    //小飞机等其他种类飞机同，同名。设置为private以区分
    public void drawFightingRun(Graphics g) {
	this.setPlaneImage(Images.BIG_PLANE_FIGHTING_IMG);//设置大飞机战斗图片
	this.setWidth(ImageConstants.BIG_PLANE_FIGHTING_WIDTH);//大小
	this.setHeight(ImageConstants.BIG_PLANE_FIGHTING_HEIGHT);
	super.draw(g);//绘制相应图片
	try {
	    Thread.sleep(Config.BIG_PLANE_STATUS_CHANGE_INTERVAL);//在大飞机状态改变间隔内线程睡眠
	} catch (InterruptedException e) {

	}
    }

    //绘制死亡线程的run方法，分为四步，hitted、baddlywounded,killed,ashed.
    public void drawKilledRun(Graphics g) {
	this.setPlaneImage(Images.BIG_PLANE_HITTED_IMG);//设置被击中绘制图片
	this.setWidth(ImageConstants.BIG_PLANE_HITTED_WIDTH);//大小
	this.setHeight(ImageConstants.BIG_PLANE_HITTED_HEIGHT);
	super.draw(g);//绘制飞机
	try {
	    Thread.sleep(Config.BIG_PLANE_STATUS_CHANGE_INTERVAL);//在大飞机状态改变间隔内线程睡眠
	} catch (InterruptedException e) {

	}

	this.setPlaneImage(Images.BIG_PLANE_BADDLY_WOUNDED_IMG);//第二状态飞机严重损坏
	this.setWidth(ImageConstants.BIG_PLANE_BADDLY_WOUNDED_WIDTH);//设置大小
	this.setHeight(ImageConstants.BIG_PLANE_BADDLY_WOUNDED_HEIGHT);
	super.draw(g);//绘制相应图片
	try {
	    Thread.sleep(Config.BIG_PLANE_STATUS_CHANGE_INTERVAL);
	} catch (InterruptedException e) {

	}

	this.setPlaneImage(Images.BIG_PLANE_KILLED_IMG);//第三状态：飞机死亡
	this.setWidth(ImageConstants.BIG_PLANE_KILLED_WIDTH);//大小
	this.setHeight(ImageConstants.BIG_PLANE_KILLED_HEIGHT);
	super.draw(g);//绘制相应图片
	try {
	    Thread.sleep(Config.BIG_PLANE_STATUS_CHANGE_INTERVAL);//线程睡眠短暂停留效果
	} catch (InterruptedException e) {

	}

	this.setPlaneImage(Images.BIG_PLANE_ASHED_IMG);//第四状态：击毁
	this.setWidth(ImageConstants.BIG_PLANE_ASHED_WIDTH);//大小
	this.setHeight(ImageConstants.BIG_PLANE_ASHED_HEIGHT);
	super.draw(g);//绘制相应图片
	try {
	    Thread.sleep(Config.BIG_PLANE_STATUS_CHANGE_INTERVAL);
	} catch (InterruptedException e) {

	}
    }

}
