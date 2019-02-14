package com.sirniloc.yam.classes.skills.passive;

import java.util.concurrent.ThreadLocalRandom;

import com.sirniloc.yam.character.capability.YAM;
import com.sirniloc.yam.classes.skills.SkillPassive;

public class Evasion extends SkillPassive{

	
	
	static final int ID = 1;

	public Evasion() {
		super("Evasion", -1, 1, 20);
	}

	@Override
	public boolean doSkillStuff(YAM caster) {
		return evade();
	}

	private boolean evade() {
		int randomNum = ThreadLocalRandom.current().nextInt(0, 101);
		return randomNum>this.getLevel();
	}

	@Override
	public int getID() {
		return ID;
	}
	
	

}
