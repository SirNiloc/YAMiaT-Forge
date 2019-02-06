package com.sirniloc.mdk.capability;

import java.util.concurrent.Callable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ABSStorage implements IStorage<IAbilityScores> {	

	@Override
	public NBTBase writeNBT(Capability<IAbilityScores> capability, IAbilityScores instance, EnumFacing side) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("mind", instance.getTotalMind());
		nbt.setInteger("body", instance.getTotalBody());
		nbt.setInteger("spirit", instance.getTotalSpirit());
		return nbt;
	}

	@Override
	public void readNBT(Capability<IAbilityScores> capability, IAbilityScores instance, EnumFacing side, NBTBase nbt) {
		if(nbt instanceof NBTTagCompound) {
			NBTTagCompound tag = (NBTTagCompound)nbt;
			instance.setMind(tag.getInteger("mind"));
			instance.setBody(tag.getInteger("body"));
			instance.setSpirit(tag.getInteger("spirit"));
		}
	}

	public static class Factory implements Callable<IAbilityScores> {
		@Override
		public IAbilityScores call() throws Exception {
			return new ABS(null);
		}
	}

}
