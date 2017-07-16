package com.beancore.factory;

import com.beancore.config.BulletType;
import com.beancore.config.ImageConstants;
import com.beancore.entity.Bullet;
import com.beancore.entity.MyPlane;

public class BulletFactory {

    public static final Bullet createYellowBullet(MyPlane myPlane) {
	int planePosX = myPlane.getPosX();//获取本机位置
	int planePosY = myPlane.getPosY();

	//相应子弹位置
	int bulletPosX = planePosX + myPlane.getWidth() / 2 - ImageConstants.YELLOW_BULLET_WIDTH / 2;
	int bulletPosY = planePosY + ImageConstants.YELLOW_BULLET_HEIGHT;

	Bullet b = new Bullet(myPlane.getPlayingPanel(), BulletType.YELLOW_BULLET);
	b.setPosX(bulletPosX);//设置相应子弹位置
	b.setPosY(bulletPosY);
	return b;
    }

    public static final Bullet[] createBlueBullet(MyPlane myPlane) {
    //分为bullet1和bullet2.
	Bullet[] bullets = new Bullet[2];
	int planePosX = myPlane.getPosX();//获取本机位置
	int planePosY = myPlane.getPosY();

	int bullet1PosX = planePosX + myPlane.getWidth() / 4 - ImageConstants.BLUE_BULLET_WIDTH / 2;
	int bullet1PosY = planePosY + ImageConstants.BLUE_BULLET_HEIGHT;//计算子弹1位置

	Bullet b1 = new Bullet(myPlane.getPlayingPanel(), BulletType.BLUE_BULLET);
	b1.setPosX(bullet1PosX);//设置子弹1位置
	b1.setPosY(bullet1PosY);

	int bullet2PosX = planePosX + myPlane.getWidth() / 4 * 3 - ImageConstants.BLUE_BULLET_WIDTH / 2;
	int bullet2PosY = planePosY + ImageConstants.BLUE_BULLET_HEIGHT;//计算子弹2位置

	Bullet b2 = new Bullet(myPlane.getPlayingPanel(), BulletType.BLUE_BULLET);
	b2.setPosX(bullet2PosX);//设置子弹2位置
	b2.setPosY(bullet2PosY);

	bullets[0] = b1;
	bullets[1] = b2;

	return bullets;
    }
}
