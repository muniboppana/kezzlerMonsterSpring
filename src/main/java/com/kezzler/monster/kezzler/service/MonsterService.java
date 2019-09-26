package com.kezzler.monster.kezzler.service;

import com.kezzler.constants.MonsterConstants;
import com.kezzler.vo.Monster;

public class MonsterService  extends Thread {

    private Monster monster;

    MonsterService(Monster monster) {
        this.monster = monster;
    }


    public synchronized void run() {
        try {
            if (this.monster.getCalories() >0 && this.monster.isMonsterWakeUp() ) {

                if (this.monster.isFoodStolen()) {
                    System.out.println(this.monster.getMonsterName() + "    stole  food from    " + this.monster.getFoodGivenMonsterName());
                    if (this.monster.isStolenPacketFoodPoisned()) {
                        this.monster.setCalories(this.monster.getCalories() + this.monster.getRandomPacketCalories() - this.monster.getStolenFoodCalories() - MonsterConstants.burnCalories);
                        System.out.println("Oh no " + this.monster.getMonsterName() + " was poisoned     " + this.monster.getCalories() + "    calories left");
                    } else {
                        this.monster.setCalories(this.monster.getCalories() + this.monster.getRandomPacketCalories() + this.monster.getStolenFoodCalories() - MonsterConstants.burnCalories);
                    }
                } else {
                    this.monster.setCalories(this.monster.getCalories() + this.monster.getRandomPacketCalories() - MonsterConstants.burnCalories);
                   // System.out.println(this.monster.getMonsterName() + "   not stolen  food from  any monster " + this.monster.getCalories() + "  left");
                }

            }
        }catch(Exception exp) {
            System.out.println("Thread is running ");
        } finally  {
            this.monster.setMonsterWakeUp(false);
            this.monster.setFoodServed(true);
        }
    }


}
