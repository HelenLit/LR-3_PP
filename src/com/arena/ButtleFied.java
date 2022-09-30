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
        System.out.println(Color.YELLOW_BACKGROUND+"\t*** �������� ����� ***\t");
        System.out.print(Color.WHITE_BACKGROUND+""+Color.BLACK);
        firstArmy.fill();
        secondArmy.fill();
    }
    public void outPutArmies(){
        System.out.println("\n"+Color.YELLOW_BACKGROUND+""+Color.BLACK+"\t*** ����� ���� ***\t");
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
            System.out.println(droid.getName() + " ������ ���.");
            return true;
        }
        return false;
    }
    protected void battle(Army army1, Army army2){
        if(!army1.hasAlive() || army1.qsize()==0 || !army2.hasAlive() || army2.qsize()==0) return;
        Scanner scan = new Scanner(System.in);
        Droid fighter, enemy;
        while (!(fighter = army1.poll()).isAlive());
        System.out.print("\n"+army1.getName() + " : ���� " + fighter.getName());
        boolean killed = false;
        if(fighter.getClass() == RocketShooter.class){
            Random rand = new Random();
            Droid droid;
            int count = rand.nextInt(1, army2.getAliveAmount() == 1 ? 2 : army2.getAliveAmount());
            do{
                for (int i = 0; !(droid = army2.getWithIndex(i)).isAlive(); i++);
                System.out.println(Color.PURPLE + "\n������ "+ fighter.getName() + " �������� ���� " + droid.getName());
                System.out.print(Color.WHITE_BACKGROUND+""+Color.BLACK);
                droid.getHarm(fighter.useWeapon());
                killed = killedMessage(droid, army2);
            }while (--count > 0 && army2.hasAlive());
        }else{
            System.out.print("\n�������: ");
            enemy = army2.getWithIndex(inputIndex(army2));
            System.out.println(Color.PURPLE + fighter.getName() + " ����� " + enemy.getName());
            System.out.print(Color.WHITE_BACKGROUND+""+Color.BLACK);
            enemy.getHarm(fighter.useWeapon());
            killed = killedMessage(enemy, army2);
        }
        if(fighter.getClass() == MedDroid.class){
            System.out.println("���� meddroid " + fighter.getName() + " �����? ");
            ((MedDroid)fighter).heal(army1.getWithIndex(inputIndex(army1)));
        }
        if(killed){
            System.out.println("\n"+fighter.getName() + " ���������.");
            fighter.upGrade();
        }
    }

    public Army fight() {
        System.out.println("\n��� ���� ���������� ������? ( 1 - " + firstArmy.getName() + ", 2 - " + secondArmy.getName() + ")");
        Scanner scan = new Scanner(System.in);
        int answ;
        while((answ = scan.nextInt()) != 1 && answ != 2);
        if(answ == 2) {
            Army tmp = firstArmy;
            firstArmy = secondArmy;
            secondArmy = tmp;
        }
        System.out.println("\t! ��� ����� ��������: -1 (��������) / ����� ����� !\n"+
                "\n���� � �������? (1 - ���, � �������; 2 - �, � ����): ");
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
                System.out.println("������� ��������.\n");
                e.printStackTrace();
            }
        }
        System.out.println(Color.RED_BACKGROUND+""+Color.WHITE+"\t*** ��� ��������� ***\t");
        System.out.print(Color.WHITE_BACKGROUND+""+Color.BLACK);
        int raunds = 0;
        while (firstArmy.hasAlive() && secondArmy.hasAlive()){
            outPutArmies();
            System.out.println(Color.BLUE_BACKGROUND + ""+Color.WHITE+"\t����� �" + ++raunds);
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
        System.out.println(Color.RED_BACKGROUND+""+Color.WHITE+"\t*** ��� �������� ***\t");
        System.out.print(Color.WHITE_BACKGROUND+""+Color.BLACK);
        Army winner;
        if(firstArmy.hasAlive())
           winner = firstArmy;
        else winner = secondArmy;
        if (intoFile){
            System.out.printf(Color.YELLOW +"" +Color.BLUE_BACKGROUND+ "\n\t-=* ���������� *=-\n\t    "+winner.getName());
            System.out.flush();
            System.setOut(standart);
        }
        return winner;
    }
}
