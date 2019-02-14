package com.sirniloc.yam.classes;

import com.sirniloc.yam.character.capability.YAM;
import com.sirniloc.yam.classes.skills.ISkill;
import com.sirniloc.yam.classes.skills.SkillPassive;
import com.sirniloc.yam.classes.skills.active.DamageRedirect;
import com.sirniloc.yam.classes.skills.active.DefyDeath;
import com.sirniloc.yam.classes.skills.active.DoubleStrike;
import com.sirniloc.yam.classes.skills.passive.CritHit;
import com.sirniloc.yam.classes.skills.passive.Evasion;
import com.sirniloc.yam.classes.skills.passive.Heal;

import net.minecraft.util.math.MathHelper;

public class ClassYAM {
	
	String name;	
	ISkill[] skills;
	
	int level;
	
	public static ClassYAM none = new ClassYAM("", null);
	
	public static ClassYAM[] classes = {
			new ClassYAM("Warrior",new ISkill[] {new Evasion(), new DamageRedirect()}),
			new ClassYAM("Healer",new ISkill[] {new Heal(),new DefyDeath()}),
			new ClassYAM("Rogue",new ISkill[] {new CritHit(),new DoubleStrike()})
	};

	public ClassYAM(String name, ISkill[] skills) {
		this.name=name;
		this.skills=skills;
	}
	
	public static ClassYAM getClassFromIndex(int i) {
		if(i<0) return none;
		return classes[MathHelper.clamp(i, 0, classes.length-1)];
	}
	
	public ISkill[] getSkills() {
		return this.skills;
	}
	
	public static boolean doClassTicks(ClassYAM c, YAM yam) {
		boolean r = false;
		for(ISkill s : c.getSkills()) {
			if(s instanceof SkillPassive) {
				if(((SkillPassive) s).isTickSkill()) {
					r = true;
					((SkillPassive) s).doTick(yam);
				}
			}
		}		
		return r;		
	}
	
	public ISkill getSkill(ISkill s) {
		for(ISkill ss : skills) {
			if(ss.getID()==s.getID()) return ss;
		}
		return null;
	}

	public boolean hasSkill(ISkill s) {
		return (this.getSkill(s)!=null);
	}
}
