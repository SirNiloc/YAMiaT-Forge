package com.sirniloc.yam.systems.leveling;

import com.sirniloc.yam.BaseYAM;
import com.sirniloc.yam.systems.ExpSystem;

import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class LevelingSystemEvents {
	
	@SubscribeEvent
	public void deathEvent(LivingDeathEvent event) {
		try {
	    	if(event.getEntityLiving().hasCapability(BaseYAM.ABS_CAP, null)) {
	        	ExpSystem.expFromKill(event.getEntityLiving().getCapability(BaseYAM.ABS_CAP, null).getYAM());
	        }
		}catch(NullPointerException e) {}        
	}
	
	@SubscribeEvent
	public void blockBreak(HarvestDropsEvent event) {
		try {
	    	if(event.getHarvester().hasCapability(BaseYAM.ABS_CAP, null)) {
	        	ExpSystem.expFromMine(event.getHarvester().getCapability(BaseYAM.ABS_CAP, null).getYAM());
	        }
		}catch(NullPointerException e) {}      
	}
	@SubscribeEvent
	public void itemCraft(ItemCraftedEvent  event) {
		try {
	    	if(event.player.hasCapability(BaseYAM.ABS_CAP, null)) {
	        	ExpSystem.expFromCrafting(event.player.getCapability(BaseYAM.ABS_CAP, null).getYAM());
	        }
		}catch(NullPointerException e) {}      
	}
	
	
	 
}
