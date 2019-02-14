package com.sirniloc.yam.systems;

import com.sirniloc.yam.BaseYAM;
import com.sirniloc.yam.character.capability.interfaces.IYam;
import com.sirniloc.yam.classes.skills.passive.Evasion;
import com.sirniloc.yam.util.AbilityScoreHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class CombatSystem {

	public static float getDamageAfterYAM(float damage, EntityLivingBase defender, Entity attacker)
	{
			
			IYam dCap = defender.getCapability(BaseYAM.ABS_CAP, null);
			IYam aCap = defender.getCapability(BaseYAM.ABS_CAP, null);
			
			dCap.addAttacker((EntityLivingBase)attacker);
			float outputDamage = 0;
			if((dCap.getClassYAM().hasSkill(new Evasion()))) {
				if(dCap.getClassYAM().getSkill(new Evasion()).doSkillStuff(dCap.getYAM()))
					return 0;
			}
			
				float trueDamage = damage;
				
				int statDefense = AbilityScoreHelper.calcMod(dCap.getTotalBody());
				int statAttack = AbilityScoreHelper.calcMod(aCap.getTotalMind());
							
				float defMod = statDefense+5.0F;
				float attMod = statAttack+5.0F;
				
				float maxMod = AbilityScoreHelper.MAX_MOD+11.0F;
				
				float d = MathHelper.clamp(defMod, 0.0F, maxMod-6);
				float a = MathHelper.clamp(attMod, 0.0F, maxMod-6);
				
				float dm = (1.0F - d / maxMod);
				float am = (1.0F - a / maxMod);
				
				outputDamage = trueDamage * (dm/am);	
			
		    return outputDamage;	    
    }
	

	
	public static float getHealFromYAM(EntityLivingBase e) {		
		float mod = AbilityScoreHelper.calcMod(e.getCapability(BaseYAM.ABS_CAP, null).getTotalSpirit())+5;
		int seconds = 20;
				
		return e.getHealth()+(mod/15)/(20*seconds);
		
	}
}
