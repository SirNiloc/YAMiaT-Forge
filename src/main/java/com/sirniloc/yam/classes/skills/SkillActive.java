package com.sirniloc.yam.classes.skills;

import com.sirniloc.yam.character.capability.YAM;

import net.minecraft.util.math.MathHelper;

public abstract class SkillActive implements ISkill{

	String name;	
	
	ISkill[] nextSkills;
	
	int skillLevelMax,levelReq,skillLevel;
	
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
	
	@Override
	public int getSkillLevel() {
		return this.skillLevel;
	}

	@Override
	public void setSkillLevel(int skillLevel) {
		this.skillLevel=MathHelper.clamp(this.skillLevel+skillLevel, 0, this.skillLevelMax);
		
	}
	
	@Override
	public String getName() {
		return this.name;
	}

}
