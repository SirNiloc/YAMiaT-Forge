package com.sirniloc.yam.classes.skills.passive;

import java.util.concurrent.ThreadLocalRandom;

import com.sirniloc.yam.character.capability.YAM;
import com.sirniloc.yam.classes.skills.SkillPassive;

public class CritHit extends SkillPassive{

	static final int ID = 3;
	
	public CritHit() {
		super("Critical Hit", -1, 1, 20);
	}

	
	@Override
	public boolean doSkillStuff(YAM caster) {
		return crit();
	}

	private boolean crit() {
		int randomNum = ThreadLocalRandom.current().nextInt(0, 101);
		return randomNum>this.getSkillLevel();
	}

	@Override
	public int getID() {
		return ID;
	}

}
