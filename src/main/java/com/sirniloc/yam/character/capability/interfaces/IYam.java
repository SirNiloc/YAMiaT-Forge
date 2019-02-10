package com.sirniloc.yam.character.capability.interfaces;

import com.sirniloc.yam.character.Race;
import com.sirniloc.yam.character.capability.CharacterYAM;

import net.minecraft.entity.EntityLivingBase;

public interface IYam {

	int getTotalMind();
	int getTotalBody();
	int getTotalSpirit();
	
	int getMind();
	int getBody();
	int getSpirit();
	
	CharacterYAM getABS();
	void cloneABS(CharacterYAM abs);
	
	void setMind(double i);
	void setBody(double i);
	void setSpirit(double i);
	

	void syncSpawn();
	void syncChange();
	void syncStatCheck();
	
	Race getRace();
	int getRaceInt();
	void setRaceInt(int i);
	
	void setExpDouble(double double1);
	void setLevelInt(int integer);
	
	double getExp();
	int getLevel();
	
	double getNextLevelExpCost();
	
	void addAttacker(EntityLivingBase attacker);
	EntityLivingBase[] getRecentAttackers();
	float[] getRecentAttTime();
	void update();
	void removeAttacker(int i);
	void deathStuff();
	void addExp(double d);
	
	

}
