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
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class YAMEventHandler {
	
	
	@SubscribeEvent	
    public void onLivingDamage(LivingDamageEvent event) {
        //System.out.println("After Armor:"+event.getEntityLiving().getName() + " was attacked from "+ event.getSource().getTrueSource() +" for "+event.getAmount());
        event.setAmount(getDamageAfterYAM(event.getAmount(),event.getEntityLiving(), event.getSource().getTrueSource()));
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
	
	@SubscribeEvent
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
		if(event.getEntityLiving().hasCapability(YAM.ABS_CAP, null)) {
				event.getEntityLiving().setHealth(getHealFromYAM(event.getEntityLiving()));
				
		}
		
		
	}
	
	
	public static ICapabilityProvider createProvider(IAbilityScores absCap) {
		return new SimpleCapabilityProvider<IAbilityScores>(YAM.ABS_CAP, null, absCap);
	}
	
	public static float getHealFromYAM(EntityLivingBase e) {

		float mod = ABSCalc.calcMod(e.getCapability(YAM.ABS_CAP, null).getTotalSpirit())+5;
		int seconds = 20;
				
		return (mod/15)/(20*seconds);
		
	}
	
	public static float getDamageAfterYAM(float damage, EntityLivingBase defender, Entity attacker)
	{
		if(defender.hasCapability(YAM.ABS_CAP, null) && attacker.hasCapability(YAM.ABS_CAP, null)) {
			
			float trueDamage = damage;
			
			int statDefense = ABSCalc.calcMod(defender.getCapability(YAM.ABS_CAP, null).getTotalBody());
			int statAttack = ABSCalc.calcMod(attacker.getCapability(YAM.ABS_CAP, null).getTotalMind());
						
			float defMod = statDefense+5.0F;
			float attMod = statAttack+5.0F;
			
			float maxMod = ABSCalc.MAX_MOD+11.0F;
			
			float d = MathHelper.clamp(defMod, 0.0F, maxMod-6);
			float a = MathHelper.clamp(attMod, 0.0F, maxMod-6);
			
			float dm = (1.0F - d / maxMod);
			float am = (1.0F - a / maxMod);
			
			float outputDamage = trueDamage * (dm/am);
			
		    return outputDamage;
	    }
		
		return damage;
    }
	 
}
