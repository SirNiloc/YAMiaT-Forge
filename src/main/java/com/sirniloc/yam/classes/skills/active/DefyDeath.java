package com.sirniloc.yam.classes.skills.active;

import com.sirniloc.yam.character.capability.YAM;
import com.sirniloc.yam.classes.skills.SkillActive;

public class DefyDeath extends SkillActive{
	static final int ID = 6;
	public DefyDeath() {
		super("Defy Death", 100, 2, 2);
	}

	@Override
	public boolean doSkillStuff(YAM caster) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getID() {
		return ID;
	}

}
