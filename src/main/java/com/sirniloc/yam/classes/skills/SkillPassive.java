package com.sirniloc.yam.classes.skills;

public abstract class SkillPassive implements ISkill{

	String name;	
	
	ISkill[] nextSkills;
	
	int skillLevelMax,levelReq;

	private int frequency;

	private boolean ticker=true;
	
	public SkillPassive(String n, int ticks, int levelReq, int levelMax) {
		this.name=n;
		this.frequency=ticks;
		this.skillLevelMax=levelMax;
		this.levelReq=levelReq;
		if(this.frequency < 0) this.ticker=false;
	}
	
	public boolean tickSkill() {
		return ticker;
	}
	
	public int getFrequency() {
		return this.frequency;
	}

}
