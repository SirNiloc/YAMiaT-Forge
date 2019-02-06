package com.sirniloc.mdk.systems;

import com.sirniloc.mdk.MDK;
import com.sirniloc.mdk.capability.ABS;
import com.sirniloc.mdk.capability.IAbilityScores;
import com.sirniloc.mdk.capability.SimpleCapabilityProvider;
import com.sirniloc.mdk.util.ABSCalc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
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
		if(event.getObject() instanceof EntityLivingBase && !(event.getObject() instanceof EntityArmorStand)) {
			
			final IAbilityScores absCap = new ABS((EntityLivingBase) event.getObject());
			event.addCapability(MDK.STAT_ID, createProvider(absCap));
		}
			
	}
	
	@SubscribeEvent
	public void onPlayerRespawn(PlayerEvent.Clone event) {
		System.out.println("Clone player ABS");
		event.getEntityLiving().getCapability(MDK.ABS_CAP, null).cloneABS(event.getOriginal().getCapability(MDK.ABS_CAP, null).getABS());	
	}
	
	@SubscribeEvent
	public void onPLayerSave(PlayerEvent.SaveToFile e) {
		if(e.getEntityPlayer().hasCapability(MDK.ABS_CAP, null)) {
			e.getEntityPlayer().getCapability(capability, facing)
		}
	}
	
	@SubscribeEvent
	public void onPLayerLoad(PlayerEvent.LoadFromFile e) {
		if(e.getEntityPlayer().hasCapability(MDK.ABS_CAP, null)) {
			
		}
	}
	
	
	public static ICapabilityProvider createProvider(IAbilityScores absCap) {
		return new SimpleCapabilityProvider<IAbilityScores>(MDK.ABS_CAP, null, absCap);
	}
	
	public static float getDamageAfterDefStats(float damage, EntityLivingBase e)
	{
		if(e.hasCapability(MDK.ABS_CAP, null)) {
			System.out.println(e.getCapability(MDK.ABS_CAP, null).getRace().getRaceFullName(e));
			float inputDamage = damage;			
			int defStat = ABSCalc.calcMod(e.getCapability(MDK.ABS_CAP, null).getTotalBody());
			float defMod = defStat+5.0F;
			float maxDefMod = ABSCalc.MAX_MOD+5.0F;
			float f = MathHelper.clamp(defMod, 0.0F, maxDefMod);			
			float outputDamage = inputDamage * (1.0F - f / (maxDefMod+6.0F));			
		    return outputDamage;
	    }
		return damage;
    }
	 
}
