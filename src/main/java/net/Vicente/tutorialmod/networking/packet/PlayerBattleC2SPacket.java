package net.Vicente.tutorialmod.networking.packet;
//
//
//import net.Vicente.tutorialmod.client.ClientBattleData;
//import net.minecraft.network.FriendlyByteBuf;
//import net.minecraftforge.network.NetworkEvent;
//
//import java.util.function.Supplier;
//
//public class PlayerBattleC2SPacket {
//    private final int battle;
//    public PlayerBattleC2SPacket(int battle){
//        this.battle = battle;
//    }
//
//    public BattleDataSyncS2CPacket(FriendlyByteBuf buf){this.battle = buf.readInt();}
//
//    public void toBytes(FriendlyByteBuf buf) {buf.writeInt(battle);}
//
//    public boolean handle(Supplier<NetworkEvent.Context> supplier){
//        NetworkEvent.Contet context = supplier.get();
//        context.enqueueWork(() -> {
//            // Here we are on the client
//            ClientBattleData.set(battle);
//        });
//        return true;
//    }
//}
