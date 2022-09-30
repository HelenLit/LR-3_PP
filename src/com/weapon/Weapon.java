package com.weapon;

import com.droid.Droid;

public abstract class Weapon {
    protected int damage;
    public static final int MAXDAMAGE = Droid.MAXHEALTH;
    protected int maxPower;
    public Weapon(){}

    public boolean incr() {
        if(damage < MAXDAMAGE - 2){
            damage += 2;
            return true;
        }
        else return false;
    }
    public int useWeapon(){ return damage; }
    public int upGrade(int add){
        damage += add;
        return add;
    }
    public abstract int upGrade();
    public int getDamage() {
        return damage;
    }
    public boolean maxPowered(){
        return damage >= maxPower;
    }
}
