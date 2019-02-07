package com.sirniloc.yam.handlers;

import com.draco18s.reasonablerealism.SimpleCapabilityProvider;
import com.sirniloc.yam.BaseYAM;
import com.sirniloc.yam.character.capa.CharacterYAM;
import com.sirniloc.yam.character.capa.interfaces.IAbilityScores;
import com.sirniloc.yam.util.AbilityScoreHelper;

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

public class EventHandlerYAM {
	
	
	@SubscribeEvent	
    public void onLivingDamage(LivingDamageEvent event) {
        try {
        	if(event.getEntityLiving().hasCapability(BaseYAM.ABS_CAP, null) && event.getSource().getTrueSource().hasCapability(BaseYAM.ABS_CAP, null)) {
	        	event.setAmount(getDamageAfterYAM(event.getAmount(),event.getEntityLiving(), event.getSource().getTrueSource()));
	        	}
        }catch(java.lang.NullPointerException e) {
        	//TODO Find Cause of NullPointerException
        }
		
        
	}
	@SubscribeEvent
	public void onSpawn(EntityConstructing event) {}
	
	@SubscribeEvent
	public void onAttachCapabilityEntity(AttachCapabilitiesEvent<Entity> event) {
		if(event.getObject() instanceof EntityLivingBase && !(event.getObject() instanceof EntityArmorStand)) {
			
			final IAbilityScores absCap = new CharacterYAM((EntityLivingBase) event.getObject());
			event.addCapability(BaseYAM.STAT_ID, createProvider(absCap));
		}
			
	}
	
	@SubscribeEvent
	public void onPlayerRespawn(PlayerEvent.Clone event) {
		
		event.getEntityLiving().getCapability(BaseYAM.ABS_CAP, null).cloneABS(event.getOriginal().getCapability(BaseYAM.ABS_CAP, null).getABS());	
		
	}
	@SubscribeEvent
	public void playerName(PlayerEvent.NameFormat event) {
		if(event.getEntityLiving().hasCapability(BaseYAM.ABS_CAP, null)){
			event.setDisplayname(event.getEntityPlayer().getName()+" ["+event.getEntityPlayer().getCapability(BaseYAM.ABS_CAP, null).getRace().getRaceName(event.getEntityPlayer())+"]");
		}
	}	
	
	@SubscribeEvent
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
		float hpMax = event.getEntityLiving().getMaxHealth();
		float hpCur = event.getEntityLiving().getHealth();
		float hpBloodied = hpMax/4;
		boolean isYAM = event.getEntityLiving().hasCapability(BaseYAM.ABS_CAP, null);
		
		if(isYAM && hpCur<(hpMax-1) && hpCur>hpBloodied) 		
			event.getEntityLiving().setHealth(getHealFromYAM(event.getEntityLiving()));	
	}	
	
	public static ICapabilityProvider createProvider(IAbilityScores absCap) {
		return new SimpleCapabilityProvider<IAbilityScores>(BaseYAM.ABS_CAP, null, absCap);
	}
	
	public static float getHealFromYAM(EntityLivingBase e) {		
		float mod = AbilityScoreHelper.calcMod(e.getCapability(BaseYAM.ABS_CAP, null).getTotalSpirit())+5;
		int seconds = 20;
				
		return e.getHealth()+(mod/15)/(20*seconds);
		
	}
	
	public static float getDamageAfterYAM(float damage, EntityLivingBase defender, Entity attacker)
	{
			
			float trueDamage = damage;
			
			int statDefense = AbilityScoreHelper.calcMod(defender.getCapability(BaseYAM.ABS_CAP, null).getTotalBody());
			int statAttack = AbilityScoreHelper.calcMod(attacker.getCapability(BaseYAM.ABS_CAP, null).getTotalMind());
						
			float defMod = statDefense+5.0F;
			float attMod = statAttack+5.0F;
			
			float maxMod = AbilityScoreHelper.MAX_MOD+11.0F;
			
			float d = MathHelper.clamp(defMod, 0.0F, maxMod-6);
			float a = MathHelper.clamp(attMod, 0.0F, maxMod-6);
			
			float dm = (1.0F - d / maxMod);
			float am = (1.0F - a / maxMod);
			
			float outputDamage = trueDamage * (dm/am);
			
		    return outputDamage;
	    
    }
	 
}
