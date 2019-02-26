package com.sirniloc.yam.systems;

import com.sirniloc.yam.BaseYAM;
import com.sirniloc.yam.character.capability.YAM;
import com.sirniloc.yam.character.capability.interfaces.IYam;

public class ExpSystem {

	public static void expFromKill(YAM characterYAM) {
		double d = characterYAM.theEntity.getMaxHealth()*(characterYAM.getLevel()*1)/characterYAM.getRecentAttackers().length;
		
		for(int i=0; i< characterYAM.getRecentAttackers().length;i++) {
			IYam aCap = (IYam) characterYAM.getRecentAttackers()[i].getCapability(BaseYAM.ABS_CAP, null);
			aCap.addExp(d);
		}
	}
	
	
}
