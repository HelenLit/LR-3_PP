package com.arena;

import com.army.Army;
import com.colors.Color;
import com.droid.Droid;
import com.droid.meddroid.MedDroid;
import com.droid.rocketshooter.RocketShooter;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class ButtleFied {
    private final PrintStream standart = System.out;
    private PrintStream fileStream;
    protected Army firstArmy;
    protected Army secondArmy;
    private boolean intoFile;
    public ButtleFied( int firstAmount, int secondAmount, String nameOfFirst, String nameOfSecond){
        if(firstAmount<=0 || secondAmount <=0 )
            throw new IllegalArgumentException();
        firstArmy = new Army(firstAmount,nameOfFirst);
        secondArmy = new Army(secondAmount,nameOfSecond);
    }
    public void fillArmies(){
        System.out.println(Color.YELLOW_BACKGROUND+"\t*** Введення даних ***\t");
        System.out.print(Color.WHITE_BACKGROUND+""+Color.BLACK);
        firstArmy.fill();
        secondArmy.fill();
    }
    public void outPutArmies(){
        System.out.println("\n"+Color.YELLOW_BACKGROUND+""+Color.BLACK+"\t*** Стани армій ***\t");
        System.out.print(Color.WHITE_BACKGROUND+""+firstArmy +secondArmy);
    }
    private int inputIndex(Army army){
        Scanner scan = new Scanner(System.in);
        int index = -1;
        if(!intoFile)
            while((index = scan.nextInt())<-1);
        if((index--) == -1) {
            Random rand = new Random();
            do{
                index = rand.nextInt(army.size());
            }while (!(army.getWithIndex(index)).isAlive());
        }
        return index;
    }
    private boolean killedMessage(Droid droid, Army army){
        if(!droid.isAlive()){
            army.minusOne();
            System.out.println(droid.getName() + " покидає гру.");
            return true;
        }
        return false;
    }
    protected void battle(Army army1, Army army2){
        if(!army1.hasAlive() || army1.qsize()==0 || !army2.hasAlive() || army2.qsize()==0) return;
        Scanner scan = new Scanner(System.in);
        Droid fighter, enemy;
        while (!(fighter = army1.poll()).isAlive());
        System.out.print("\n"+army1.getName() + " : дроїд " + fighter.getName());
        boolean killed = false;
        if(fighter.getClass() == RocketShooter.class){
            Random rand = new Random();
            Droid droid;
            int count = rand.nextInt(1, army2.getAliveAmount() == 1 ? 2 : army2.getAliveAmount());
            do{
                for (int i = 0; !(droid = army2.getWithIndex(i)).isAlive(); i++);
                System.out.println(Color.PURPLE + "\nРакета "+ fighter.getName() + " наносить урон " + droid.getName());
                System.out.print(Color.WHITE_BACKGROUND+""+Color.BLACK);
                droid.getHarm(fighter.useWeapon());
                killed = killedMessage(droid, army2);
            }while (--count > 0 && army2.hasAlive());
        }else{
            System.out.print("\nОпонент: ");
            enemy = army2.getWithIndex(inputIndex(army2));
            System.out.println(Color.PURPLE + fighter.getName() + " атакує " + enemy.getName());
            System.out.print(Color.WHITE_BACKGROUND+""+Color.BLACK);
            enemy.getHarm(fighter.useWeapon());
            killed = killedMessage(enemy, army2);
        }
        if(fighter.getClass() == MedDroid.class){
            System.out.println("Кого meddroid " + fighter.getName() + " полікує? ");
            ((MedDroid)fighter).heal(army1.getWithIndex(inputIndex(army1)));
        }
        if(killed){
            System.out.println("\n"+fighter.getName() + " покращено.");
            fighter.upGrade();
        }
    }

    public Army fight() {
        System.out.println("\nЯка армія нападатиме першою? ( 1 - " + firstArmy.getName() + ", 2 - " + secondArmy.getName() + ")");
        Scanner scan = new Scanner(System.in);
        int answ;
        while((answ = scan.nextInt()) != 1 && answ != 2);
        if(answ == 2) {
            Army tmp = firstArmy;
            firstArmy = secondArmy;
            secondArmy = tmp;
        }
        System.out.println("\t! При виборі опонента: -1 (рандомно) / номер дроїда !\n"+
                "\nВивід у консоль? (1 - так, у консоль; 2 - ні, у файл): ");
        while((answ = scan.nextInt()) != 1 && answ != 2);
        if(answ == 2) {
            intoFile = true;
            try {
                File myFile = new File("D:\\Projects\\Java\\LR-3_PP\\fight.json");
                if (!myFile.createNewFile()) {
                    myFile.delete();
                    myFile.createNewFile();
                }
                System.setOut(fileStream = new PrintStream(
                        new BufferedOutputStream(
                                new FileOutputStream(myFile))));
            } catch (IOException e) {
                System.out.println("Помилка знайдена.\n");
                e.printStackTrace();
            }
        }
        System.out.println(Color.RED_BACKGROUND+""+Color.WHITE+"\t*** Бій розпочато ***\t");
        System.out.print(Color.WHITE_BACKGROUND+""+Color.BLACK);
        int raunds = 0;
        while (firstArmy.hasAlive() && secondArmy.hasAlive()){
            outPutArmies();
            System.out.println(Color.BLUE_BACKGROUND + ""+Color.WHITE+"\tРаунд №" + ++raunds);
            System.out.print(Color.WHITE_BACKGROUND+""+Color.BLACK);
            intoFile = true;
            firstArmy.createQueue();
            secondArmy.createQueue();
            while (firstArmy.qsize() > 0 && secondArmy.qsize() > 0){
                if(firstArmy.hasAlive()){
                    battle(firstArmy,secondArmy);
                }
                if(secondArmy.hasAlive()){
                    battle(secondArmy,firstArmy);
                }
            }
        }
        System.out.println(Color.RED_BACKGROUND+""+Color.WHITE+"\t*** Бій закінчено ***\t");
        System.out.print(Color.WHITE_BACKGROUND+""+Color.BLACK);
        Army winner;
        if(firstArmy.hasAlive())
           winner = firstArmy;
        else winner = secondArmy;
        if (intoFile){
            System.out.printf(Color.YELLOW +"" +Color.BLUE_BACKGROUND+ "\n\t-=* Переможець *=-\n\t    "+winner.getName());
            System.out.flush();
            System.setOut(standart);
        }
        return winner;
    }
}
