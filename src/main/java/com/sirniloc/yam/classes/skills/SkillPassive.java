package com.sirniloc.yam.classes.skills;

import com.sirniloc.yam.character.capability.YAM;

public abstract class SkillPassive implements ISkill{

	String name;	
	
	ISkill[] nextSkills;
	
	int skillLevelMax,levelReq;

	private int frequency;

	private boolean ticker=true;
	
	private int ticks=0;
	
	private int level;
	
	public SkillPassive(String n, int ticks, int levelReq, int levelMax) {
		this.name=n;
		this.frequency=ticks;
		this.skillLevelMax=levelMax;
		this.levelReq=levelReq;
		if(this.frequency < 0) this.ticker=false;
	}
	
	public void doTick(YAM caster) {
		ticks++;
		if(ticks>=frequency) {
			doSkillStuff(caster);
			ticks=0;
		}
	}
	
	public boolean isTickSkill() {
		return ticker;
	}
	
	public int getFrequency() {
		return this.frequency;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
