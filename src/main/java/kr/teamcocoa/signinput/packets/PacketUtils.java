package kr.teamcocoa.signinput.packets;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundOpenSignEditorPacket;
import net.minecraft.network.protocol.game.ServerboundSignUpdatePacket;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_18_R2.util.CraftMagicNumbers;
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

    public static Packet createSignDefaultTextPacket() {
        if(lines.length != 4) {
            throw new IllegalArgumentException("A length of lines should be 4!");
        }

//        for (String line : lines) {
//            if(line.length() > 15) {
//                throw new IllegalArgumentException("Each length of a line should be lower than 16!");
//            }
//        }

        BlockPos position = new BlockPos(0, 0, 0);
        ServerboundSignUpdatePacket packet = new ServerboundSignUpdatePacket(position, lines[0], lines[1], lines[2], lines[3]);
        return packet;
    }

    public static void sendSignInputPacket(String[] lines) {
        if(lines.length != 4) {
            throw new IllegalArgumentException("A length of lines should be 4!");
        }

        BlockPos position = new BlockPos(0, 0, 0);
        BlockState state = CraftMagicNumbers.getBlock(Material.OAK_SIGN, (byte) 0);
        ClientboundBlockUpdatePacket blockPositionUpdatePacket = new ClientboundBlockUpdatePacket(position, state);

        net.minecraft.world.level.block.entity.SignBlockEntity

        ClientboundOpenSignEditorPacket packet = new ClientboundOpenSignEditorPacket(position);

    }

}
