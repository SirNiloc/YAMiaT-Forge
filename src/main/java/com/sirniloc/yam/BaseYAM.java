package com.sirniloc.yam;

import com.sirniloc.yam.character.capability.CapabilityYAM;
import com.sirniloc.yam.character.capability.interfaces.IYam;
import com.sirniloc.yam.events.YAMEvents;
import com.sirniloc.yam.systems.leveling.LevelingSystemEvents;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = BaseYAM.MODID, version = BaseYAM.VERSION)
public class BaseYAM
{
    public static final String MODID = "yam";
    public static final String VERSION = "0.4";
    
    
    @CapabilityInject(IYam.class)
	public static Capability<IYam> ABS_CAP = null;	
	
	public static final ResourceLocation STAT_ID = new ResourceLocation(BaseYAM.MODID, "CAPABILITY_STATS");
    
    @EventHandler
    public void preInit(FMLInitializationEvent event)    {
    	
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	MinecraftForge.EVENT_BUS.register(new LevelingSystemEvents());

    	MinecraftForge.EVENT_BUS.register(new YAMEvents());
    }
    @EventHandler
    public void postInit(FMLInitializationEvent event)    {
    	CapabilityYAM.postInit();
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
