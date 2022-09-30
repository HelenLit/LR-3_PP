package com.droid.b2;

import com.colors.Color;
import com.droid.Droid;
import com.weapon.Weapon;
import com.weapon.superblaster.SuperBlaster;

import java.util.Random;

public class B2 extends Droid {
    private Random rand;
    {
        rand = new Random();
    }
    public B2(String name){
        super(name);
        setHealth(MAXHEALTH - rand.nextInt(MAXHEALTH/10));
        setArmour(rand.nextInt(10, 30));
        setWeapon(new SuperBlaster());
    }

    public B2(String name, int health, int armour) {
        super(name, health, armour, new SuperBlaster());
    }
    @Override
    public void upGradeArmour() {
        if(getArmour() == MAXARMOUR) return;
        System.out.println(Color.PURPLE + "Броня дроїда " + getName() + " збільшилась на " +
                upGradeArmour(rand.nextInt(Droid.MAXARMOUR/3)));
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
}
