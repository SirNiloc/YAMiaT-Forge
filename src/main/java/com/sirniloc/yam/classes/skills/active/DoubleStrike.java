package com.sirniloc.yam.classes.skills.active;

import com.sirniloc.yam.character.capability.YAM;
import com.sirniloc.yam.classes.skills.SkillActive;

public class DoubleStrike extends SkillActive{

	static final int ID = 5;
	public DoubleStrike() {
		super("Double Strike", 50, 1, 5);
	}


	@Override
	public boolean doSkillStuff(YAM caster) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}
