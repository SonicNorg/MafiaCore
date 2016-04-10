package com.norg.mafia.test;

import com.norg.mafia.engine.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MafiaTest {

    public static void main(String[] args) throws  Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Master vedushiy = new Master(
                new Game(
                        new ArrayList<Player>() {
                            {
                                Role role;
                                for (int i = 0; i < 10; i++) {
                                    System.out.print("Роль " + (i + 1) + "-го игрока: ");
                                    //mafiaCore.initPlayer(i, true, br.readLine(), Integer.parseInt(br.readLine()));

                                    if (i == 0) role = Role.DON;
                                    else if (i < 3) role = Role.MAFIA;
                                    else if (i == 3) role = Role.SHERIFF;
                                    else role = Role.CITIZEN;
                                    add(new Player(i, true, role));
                                    System.out.println(role);
                                }
                                role = null; //for GC
                            }}
                ), new InGameLog()
        );

        MafiaCore mafiaCore = new MafiaCore();

        System.out.println("Доброе утро, город!..");

        mafiaCore.startDay();

        mafiaCore.nextTurn(10); //10 sec speech
        System.out.println(mafiaCore.getPhase());
        Thread.sleep(3000);
        mafiaCore.nominate(6);
        Thread.sleep(3000);
        System.out.println(mafiaCore.getPhase());
        System.out.println(mafiaCore.getNominations(mafiaCore.getDay(), 0));
        Thread.sleep(5000);
//        while (mafiaCore.getPhase() == GamePhase.SPEECH);
        System.out.println(mafiaCore.getPhase());
        for (int i=0; i<11; i++) {
            mafiaCore.nextTurn(10);
            Thread.sleep(2000);
//            mafiaCore.endSpeech();
            System.out.println(mafiaCore.getPhase());
        }
    }
}
