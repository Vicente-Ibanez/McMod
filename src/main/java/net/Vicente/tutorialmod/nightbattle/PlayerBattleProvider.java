package net.Vicente.tutorialmod.nightbattle;

import net.Vicente.tutorialmod.thirst.PlayerThirst;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerBattleProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerBattle> PLAYER_BATTLE = CapabilityManager.get(new CapabilityToken<PlayerBattle>() { });

    private PlayerBattle battle = null;

    private final LazyOptional<PlayerBattle> optional = LazyOptional.of(this::createPlayerBattle);

    private PlayerBattle createPlayerBattle(){
        if(this.battle == null){
            this.battle = new PlayerBattle();
        }

        return this.battle;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_BATTLE){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerBattle().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerBattle().loadNBTData(nbt);
    }
}
