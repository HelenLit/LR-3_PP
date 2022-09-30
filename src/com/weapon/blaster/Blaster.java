package com.weapon.blaster;

import com.weapon.Weapon;
public class Blaster extends Weapon {

    public Blaster(){
        damage = 20;
        maxPower = 30;
    }

    @Override
    public String toString() {
        return "Blaster";
    }
    public int upGrade() {
        return super.upGrade(10);
    }
}
