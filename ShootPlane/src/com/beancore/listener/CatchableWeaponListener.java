package com.beancore.listener;

import com.beancore.entity.CatchableWeapon;

public interface CatchableWeaponListener {
    public void onCatchableWeaponLocationChanged(CatchableWeapon weapon);
    //补给武器位置改变监听器
}
