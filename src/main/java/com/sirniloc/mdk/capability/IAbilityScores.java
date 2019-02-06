package com.sirniloc.mdk.capability;

import com.sirniloc.mdk.character.Race;

public interface IAbilityScores {

	int getTotalMind();
	int getTotalBody();
	int getTotalSpirit();
	
	int getMind();
	int getBody();
	int getSpirit();
	
	ABS getABS();
	void cloneABS(ABS abs);
	
	void setMind(int i);
	void setBody(int i);
	void setSpirit(int i);
	

	void syncSpawn();
	void syncChange();
	void syncStatCheck();
	
	Race getRace();
	int getRaceInt();
	

}