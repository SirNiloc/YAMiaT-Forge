package com.sirniloc.yam.classes.skills;

public interface ISkill {

	boolean useSkill(SkillUser caster);
	
	boolean isPassive();
	
}
