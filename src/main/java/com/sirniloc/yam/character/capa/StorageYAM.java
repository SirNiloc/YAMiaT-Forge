package com.sirniloc.yam.character.capa;

import java.util.concurrent.Callable;

import com.sirniloc.yam.character.NBTHelper;
import com.sirniloc.yam.character.capa.interfaces.IAbilityScores;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class StorageYAM implements IStorage<IAbilityScores> {	

	@Override
	public NBTBase writeNBT(Capability<IAbilityScores> capability, IAbilityScores instance, EnumFacing side) {
		return NBTHelper.writeNBT(capability, instance, side);
	}

	@Override
	public void readNBT(Capability<IAbilityScores> capability, IAbilityScores instance, EnumFacing side, NBTBase nbt) {
		NBTHelper.readNBT(capability, instance, side, nbt);
	}

	public static class Factory implements Callable<IAbilityScores> {
		@Override
		public IAbilityScores call() throws Exception {
			return new CharacterYAM(null);
		}
	}

}
