package com.sirniloc.yam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sirniloc.yam.character.capability.CapabilityYAM;
import com.sirniloc.yam.character.capability.interfaces.IYam;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BaseYAM.MODID)
public class BaseYAM
{
    public static final String MODID = "yam";
    
    
    @CapabilityInject(IYam.class)
	public static Capability<IYam> ABS_CAP = null;	
	
	public static final ResourceLocation STAT_ID = new ResourceLocation(BaseYAM.MODID, "CAPABILITY_STATS");
    
    //TODO Update
    
    private static final Logger LOGGER = LogManager.getLogger();
    
    public BaseYAM() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
    	CapabilityYAM.postInit();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
    }

    
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
    }

    
    
    ///////
    
    public static int calcMod(int aScore) {
    	if(aScore == 1)		return -5;
    	if(aScore <= 3)		return -4;
    	if(aScore <= 5) 	return -3;
    	if(aScore <= 7) 	return -2;
    	if(aScore <= 9) 	return -1;
    	if(aScore <= 11) 	return 0;
    	if(aScore <= 13) 	return 1;
    	if(aScore <= 15) 	return 2;
    	if(aScore <= 17) 	return 3;
    	if(aScore <= 19) 	return 4;
    	if(aScore <= 21) 	return 5;
    	if(aScore <= 23) 	return 6;
    	if(aScore <= 25) 	return 7;
    	if(aScore <= 27) 	return 8;
    	if(aScore <= 29) 	return 9;
    	if(aScore == 30) 	return 10;
    						return -5;
    }
}
