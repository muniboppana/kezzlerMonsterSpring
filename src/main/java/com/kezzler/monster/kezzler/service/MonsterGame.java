package com.kezzler.monster.kezzler.service;

import com.kezzler.vo.Monster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MonsterGame {

    @Autowired
    MonsterHelper monsterHelper;

    public void startMonsterGame(List<Monster> monsterList) {

        System.out.println("Hello Monster Game started ");
        System.out.println(monsterList);
        boolean isResult = true;
        do {

            if (monsterList.size() > 1) {
                monsterHelper.initialFooddistributedForMonsters(monsterList);
                List<Monster> ripMonsters = new ArrayList<Monster>();
                for (final Monster monster : monsterList) {
                    monster.setMonsterWakeUp(true);
                    //checking the Monster food stolen or not from Others
                    monsterHelper.getStolenFoodFromSleepingMonsters(monsterList, monster);
                    if (monster.getCalories() > 0) {
                        new MonsterService(monster).start();
                    }
                    //RIP Monster List
                    if (monster.getCalories() == 0) {
                        System.out.println(monster.getMonsterName() + "  RIP  ");
                        ripMonsters.add(monster);
                    }
                }

                if (ripMonsters.size() > 0) {
                    monsterList.removeAll(ripMonsters);
                }

            }
            if (monsterList.size() == 1) {
                System.out.println("The winner is " + monsterList.get(0).getMonsterName() + " with  " + monsterList.get(0).getCalories() + "  left ");
                isResult = false;
            }
        } while (isResult);


    }
}
