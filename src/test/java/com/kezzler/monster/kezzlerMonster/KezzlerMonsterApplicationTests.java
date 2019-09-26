package com.kezzler.monster.kezzlerMonster;

import com.kezzler.constants.MonsterConstants;
import com.kezzler.monster.kezzler.service.MonsterGame;
import com.kezzler.monster.kezzler.service.MonsterHelper;
import com.kezzler.vo.Monster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KezzlerMonsterApplicationTests {

	@Autowired
	MonsterGame monsterGame;
	@Autowired
	MonsterHelper monsterHelper;
	List<Monster> monsterList ;


	private static List<Monster> populateInitialMonsters(int monsterSize) {
		List<Monster> monsterList = new ArrayList<Monster>();
		for (int i = 0; i < monsterSize; i++) {
			monsterList.add(new Monster("Monster " + i ,  MonsterConstants.calories) );
		}
		return monsterList;
	}

	/**
	 * Five Monsters
	 */
	@Test
	public void testStartMonsterGame() {
		monsterList = populateInitialMonsters(MonsterConstants.monsterSize);
		monsterGame.startMonsterGame(monsterList);

	}

	/**
	 * Two Monsters
	 */
	@Test
	public void testMonsterGameWithTwoMonsters() {
		monsterList = populateInitialMonsters(2);
		monsterGame.startMonsterGame(monsterList);

	}

	/**
	 * Single Monster
	 */
	@Test
	public void testMonsterGameWithSingleMonsters() {
		monsterList = populateInitialMonsters(1);
		monsterGame.startMonsterGame(monsterList);

	}

	@Test
	public void testMonsterGameWithThreeMonsters() {
		monsterList = populateInitialMonsters(3);
		monsterGame.startMonsterGame(monsterList);

	}


}
