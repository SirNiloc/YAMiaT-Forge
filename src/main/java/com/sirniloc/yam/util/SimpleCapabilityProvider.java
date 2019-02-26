package com.sirniloc.yam.util;

import javax.annotation.Nullable;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

/**
 * 
 * @author Draco18s
 *
 * @param <HANDLER>
 */

public class SimpleCapabilityProvider <HANDLER> implements ICapabilitySerializable<NBTBase> {


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
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == getCapability();

	}
	@Override
	@Nullable
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if (capability == getCapability()) {
			return getCapability().cast(getInstance());
		}
		return null;
	}

	@Override
	public NBTBase serializeNBT() {
		return getCapability().writeNBT(getInstance(), getFacing());
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
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