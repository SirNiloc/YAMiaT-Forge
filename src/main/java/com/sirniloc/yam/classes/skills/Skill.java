package com.sirniloc.yam.classes.skills;

public class Skill implements ISkill{

	String name;	
	private boolean passive;
	
	Skill[] nextSkills;
	
	int skillLevelMax,levelReq;
	
	double cost;

	public Skill(String n, double cost, boolean isPassive, int levelReq, int levelMax) {
		this.name=n;
		this.cost=cost;
		this.passive=isPassive;
		this.skillLevelMax=levelMax;
		this.levelReq=levelReq;
	}
	
	@Override
	public boolean useSkill(SkillUser caster) {
		if((caster.getMana()-cost)>=0) {	
			caster.useMana(cost);
			doSkillStuff();
			return true;
		}else {
			return false;
		}
	}
	
	protected void doSkillStuff() {
		
	}

	@Override
	public boolean isPassive() {
		return passive;
	}
}
