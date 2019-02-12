package com.sirniloc.yam.classes;

import com.sirniloc.yam.classes.skills.Skill;

public class Class {
	
	String name;	
	Skill[] skills;
	
	int level;
	
	public static Class[] classes = {
			new Class("Warrior",new Skill[] {}),
			new Class("Healer",new Skill[] {}),
			new Class("Rogue",new Skill[] {})
	};

	public Class(String name, Skill[] skills) {
		this.name=name;
		this.skills=skills;
	}
}
