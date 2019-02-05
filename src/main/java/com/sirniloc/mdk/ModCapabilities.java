package com.sirniloc.mdk;
import com.sirniloc.mdk.capability.IScores;
import com.sirniloc.mdk.character.CharacterMDK;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModCapabilities {

	@CapabilityInject(IScores.class)
	public static Capability<IScores> CAPABILITY_STATS = null;

	@SuppressWarnings("deprecation")
	public static void registerCapabilites() {
		CapabilityManager.INSTANCE.register(IScores.class, new CapabilityCharacter(), CharacterMDK.class);
	}
	
	public static class CapabilityCharacter implements IStorage<IScores>{

		@Override
		public NBTBase writeNBT(Capability<IScores> capability, IScores instance, EnumFacing side) {
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setInteger("mind", instance.getMind());
			nbt.setInteger("body", instance.getBody());
			nbt.setInteger("spirit", instance.getSpirit());
			return nbt;
		}

		@Override
		public void readNBT(Capability<IScores> capability, IScores instance, EnumFacing side, NBTBase nbt) {

		}
		
	}
}
