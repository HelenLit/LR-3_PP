package com.droid.rocketshooter;

import com.colors.Color;
import com.droid.Droid;
import com.weapon.Weapon;
import com.weapon.rocketlauncher.RocketLauncher;
import java.util.Random;

public class RocketShooter extends Droid {
    private Random rand;
    {
        rand = new Random();
    }
    public RocketShooter(String name){
        super(name);
        setHealth(MAXHEALTH - rand.nextInt(MAXHEALTH/10));
        setArmour(rand.nextInt(10, 40));
        setWeapon(new RocketLauncher());
    }
    public RocketShooter(String name, int health, int armour) {
        super(name,health, armour, new RocketLauncher());
    }
    @Override
    public void upGradeArmour() {
        if(getArmour() == MAXARMOUR) return;
        System.out.println(Color.PURPLE + "Броня дроїда " + getName() + " збільшилась на " +
                upGradeArmour(rand.nextInt(Droid.MAXARMOUR/5)));
        System.out.print(Color.WHITE_BACKGROUND+""+Color.BLACK);
    }

    @Override
    public void upGradeWeapon() { }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(super.toString());
        return res.toString();
    }
}
