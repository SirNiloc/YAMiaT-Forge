package com.sirniloc.mdk.systems;

import com.sirniloc.mdk.MDK;
import com.sirniloc.mdk.capability.ABS;
import com.sirniloc.mdk.capability.SimpleCapabilityProvider;
import com.sirniloc.mdk.util.ABSCalc;
import com.sirniloc.mdk.capability.IAbilityScores;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MDKEventHandler {
	
	
	@SubscribeEvent	
    public void onLivingDamage(LivingDamageEvent event) {
        //System.out.println("After Armor:"+event.getEntityLiving().getName() + " was attacked from "+ event.getSource().getTrueSource() +" for "+event.getAmount());
        event.setAmount(getDamageAfterDefStats(event.getAmount(),event.getEntityLiving()));
        //System.out.println("After Stats:"+event.getEntityLiving().getName() + " was attacked from "+ event.getSource().getTrueSource() +" for "+event.getAmount());
        
        
	}
	@SubscribeEvent
	public void onSpawn(EntityConstructing event) {
		
		
		//System.out.println(event.getEntity().getName()+" has IScores: "+event.getEntity().hasCapability(ModCapabilities.CAPABILITY_STATS, null));
	}
	@SubscribeEvent
	public void onAttachCapabilityEntity(AttachCapabilitiesEvent<Entity> event) {
		if(event.getObject() instanceof EntityLivingBase && !(event.getObject() instanceof EntityArmorStand)&& !(event.getObject() instanceof EntityPlayer)) {
			
			final IAbilityScores absCap = new ABS((EntityLivingBase) event.getObject());
			event.addCapability(MDK.STAT_ID, createProvider(absCap));
		}
			
	}
	
	public static ICapabilityProvider createProvider(IAbilityScores absCap) {
		return new SimpleCapabilityProvider<IAbilityScores>(MDK.ABS_CAP, null, absCap);
	}
	
	public static float getDamageAfterDefStats(float damage, EntityLivingBase e)
	{
		if(e.hasCapability(MDK.ABS_CAP, null)) {
			float inputDamage = damage;
			System.out.println("ABS damage reduction Body:"+e.getCapability(MDK.ABS_CAP, null).getBody());
			
			int defStat = ABSCalc.calcMod(e.getCapability(MDK.ABS_CAP, null).getBody());
			float defMod = defStat+5.0F;
			float maxDefMod = ABSCalc.MAXABILITYMOD+5.0F;
			float f = MathHelper.clamp(defMod, 0.0F, maxDefMod);

			e.getCapability(MDK.ABS_CAP, null).setBody(e.getCapability(MDK.ABS_CAP, null).getBody()+1); //TODO remove test

			float outputDamage = damage * (1.0F - f / (maxDefMod+6.0F));
			
			System.out.println("Def Stat:"+defStat+" Damage in:"+inputDamage+" | Damage out:"+outputDamage);
			
		    return outputDamage;
	    }
		return damage;
    }
	 
}
