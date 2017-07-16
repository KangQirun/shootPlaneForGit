package com.beancore.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

import com.beancore.config.CatchableWeaponType;
import com.beancore.config.Config;
import com.beancore.config.EnemyPlaneType;
import com.beancore.config.ImageConstants;
import com.beancore.entity.Bomb;
import com.beancore.entity.Bullet;
import com.beancore.entity.CatchableWeapon;
import com.beancore.entity.EnemyPlane;
import com.beancore.entity.MyPlane;
import com.beancore.factory.CatchableWeaponFactory;
import com.beancore.factory.EnemyPlaneFactory;
import com.beancore.listener.BulletListener;
import com.beancore.listener.CatchableWeaponListener;
import com.beancore.listener.EnemyPlaneListener;
import com.beancore.util.Images;
import com.beancore.util.SoundPlayer;

public class GamePlayingPanel extends JPanel implements MouseListener, MouseMotionListener, BulletListener,
	EnemyPlaneListener, CatchableWeaponListener, ImageObserver {
    private static final long serialVersionUID = 1L;
    private static int ANIMATION_STEP_1 = 1;
    private static int ANIMATION_STEP_2 = 2;

    private volatile List<Bullet> bullets;//�ӵ��б�
    private volatile List<EnemyPlane> enemyPlanes;//�л��б�
    private int score;//����
    private MyPlane myPlane;

    private CatchableWeapon popBomb;//ը��
    private CatchableWeapon popDoubleLaser;//˫�ؼ�����

    private Thread paintThread;//�����߳�
    
    //����ʣ��ʱ��
    private int remainTimeToPopSmallPlane;//����С�ɻ���ʣ��ʱ��
    private int remainTimeToPopBigPlane;//������ɻ���ʣ��ʱ��
    private int remainTimeToPopBossPlane;//����Boss�ɻ���ʣ��ʱ��

    private int remainTimeToPopBomb;//����ը����ʣ��ʱ��
    private int remainTimeToPopDoubleLaser;//����˫�ؼ�������ʣ��ʱ��

    private int remainTimeDoubleLaserRunOut;//˫�ؼ������þ�ʣ��ʱ��

    private int bombAnomationStep;
    private int doubleLaserAnimationStep;
    
    //��������������
    private SoundPlayer smallPlaneKilledSoundPlayer;
    private SoundPlayer bigPlaneKilledSoundPlayer;
    private SoundPlayer bossPlaneKilledSoundPlayer;

    private SoundPlayer bossPlaneFlyingSoundPlayer;
    private SoundPlayer popWeaponSoundPlayer;

    private SoundPlayer fireBulletSoundPlayer;
    private SoundPlayer useBombSoundPlayer;

    private SoundPlayer getDoubleLaserSoundPlayer;
    private SoundPlayer getBombSoundPlayer;

    private SoundPlayer gameMusicSoundPlayer;
    private SoundPlayer gameOverSoundPlayer;

    public GamePlayingPanel() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
	this.initSoundPlayer();//��ʼ������������
	this.initComponents();//��ʼ�����
    }

    private void initComponents() {
	this.addMouseMotionListener(this);//��������¼�����
	this.addMouseListener(this);
	this.setSize(Config.MAIN_FRAME_WIDTH, Config.MAIN_FRAME_HEIGHT);
	this.setDoubleBuffered(true);//���ô����ʹ�û��������л���
	this.setOpaque(false);
    }
    
    //��ʼ������������
    private void initSoundPlayer() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
	smallPlaneKilledSoundPlayer = new SoundPlayer(Config.SMALL_PLANE_KILLED_AUDIO);
	bigPlaneKilledSoundPlayer = new SoundPlayer(Config.BIG_PLANE_KILLED_AUDIO);
	bossPlaneKilledSoundPlayer = new SoundPlayer(Config.BOSS_PLANE_KILLED_AUDIO);

	bossPlaneFlyingSoundPlayer = new SoundPlayer(Config.BOSS_PLANE_FLYING_AUDIO);
	popWeaponSoundPlayer = new SoundPlayer(Config.POP_WEAPON_AUDIO);

	fireBulletSoundPlayer = new SoundPlayer(Config.FIRE_BULLET_AUDIO);
	useBombSoundPlayer = new SoundPlayer(Config.USER_BOMB_AUDIO);

	getDoubleLaserSoundPlayer = new SoundPlayer(Config.GET_DOUBLE_LASER_AUDIO);
	getBombSoundPlayer = new SoundPlayer(Config.GET_BOMB_AUDIO);

	gameMusicSoundPlayer = new SoundPlayer(Config.GAME_MUSIC_AUDIO);
	gameOverSoundPlayer = new SoundPlayer(Config.GAME_OVER_AUDIO);
    }

    @Override
    public void onBulletLocationChanged(Bullet b) {//�����ӵ�λ�õı仯
	if (b != null) {
	    b.setPosY(b.getPosY() - b.getSpeed());//�����ӵ�����λ��
	    if (b.getPosY() <= 0) {//���������<0(�ӵ�����Ļ����)
		synchronized (this.bullets) {
		    this.bullets.remove(b);//���б����Ƴ��ӵ�
		}
	    }

	    EnemyPlane enemyPlane = b.hitEnemyPlanes();
	    if (enemyPlane != null) {
		enemyPlane.drawFighting(this.getComponentGraphics(this.getGraphics()));
		if (enemyPlane.isKilled()) {//����л�����
		    switch (enemyPlane.getEnemyType()) {
		    case SMALL_ENEMY_PLANE://���л���С�ɻ�
			this.smallPlaneKilledSoundPlayer.play();//����С�ɻ���������
			break;
		    case BIG_ENEMY_PLANE://����л��Ǵ�ɻ�
			this.bigPlaneKilledSoundPlayer.play();
			break;
		    case BOSS_ENEMY_PLANE://�����Boss�ɻ�
			this.bossPlaneFlyingSoundPlayer.stop();
			this.bossPlaneKilledSoundPlayer.play();
			break;
		    }
		    synchronized (this) {
			this.score += enemyPlane.getKilledScore();//������Ӧ����
		    }
		    synchronized (this.enemyPlanes) {
			this.enemyPlanes.remove(enemyPlane);//�Ƴ������ĵл�
		    }
		    synchronized (this.bullets) {
			this.bullets.remove(b);//�Ƴ��ӵ�
		    }
		    enemyPlane.drawKilled(this.getComponentGraphics(this.getGraphics()));
		}
	    }
	}
    }

    @Override
    public void onEnemyPlaneLocationChanged(EnemyPlane p) {//���õл�λ�õı仯
	if (p != null && !p.isKilled()) {//���ɻ�������û������
	    p.setPosY(p.getPosY() + p.getSpeed());//��ȡ�л�����λ��
	    if (p.getPosY() >= this.getHeight()) {//���л���δ����Ļ����ʧ
		if (p.getEnemyType().equals(EnemyPlaneType.BOSS_ENEMY_PLANE)) {//����л���Boss�ɻ�
		    this.bossPlaneFlyingSoundPlayer.stop();//ֹͣ������Ӧ����
		}
		synchronized (this.enemyPlanes) {//���л��½�����Ļ����
		    enemyPlanes.remove(p);//�Ƴ��л�
		}
	    } else {
		if (p.getRectangle().intersects(myPlane.getRectange())) {//����л��뱾���ཻ
		    // ��Ϸ����
		    synchronized (myPlane) {
			if (myPlane.isAlive()) {
			    this.stopGame();
			}
		    }
		}
	    }
	}
    }

    @Override
    public void onCatchableWeaponLocationChanged(CatchableWeapon weapon) {//�ɻ�ȡ����λ�ñ仯
	if (weapon != null) {
	    synchronized (weapon) {
		int posY = weapon.getPosY();
		if (weapon.isUseAnimation()) {
		    switch (weapon.getWeaponType()) {
		    case BOMB://��������ը��
			if (this.bombAnomationStep == ANIMATION_STEP_1) {//����������ڵ�һ��
			    posY += Config.POP_WEAPON_ANIMATION_MOVE_FORWARD_SPEED;
			    this.bombAnomationStep++;
			} else if (this.bombAnomationStep == ANIMATION_STEP_2) {//����������ڵڶ���
			    posY -= Config.POP_WEAPON_ANIMATION_MOV_BACK_SPEED;
			    this.bombAnomationStep = 0;
			    weapon.setUseAnimation(false);
			    weapon.setUseAnimationDone(true);
			}
			break;
		    case DOUBLE_LASER://���������˫�ؼ�����
			if (this.doubleLaserAnimationStep == ANIMATION_STEP_1) {//����������ڵ�һ��
			    posY += Config.POP_WEAPON_ANIMATION_MOVE_FORWARD_SPEED;
			    this.doubleLaserAnimationStep++;
			} else if (this.doubleLaserAnimationStep == ANIMATION_STEP_2) {//����������ڵڶ���
			    posY -= Config.POP_WEAPON_ANIMATION_MOV_BACK_SPEED;
			    this.doubleLaserAnimationStep = 0;
			    weapon.setUseAnimation(false);
			    weapon.setUseAnimationDone(true);
			}
			break;
		    }
		} else {
		    posY += weapon.getSpeed();
		}

		weapon.setPosY(posY);

		if (!weapon.isUseAnimationDone() && weapon.getPosY() >= this.getHeight() / 5) {
		    weapon.setUseAnimation(true);
		    switch (weapon.getWeaponType()) {
		    case BOMB://���������ը��
			if (this.bombAnomationStep == 0) {
			    this.bombAnomationStep++;
			}
			break;
		    case DOUBLE_LASER://���������˫�ؼ�����
			if (this.doubleLaserAnimationStep == 0) {
			    this.doubleLaserAnimationStep++;
			}
			break;
		    }
		}

		if (weapon.getPosY() >= this.getHeight()) {
		    weapon.setWeaponDisappear(true);
		} else {
		    if (weapon.getRectangle().intersects(myPlane.getRectange())) {
		    	//������������뱾�������ཻ��������ץȡ��������
			switch (weapon.getWeaponType()) {
			case BOMB://��������ը��
			    if (myPlane.getHoldBombCount() < Config.BOMB_MAX_HOLD_COUNT) {//����������
				myPlane.getHoldBombList().add((Bomb) weapon);//�����������
				this.getBombSoundPlayer.play();//���Ż�ȡ��������
			    }
			    break;
			case DOUBLE_LASER://���������˫�ؼ�����
			    myPlane.setHitDoubleLaser(true);
			    this.getDoubleLaserSoundPlayer.play();//������Ӧ����
			    break;
			}
			weapon.setWeaponDisappear(true);
		    }
		}
	    }
	}
    }

    private void drawScore(Graphics g) {
	Graphics2D g2d = (Graphics2D) g;
	List<Integer> intList = new ArrayList<Integer>();
	int scoreCopy = this.score;
	int quotient = 0;
	while ((quotient = scoreCopy / 10) != 0) {
	    intList.add(scoreCopy % 10);
	    scoreCopy = quotient;
	}
	intList.add(scoreCopy % 10);
	// draw
	int posX = Config.SCORE_IMG_POS_X;
	int posY = Config.SCORE_IMG_POS_Y;
	g2d.drawImage(Images.SCORE_IMG, posX, posY, ImageConstants.SCORE_IMG_WIDTH, ImageConstants.SCORE_IMG_HEIGHT,
		this);
	posX += ImageConstants.SCORE_IMG_WIDTH;
	posY += ImageConstants.SCORE_IMG_HEIGHT - ImageConstants.NUMBER_0_HEIGHT;
	int size = intList.size();
	for (int i = size - 1; i >= 0; i--) {
	    switch (intList.get(i)) {
	    case Config.NUMBER_0:
		g2d.drawImage(Images.NUMBER_0_IMG, posX, posY, ImageConstants.NUMBER_0_WIDTH,
			ImageConstants.NUMBER_0_HEIGHT, this);
		posX += ImageConstants.NUMBER_0_WIDTH;
		break;
	    case Config.NUMBER_1:
		g2d.drawImage(Images.NUMBER_1_IMG, posX, posY, ImageConstants.NUMBER_1_WIDTH,
			ImageConstants.NUMBER_1_HEIGHT, this);
		posX += ImageConstants.NUMBER_1_WIDTH;
		break;
	    case Config.NUMBER_2:
		g2d.drawImage(Images.NUMBER_2_IMG, posX, posY, ImageConstants.NUMBER_2_WIDTH,
			ImageConstants.NUMBER_2_HEIGHT, this);
		posX += ImageConstants.NUMBER_2_WIDTH;
		break;
	    case Config.NUMBER_3:
		g2d.drawImage(Images.NUMBER_3_IMG, posX, posY, ImageConstants.NUMBER_3_WIDTH,
			ImageConstants.NUMBER_3_HEIGHT, this);
		posX += ImageConstants.NUMBER_3_WIDTH;
		break;
	    case Config.NUMBER_4:
		g2d.drawImage(Images.NUMBER_4_IMG, posX, posY, ImageConstants.NUMBER_4_WIDTH,
			ImageConstants.NUMBER_4_HEIGHT, this);
		posX += ImageConstants.NUMBER_4_WIDTH;
		break;
	    case Config.NUMBER_5:
		g2d.drawImage(Images.NUMBER_5_IMG, posX, posY, ImageConstants.NUMBER_5_WIDTH,
			ImageConstants.NUMBER_5_HEIGHT, this);
		posX += ImageConstants.NUMBER_5_WIDTH;
		break;
	    case Config.NUMBER_6:
		g2d.drawImage(Images.NUMBER_6_IMG, posX, posY, ImageConstants.NUMBER_6_WIDTH,
			ImageConstants.NUMBER_6_HEIGHT, this);
		posX += ImageConstants.NUMBER_6_WIDTH;
		break;
	    case Config.NUMBER_7:
		g2d.drawImage(Images.NUMBER_7_IMG, posX, posY, ImageConstants.NUMBER_7_WIDTH,
			ImageConstants.NUMBER_7_HEIGHT, this);
		posX += ImageConstants.NUMBER_7_WIDTH;
		break;
	    case Config.NUMBER_8:
		g2d.drawImage(Images.NUMBER_8_IMG, posX, posY, ImageConstants.NUMBER_8_WIDTH,
			ImageConstants.NUMBER_8_HEIGHT, this);
		posX += ImageConstants.NUMBER_8_WIDTH;
		break;
	    case Config.NUMBER_9:
		g2d.drawImage(Images.NUMBER_9_IMG, posX, posY, ImageConstants.NUMBER_9_WIDTH,
			ImageConstants.NUMBER_9_HEIGHT, this);
		posX += ImageConstants.NUMBER_9_WIDTH;
		break;
	    }
	}
    }

    private void drawBomb(Graphics g) {
	if (this.myPlane.getHoldBombCount() > 0) {//���������
	    Graphics2D g2d = (Graphics2D) g;
	    int posX = Config.CAUGHT_BOMB_IMG_POS_X;//λ��
	    int posY = Config.CAUGHT_BOMB_IMG_POS_Y;
	    g2d.drawImage(Images.CAUGHT_BOMB_IMG, posX, posY, ImageConstants.CAUGHT_BOMB_WIDTH,
		    ImageConstants.CAUGHT_BOMB_HEIGHT, this);//����ͼƬ

	    posX += ImageConstants.CAUGHT_BOMB_WIDTH;//��λ��
	    posY += (ImageConstants.CAUGHT_BOMB_HEIGHT - ImageConstants.X_MARK_HEIGHT) / 2;

	    g2d.drawImage(Images.X_MARK_IMG, posX, posY, ImageConstants.X_MARK_WIDTH, ImageConstants.X_MARK_HEIGHT,
		    this);
	    posX += ImageConstants.X_MARK_WIDTH;
	    switch (this.myPlane.getHoldBombCount()) {
	    case Config.ONE_BOMB://��ֻ��һ��ը��
		g2d.drawImage(Images.NUMBER_1_IMG, posX, posY, ImageConstants.NUMBER_1_WIDTH,
			ImageConstants.NUMBER_1_HEIGHT, this);//������ӦͼƬ
		break;
	    case Config.TWO_BOMB://��������ը��
		g2d.drawImage(Images.NUMBER_2_IMG, posX, posY, ImageConstants.NUMBER_2_WIDTH,
			ImageConstants.NUMBER_2_HEIGHT, this);//������ӦͼƬ
		break;
	    case Config.THREE_BOMB://��������ը��
		g2d.drawImage(Images.NUMBER_3_IMG, posX, posY, ImageConstants.NUMBER_3_WIDTH,
			ImageConstants.NUMBER_3_HEIGHT, this);//������ӦͼƬ
		break;
	    }

	}
    }

    class PaintThread implements Runnable {//�����߳�

	@Override
	public void run() {
	    while (myPlane.isAlive()) {//���������
		for (int i = 0; i < bullets.size(); i++) {
		    Bullet b = bullets.get(i);
		    onBulletLocationChanged(b);
		}

		for (int i = 0; i < enemyPlanes.size(); i++) {
		    EnemyPlane enemyPlane = enemyPlanes.get(i);
		    onEnemyPlaneLocationChanged(enemyPlane);
		}

		// ADD PLANE
		if (remainTimeToPopSmallPlane > 0) {
		    remainTimeToPopSmallPlane -= Config.GAME_PANEL_REPAINT_INTERVAL;
		} else {
		    // pop a small enemy plane
		    EnemyPlane smallPlane = EnemyPlaneFactory.createEnemyPlane(GamePlayingPanel.this,
			    EnemyPlaneType.SMALL_ENEMY_PLANE);
		    synchronized (GamePlayingPanel.this.enemyPlanes) {
			enemyPlanes.add(smallPlane);
		    }
		    remainTimeToPopSmallPlane = Config.POP_SMALL_ENEMY_PLANE_INTERVAL;
		}

		if (remainTimeToPopBigPlane > 0) {
		    remainTimeToPopBigPlane -= Config.GAME_PANEL_REPAINT_INTERVAL;
		} else {
		    // pop a big enemy plane
		    EnemyPlane bigPlane = EnemyPlaneFactory.createEnemyPlane(GamePlayingPanel.this,
			    EnemyPlaneType.BIG_ENEMY_PLANE);
		    synchronized (GamePlayingPanel.this.enemyPlanes) {
			enemyPlanes.add(bigPlane);
		    }
		    remainTimeToPopBigPlane = Config.POP_BIG_ENEMY_PLANE_INTERVAL;
		}

		if (remainTimeToPopBossPlane > 0) {
		    remainTimeToPopBossPlane -= Config.GAME_PANEL_REPAINT_INTERVAL;
		} else {
		    // pop a boss enemy plane
		    EnemyPlane bossPlane = EnemyPlaneFactory.createEnemyPlane(GamePlayingPanel.this,
			    EnemyPlaneType.BOSS_ENEMY_PLANE);
		    synchronized (GamePlayingPanel.this.enemyPlanes) {
			enemyPlanes.add(bossPlane);
		    }
		    remainTimeToPopBossPlane = Config.POP_BOSS_ENEMY_PLANE_INTERVAL;
		    bossPlaneFlyingSoundPlayer.loop();
		}

		// ADD BOMB
		if (remainTimeToPopBomb > 0) {
		    remainTimeToPopBomb -= Config.GAME_PANEL_REPAINT_INTERVAL;
		} else {
		    // pop bomb
		    popBomb = CatchableWeaponFactory.createCatchableWeapon(GamePlayingPanel.this,
			    CatchableWeaponType.BOMB);
		    remainTimeToPopBomb = Config.POP_BOMBO_INTERVAL;
		    popWeaponSoundPlayer.play();
		}

		if (popBomb != null) {
		    onCatchableWeaponLocationChanged(popBomb);
		}

		// ADD DOUBLE LASER
		if (remainTimeToPopDoubleLaser > 0) {
		    remainTimeToPopDoubleLaser -= Config.GAME_PANEL_REPAINT_INTERVAL;
		} else {
		    // pop double laser
		    popDoubleLaser = CatchableWeaponFactory.createCatchableWeapon(GamePlayingPanel.this,
			    CatchableWeaponType.DOUBLE_LASER);
		    remainTimeToPopDoubleLaser = Config.POP_DOUBLE_LASER_INTERVAL;
		    popWeaponSoundPlayer.play();
		}

		if (popDoubleLaser != null) {
		    onCatchableWeaponLocationChanged(popDoubleLaser);
		}

		// CHECK DOUBLE LASER BULLETS RUN OUT
		if (remainTimeDoubleLaserRunOut > 0) {
		    remainTimeDoubleLaserRunOut -= Config.GAME_PANEL_REPAINT_INTERVAL;
		} else {
		    myPlane.setHitDoubleLaser(false);
		    popDoubleLaser = null;
		    remainTimeDoubleLaserRunOut = Config.DOUBLE_LASER_LAST_TIME;
		}

		if (popBomb != null && popBomb.isWeaponDisappear()) {
		    popBomb = null;
		}

		if (popDoubleLaser != null && popDoubleLaser.isWeaponDisappear()) {
		    popDoubleLaser = null;
		}

		GamePlayingPanel.this.repaint();

		try {
		    Thread.sleep(Config.GAME_PANEL_REPAINT_INTERVAL);
		} catch (InterruptedException e) {

		}
	    }
	}

    }

    @Override
    protected void paintComponent(Graphics g) {//�������
	super.paintComponent(g);
	drawScore(g);
	drawBomb(g);
	myPlane.draw(g);
	for (int i = 0; i < this.enemyPlanes.size(); i++) {
	    EnemyPlane enemyPlane = this.enemyPlanes.get(i);
	    enemyPlane.draw(g);
	}
	for (int i = 0; i < this.bullets.size(); i++) {
	    Bullet b = this.bullets.get(i);
	    b.draw(g);
	}
	if (this.popBomb != null && !this.popBomb.isWeaponDisappear()) {
	    this.popBomb.draw(g);
	}
	if (this.popDoubleLaser != null && !this.popDoubleLaser.isWeaponDisappear()) {
	    this.popDoubleLaser.draw(g);
	}
    }

    public void startGame() {
	this.score = 0;//��ʼ������
	this.bombAnomationStep = 0;
	this.doubleLaserAnimationStep = 0;
	this.remainTimeToPopSmallPlane = Config.POP_SMALL_ENEMY_PLANE_INTERVAL;
	//���¼�¼����ɻ�����ʣ��ʱ��
	this.remainTimeToPopBigPlane = Config.POP_BIG_ENEMY_PLANE_INTERVAL;
	this.remainTimeToPopBossPlane = Config.POP_BOSS_ENEMY_PLANE_INTERVAL;
	this.remainTimeToPopBomb = Config.POP_BOMBO_INTERVAL;
	this.remainTimeToPopDoubleLaser = Config.POP_DOUBLE_LASER_INTERVAL;
	this.remainTimeDoubleLaserRunOut = Config.DOUBLE_LASER_LAST_TIME;
	this.bullets = new LinkedList<Bullet>();
	this.enemyPlanes = new LinkedList<EnemyPlane>();
	this.myPlane = new MyPlane(this);
	this.myPlane.setAlive(true);//��ʼ��myplane������״̬Ϊalive
	this.myPlane.setPosX((Config.MAIN_FRAME_WIDTH - ImageConstants.MY_PLANE_WIDTH) / 2);
	this.myPlane.setPosY(Config.MAIN_FRAME_HEIGHT - ImageConstants.MY_PLANE_HEIGHT);
	this.gameMusicSoundPlayer.loop();//��Ϸ���ֿ�ʼѭ������
	this.fireBulletSoundPlayer.loop();//��������ѭ������
	this.paintThread = new Thread(new PaintThread());
	this.paintThread.start();//���������߳�
    }

    public void stopGame() {//ֹͣ��Ϸ
	this.myPlane.setAlive(false);//���ñ�������״̬Ϊ��
	this.gameMusicSoundPlayer.stop();//ֹͣ��������
	this.fireBulletSoundPlayer.stop();
	this.bossPlaneFlyingSoundPlayer.stop();
	this.gameOverSoundPlayer.play();//������Ϸ��������
    }

    private void useBomb() {//ʹ��ը��
	if (this.myPlane.getHoldBombCount() > 0) {//��ը�����>0
	    Graphics g = this.getComponentGraphics(this.getGraphics());
	    //�������ڻ��ƴ������ graphics ����
	    for (EnemyPlane enemyPlane : this.enemyPlanes) {//��
		synchronized (this) {//��������ʵ������ͬ����������̲߳���������£����ݳ����쳣
		    this.score += enemyPlane.getKilledScore();//��������
		}
		switch (enemyPlane.getEnemyType()) {
		case SMALL_ENEMY_PLANE://���л�ΪС��
		    this.smallPlaneKilledSoundPlayer.play();//������Ӧ����
		    break;
		case BIG_ENEMY_PLANE://����
		    this.bigPlaneKilledSoundPlayer.play();
		    break;
		case BOSS_ENEMY_PLANE://boss��
		    this.bossPlaneKilledSoundPlayer.play();
		    break;
		}
		enemyPlane.drawKilled(g);
	    }
	    synchronized (this.bullets) {
		this.bullets.clear();//����ӵ���
	    }
	    synchronized (this.enemyPlanes) {
		this.enemyPlanes.clear();
	    }
	    this.popBomb = null;
	    this.popDoubleLaser = null;

	    this.myPlane.getHoldBombList().remove(0);//?

	    this.repaint();
	}
    }

    @Override
    public void mouseDragged(MouseEvent e) {
	// nothing to do
    }

    @Override
    public void mouseMoved(MouseEvent e) {
	if (this.myPlane != null && this.myPlane.isAlive()) {
	    myPlane.mouseMoved(e);
	    this.repaint();
	}
    }

    public List<Bullet> getBullets() {
	return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
	this.bullets = bullets;//�����ӵ�
    }

    public List<EnemyPlane> getEnemyPlanes() {
	return enemyPlanes;//��ȡ�л�
    }

    public void setEnemyPlanes(List<EnemyPlane> enemyPlanes) {
	this.enemyPlanes = enemyPlanes;//���õл��б�
    }

    public MyPlane getMyPlane() {
	return myPlane;//��ȡ��������
    }

    public int getScore() {
	return score;//��ȡ����
    }

    @Override
    public void mouseClicked(MouseEvent e) {//������
	if (this.myPlane.isAlive() && this.myPlane.getHoldBombCount() > 0) {
	    useBombSoundPlayer.play();//����ʹ��ը������
	    useBomb();
	}
    }

    @Override
    public void mousePressed(MouseEvent e) {
	// nothing to do
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	// nothing to do
    }

    @Override
    public void mouseEntered(MouseEvent e) {
	// nothing to do
    }

    @Override
    public void mouseExited(MouseEvent e) {
	// nothing to do
    }

}
