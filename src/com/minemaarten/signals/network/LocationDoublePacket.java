package com.minemaarten.signals.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * MineChess
 * 
 * @author MineMaarten www.minemaarten.com
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public abstract class LocationDoublePacket<REQ extends AbstractPacket> extends AbstractPacket<REQ> {

    protected double x, y, z;

    public LocationDoublePacket() {

    }

    public LocationDoublePacket(double x, double y, double z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void toBytes(ByteBuf buf) {

        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
    }

    @Override
    public void fromBytes(ByteBuf buf) {

        x = buf.readDouble();
        y = buf.readDouble();
        z = buf.readDouble();
    }

    public NetworkRegistry.TargetPoint getTargetPoint(World world) {

        return getTargetPoint(world, 64);
    }

    public NetworkRegistry.TargetPoint getTargetPoint(World world, double updateDistance) {

        return new NetworkRegistry.TargetPoint(world.provider.getDimension(), x, y, z, updateDistance);
    }
}
