package kr.teamcocoa.signinput.packets;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundOpenSignEditorPacket;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_18_R2.block.CraftSign;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_18_R2.util.CraftMagicNumbers;
import org.bukkit.entity.Player;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PacketUtils {

    public static void sendPackets(Player player, Packet... packets) {
        ServerGamePacketListenerImpl connection = ((CraftPlayer) player).getHandle().connection;
        for(Packet packet : packets) {
            connection.send(packet);
        }
    }

    public static void sendSignInputPacket(Player player, String[] lines) {
        if(lines.length != 4) {
            throw new IllegalArgumentException("A length of lines should be 4!");
        }

        BlockPos position = new BlockPos(0, 1, 0);
        BlockState state = CraftMagicNumbers.getBlock(Material.OAK_SIGN, (byte) 0);

        Component[] components = CraftSign.sanitizeLines(lines);
        SignBlockEntity sign = new SignBlockEntity(position, Blocks.OAK_SIGN.defaultBlockState());

        for (int i = 0; i < components.length; i++) {
            sign.setMessage(i, components[i]);
        }

        ClientboundBlockUpdatePacket blockUpdatePacket = new ClientboundBlockUpdatePacket(position, state);
        ClientboundBlockEntityDataPacket setSignLinePacket = sign.getUpdatePacket();
        ClientboundOpenSignEditorPacket signOpenPacket = new ClientboundOpenSignEditorPacket(position);

        sendPackets(player, blockUpdatePacket, setSignLinePacket, signOpenPacket);
    }

}
