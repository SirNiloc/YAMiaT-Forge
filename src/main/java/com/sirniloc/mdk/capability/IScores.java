package com.sirniloc.mdk.capability;

public interface IScores {

	int getMind();
	int getBody();
	int getSpirit();

	void syncSpawn();
	void syncChange();
	void syncStatCheck();
	
}
