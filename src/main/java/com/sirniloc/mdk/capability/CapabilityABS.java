package com.sirniloc.mdk.capability;

import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityABS {


	public static void postInit()
	{

		CapabilityManager.INSTANCE.register(IAbilityScores.class, new ABSStorage(), new ABSStorage.Factory());
		
		
	}
}
