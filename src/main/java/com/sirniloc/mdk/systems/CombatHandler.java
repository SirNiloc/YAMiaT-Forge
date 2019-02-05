package com.sirniloc.mdk.systems;

import com.sirniloc.mdk.MDK;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CombatHandler {
	
	@SubscribeEvent	
    public void onLivingHurt(LivingHurtEvent event) {
        System.out.println("Before Armor:"+event.getEntityLiving().getName() + " was attacked from "+ event.getSource().getTrueSource() +" for "+event.getAmount());
        
        
	}
	@SubscribeEvent	
    public void onLivingDamage(LivingDamageEvent event) {
        System.out.println("After Armor:"+event.getEntityLiving().getName() + " was attacked from "+ event.getSource().getTrueSource() +" for "+event.getAmount());
        event.setAmount(getDamageAfterDefStats(event.getAmount(),event.getEntityLiving()));
        System.out.println("After Stats:"+event.getEntityLiving().getName() + " was attacked from "+ event.getSource().getTrueSource() +" for "+event.getAmount());
        
        
	}
	
	public static float getDamageAfterDefStats(float damage, EntityLivingBase e)
	{
		int defStat = e.*Get the Stat*;
		float defMod = defStat+5.0F;
		
		float maxDefMod = MDK.MAXABILITYMOD+5.0F;
		float f = MathHelper.clamp(defMod, 0.0F, maxDefMod);
	    return damage * (1.0F - f / (maxDefMod+6.0F));
    }
	 
}
