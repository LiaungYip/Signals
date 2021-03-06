package com.minemaarten.signals.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler{

    private static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("Signals");
    private static int discriminant;

    public static void init(){

        INSTANCE.registerMessage(PacketUpdateGui.class, PacketUpdateGui.class, discriminant++, Side.CLIENT);
        INSTANCE.registerMessage(PacketSyncStationNames.class, PacketSyncStationNames.class, discriminant++, Side.CLIENT);
        INSTANCE.registerMessage(PacketUpdateMessage.class, PacketUpdateMessage.class, discriminant++, Side.CLIENT);
        INSTANCE.registerMessage(PacketUpdateNetworkController.class, PacketUpdateNetworkController.class, discriminant++, Side.CLIENT);
        INSTANCE.registerMessage(PacketUpdateMinecartPath.class, PacketUpdateMinecartPath.class, discriminant++, Side.CLIENT);
        INSTANCE.registerMessage(PacketSpawnParticle.class, PacketSpawnParticle.class, discriminant++, Side.CLIENT);
        INSTANCE.registerMessage(PacketUpdateMinecartEngineState.class, PacketUpdateMinecartEngineState.class, discriminant++, Side.CLIENT);

        INSTANCE.registerMessage(PacketGuiButton.class, PacketGuiButton.class, discriminant++, Side.SERVER);
        INSTANCE.registerMessage(PacketUpdateTextfield.class, PacketUpdateTextfield.class, discriminant++, Side.SERVER);
        INSTANCE.registerMessage(PacketUpdateTextfieldEntity.class, PacketUpdateTextfieldEntity.class, discriminant++, Side.SERVER);
    }

    public static void sendToAll(IMessage message){

        INSTANCE.sendToAll(message);
    }

    public static void sendTo(IMessage message, EntityPlayerMP player){

        INSTANCE.sendTo(message, player);
    }

    public static void sendToAllAround(LocationIntPacket message, World world, double distance){

        sendToAllAround(message, message.getTargetPoint(world, distance));
    }

    public static void sendToAllAround(LocationIntPacket message, World world){

        sendToAllAround(message, message.getTargetPoint(world));
    }

    public static void sendToAllAround(LocationDoublePacket message, World world){

        sendToAllAround(message, message.getTargetPoint(world));
    }

    public static void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint point){

        INSTANCE.sendToAllAround(message, point);
    }

    public static void sendToDimension(IMessage message, int dimensionId){

        INSTANCE.sendToDimension(message, dimensionId);
    }

    public static void sendToServer(IMessage message){

        INSTANCE.sendToServer(message);
    }
}
