package com.sirniloc.yam.classes.skills.passive;

import com.sirniloc.yam.character.capability.YAM;
import com.sirniloc.yam.classes.skills.SkillPassive;

public class Heal extends SkillPassive{

	public Heal() {
		super("Heal", 200, 1, 2);
	}

	@Override
	public void doSkillStuff(YAM caster) {
		caster.theEntity.heal(getLevel());
	}

}
