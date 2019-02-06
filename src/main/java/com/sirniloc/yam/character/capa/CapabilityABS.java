package com.sirniloc.yam.character.capa;

import com.sirniloc.yam.character.capa.interfaces.IAbilityScores;

import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityABS {	
	public static void postInit()
	{
		CapabilityManager.INSTANCE.register(IAbilityScores.class, new ABSStorage(), new ABSStorage.Factory());
	}
}
