package com.weapon.superblaster;

import com.weapon.Weapon;

public class SuperBlaster extends Weapon {

    public SuperBlaster(){
        damage = 40;
        maxPower = 60;
    }

    @Override
    public String toString() {
        return "Super Blaster";
    }
    public int upGrade() {
        if(maxPowered()) return 0;
        return super.upGrade(10);
    }
}
