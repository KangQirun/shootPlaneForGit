package com.beancore.factory;

import com.beancore.config.BulletType;
import com.beancore.config.ImageConstants;
import com.beancore.entity.Bullet;
import com.beancore.entity.MyPlane;

public class BulletFactory {

    public static final Bullet createYellowBullet(MyPlane myPlane) {
	int planePosX = myPlane.getPosX();//��ȡ����λ��
	int planePosY = myPlane.getPosY();

	//��Ӧ�ӵ�λ��
	int bulletPosX = planePosX + myPlane.getWidth() / 2 - ImageConstants.YELLOW_BULLET_WIDTH / 2;
	int bulletPosY = planePosY + ImageConstants.YELLOW_BULLET_HEIGHT;

	Bullet b = new Bullet(myPlane.getPlayingPanel(), BulletType.YELLOW_BULLET);
	b.setPosX(bulletPosX);//������Ӧ�ӵ�λ��
	b.setPosY(bulletPosY);
	return b;
    }

    public static final Bullet[] createBlueBullet(MyPlane myPlane) {
    //��Ϊbullet1��bullet2.
	Bullet[] bullets = new Bullet[2];
	int planePosX = myPlane.getPosX();//��ȡ����λ��
	int planePosY = myPlane.getPosY();

	int bullet1PosX = planePosX + myPlane.getWidth() / 4 - ImageConstants.BLUE_BULLET_WIDTH / 2;
	int bullet1PosY = planePosY + ImageConstants.BLUE_BULLET_HEIGHT;//�����ӵ�1λ��

	Bullet b1 = new Bullet(myPlane.getPlayingPanel(), BulletType.BLUE_BULLET);
	b1.setPosX(bullet1PosX);//�����ӵ�1λ��
	b1.setPosY(bullet1PosY);

	int bullet2PosX = planePosX + myPlane.getWidth() / 4 * 3 - ImageConstants.BLUE_BULLET_WIDTH / 2;
	int bullet2PosY = planePosY + ImageConstants.BLUE_BULLET_HEIGHT;//�����ӵ�2λ��

	Bullet b2 = new Bullet(myPlane.getPlayingPanel(), BulletType.BLUE_BULLET);
	b2.setPosX(bullet2PosX);//�����ӵ�2λ��
	b2.setPosY(bullet2PosY);

	bullets[0] = b1;
	bullets[1] = b2;

	return bullets;
    }
}
