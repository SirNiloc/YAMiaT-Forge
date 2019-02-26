package com.sirniloc.yam.character;

import com.sirniloc.yam.character.capability.interfaces.IYam;

import net.minecraft.nbt.INBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class CharacterNBT {

	public static void readNBT(Capability<IYam> capability, IYam instance, EnumFacing side, INBTBase nbt) {
		if(nbt instanceof NBTTagCompound) {
			NBTTagCompound tag = (NBTTagCompound)nbt;
			instance.setMind(tag.getInt("mind"));
			instance.setBody(tag.getInt("body"));
			instance.setSpirit(tag.getInt("spirit"));
			instance.setRaceInt(tag.getInt("race"));
			instance.setExpDouble(tag.getDouble("exp"));
			instance.setLevelInt(tag.getInt("level"));
			instance.setMana(tag.getDouble("mana"));
			instance.setManaMax(tag.getInt("maxmana"));
			instance.setClassInt(tag.getInt("class"));
		}
	}
	
	public static INBTBase writeNBT(Capability<IYam> capability, IYam instance, EnumFacing side) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInt("mind", instance.getMind());
		nbt.setInt("body", instance.getBody());
		nbt.setInt("spirit", instance.getSpirit());
		nbt.setInt("race", instance.getRaceInt());
		nbt.setDouble("exp", instance.getExp());
		nbt.setInt("level", instance.getLevel());
		nbt.setDouble("mana", instance.getMana());
		nbt.setInt("maxmana", instance.getManaMax());
		nbt.setInt("class", instance.getClassIndex());
		return nbt;
		
	}
}
