package com.kezzler.monster.kezzlerMonster;

import com.kezzler.constants.MonsterConstants;
import com.kezzler.monster.kezzler.service.MonsterGame;
import com.kezzler.vo.Monster;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackages="com.kezzler")

public class KezzlerMonsterApplication  {


	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(KezzlerMonsterApplication.class, args);
		MonsterGame monsterGame =  ctx.getBean(MonsterGame.class);
		List<Monster> monsterList  = populateInitialMonsters();
		monsterGame.startMonsterGame(monsterList);
	}

	private static List<Monster> populateInitialMonsters() {
			List<Monster> monsterList = new ArrayList<Monster>();
			for (int i = 0; i < MonsterConstants.monsterSize; i++) {
				monsterList.add(new Monster("Monster " + i ,  MonsterConstants.calories) );
			}
			return monsterList;
		}

	}



