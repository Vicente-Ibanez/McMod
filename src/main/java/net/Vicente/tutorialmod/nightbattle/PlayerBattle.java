package net.Vicente.tutorialmod.nightbattle;

import net.minecraft.nbt.CompoundTag;

public class PlayerBattle {
    // Saves Data
    private int battle;
    private final int MAX_BATTLE = 10;
    private final int MIN_BATTLE = 0;

    public int getBattle() {return battle;}

    public void addBattle(int add) {this.battle = Math.min(battle + add, MAX_BATTLE);}
    public void subBattle(int sub) {this.battle = Math.max(battle - sub, MIN_BATTLE);}

    public void copyFrom(PlayerBattle source) {this.battle = source.battle;}

    public void saveNBTData(CompoundTag nbt) {nbt.putInt("battle", battle);}

    public void loadNBTData(CompoundTag nbt){
        battle = nbt.getInt("battle");
    }
}
