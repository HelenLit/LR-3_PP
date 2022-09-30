package com.weapon.simple;

import com.weapon.Weapon;

public class SimpleWeapon extends Weapon {
    public SimpleWeapon(){
        damage = 15;
        maxPower = 25;
    }

    @Override
    public String toString() {
        return "Simple Weapon";
    }
    public int upGrade() {
        if(maxPowered()) return 0;
        return super.upGrade(5);
    }
}
