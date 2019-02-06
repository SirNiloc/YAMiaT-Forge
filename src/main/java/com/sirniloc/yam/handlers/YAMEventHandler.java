package com.sirniloc.yam.handlers;

import com.draco18s.reasonablerealism.SimpleCapabilityProvider;
import com.sirniloc.yam.YAM;
import com.sirniloc.yam.character.capa.ABS;
import com.sirniloc.yam.character.capa.interfaces.IAbilityScores;
import com.sirniloc.yam.util.ABSCalc;

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

public class YAMEventHandler {
	
	
	@SubscribeEvent	
    public void onLivingDamage(LivingDamageEvent event) {
        //System.out.println("After Armor:"+event.getEntityLiving().getName() + " was attacked from "+ event.getSource().getTrueSource() +" for "+event.getAmount());
        event.setAmount(getDamageAfterDefStats(event.getAmount(),event.getEntityLiving()));
        //System.out.println("After Stats:"+event.getEntityLiving().getName() + " was attacked from "+ event.getSource().getTrueSource() +" for "+event.getAmount());
        
        
	}
	@SubscribeEvent
	public void onSpawn(EntityConstructing event) {}
	
	@SubscribeEvent
	public void onAttachCapabilityEntity(AttachCapabilitiesEvent<Entity> event) {
		if(event.getObject() instanceof EntityLivingBase && !(event.getObject() instanceof EntityArmorStand)) {
			
			final IAbilityScores absCap = new ABS((EntityLivingBase) event.getObject());
			event.addCapability(YAM.STAT_ID, createProvider(absCap));
		}
			
	}
	
	@SubscribeEvent
	public void onPlayerRespawn(PlayerEvent.Clone event) {
		System.out.println("Clone player ABS");
		event.getEntityLiving().getCapability(YAM.ABS_CAP, null).cloneABS(event.getOriginal().getCapability(YAM.ABS_CAP, null).getABS());	
		
	}
	
	
	
	public static ICapabilityProvider createProvider(IAbilityScores absCap) {
		return new SimpleCapabilityProvider<IAbilityScores>(YAM.ABS_CAP, null, absCap);
	}
	
	public static float getDamageAfterDefStats(float damage, EntityLivingBase e)
	{
		if(e.hasCapability(YAM.ABS_CAP, null)) {
			System.out.println(e.getCapability(YAM.ABS_CAP, null).toString());
			
			e.getCapability(YAM.ABS_CAP, null).setBody(e.getCapability(YAM.ABS_CAP, null).getBody()+1);
			
			float inputDamage = damage;			
			int defStat = ABSCalc.calcMod(e.getCapability(YAM.ABS_CAP, null).getTotalBody());
			float defMod = defStat+5.0F;
			float maxDefMod = ABSCalc.MAX_MOD+5.0F;
			float f = MathHelper.clamp(defMod, 0.0F, maxDefMod);			
			float outputDamage = inputDamage * (1.0F - f / (maxDefMod+6.0F));			
		    return outputDamage;
	    }
		return damage;
    }
	 
}
