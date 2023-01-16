package net.Vicente.tutorialmod.event;

import net.Vicente.tutorialmod.TutorialMod;
import net.Vicente.tutorialmod.entity.ModEntityTypes;
import net.Vicente.tutorialmod.entity.custom.ModSkeleton;
import net.Vicente.tutorialmod.entity.custom.ModZombie;
import net.Vicente.tutorialmod.nightbattle.PlayerBattleProvider;
import net.Vicente.tutorialmod.thirst.PlayerThirst;
import net.Vicente.tutorialmod.thirst.PlayerThirstProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;


public class ModEvents {
    @Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void modSkeletonHurt(LivingHurtEvent event){
            if(event.getEntity() instanceof ModSkeleton && (event.getSource().isExplosion() || event.getSource().isProjectile()) ) {
                event.setCanceled(true);
            }
        }

        @SubscribeEvent
        public static void nightBattle(LivingEntityUseItemEvent event){
            if(event.getEntity().getLevel().dayTime() > 13500) { // And the save doesn't have a night already registered
                new NightBattles(event);
            }

                // else if dayTime >= Sunrise --> Stop NightBattles
        }
        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event){
            if(event.getObject() instanceof Player){
                if(!event.getObject().getCapability(PlayerThirstProvider.PLAYER_THIRST).isPresent()){
                    event.addCapability(new ResourceLocation(TutorialMod.MOD_ID, "properties"), new PlayerThirstProvider());
                    System.out.println("TEST_THIRST");
                    System.out.println(event.getObject().getCapability(PlayerThirstProvider.PLAYER_THIRST).isPresent());
                }
                if(!event.getObject().getCapability(PlayerBattleProvider.PLAYER_BATTLE).isPresent()){
                    event.addCapability(new ResourceLocation(TutorialMod.MOD_ID, "properties_battle"), new PlayerBattleProvider());
                    System.out.println("TEST_BATTLE");
                    System.out.println(event.getObject().getCapability(PlayerThirstProvider.PLAYER_THIRST).isPresent());
                }
            }
        }

        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event){
            if(event.isWasDeath()){
                // Thirst
                event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(oldStore -> {
                    event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);

                    });

                });
                // Battle
                event.getOriginal().getCapability(PlayerBattleProvider.PLAYER_BATTLE).ifPresent(oldStore -> {
                    event.getOriginal().getCapability(PlayerBattleProvider.PLAYER_BATTLE).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);

                    });

                });
            }
        }

        @SubscribeEvent
        public static void onRegisterCapabilities(RegisterCapabilitiesEvent event){
            event.register(PlayerThirst.class);
        }

        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event){
            if(event.side == LogicalSide.SERVER){
                // Battle
                event.player.getCapability(PlayerBattleProvider.PLAYER_BATTLE).ifPresent(battle -> {
                    if(battle.getBattle() >= 0 && event.player.getRandom().nextFloat() < 0.005f) {
                        // Once every 10 sec on Avg
                        battle.addBattle(1);
                        event.player.sendSystemMessage(Component.literal("Added Battle Level"));
                    }
                });
//                // Thirst
//                event.player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
//                    if(thirst.getThirst() > 0 && event.player.getRandom().nextFloat() < 0.005f) {
//                        // Once every 10 sec on Avg
//                        thirst.subThirst(1);
//                        event.player.sendSystemMessage(Component.literal("Subtracted Thirst"));
//                    }
//                });



            }
        }
    }

    @Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {

        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(ModEntityTypes.MODSKELETON.get(), ModSkeleton.setAttributes());
            event.put(ModEntityTypes.MODZOMBIE.get(), ModZombie.setAttributes());
        }

    }


}
