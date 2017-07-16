package com.beancore.listener;

import com.beancore.entity.Bullet;

public interface BulletListener {
    void onBulletLocationChanged(Bullet b);//子弹位置改变监听器
}
