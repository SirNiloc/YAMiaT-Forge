package com.sirniloc.mdk.capability;

public interface IAbilityScores {

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
	

}
