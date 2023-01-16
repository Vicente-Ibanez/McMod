package net.Vicente.tutorialmod.item;

import net.Vicente.tutorialmod.TutorialMod;
import net.Vicente.tutorialmod.entity.ModEntityTypes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    // add item
    // registeries object of type <item> Alias = EVENTBUS.register_event_bus("item_name")
    // Second Line gives the item properties
    // to make new items add a new registeryobject and change names
    public static final RegistryObject<Item> ZIRCON = ITEMS.register("zircon",
            ()->new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));
    public static final RegistryObject<Item> RAW_ZIRCON = ITEMS.register("raw_zircon",
            ()->new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> MOD_SKELETON_EGG = ITEMS.register("mod_skeleton_egg",
            ()-> new ForgeSpawnEggItem(ModEntityTypes.MODSKELETON, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));
    public static final RegistryObject<Item> MOD_ZOMBIE_EGG = ITEMS.register("mod_zombie_egg",
                ()-> new ForgeSpawnEggItem(ModEntityTypes.MODZOMBIE, 0x22b341, 0x19732e,
                        new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
