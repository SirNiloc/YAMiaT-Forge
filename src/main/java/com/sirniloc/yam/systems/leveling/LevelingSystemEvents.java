package com.sirniloc.yam.systems.leveling;

import com.sirniloc.yam.BaseYAM;

import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LevelingSystemEvents {
	
	
	@SubscribeEvent
	public void deathEvent(LivingDeathEvent event) {
		try {
	    	if(event.getEntityLiving().hasCapability(BaseYAM.ABS_CAP, null)) {
	        	event.getEntityLiving().getCapability(BaseYAM.ABS_CAP, null).deathStuff();
	        }
		}catch(NullPointerException e) {}        
	}
	 
}
