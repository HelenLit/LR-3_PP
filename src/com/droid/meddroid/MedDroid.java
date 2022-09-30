package com.droid.meddroid;

import com.colors.Color;
import com.droid.Droid;
import com.weapon.Weapon;
import com.weapon.simple.SimpleWeapon;
import java.util.Random;

public class MedDroid extends Droid {
    protected String name;
    private Random rand;
    protected int healAbility;
    {
        rand = new Random();
        healAbility = 15;
    }
    public MedDroid(String name){
        super(name);
        setHealth(MAXHEALTH - rand.nextInt(MAXHEALTH/10));
        setArmour(rand.nextInt(17, 30));
        setWeapon(new SimpleWeapon());
    }

    public MedDroid(String name, int health, int armour) {
        super(name,health, armour, new SimpleWeapon());
    }
    public void heal(Droid droid){
        System.out.println(Color.GREEN + "Дроїд " + getName() + " лікує " + droid.getName() + " на " +
                droid.upGradeHealth(rand.nextInt(healAbility/3,healAbility)) + " очків здоров'я.");
        System.out.print(Color.WHITE_BACKGROUND+""+Color.BLACK);
    }
    @Override
    public void upGradeArmour() {
        if(getArmour() == MAXARMOUR) return;
        System.out.println(Color.PURPLE + "Броня дроїда " + getName() + " збільшилась на " +
                upGradeArmour(rand.nextInt(Droid.MAXARMOUR/2)));
        System.out.print(Color.WHITE_BACKGROUND+""+Color.BLACK);
    }

    @Override
    public void upGradeWeapon() {
        if(!weapon.maxPowered()){
            System.out.println(Color.BLUE+"Урон " + weapon +" дроїда " + getName() + " збільшився на "+weapon.upGrade());
            System.out.print(Color.WHITE_BACKGROUND+""+Color.BLACK);
        }
    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(super.toString());
        return res.toString();
    }

    @Override
    public void upGrade() {
        super.upGrade();
        healAbility += 5;
    }
}
