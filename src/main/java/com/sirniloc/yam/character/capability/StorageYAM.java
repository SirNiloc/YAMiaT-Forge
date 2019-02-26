package com.sirniloc.yam.character.capability;

import java.util.concurrent.Callable;

import com.sirniloc.yam.character.CharacterNBT;
import com.sirniloc.yam.character.capability.interfaces.IYam;

import net.minecraft.nbt.INBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class StorageYAM implements IStorage<IYam> {	

	@Override
	public INBTBase writeNBT(Capability<IYam> capability, IYam instance, EnumFacing side) {
		return CharacterNBT.writeNBT(capability, instance, side);
	}

	@Override
	public void readNBT(Capability<IYam> capability, IYam instance, EnumFacing side, INBTBase nbt) {
		CharacterNBT.readNBT(capability, instance, side, nbt);
	}

	public static class Factory implements Callable<IYam> {
		@Override
		public IYam call() throws Exception {
			return new YAM(null);
		}
	}


}
