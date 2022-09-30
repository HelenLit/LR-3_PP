package com.droid;

import com.colors.Color;
import com.droid.b1.B1;
import com.droid.b2.B2;
import com.droid.meddroid.MedDroid;
import com.droid.rocketshooter.RocketShooter;
import com.weapon.Weapon;

public abstract class Droid {
    public static Droid getModel(String model, String name, int health, int armour){
        if(model.compareToIgnoreCase("B2") == 0) return new B2(name, health, armour);
        if(model.compareToIgnoreCase("MedDroid") == 0) return new MedDroid(name, health, armour);
        if(model.compareToIgnoreCase("RocketShooter") == 0) return new RocketShooter(name, health, armour);
        return new B1(name, health, armour);
    }
    public static final int MAXHEALTH = 100;
    public static final int MAXARMOUR = 30;
    protected Weapon weapon;
    protected int health;
    protected int armour;
    protected String name;
    public Droid(String name){
        this.name = name;
    }
    public Droid(String name, int health, int armour, Weapon weapon) {
        this(name);
        this.health = health > MAXHEALTH ? MAXHEALTH : health;
        this.armour = armour > MAXARMOUR ? MAXARMOUR : armour;
        this.weapon = weapon;
    }

    public void setHealth(int health) {
        this.health = health > MAXHEALTH ? MAXHEALTH : health;
    }

    public void setArmour(int armour) {
        this.armour = armour > MAXARMOUR ? MAXARMOUR : armour;;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return weapon.getDamage();
    }

    public int getArmour() {
        return armour;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public boolean isAlive(){
        return health > 0;
    }
    public int upGradeHealth(int add){
        health += health +add > MAXHEALTH ? add = MAXHEALTH-health : add;
        return add;
    }
    public int upGradeArmour(int add){
        armour += armour + add > MAXARMOUR ? add=MAXARMOUR-armour : add;
        return add;
    }
    public abstract void upGradeArmour();
    public abstract void upGradeWeapon();
    public int useWeapon(){
        int harm = getDamage();
        System.out.println((Color.RED + getName() + " наносить урон: " + harm));
        System.out.print(Color.WHITE_BACKGROUND+""+Color.BLACK);
        getWeapon().incr();
        return harm;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Дроїд " + getName() +" (" +this.getClass().getSimpleName()+")" + " :");
        if(isAlive())
            res.append("\n\tЗдров'я: " + getHealth() +
                    "\n\tБроня: " + getArmour() + "\n\tЗброя: "+ getWeapon() + "\n\tУрон: " + getDamage());
        else {
            res.append(Color.RED + "\tdead");
            res.append(Color.WHITE_BACKGROUND+""+Color.BLACK);
        }
        return res.toString();
    }
    public void getHarm(int harm) {
        System.out.println(Color.RED + "У " + name  + " " + ((Droid)this).upGradeHealth(getArmour()-harm) + " здоров\'я");
        System.out.print(Color.WHITE_BACKGROUND+""+Color.BLACK);
    }

    public void upGrade(){
        upGradeArmour();
        upGradeWeapon();
    }
    public String getName(){
        return name;
        }
}
