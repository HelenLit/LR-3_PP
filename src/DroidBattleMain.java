import com.arena.ButtleFied;
import com.army.Army;
import com.colors.Color;

import java.io.*;
import java.util.Scanner;

public class DroidBattleMain {
     private static void outputFromFile(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("fight.json"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }catch (FileNotFoundException e){
            System.out.println("������� ��������.\n");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("������� ��������.\n");
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {

        int i = 0;
        String[] armyNames = new String[2];
        Scanner scan = new Scanner(System.in);
        int[] amounts = new int[2];
        while(++i<3){
            System.out.printf("\n������ ����� ��� ������� �%d: ",i);
            while((armyNames[i-1] = scan.nextLine()).isBlank());
            System.out.print("������ ����� ���� � " + armyNames[i-1] + " ? ");
            while((amounts[i-1] = scan.nextInt()) <= 0);
        }
        ButtleFied buttleFied = new ButtleFied(amounts[0],amounts[1],armyNames[0],armyNames[1]);
        buttleFied.fillArmies();
        Army winner = buttleFied.fight();
        System.out.println("\n"+Color.YELLOW+""+Color.BLUE_BACKGROUND+"\t-=* ���������� *=-\n\t\t"+winner.getName());
        System.out.print(Color.WHITE_BACKGROUND+""+Color.BLACK+winner);
        System.out.println("������� ����� ���? 1 - ���, 2 - � ");
        int answ;
        while((answ = scan.nextInt()) != 1 && answ != 2);
        if(answ == 1)
            outputFromFile();
    }

}