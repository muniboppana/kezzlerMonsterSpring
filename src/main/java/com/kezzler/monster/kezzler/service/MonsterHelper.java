package com.kezzler.monster.kezzler.service;

import com.kezzler.constants.MonsterConstants;
import com.kezzler.vo.Monster;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class MonsterHelper {

    static Random random = new Random();

    public  void getStolenFoodFromSleepingMonsters(List<Monster> monsterList, Monster stolenMonster) {

        if(monsterList.size()>1) {

            //cheking already food stolen one monster then no need to stolen any one second time
            boolean isFoodStolenAlready = false;
            for (Monster monster : monsterList) {
                if (monster.isFoodStolen()) {
                    isFoodStolenAlready = true;

                }
            }

            if (!isFoodStolenAlready) {
                //stole food from sleeping monsters
                List<Monster> sleePingMonsters = new ArrayList<Monster>();

                if (monsterList.size() > 2) {
                    for (Monster monster : monsterList) {
                        if (!monster.isMonsterWakeUp() && !monster.isFoodServed()) {
                            sleePingMonsters.add(monster);
                        }
                    }

                    Monster   foodGivingMonster = getRandomStolenMonster(sleePingMonsters);

                        for (Monster monster : monsterList) {
                            if (monster.getMonsterName().equalsIgnoreCase(foodGivingMonster.getMonsterName())) {
                                populatePacketFoodValue(monster,  stolenMonster);
                                break;
                            }
                        }

                        //updating total monster values with stolen info
                         updateMainMonsterDetails(monsterList, stolenMonster);

                    }  else  if(monsterList.size() ==2) {

                    for(Monster monster : monsterList) {
                        if(!monster.isMonsterWakeUp()) {
                            populatePacketFoodValue(monster,  stolenMonster);
                        }
                    }
                    //updating main monster list
                    updateMainMonsterDetails(monsterList, stolenMonster);

                 }  // end monster list is 2  it should take food from one monster
                }
            }

    }

    /**
     *
     * @param monsterList
     * @param stolenMonster
     */
    private void updateMainMonsterDetails(List<Monster> monsterList, Monster stolenMonster) {
        for (Monster monster : monsterList) {
            if (monster.getMonsterName().equalsIgnoreCase(stolenMonster.getMonsterName())) {
                monster.setStolenPacketFoodPoisned(stolenMonster.isStolenPacketFoodPoisned());
                monster.setFoodStolen(stolenMonster.isFoodStolen());
                monster.setStolenFoodCalories(stolenMonster.getStolenFoodCalories());
                monster.setFoodGivenMonsterName(stolenMonster.getFoodGivenMonsterName());
                break;
            }
        }
    }

    /**
     *
     * @param monster
     * @param stolenMonster
     */
    private void populatePacketFoodValue(Monster monster, Monster stolenMonster) {
          monster.setFoodGiven(true);
        if (monster.isStolenPacketFoodPoisned()) {
            stolenMonster.setStolenPacketFoodPoisned(true);
        } else {
            stolenMonster.setStolenPacketFoodPoisned(false);
        }
        stolenMonster.setFoodStolen(true);
        stolenMonster.setStolenFoodCalories(monster.getRandomPacketCalories());
        stolenMonster.setFoodGivenMonsterName(monster.getMonsterName());
    }

    /**
     *
     * @param sleePingMonsters
     * @return
     */
    private Monster getFoodGivingMonster(List<Monster> sleePingMonsters) {

        for(Monster monster : sleePingMonsters) {
            if(!monster.isMonsterWakeUp() && !monster.isFoodServed() ) {
                return monster;
            }
        }
        return null;
    }

    /**
     *
     * @param monsterList
     * @return
     */
    public  Monster getRandomStolenMonster(List<Monster> monsterList) {
        return getStolenFoodFromSleepingMonsterList(monsterList);

    }

    /**
     *
     * @param monsterList
     */
    public  void initialFooddistributedForMonsters(List<Monster> monsterList) {

        Monster staticPoisnedFoodPkt =  getstaticllyPoisonedFoodPacketFromList(monsterList);
        for(Monster monster: monsterList) {
            monster.setStolenPacketFoodPoisned(false);
            monster.setMonsterWakeUp(false);
            monster.setFoodStolen(false);
            monster.setFoodServed(false);
            monster.setFoodGiven(false);
            monster.setStolenFoodCalories(0);
            monster.setRandomPacketCalories(getRandomCaloriesFood());
            if(staticPoisnedFoodPkt.getMonsterName().equalsIgnoreCase(monster.getMonsterName())) {
                int position = noofPosionpacketsInList(monsterList.size());
                if (position > 0 ) {
                    monster.setStolenPacketFoodPoisned(true);
                }
            }
        }

    }

    public  int noofPosionpacketsInList(int monsterListSize) {
        return (int) Math.round (monsterListSize* MonsterConstants.poisnedFoodPackets);
    }

    public  Monster getstaticllyPoisonedFoodPacketFromList(List<Monster> monsterList) {
        int position= getRandomPosineAssignment(monsterList.size());
        return monsterList.get(position);
    }

    public  int getRandomPosineAssignment(int size) {
        return random.nextInt(size);
    }

    public  int getRandomCaloriesFood () {
        return random.nextInt(MonsterConstants.foodPacketRandomCalories);
    }

    public  Monster getStolenFoodFromSleepingMonsterList(List<Monster> monsterList) {
        int position= getRandomPosineAssignment(monsterList.size());
        return monsterList.get(position);
    }


}
