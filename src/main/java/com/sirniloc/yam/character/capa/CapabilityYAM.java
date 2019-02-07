package com.sirniloc.yam.character.capa;

import com.sirniloc.yam.character.capa.interfaces.IAbilityScores;

import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityYAM {	
	public static void postInit()
	{
		CapabilityManager.INSTANCE.register(IAbilityScores.class, new StorageYAM(), new StorageYAM.Factory());
	}
}
