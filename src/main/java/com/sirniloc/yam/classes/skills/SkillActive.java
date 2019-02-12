package com.sirniloc.yam.classes.skills;

import com.sirniloc.yam.character.capability.YAM;

public abstract class SkillActive implements ISkill{

	String name;	
	
	ISkill[] nextSkills;
	
	int skillLevelMax,levelReq;
	
	double cost;

	public SkillActive(String n, double cost, int levelReq, int levelMax) {
		this.name=n;
		this.cost=cost;
		this.skillLevelMax=levelMax;
		this.levelReq=levelReq;
	}
	
	public boolean useSkill(YAM caster) {
		if((caster.getMana()-cost)>=0) {	
			caster.useMana(cost);
			doSkillStuff(caster);
			return true;
		}else {
			return false;
		}
	}

}
