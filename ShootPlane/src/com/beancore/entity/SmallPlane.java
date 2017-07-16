package com.beancore.entity;

import java.awt.Graphics;

import com.beancore.config.Config;
import com.beancore.config.EnemyPlaneType;
import com.beancore.config.ImageConstants;
import com.beancore.ui.GamePlayingPanel;
import com.beancore.util.Images;

public class SmallPlane extends EnemyPlane {

    public SmallPlane(GamePlayingPanel getPlayingPanel, EnemyPlaneType enemyType) {
	super(getPlayingPanel, enemyType);//初始化
    }

    @Override
    public void drawFighting(Graphics g) {
	new Thread(new DrawFighting(g)).start();//执行相应绘制线程
    }

    private void drawFightingRun(Graphics g) {//战斗绘制run方法
	this.setPlaneImage(Images.SMALL_PLANE_FIGHTING_IMG);//设置战斗图片
	this.setWidth(ImageConstants.SMALL_PLANE_FIGHTING_WIDTH);//大小
	this.setHeight(ImageConstants.SMALL_PLANE_FIGHTING_HEIGHT);
	super.draw(g);//绘制相应线程
	try {
	    Thread.sleep(Config.SMALL_PLANE_STATUS_CHANGE_INTERVAL);//状态变化间隔内线程睡眠
	} catch (InterruptedException e) {

	}
    }

    @Override
    public void drawKilled(Graphics g) {
	new Thread(new DrawKilled(g)).start();
    }

    //绘制死亡线程的run方法，分为两步，killed,ashed.
    private void drawKilledRun(Graphics g) {
	this.setPlaneImage(Images.SMALL_PLANE_KILLED_IMG);//第一状态
	this.setWidth(ImageConstants.SMALL_PLANE_KILLED_WIDTH);//大小
	this.setHeight(ImageConstants.SMALL_PLANE_KILLED_HEIGHT);
	super.draw(g);//绘制相应图片
	try {
	    Thread.sleep(Config.SMALL_PLANE_STATUS_CHANGE_INTERVAL);
	} catch (InterruptedException e) {

	}
	this.setPlaneImage(Images.SMALL_PLANE_ASHED_IMG);//第二状态
	this.setWidth(ImageConstants.SMALL_PLANE_ASHED_WIDTH);//大小
	this.setHeight(ImageConstants.SMALL_PLANE_ASHED_HEIGHT);
	super.draw(g);//绘制相应图片
	try {
	    Thread.sleep(Config.SMALL_PLANE_STATUS_CHANGE_INTERVAL);
	} catch (InterruptedException e) {

	}
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
	public void run() {
	    drawKilledRun(g);
	}

    }

}
