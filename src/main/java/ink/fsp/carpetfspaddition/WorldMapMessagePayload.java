package ink.fsp.carpetfspaddition;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class WorldMapMessagePayload implements CustomPayload {
    public static final PacketCodec<PacketByteBuf, WorldMapMessagePayload> STREAM_CODEC = CustomPayload.codecOf(WorldMapMessagePayload::write, WorldMapMessagePayload::new);
    public Id<WorldMapMessagePayload> TYPE;

    public WorldMapMessagePayload(PacketByteBuf packetByteBuf) {
    }

    @Override
    public Id<? extends WorldMapMessagePayload> getId() {
        return TYPE;
    }
    public void write(PacketByteBuf output) {
//        output.writeNbt(data);
    }
    public void setTYPE(Identifier identifier) {
        TYPE = new CustomPayload.Id<>(identifier);
    }
}
