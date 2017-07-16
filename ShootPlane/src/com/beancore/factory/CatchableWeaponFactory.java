package com.beancore.factory;

import java.util.Random;

import com.beancore.config.CatchableWeaponType;
import com.beancore.config.Config;
import com.beancore.config.ImageConstants;
import com.beancore.entity.Bomb;
import com.beancore.entity.CatchableWeapon;
import com.beancore.entity.DoubleLaser;
import com.beancore.ui.GamePlayingPanel;
import com.beancore.util.Images;

public class CatchableWeaponFactory {//生成两种补给武器：炸弹和双重激光器
    public static final Random rand = new Random();

    public static CatchableWeapon createCatchableWeapon(GamePlayingPanel playingPanel, CatchableWeaponType weaponType) {
	CatchableWeapon weapon = null;
	switch (weaponType) {
	case BOMB://若武器类型为炸弹，设置相应参数
	    weapon = new Bomb(playingPanel, weaponType);
	    weapon.setWidth(ImageConstants.BOMB_WIDTH);//宽度
	    weapon.setHeight(ImageConstants.BOMB_HEIGHT);//高度
	    weapon.setWeaponImage(Images.BOMB_IMG);//设置图片
	    weapon.setSpeed(Config.POP_WEAPON_MOVE_SPEED);//速度
	    break;
	case DOUBLE_LASER://若武器类型为双重激光器，设置相应参数
	    weapon = new DoubleLaser(playingPanel, weaponType);
	    weapon.setWidth(ImageConstants.DOUBLE_LASER_WIDTH);//宽度
	    weapon.setHeight(ImageConstants.DOUBLE_LASER_HEIGHT);//高度
	    weapon.setWeaponImage(Images.DOUBLE_LASER_IMG);//设置图片
	    weapon.setSpeed(Config.POP_WEAPON_MOVE_SPEED);//速度
	    break;
	}

	int posX = rand.nextInt(playingPanel.getWidth() - weapon.getWidth());//获取随机数作为武器横坐标
	int posY = 0;
	weapon.setPosX(posX);
	weapon.setPosY(posY);

	return weapon;
    }
}