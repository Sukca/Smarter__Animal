package net.leo.smartanimal;

import com.mojang.logging.LogUtils;
import net.leo.smartanimal.block.ModBlocks;
import net.leo.smartanimal.entity.ai.goal.CustomAvoidEntityGoal;
import net.leo.smartanimal.items.ModCreativeModTab;
import net.leo.smartanimal.items.ModItems;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(smartanimal.MODID)
public class smartanimal
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "smartanimal";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public smartanimal()
    {


        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        /*ModCreativeModTab.register(modEventBus);

        ModItems.register(modEventBus);

        ModBlocks.register(modEventBus);*/

        // this up here are tests thing for the mod plese do not use this for source

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Animal) {
            Animal animal = (Animal) event.getEntity();
            // Aumenta i parametri di velocità per far scappare gli animali più velocemente
            CustomAvoidEntityGoal<Player> avoidPlayerGoal = new CustomAvoidEntityGoal<>(animal, Player.class, 10.0F, 2.0D, 2.5D);
            animal.goalSelector.addGoal(1, avoidPlayerGoal);
            LOGGER.info("Added CustomAvoidEntityGoal to Animal with increased speed: {}", animal);
        }

        if (event.getEntity() instanceof IronGolem) {
            for(Goal goal:((IronGolem) event.getEntity()).targetSelector.getAvailableGoals()){
                if(goal instanceof CustomAvoidEntityGoal<?>){
                    ((IronGolem) event.getEntity()).targetSelector.removeGoal(goal);
                }
            }
        }

        if (event.getEntity() instanceof SnowGolem) {
            for(Goal goal:((SnowGolem) event.getEntity()).targetSelector.getAvailableGoals()){
                if(goal instanceof CustomAvoidEntityGoal<?>){
                    ((SnowGolem) event.getEntity()).targetSelector.removeGoal(goal);
                }
            }
        }
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        /*if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.DICK);
        }

        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.GEM);
        }*/

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
