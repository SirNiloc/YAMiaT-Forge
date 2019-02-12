package com.sirniloc.yam.systems.leveling;

import com.sirniloc.yam.BaseYAM;
import com.sirniloc.yam.systems.ExpSystem;

import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LevelingSystemEvents {
	
	@SubscribeEvent
	public void deathEvent(LivingDeathEvent event) {
		try {
	    	if(event.getEntityLiving().hasCapability(BaseYAM.ABS_CAP, null)) {
	        	ExpSystem.expFromKill(event.getEntityLiving().getCapability(BaseYAM.ABS_CAP, null).getYAM());
	        }
		}catch(NullPointerException e) {}        
	}
	
	
	
	
	 
}
