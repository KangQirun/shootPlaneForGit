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
    private int posX;//当前位置
    private int posY;

    private int width;//大小
    private int height;
    private Image planeImage;//图片
    private Image planeFlyingImage;
    private boolean isAlive;//存活状态
    private boolean hitDoubleLaser;
    private List<Bomb> holdBombList;//炸弹列表
    private BulletType bulletType;//子弹类型
    private GamePlayingPanel playingPanel;
    private boolean flip;

    public MyPlane(GamePlayingPanel playingPanel) {
	this.isAlive = true;//初始化
	this.flip = true;
	this.playingPanel = playingPanel;
	this.width = ImageConstants.MY_PLANE_WIDTH;//大小
	this.height = ImageConstants.MY_PLANE_HEIGHT;
	this.planeImage = Images.MY_PLANE_IMG;//图片
	this.planeFlyingImage = Images.MY_PLANE_FLYING_IMG;
	this.holdBombList = new LinkedList<Bomb>();//炸弹列表
	new Thread(new LauchBulletThread()).start();//启动发送子弹线程
    }

    public Rectangle getRectange() {//框定矩形区域，以检查是否碰撞
	int fix = width / 3;
	return new Rectangle(posX + fix, posY, width / 3, height);
    }

    public void draw(Graphics g) {//绘制相应图片图片
	Graphics2D g2d = (Graphics2D) g;
	if (flip) {
	    g2d.drawImage(planeImage, posX, posY, width, height, playingPanel);
	} else {
	    g2d.drawImage(planeFlyingImage, posX, posY, width, height, playingPanel);
	}
	flip = !flip;
    }

    public void mouseMoved(MouseEvent e) {//处理鼠标事件
	int x = e.getX();//获取横坐标
	int y = e.getY();//获取纵坐标
	posX = x - width / 2;//计算当前位置
	posY = y - height / 2;
    }

    public void lauchBullet() {//发射子弹
	if (isAlive) {//当本机存活
	    if (hitDoubleLaser) {//如果得到双重激光炮
		Bullet bullets[] = BulletFactory.createBlueBullet(this);//生成蓝色子弹
		for (Bullet bullet : bullets) {
		    bullet.addBulletListener(this.playingPanel);
		    synchronized (this.playingPanel.getBullets()) {
			this.playingPanel.getBullets().add(bullet);//将子弹加入列表
		    }
		}
	    } 
	    else {//普通子弹
		Bullet bullet = BulletFactory.createYellowBullet(this);//生成黄色子弹（普通）
		bullet.addBulletListener(this.playingPanel);
		synchronized (this.playingPanel.getBullets()) {
		    this.playingPanel.getBullets().add(bullet);//将子弹加入列表
		}
	    }
	}
    }

    class LauchBulletThread implements Runnable {//发射子弹线程
	public void run() {
	    while (isAlive) {
		try {
		    Thread.sleep(Config.BULLET_FIRE_INTERVAL);//在子弹开火间隔内线程睡眠以隔开距离
		} catch (InterruptedException e) {

		}
		lauchBullet();
	    }
	}
    }
    
    //以下是一些get和set方法
    public int getHoldBombCount() {
	return this.holdBombList.size();//获取炸弹数目
    }

    public int getPosX() {
	return posX;//获取横坐标
    }

    public void setPosX(int posX) {
	this.posX = posX;//设置横坐标
    }

    public int getPosY() {
	return posY;//获取纵坐标
    }

    public void setPosY(int posY) {
	this.posY = posY;//设置纵坐标
    }

    public int getWidth() {
	return width;//获取宽度
    }

    public void setWidth(int width) {
	this.width = width;//设置宽度
    }

    public int getHeight() {
	return height;//获取高度
    }

    public void setHeight(int height) {
	this.height = height;//设置高度
    }

    public Image getPlaneImage() {
	return planeImage;//获取图片
    }

    public void setPlaneImage(Image planeImage) {
	this.planeImage = planeImage;//设置图片
    }

    public boolean isAlive() {
	return isAlive;//获取存活状态
    }

    public void setAlive(boolean isAlive) {
	this.isAlive = isAlive;//设置存活状态
    }

    public boolean isHitDoubleLaser() {
	return hitDoubleLaser;
    }

    public void setHitDoubleLaser(boolean hitDoubleLaser) {
	this.hitDoubleLaser = hitDoubleLaser;
    }

    public List<Bomb> getHoldBombList() {
	return holdBombList;//返回炸弹列表
    }

    public void setHoldBombList(List<Bomb> holdBombList) {
	this.holdBombList = holdBombList;
    }

    public BulletType getBulletType() {
	return bulletType;//获取子弹类型
    }

    public void setBulletType(BulletType bulletType) {
	this.bulletType = bulletType;//设置子弹类型
    }

    public GamePlayingPanel getPlayingPanel() {
	return playingPanel;
    }

    public void setPlayingPanel(GamePlayingPanel playingPanel) {
	this.playingPanel = playingPanel;
    }
}
