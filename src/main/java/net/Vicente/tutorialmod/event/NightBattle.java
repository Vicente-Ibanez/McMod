package net.Vicente.tutorialmod.event;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

import javax.annotation.Nonnull;

public class NightBattle extends SavedData {

    @Nonnull
    public static NightBattle get(Level level){
        if (level.isClientSide) {
            throw new RuntimeException("Don't access this client-side!");
        }
        DimensionDataStorage storage = ((ServerLevel)level).getDataStorage();
        return storage.computeIfAbsent(NightBattle::new, NightBattle::new, "nightbattlehandler");
    }

    public NightBattle(){
    }

    public NightBattle(CompoundTag tag){

    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        return tag;
    }


}
