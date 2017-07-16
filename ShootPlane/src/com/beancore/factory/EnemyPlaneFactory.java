package com.beancore.factory;

import java.util.Random;

import com.beancore.config.Config;
import com.beancore.config.EnemyPlaneType;
import com.beancore.config.ImageConstants;
import com.beancore.entity.BigPlane;
import com.beancore.entity.BossPlane;
import com.beancore.entity.EnemyPlane;
import com.beancore.entity.SmallPlane;
import com.beancore.ui.GamePlayingPanel;
import com.beancore.util.Images;

public class EnemyPlaneFactory {
    public static final Random rand = new Random();

    //生成敌机
    public static final EnemyPlane createEnemyPlane(GamePlayingPanel playingPanel, EnemyPlaneType enemyPlaneType) {
	EnemyPlane enemyPlane = null;
	switch (enemyPlaneType) {
	case SMALL_ENEMY_PLANE://若敌机为小型飞机
	    enemyPlane = new SmallPlane(playingPanel, enemyPlaneType);
	    enemyPlane.setWidth(ImageConstants.SMALL_PLANE_WIDTH);//大小
	    enemyPlane.setHeight(ImageConstants.SMALL_PLANE_HEIGHT);
	    enemyPlane.setPlaneImage(Images.SMALL_PLANE_IMG);//设置图片
	    enemyPlane.setKilledCount(Config.BULLET_COUNT_TO_KILL_SMALL_PLANE);//杀死小飞机所需子弹数
	    enemyPlane.setKilledScore(Config.KILL_SMALL_PLANE_SCORE);//杀死小飞机获得的分数
	    break;
	case BIG_ENEMY_PLANE://若敌机为大飞机
	    enemyPlane = new BigPlane(playingPanel, enemyPlaneType);
	    enemyPlane.setWidth(ImageConstants.BIG_PLANE_WIDTH);//大小
	    enemyPlane.setHeight(ImageConstants.BIG_PLANE_HEIGHT);
	    enemyPlane.setPlaneImage(Images.BIG_PLANE_IMG);//设置图片
	    enemyPlane.setKilledCount(Config.BULLET_COUNT_TO_KILL_BIG_PLANE);//杀死大飞机所需子弹数
	    enemyPlane.setKilledScore(Config.KILL_BIG_PLANE_SCORE);//杀死大飞机获得分数
	    break;
	case BOSS_ENEMY_PLANE://若敌机为Boss飞机
	    enemyPlane = new BossPlane(playingPanel, enemyPlaneType);
	    enemyPlane.setWidth(ImageConstants.BOSS_PLANE_WIDTH);//大小
	    enemyPlane.setHeight(ImageConstants.BOSS_PLANE_HEIGHT);
	    enemyPlane.setPlaneImage(Images.BOSS_PLANE_IMG);//设置图片
	    enemyPlane.setKilledCount(Config.BULLET_COUNT_TO_KILL_BOSS_PLANE);//杀死Boss飞机所需子弹数
	    enemyPlane.setKilledScore(Config.KILL_BOSS_PLANE_SCORE);//杀死Boss飞机获得分数
	    break;
	}

	int posX = rand.nextInt(playingPanel.getWidth() - enemyPlane.getWidth());
	//获取随机数作为敌机的横坐标
	int posY = 0;
	enemyPlane.setPosX(posX);//设置位置
	enemyPlane.setPosY(posY);
	int speed = rand.nextInt(Config.ENEMY_PLANE_MOVE_SPEED_MAX - Config.ENEMY_PLANE_MOVE_SPEED_MIN)
		+ Config.ENEMY_PLANE_MOVE_SPEED_MIN;
	enemyPlane.setSpeed(speed);//设置速度
	enemyPlane.addEnemyPlaneListener(playingPanel);
	return enemyPlane;
    }
}