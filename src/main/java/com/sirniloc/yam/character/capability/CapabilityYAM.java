package com.sirniloc.yam.character.capability;

import com.sirniloc.yam.BaseYAM;
import com.sirniloc.yam.character.capability.interfaces.IYam;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class CapabilityYAM {	
	public static void postInit()
	{
		CapabilityManager.INSTANCE.register(IYam.class, new StorageYAM(), new StorageYAM.Factory());
	}	

	
	public static String getNameYAM(EntityLivingBase e) {
		IYam cap = (IYam) e.getCapability(BaseYAM.ABS_CAP, null);
		String r = "["+cap.getLevel()+"] "+e.getName()+" ("+cap.getRace().getName(e)+")";
		return r;
	}

	public static ICapabilityProvider createProvider(IYam absCap, Entity o) {
		return new SimpleCapabilityProvider<IYam>(BaseYAM.ABS_CAP, absCap, o);
	}
}
