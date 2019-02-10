package com.sirniloc.yam.character.capability;

import com.sirniloc.yam.BaseYAM;
import com.sirniloc.yam.character.capability.interfaces.IYam;
import com.sirniloc.yam.util.SimpleCapabilityProvider;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class CapabilityYAM {	
	public static void postInit()
	{
		CapabilityManager.INSTANCE.register(IYam.class, new StorageYAM(), new StorageYAM.Factory());
	}	

	public static ICapabilityProvider createProvider(IYam absCap) {		
		return new SimpleCapabilityProvider<IYam>(BaseYAM.ABS_CAP, null, absCap);
	}
	
	public static String getNameYAM(EntityLivingBase e) {
		IYam cap = e.getCapability(BaseYAM.ABS_CAP, null);
		String r = "["+cap.getLevel()+"] "+e.getName()+" ("+cap.getRace().getName(e)+")";
		return r;
	}
}
