package com.sirniloc.yam.util;

import javax.annotation.Nullable;

import net.minecraft.nbt.INBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

/**
 * 
 * @author Draco18s
 *
 * @param <HANDLER>
 */

public class SimpleCapabilityProvider <HANDLER> implements ICapabilitySerializable<INBTBase> {


	private final Capability<HANDLER> capability;

	private final EnumFacing facing;
	private final HANDLER instance;

	public SimpleCapabilityProvider(Capability<HANDLER> capability, @Nullable EnumFacing facing) {
		this(capability, facing, capability.getDefaultInstance());
	}

	public SimpleCapabilityProvider(Capability<HANDLER> capability, @Nullable EnumFacing facing, HANDLER instance) {
		this.capability = capability;
		this.instance = instance;
		this.facing = facing;
	}
		
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, EnumFacing side) {
		if (capability == getCapability()) {
			return getCapability().cast(getInstance());
		}
		return null;
	}

	@Override
	public INBTBase serializeNBT() {
		return getCapability().writeNBT(getInstance(), getFacing());
	}

	@Override
	public void deserializeNBT(INBTBase nbt) {
		getCapability().readNBT(getInstance(), getFacing(), nbt);
	}

	public final Capability<HANDLER> getCapability() {
		return capability;
	}
	
	@Nullable
	public EnumFacing getFacing() {
		return facing;
	}

	public final HANDLER getInstance() {
		return instance;
	}

}