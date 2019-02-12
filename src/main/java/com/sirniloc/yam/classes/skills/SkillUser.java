package com.sirniloc.yam.classes.skills;

public class SkillUser {

	float mana;
	
	public float getMana() {
		return this.mana;
	}

	public void useMana(double cost) {
		mana-=cost;
	}
}
