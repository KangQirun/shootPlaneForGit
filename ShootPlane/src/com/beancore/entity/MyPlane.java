package com.beancore.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import com.beancore.config.BulletType;
import com.beancore.config.Config;
import com.beancore.config.ImageConstants;
import com.beancore.factory.BulletFactory;
import com.beancore.ui.GamePlayingPanel;
import com.beancore.util.Images;

public class MyPlane {
    /**
     * Current plane position
     * */
    private int posX;//��ǰλ��
    private int posY;

    private int width;//��С
    private int height;
    private Image planeImage;//ͼƬ
    private Image planeFlyingImage;
    private boolean isAlive;//���״̬
    private boolean hitDoubleLaser;
    private List<Bomb> holdBombList;//ը���б�
    private BulletType bulletType;//�ӵ�����
    private GamePlayingPanel playingPanel;
    private boolean flip;

    public MyPlane(GamePlayingPanel playingPanel) {
	this.isAlive = true;//��ʼ��
	this.flip = true;
	this.playingPanel = playingPanel;
	this.width = ImageConstants.MY_PLANE_WIDTH;//��С
	this.height = ImageConstants.MY_PLANE_HEIGHT;
	this.planeImage = Images.MY_PLANE_IMG;//ͼƬ
	this.planeFlyingImage = Images.MY_PLANE_FLYING_IMG;
	this.holdBombList = new LinkedList<Bomb>();//ը���б�
	new Thread(new LauchBulletThread()).start();//���������ӵ��߳�
    }

    public Rectangle getRectange() {//�򶨾��������Լ���Ƿ���ײ
	int fix = width / 3;
	return new Rectangle(posX + fix, posY, width / 3, height);
    }

    public void draw(Graphics g) {//������ӦͼƬͼƬ
	Graphics2D g2d = (Graphics2D) g;
	if (flip) {
	    g2d.drawImage(planeImage, posX, posY, width, height, playingPanel);
	} else {
	    g2d.drawImage(planeFlyingImage, posX, posY, width, height, playingPanel);
	}
	flip = !flip;
    }

    public void mouseMoved(MouseEvent e) {//��������¼�
	int x = e.getX();//��ȡ������
	int y = e.getY();//��ȡ������
	posX = x - width / 2;//���㵱ǰλ��
	posY = y - height / 2;
    }

    public void lauchBullet() {//�����ӵ�
	if (isAlive) {//���������
	    if (hitDoubleLaser) {//����õ�˫�ؼ�����
		Bullet bullets[] = BulletFactory.createBlueBullet(this);//������ɫ�ӵ�
		for (Bullet bullet : bullets) {
		    bullet.addBulletListener(this.playingPanel);
		    synchronized (this.playingPanel.getBullets()) {
			this.playingPanel.getBullets().add(bullet);//���ӵ������б�
		    }
		}
	    } 
	    else {//��ͨ�ӵ�
		Bullet bullet = BulletFactory.createYellowBullet(this);//���ɻ�ɫ�ӵ�����ͨ��
		bullet.addBulletListener(this.playingPanel);
		synchronized (this.playingPanel.getBullets()) {
		    this.playingPanel.getBullets().add(bullet);//���ӵ������б�
		}
	    }
	}
    }

    class LauchBulletThread implements Runnable {//�����ӵ��߳�
	public void run() {
	    while (isAlive) {
		try {
		    Thread.sleep(Config.BULLET_FIRE_INTERVAL);//���ӵ����������߳�˯���Ը�������
		} catch (InterruptedException e) {

		}
		lauchBullet();
	    }
	}
    }
    
    //������һЩget��set����
    public int getHoldBombCount() {
	return this.holdBombList.size();//��ȡը����Ŀ
    }

    public int getPosX() {
	return posX;//��ȡ������
    }

    public void setPosX(int posX) {
	this.posX = posX;//���ú�����
    }

    public int getPosY() {
	return posY;//��ȡ������
    }

    public void setPosY(int posY) {
	this.posY = posY;//����������
    }

    public int getWidth() {
	return width;//��ȡ���
    }

    public void setWidth(int width) {
	this.width = width;//���ÿ��
    }

    public int getHeight() {
	return height;//��ȡ�߶�
    }

    public void setHeight(int height) {
	this.height = height;//���ø߶�
    }

    public Image getPlaneImage() {
	return planeImage;//��ȡͼƬ
    }

    public void setPlaneImage(Image planeImage) {
	this.planeImage = planeImage;//����ͼƬ
    }

    public boolean isAlive() {
	return isAlive;//��ȡ���״̬
    }

    public void setAlive(boolean isAlive) {
	this.isAlive = isAlive;//���ô��״̬
    }

    public boolean isHitDoubleLaser() {
	return hitDoubleLaser;
    }

    public void setHitDoubleLaser(boolean hitDoubleLaser) {
	this.hitDoubleLaser = hitDoubleLaser;
    }

    public List<Bomb> getHoldBombList() {
	return holdBombList;//����ը���б�
    }

    public void setHoldBombList(List<Bomb> holdBombList) {
	this.holdBombList = holdBombList;
    }

    public BulletType getBulletType() {
	return bulletType;//��ȡ�ӵ�����
    }

    public void setBulletType(BulletType bulletType) {
	this.bulletType = bulletType;//�����ӵ�����
    }

    public GamePlayingPanel getPlayingPanel() {
	return playingPanel;
    }

    public void setPlayingPanel(GamePlayingPanel playingPanel) {
	this.playingPanel = playingPanel;
    }
}
