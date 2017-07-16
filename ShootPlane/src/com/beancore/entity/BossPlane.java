package com.beancore.entity;

import java.awt.Graphics;

import com.beancore.config.Config;
import com.beancore.config.EnemyPlaneType;
import com.beancore.config.ImageConstants;
import com.beancore.ui.GamePlayingPanel;
import com.beancore.util.Images;

public class BossPlane extends EnemyPlane {

    public BossPlane(GamePlayingPanel getPlayingPanel, EnemyPlaneType enemyType) {
	super(getPlayingPanel, enemyType);//��ʼ��
    }

    @Override
    public void drawFighting(Graphics g) {
	new Thread(new DrawFighting(g)).start();//ִ��ս�������߳�
    }

    @Override
    public void drawKilled(Graphics g) {
	new Thread(new DrawKilled(g)).start();//ִ�����������߳�
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
	public void run() {//��Ӧ��run����
	    drawKilledRun(g);
	}

    }

    public void drawFightingRun(Graphics g) {//ս�������̵߳�run����
	this.setPlaneImage(Images.BOSS_PLANE_FIGHTING_IMG);//����ս��ͼƬ
	this.setWidth(ImageConstants.BOSS_PLANE_FIGHTING_WIDTH);//��С
	this.setHeight(ImageConstants.BOSS_PLANE_FIGHTING_HEIGHT);
	super.draw(g);//������ӦͼƬ
	try {
	    Thread.sleep(Config.BOSS_PLANE_STATUS_CHANGE_INTERVAL);//��״̬�ı������߳�˯��
	} catch (InterruptedException e) {

	}
    }

    //���������̵߳�run��������Ϊ�Ĳ���hitted��baddlywounded,killed,ashed.
    public void drawKilledRun(Graphics g) {//���������̵߳�run����
	this.setPlaneImage(Images.BOSS_PLANE_HITTED_IMG);//��һ״̬��������
	this.setWidth(ImageConstants.BOSS_PLANE_HITTED_WIDTH);//��С
	this.setHeight(ImageConstants.BOSS_PLANE_HITTED_HEIGHT);
	super.draw(g);//������ӦͼƬ
	try {
	    Thread.sleep(Config.BOSS_PLANE_STATUS_CHANGE_INTERVAL);
	} catch (InterruptedException e) {

	}

	this.setPlaneImage(Images.BOSS_PLANE_BADDLY_WOUNDED_IMG);//�ڶ�״̬:������
	this.setWidth(ImageConstants.BOSS_PLANE_BADDLY_WOUNDED_WIDTH);//��С
	this.setHeight(ImageConstants.BOSS_PLANE_BADDLY_WOUNDED_HEIGHT);
	super.draw(g);//������ӦͼƬ
	try {
	    Thread.sleep(Config.BOSS_PLANE_STATUS_CHANGE_INTERVAL);
	} catch (InterruptedException e) {

	}

	this.setPlaneImage(Images.BOSS_PLANE_KILLED_IMG);//����״̬������
	this.setWidth(ImageConstants.BOSS_PLANE_KILLED_WIDTH);//��С
	this.setHeight(ImageConstants.BOSS_PLANE_KILLED_HEIGHT);
	super.draw(g);//������ӦͼƬ
	try {
	    Thread.sleep(Config.BOSS_PLANE_STATUS_CHANGE_INTERVAL);
	} catch (InterruptedException e) {

	}

	this.setPlaneImage(Images.BOSS_PLANE_ASHED_IMG);//����״̬������
	this.setWidth(ImageConstants.BOSS_PLANE_ASHED_WIDTH);//��С
	this.setHeight(ImageConstants.BOSS_PLANE_ASHED_HEIGHT);
	super.draw(g);//������ӦͼƬ
	try {
	    Thread.sleep(Config.BOSS_PLANE_STATUS_CHANGE_INTERVAL);
	} catch (InterruptedException e) {

	}
    }
}
