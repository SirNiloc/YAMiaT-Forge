package com.sirniloc.yam.classes.skills;

import com.sirniloc.yam.character.capability.YAM;

public interface ISkill {
	
	boolean doSkillStuff(YAM caster);
	
	int getID();
}
