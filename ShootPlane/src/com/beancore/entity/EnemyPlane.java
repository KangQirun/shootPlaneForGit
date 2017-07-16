package com.beancore.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import com.beancore.config.EnemyPlaneType;
import com.beancore.listener.EnemyPlaneListener;
import com.beancore.ui.GamePlayingPanel;

public abstract class EnemyPlane {
	//�л������࣬��Ϊ���౻smallplane��bigplane��BOSSplane��������̳�
    private int posX;//λ��
    private int posY;
    private int width;//��С
    private int height;
    private int speed;//�ٶ�
    private int hittedCount;//����ͳ��
    private int killedCount;//ɱ��ͳ��
    private int killedScore;//ɱ���÷�
    private Image planeImage;
    private EnemyPlaneListener listener;
    private EnemyPlaneType enemyType;//�л�����
    private GamePlayingPanel gamePlayingPanel;

    //����Ϊ�л��Ĺ��췽������ʹ�ù�����������һ������ĵл�ʱ����Ҫ�ṩ�л����Ƶ����͵л�������
    public EnemyPlane(GamePlayingPanel getPlayingPanel, EnemyPlaneType enemyType) {
	this.gamePlayingPanel = getPlayingPanel;
	this.enemyType = enemyType;
	this.hittedCount = 0;//��ʼ�����д���
    }

    public Rectangle getRectangle() {//��ȡ�������򣬼����ײ��
	return new Rectangle(posX, posY, width, height);
    }

    public void draw(Graphics g) {//����������ϻ��Ʒɻ�
	Graphics2D g2d = (Graphics2D) g;
	g2d.drawImage(planeImage, posX, posY, width, height, gamePlayingPanel);
    }

    public void addEnemyPlaneListener(EnemyPlaneListener listener) {
    //������ӵл�λ���ƶ�������
	this.listener = listener;
    }

    public void addHittedCount() {//����ͳ�Ƶ���
	this.hittedCount++;
    }

    public boolean isKilled() {//�ж��Ƿ񱻻���
	return this.hittedCount >= this.killedCount;//������������=�����޶�
    }

    public abstract void drawFighting(Graphics g);//���󷽷���������ʵ�֣���ʾ�л��յ�����ʱͼƬ

    public abstract void drawKilled(Graphics g);//���󷽷���������ʵ�֣���ʾ�л�������ʱͼƬ

    
    //����ΪһЩGetter��Setter����
    public EnemyPlaneType getEnemyType() {//��ȡ�л�����
	return enemyType;
    }

    public void setEnemyType(EnemyPlaneType enemyType) {//���õл�����
	this.enemyType = enemyType;
    }

    public GamePlayingPanel getGamePlayingPanel() {
	return gamePlayingPanel;
    }

    public void setGamePlayingPanel(GamePlayingPanel gamePlayingPanel) {
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

    public int getWidth() {//��ȡ���
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

    public int getSpeed() {//��ȡ�ٶ�
	return speed;
    }

    public void setSpeed(int speed) {//�����ٶ�
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

    public int getHittedCount() {//��ȡ���д���
	return hittedCount;
    }

    public void setHittedCount(int hittedCount) {//���ô��д���
	this.hittedCount = hittedCount;
    }

    public int getKilledCount() {//��ȡɱ��ͳ��
	return killedCount;
    }

    public void setKilledCount(int killedCount) {//����ɱ��ͳ��
	this.killedCount = killedCount;
    }

    public int getKilledScore() {
	return killedScore;
    }

    public void setKilledScore(int killedScore) {
	this.killedScore = killedScore;
    }

}
