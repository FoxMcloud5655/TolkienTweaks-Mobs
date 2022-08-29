package com.greatorator.tolkienmobs.network;

import codechicken.lib.packet.PacketCustom;
import codechicken.lib.packet.PacketCustomChannelBuilder;
import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.event.EventNetworkChannel;

/**
 * Created by brandon3055 on 20/08/22.
 */
public class TTMNetwork {

    public static final ResourceLocation CHANNEL = new ResourceLocation(TolkienMobs.MODID + ":network");
    public static EventNetworkChannel netChannel;

    //Server to client

    //Client to server
    public static final int S_UPDATE_SIGN = 1;

    public static void sendSignUpdate(BlockPos blockPos, String line1, String line2, String line3, String line4) {
        PacketCustom packet = new PacketCustom(CHANNEL, S_UPDATE_SIGN);
        packet.writePos(blockPos);
        packet.writeString(line1);
        packet.writeString(line2);
        packet.writeString(line3);
        packet.writeString(line4);
        packet.sendToServer();

    }

    public static void init() {
        netChannel = PacketCustomChannelBuilder.named(CHANNEL)
                .networkProtocolVersion(() -> "1")
                .clientAcceptedVersions(e -> true)
                .serverAcceptedVersions(e -> true)
                .assignClientHandler(() -> ClientPacketHandler::new)
                .assignServerHandler(() -> ServerPacketHandler::new)
                .build();
    }
}