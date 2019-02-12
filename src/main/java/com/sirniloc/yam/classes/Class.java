package com.sirniloc.yam.classes;

import com.sirniloc.yam.classes.skills.ISkill;
import com.sirniloc.yam.classes.skills.active.*;
import com.sirniloc.yam.classes.skills.passive.*;

public class Class {
	
	String name;	
	ISkill[] skills;
	
	int level;
	
	public static Class[] classes = {
			new Class("Warrior",new ISkill[] {new Evasion(), new DamageRedirect()}),
			new Class("Healer",new ISkill[] {new Heal(),new DefyDeath()}),
			new Class("Rogue",new ISkill[] {new CritHit(),new DoubleStrike()})
	};

	public Class(String name, ISkill[] skills) {
		this.name=name;
		this.skills=skills;
	}
}
