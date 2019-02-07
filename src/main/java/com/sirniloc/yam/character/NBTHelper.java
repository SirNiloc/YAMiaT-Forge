package com.sirniloc.yam.character;

import com.sirniloc.yam.character.capa.interfaces.IAbilityScores;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class NBTHelper {

	public static void readNBT(Capability<IAbilityScores> capability, IAbilityScores instance, EnumFacing side, NBTBase nbt) {
		if(nbt instanceof NBTTagCompound) {
			NBTTagCompound tag = (NBTTagCompound)nbt;
			instance.setMind(tag.getInteger("mind"));
			instance.setBody(tag.getInteger("body"));
			instance.setSpirit(tag.getInteger("spirit"));
			instance.setRaceInt(tag.getInteger("race"));
		}
	}
	
	public static NBTBase writeNBT(Capability<IAbilityScores> capability, IAbilityScores instance, EnumFacing side) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("mind", instance.getMind());
		nbt.setInteger("body", instance.getBody());
		nbt.setInteger("spirit", instance.getSpirit());
		nbt.setInteger("race", instance.getRaceInt());
		return nbt;
		
	}
}
