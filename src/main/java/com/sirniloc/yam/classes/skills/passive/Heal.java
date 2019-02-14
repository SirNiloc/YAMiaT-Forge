package com.sirniloc.yam.classes.skills.passive;

import com.sirniloc.yam.character.capability.YAM;
import com.sirniloc.yam.classes.skills.SkillPassive;

public class Heal extends SkillPassive{

	static final int ID = 2;
	
	public Heal() {
		super("Heal", 200, 1, 2);
	}

	@Override
	public boolean doSkillStuff(YAM caster) {
		caster.theEntity.heal(getSkillLevel());
		return true;
	}

	@Override
	public int getID() {
		return ID;
	}

	

}
