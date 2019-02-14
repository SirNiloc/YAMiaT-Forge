package com.sirniloc.yam.classes.skills.passive;

import com.sirniloc.yam.character.capability.YAM;
import com.sirniloc.yam.classes.skills.SkillPassive;

public class CritHit extends SkillPassive{

	static final int ID = 3;
	
	public CritHit() {
		super("Critical Hit", -1, 1, 20);
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
