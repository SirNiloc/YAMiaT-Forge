package com.sirniloc.yam.character.capability.interfaces;

import com.sirniloc.yam.character.capability.YAM;
import com.sirniloc.yam.character.race.Race;

import net.minecraft.entity.EntityLivingBase;

public interface IYam {

	int getTotalMind();
	int getTotalBody();
	int getTotalSpirit();
	
	int getMind();
	int getBody();
	int getSpirit();
	
	YAM getYAM();
	void cloneABS(YAM abs);
	
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
	void addExp(double d);
	void removeAttacker(int j);
	
	

}
