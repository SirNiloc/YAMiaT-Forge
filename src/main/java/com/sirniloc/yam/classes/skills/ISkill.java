package com.sirniloc.yam.classes.skills;

import com.sirniloc.yam.character.capability.YAM;

public interface ISkill {
	
	boolean doSkillStuff(YAM caster);
	
	int getID();
		
	public int getSkillLevel();
	public void setSkillLevel(int skillLevel);
	
	public static void usingSkill(ISkill s) {
		System.out.println("Using "+s.getName());
	}

	String getName();


}
