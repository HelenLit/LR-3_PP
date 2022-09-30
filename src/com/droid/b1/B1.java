package com.droid.b1;
import java.util.Random;
import com.droid.Droid;
import com.weapon.Weapon;
import com.weapon.blaster.Blaster;
import com.weapon.superblaster.SuperBlaster;
import com.colors.Color;

//20 окс
//29 мкс


public class B1 extends Droid {
    private Random rand;
    {
        rand = new Random();
    }
    public B1(String name){
        super(name);
        setHealth(MAXHEALTH - rand.nextInt(MAXHEALTH/10));
        setArmour(rand.nextInt(5, 20));
        setWeapon(new Blaster());
    }

    public B1(String name, int health, int armour) {
        super(name, health, armour, new Blaster());
        this.name = name;
    }

    @Override
    public void upGradeWeapon() {
        if(!weapon.maxPowered()){
            System.out.println(Color.BLUE+"Урон " + weapon +" дроїда " + getName() + " збільшився на "+weapon.upGrade());
            System.out.print(Color.WHITE_BACKGROUND+""+Color.BLACK);
        }
        else if(weapon.getClass() != SuperBlaster.class) {
            setWeapon(new SuperBlaster());
            System.out.println(Color.BLUE+"Дроїд "+ getName() +" здобув нову зброю: "+ weapon +", з уроном: "+weapon.getDamage());
            System.out.print(Color.WHITE_BACKGROUND+""+Color.BLACK);
        }
    }
    @Override
    public void upGradeArmour() {
        if(getArmour() == MAXARMOUR) return;
        System.out.println(Color.BLUE + "Броня дроїда " + getName() + " збільшилась на " +
                upGradeArmour(rand.nextInt(Droid.MAXARMOUR/5)));
        System.out.print(Color.WHITE_BACKGROUND+""+Color.BLACK);
    }

    @Override
    public int useWeapon() {
        int harm = super.useWeapon();
        return harm;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(super.toString());
        return res.toString();
    }


}
