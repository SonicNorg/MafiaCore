import java.io.BufferedReader;
import java.io.InputStreamReader;


public class MafiaTest {

    public static void main(String[] args) throws  Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        MafiaCore mafiaCore = new MafiaCore();
        System.err.println("Раздача карт...");
        for (int i = 0; i<10; i++) {
            System.out.print("Роль " + (i+1) + "-го игрока: ");
            //mafiaCore.initPlayer(i, true, br.readLine(), Integer.parseInt(br.readLine()));
            int role = 0;
            if (i<3) role = i+20;
            else if (i==3) role = 22;
            else role = 23;
            mafiaCore.initPlayer(i, true, "", role);
            System.out.print(role);
        }
        System.err.println("Доброе утро, город!..");

        mafiaCore.startDay();

        mafiaCore.nextTurn(60);
        System.err.println(mafiaCore.getPhase());
        Thread.sleep(4000);
        mafiaCore.nominate(6);
        Thread.sleep(4000);
        System.err.println(mafiaCore.getPhase());
        System.err.println(mafiaCore.getNominations(mafiaCore.getDay(), 0));
        Thread.sleep(4000);
        System.err.println(mafiaCore.getPhase());
    }
}
