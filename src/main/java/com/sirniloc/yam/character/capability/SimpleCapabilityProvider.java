package com.sirniloc.yam.character.capability;

import com.sirniloc.yam.character.capability.interfaces.IYam;

import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class SimpleCapabilityProvider<T> implements ICapabilityProvider {

	Entity entity;
	Capability<IYam> cap;
	IYam iYam;
	
	public SimpleCapabilityProvider(Capability<IYam> c, IYam y, Entity o) {
		entity=o;
		cap = c;
		iYam = y;
		
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, EnumFacing facing) {
		return entity.getCapability(capability, facing);
	}

}
