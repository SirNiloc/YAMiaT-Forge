package com.sirniloc.yam.handlers;

import com.sirniloc.yam.BaseYAM;
import com.sirniloc.yam.character.capability.CapabilityYAM;
import com.sirniloc.yam.character.capability.CharacterYAM;
import com.sirniloc.yam.character.capability.interfaces.IYam;
import com.sirniloc.yam.systems.CombatSystem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class YAMEvents {
	
	
	@SubscribeEvent	
    public void onLivingDamage(LivingDamageEvent event) {
        try {
        	if(event.getEntityLiving().hasCapability(BaseYAM.ABS_CAP, null) && event.getSource().getTrueSource().hasCapability(BaseYAM.ABS_CAP, null)) {
	        	event.setAmount(CombatSystem.getDamageAfterYAM(event.getAmount(),event.getEntityLiving(), event.getSource().getTrueSource()));
	        	}
        }catch(java.lang.NullPointerException e) {}        
	}
		
	@SubscribeEvent
	public void onAttachCapabilityEntity(AttachCapabilitiesEvent<Entity> event) {
		if(event.getObject() instanceof EntityLivingBase && !(event.getObject() instanceof EntityArmorStand)) {
			
			final IYam absCap = new CharacterYAM((EntityLivingBase) event.getObject());
			event.addCapability(BaseYAM.STAT_ID, CapabilityYAM.createProvider(absCap));
		}
			
	}
	
	@SubscribeEvent
	public void onPlayerRespawn(PlayerEvent.Clone event) {		
		event.getEntityLiving().getCapability(BaseYAM.ABS_CAP, null).cloneABS(event.getOriginal().getCapability(BaseYAM.ABS_CAP, null).getCharacter());		
	}
	
	@SubscribeEvent
	public void playerName(PlayerEvent.NameFormat event) {
		if(event.getEntityLiving().hasCapability(BaseYAM.ABS_CAP, null)){
			event.setDisplayname(CapabilityYAM.getNameYAM(event.getEntityLiving()));
		}
	}	
	
	@SubscribeEvent
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
		float hpMax = event.getEntityLiving().getMaxHealth();
		float hpCur = event.getEntityLiving().getHealth();
		float hpBloodied = hpMax/4;
		boolean isYAM = event.getEntityLiving().hasCapability(BaseYAM.ABS_CAP, null);
		
		if(isYAM) event.getEntityLiving().getCapability(BaseYAM.ABS_CAP, null).update();
		
		if(isYAM && hpCur<(hpMax-1) && hpCur>hpBloodied) 		
			event.getEntityLiving().setHealth(CombatSystem.getHealFromYAM(event.getEntityLiving()));	
	}
	
	
	
	
	 
}
