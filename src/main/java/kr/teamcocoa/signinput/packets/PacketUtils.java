package kr.teamcocoa.signinput.packets;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundOpenSignEditorPacket;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PacketUtils {

    public static void sendPackets(Player player, Packet... packets) {
        ServerGamePacketListenerImpl connection = ((CraftPlayer) player).getHandle().connection;
        for(Packet packet : packets) {
            connection.send(packet);
        }
    }

    public static Packet createBlockPositionPacket() {
        BlockPos position = new BlockPos(0, 0, 0);
        BlockState state = Block.byItem(CraftItemStack.asNMSCopy(new ItemStack(Material.OAK_SIGN)).getItem()).defaultBlockState();
        ClientboundBlockUpdatePacket packet = new ClientboundBlockUpdatePacket(position, state);
        return packet;
    }

    public static Packet createSignDefaultTextPacket(String[] lines) {

    }

    public static Packet createSignInputPacket() {
        BlockPos position = new BlockPos(0, 0, 0);
        ClientboundOpenSignEditorPacket packet = new ClientboundOpenSignEditorPacket(position);
        return packet;
    }

}
