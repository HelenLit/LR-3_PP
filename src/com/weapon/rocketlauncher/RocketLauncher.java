package com.weapon.rocketlauncher;
import com.droid.Droid;
import com.weapon.Weapon;

import java.util.Random;

public class RocketLauncher extends Weapon {

    @Override
    public int useWeapon() {
        Random random = new Random();
        return random.nextInt(Weapon.MAXDAMAGE);
    }

    @Override
    public int upGrade() {
        return 0;
    }

    @Override
    public String toString() {
        return "Rocket Launcher";
    }
}
