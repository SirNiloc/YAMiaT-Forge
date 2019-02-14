package com.sirniloc.yam.classes.skills.active;

import com.sirniloc.yam.character.capability.YAM;
import com.sirniloc.yam.classes.skills.SkillActive;

public class DamageRedirect extends SkillActive{

	
	static final int ID = 4;
	
	public DamageRedirect() {
		super("Damage Redirect", 20, 1, 10);
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
