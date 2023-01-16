package net.Vicente.tutorialmod.event;

import net.Vicente.tutorialmod.TutorialMod;
import net.Vicente.tutorialmod.nightbattle.PlayerBattleProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class NightBattles {

    public static final DeferredRegister<Activity> NIGHT_BATTLE =
            DeferredRegister.create(ForgeRegistries.ACTIVITIES, TutorialMod.MOD_ID);


    public NightBattles(LivingEntityUseItemEvent event) {
        event.getEntity().getCapability(PlayerBattleProvider.PLAYER_BATTLE);
//        LivingEntity entity = event.getEntity();

//        System.out.println("Preview");
//        System.out.println(event.getEntity().getActiveEffects());
//        System.out.println("End Preview");
        // not getting mob effect ;( not getting that the list contains UNLUCK
        if(event.getEntity().getActiveEffects().contains(MobEffects.UNLUCK) && event.getEntity().getY() > 64){
            event.getEntity().removeEffect(MobEffects.UNLUCK);
            event.getEntity().removeEffect(MobEffects.HUNGER);
            event.getEntity().removeEffect(MobEffects.DIG_SLOWDOWN);
            event.getEntity().removeEffect(MobEffects.POISON);
            System.out.println("1");

        }else if (!event.getEntity().getActiveEffects().contains(MobEffects.UNLUCK) && event.getEntity().getY() < 64) {
            event.getEntity().addEffect(new MobEffectInstance(MobEffects.UNLUCK, 20000, 0));
            event.getEntity().addEffect(new MobEffectInstance(MobEffects.HUNGER, 20000, 3));
            System.out.println("2");
            System.out.println(event.getEntity().getActiveEffects().contains(MobEffects.UNLUCK));


        }else if(event.getEntity().getActiveEffects().contains(MobEffects.UNLUCK) && event.getEntity().getY() < 64){
            if(event.getEntity().getEffect(MobEffects.UNLUCK).getDuration() < 5000 && !event.getEntity().getActiveEffects().contains(MobEffects.DIG_SLOWDOWN)){
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.POISON, 20000, 1));
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 10000, 0));
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.WITHER, 10, 0));
                System.out.println("3");

            }else if(event.getEntity().getEffect(MobEffects.UNLUCK).getDuration() < 10000 && !event.getEntity().getActiveEffects().contains(MobEffects.LEVITATION)) {
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.POISON, 20000, 0));
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.LEVITATION, 1000, 0));
                System.out.println("4");
            }

        }


    }

    public static void register(IEventBus eventBus) {
        NIGHT_BATTLE.register(eventBus);
    }

}
