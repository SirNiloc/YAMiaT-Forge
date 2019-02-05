package com.sirniloc.mdk;

import com.sirniloc.mdk.capability.CapabilityABS;
import com.sirniloc.mdk.capability.IAbilityScores;
import com.sirniloc.mdk.systems.MDKEventHandler;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = MDK.MODID, version = MDK.VERSION)
public class MDK
{
    public static final String MODID = "mdk";
    public static final String VERSION = "1.0";
    
    
    @CapabilityInject(IAbilityScores.class)
	public static Capability<IAbilityScores> ABS_CAP = null;	
	
	public static final ResourceLocation STAT_ID = new ResourceLocation(MDK.MODID, "CAPABILITY_STATS");
    
    @EventHandler
    public void preInit(FMLInitializationEvent event)    {
    	
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	MinecraftForge.EVENT_BUS.register(new MDKEventHandler());
    }
    @EventHandler
    public void postInit(FMLInitializationEvent event)    {
    	CapabilityABS.postInit();
    }
    
    public static int calcMod(int aScore) {

    	if(aScore == 1) return -5;
    	if(aScore <= 3) return -4;
    	if(aScore <= 5) return -3;
    	if(aScore <= 7) return -2;
    	if(aScore <=9) return -1;
    	if(aScore <=11) return 0;
    	if(aScore <=13) return 1;
    	if(aScore <=15) return 2;
    	if(aScore <=17) return 3;
    	if(aScore <=19) return 4;
    	if(aScore <=21) return 5;
    	if(aScore <=23) return 6;
    	if(aScore <=25) return 7;
    	if(aScore <=27) return 8;
    	if(aScore <=29) return 9;
    	if(aScore ==30) return 10;
    	return -5;
    }
}
