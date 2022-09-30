package com.army;

import com.droid.Droid;
import com.droid.b2.B2;
import com.droid.b1.B1;
import com.droid.meddroid.MedDroid;
import com.droid.rocketshooter.RocketShooter;

import java.util.*;

public class Army {
    protected Droid[] army;
    private Queue<Droid> armyQueue;
    private int aliveAmount;

    private String name;

    public Army(int aliveAmount, String name) {
        if(aliveAmount<=0)
            throw new IllegalArgumentException();
        this.name = name;
        this.aliveAmount = aliveAmount;
        army = new Droid[aliveAmount];
    }

//    protected Droid getDroid(String model,String name, int health,int armour){
//        if(model.compareToIgnoreCase("В2") == 0) return new B2(name, health, armour);
//        if(model.compareToIgnoreCase("MedDroid") == 0) return new MedDroid(name, health, armour);
//        if(model.compareToIgnoreCase("RocketShooter") == 0) return new RocketShooter(name, health, armour);
//        return new B1(name, health, armour);
//    }
    public void fill(){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n"+this.getName() + ":\nВведення вручну чи випадкове?(1 - вручну, 2 - випадкове): ");
        int answ;
        String[] names = new String[aliveAmount];
        do {
            answ = scan.nextInt();
        }while ((answ != 1) && (answ != 2));
        System.out.println("\t*** Введення імен дроїдів для армії " + getName() + " ***");
        int i = 0;
        do{
            System.out.print("\nІм'я для дроїда №" + (i+1)+": ");
            while((names[i] = scan.nextLine().trim()).isBlank());
        }while (++i < aliveAmount);
        if(answ == 1) {
            System.out.println("""
                    \nДоступні типи дроїдів: В1(blaster), В2(superblaster), MedDroid(simple weapon), RocketShooter(rocket launcher)
                    Введіть для кожного дроїда його тип, кількість здоров'я та якість броні:""");
            i = 0;
            do {

                System.out.print("\nTип, здоров'я та бронь для " + names[i] + ": ");
                String input = scan.nextLine();
                army[i] = Droid.getModel(
                        input.substring(0,input.indexOf(" ")),
                        names[i],
                        Integer.parseInt(input.substring(input.indexOf(" ")+1,input.lastIndexOf(" "))),
                        Integer.parseInt(input.substring(input.lastIndexOf(" ")+1))
                        );
            } while (++i < aliveAmount);
        }
        else {  //answ == 2
            i = 0;
            do {
                Random rand = new Random();
                int type = rand.nextInt(0,4);
                army[i] = switch (type) {
                    case 1 -> new B2(names[i]);
                    case 2 -> new MedDroid(names[i]);
                    case 3 -> new RocketShooter(names[i]);
                    default -> new B1(names[i]);
                };
            } while (++i < aliveAmount);
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("\n---- Армія " + getName()+"("+army.length+" дроїдів):\n");
        for (int i = 0; i < army.length; i++) {
            str.append("\n#" + (i+1) + ": " +army[i]+"\n");
        }
        return str.toString();
    }
    public Droid[] getArmy() {
        return army;
    }
    public int minusOne(){
        if(armyQueue != null) armyQueue.poll();
        return --aliveAmount;
    }
    public int getAliveAmount() {
        return aliveAmount;
    }
    public String getName() {
        return name;
    }
    public boolean hasAlive(){
        return aliveAmount > 0;
    }
    public void createQueue(){
       armyQueue = new ArrayDeque<Droid>(Arrays.stream(army).filter(droid -> droid.isAlive()).toList());
    }
    public Droid poll(){
        return armyQueue.poll();
    }
    public int qsize(){
        if(armyQueue == null || armyQueue.stream().noneMatch(Droid::isAlive)) return 0;
        return armyQueue.size();
    }
    public Droid getWithIndex(int index){
        if( index < 0 || index > army.length) return null;
        return army[index];
    }
    public int size(){
        return army.length;
    }
}

