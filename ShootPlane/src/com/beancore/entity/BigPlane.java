package com.beancore.entity;

import java.awt.Graphics;

import com.beancore.config.Config;
import com.beancore.config.EnemyPlaneType;
import com.beancore.config.ImageConstants;
import com.beancore.ui.GamePlayingPanel;
import com.beancore.util.Images;

public class BigPlane extends EnemyPlane {//�̳ел�����

    public BigPlane(GamePlayingPanel getPlayingPanel, EnemyPlaneType enemyType) {
	super(getPlayingPanel, enemyType);
    }//��ʼ��

    @Override
    public void drawFighting(Graphics g) {//��д������󷽷�
	new Thread(new DrawFighting(g)).start();//ս�������߳̿�ʼִ�У�Java ��������ø��̵߳� run ������
    }

    @Override
    public void drawKilled(Graphics g) {//��д������󷽷�
	new Thread(new DrawKilled(g)).start();//���ٻ����߳̿�ʼִ��
    }

    class DrawFighting implements Runnable {
	private Graphics g;

	DrawFighting(Graphics g) {
	    this.g = g;
	}

	@Override
	public void run() {
	    drawFightingRun(g);//������Ӧrun����
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

    //С�ɻ�����������ɻ�ͬ��ͬ��������Ϊprivate������
    public void drawFightingRun(Graphics g) {
	this.setPlaneImage(Images.BIG_PLANE_FIGHTING_IMG);//���ô�ɻ�ս��ͼƬ
	this.setWidth(ImageConstants.BIG_PLANE_FIGHTING_WIDTH);//��С
	this.setHeight(ImageConstants.BIG_PLANE_FIGHTING_HEIGHT);
	super.draw(g);//������ӦͼƬ
	try {
	    Thread.sleep(Config.BIG_PLANE_STATUS_CHANGE_INTERVAL);//�ڴ�ɻ�״̬�ı������߳�˯��
	} catch (InterruptedException e) {

	}
    }

    //���������̵߳�run��������Ϊ�Ĳ���hitted��baddlywounded,killed,ashed.
    public void drawKilledRun(Graphics g) {
	this.setPlaneImage(Images.BIG_PLANE_HITTED_IMG);//���ñ����л���ͼƬ
	this.setWidth(ImageConstants.BIG_PLANE_HITTED_WIDTH);//��С
	this.setHeight(ImageConstants.BIG_PLANE_HITTED_HEIGHT);
	super.draw(g);//���Ʒɻ�
	try {
	    Thread.sleep(Config.BIG_PLANE_STATUS_CHANGE_INTERVAL);//�ڴ�ɻ�״̬�ı������߳�˯��
	} catch (InterruptedException e) {

	}

	this.setPlaneImage(Images.BIG_PLANE_BADDLY_WOUNDED_IMG);//�ڶ�״̬�ɻ�������
	this.setWidth(ImageConstants.BIG_PLANE_BADDLY_WOUNDED_WIDTH);//���ô�С
	this.setHeight(ImageConstants.BIG_PLANE_BADDLY_WOUNDED_HEIGHT);
	super.draw(g);//������ӦͼƬ
	try {
	    Thread.sleep(Config.BIG_PLANE_STATUS_CHANGE_INTERVAL);
	} catch (InterruptedException e) {

	}

	this.setPlaneImage(Images.BIG_PLANE_KILLED_IMG);//����״̬���ɻ�����
	this.setWidth(ImageConstants.BIG_PLANE_KILLED_WIDTH);//��С
	this.setHeight(ImageConstants.BIG_PLANE_KILLED_HEIGHT);
	super.draw(g);//������ӦͼƬ
	try {
	    Thread.sleep(Config.BIG_PLANE_STATUS_CHANGE_INTERVAL);//�߳�˯�߶���ͣ��Ч��
	} catch (InterruptedException e) {

	}

	this.setPlaneImage(Images.BIG_PLANE_ASHED_IMG);//����״̬������
	this.setWidth(ImageConstants.BIG_PLANE_ASHED_WIDTH);//��С
	this.setHeight(ImageConstants.BIG_PLANE_ASHED_HEIGHT);
	super.draw(g);//������ӦͼƬ
	try {
	    Thread.sleep(Config.BIG_PLANE_STATUS_CHANGE_INTERVAL);
	} catch (InterruptedException e) {

	}
    }

}
