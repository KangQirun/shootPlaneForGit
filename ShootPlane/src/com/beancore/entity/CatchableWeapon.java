package com.beancore.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import com.beancore.config.CatchableWeaponType;
import com.beancore.ui.GamePlayingPanel;

public abstract class CatchableWeapon {
	//��������Ϊ���࣬��BOMB��DoubleLaser��������̳�
    private int posX;//λ��
    private int posY;

    private int width;//��С
    private int height;
    private Image weaponImage;//��ӦͼƬ

    private GamePlayingPanel gamePlayingPanel;
    private CatchableWeaponType weaponType;
    private boolean useAnimation;
    private boolean useAnimationDone;
    private int speed;
    private boolean weaponDisappear;

    //���ݲ�����Ϣ���г�ʼ��
    public CatchableWeapon(GamePlayingPanel gamePlayingPanel, CatchableWeaponType weaponType) {
	this.gamePlayingPanel = gamePlayingPanel;
	this.weaponType = weaponType;
	this.useAnimation = false;
	this.useAnimationDone = false;
	this.weaponDisappear = false;
    }

    public Rectangle getRectangle() {//��ȡ���������ж��Ƿ���յ�������
	return new Rectangle(posX, posY, width, height);
    }

    public void draw(Graphics g) {//������ӦͼƬ
	Graphics2D g2d = (Graphics2D) g;
	g2d.drawImage(weaponImage, posX, posY, width, height, gamePlayingPanel);
    }

    //������һЩset��get����
    public int getPosX() {
	return posX;
    }

    public void setPosX(int posX) {//���ú�������
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

    public Image getWeaponImage() {//��ȡ����ͼƬ
	return weaponImage;
    }

    public void setWeaponImage(Image weaponImage) {//��������ͼƬ
	this.weaponImage = weaponImage;
    }

    public GamePlayingPanel getGamePlayingPanel() {
	return gamePlayingPanel;
    }

    public void setGamePlayingPanel(GamePlayingPanel gamePlayingPanel) {
	this.gamePlayingPanel = gamePlayingPanel;
    }

    public CatchableWeaponType getWeaponType() {//��ȡ��������
	return weaponType;
    }

    public void setWeaponType(CatchableWeaponType weaponType) {//������������
	this.weaponType = weaponType;
    }

    public int getSpeed() {//��ȡ�ٶ�
	return speed;
    }

    public void setSpeed(int speed) {//�����ٶ�
	this.speed = speed;
    }

    public boolean isUseAnimation() {
	return useAnimation;//��ȡʹ�ö���
    }

    public void setUseAnimation(boolean useAnimation) {
	this.useAnimation = useAnimation;//����ʹ�ö���
    }

    public boolean isUseAnimationDone() {
	return useAnimationDone;
    }

    public void setUseAnimationDone(boolean useAnimationDone) {
	this.useAnimationDone = useAnimationDone;
    }

    public boolean isWeaponDisappear() {//���������Ƿ���ʧ״̬
	return weaponDisappear;
    }

    public void setWeaponDisappear(boolean weaponDisappear) {//���������Ƿ���ʧ״̬
	this.weaponDisappear = weaponDisappear;
    }

}
