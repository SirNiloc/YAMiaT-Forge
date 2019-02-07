package com.sirniloc.yam.character.capa;

import java.util.concurrent.Callable;

import com.sirniloc.yam.character.YAMHelper;
import com.sirniloc.yam.character.capa.interfaces.IAbilityScores;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ABSStorage implements IStorage<IAbilityScores> {	

	@Override
	public NBTBase writeNBT(Capability<IAbilityScores> capability, IAbilityScores instance, EnumFacing side) {
		return YAMHelper.writeNBT(capability, instance, side);
	}

	@Override
	public void readNBT(Capability<IAbilityScores> capability, IAbilityScores instance, EnumFacing side, NBTBase nbt) {
		YAMHelper.readNBT(capability, instance, side, nbt);
	}

	public static class Factory implements Callable<IAbilityScores> {
		@Override
		public IAbilityScores call() throws Exception {
			return new ABS(null);
		}
	}

}
