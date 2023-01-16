package net.Vicente.tutorialmod.entity;

import net.Vicente.tutorialmod.TutorialMod;
import net.Vicente.tutorialmod.entity.custom.ModSkeleton;
import net.Vicente.tutorialmod.entity.custom.ModZombie;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TutorialMod.MOD_ID);

    public static final RegistryObject<EntityType<ModSkeleton>> MODSKELETON =
            ENTITY_TYPES.register("modskeleton",
                    () -> EntityType.Builder.of(ModSkeleton::new, MobCategory.MONSTER)
                            .sized(0.4f, 1.5f)
                            .build(new ResourceLocation(TutorialMod.MOD_ID, "modskeleton").toString()));
    public static final RegistryObject<EntityType<ModZombie>> MODZOMBIE =
                ENTITY_TYPES.register("modzombie",
                    () -> EntityType.Builder.of(ModZombie::new, MobCategory.MONSTER)
                            .sized(0.4f, 1.5f)
                            .build(new ResourceLocation(TutorialMod.MOD_ID, "modzombie").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
