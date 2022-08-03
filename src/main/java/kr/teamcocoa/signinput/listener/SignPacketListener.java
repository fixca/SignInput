package kr.teamcocoa.signinput.listener;

import io.github.retrooper.packetevents.event.PacketListenerAbstract;
import io.github.retrooper.packetevents.event.PacketListenerPriority;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.updatesign.WrappedPacketInUpdateSign;
import kr.teamcocoa.signinput.SignInputManager;
import org.bukkit.entity.Player;

public class SignPacketListener extends PacketListenerAbstract {

    public SignPacketListener() {
        super(PacketListenerPriority.HIGH);
    }

    @Override
    public void onPacketPlayReceive(PacketPlayReceiveEvent e) {
        Player player = e.getPlayer();

        if(e.getPacketId() == PacketType.Play.Client.UPDATE_SIGN) {
            if(!SignInputManager.getInstance().isPlayerPendingInput(player)) {
                return;
            }

            WrappedPacketInUpdateSign signPacket = new WrappedPacketInUpdateSign(e.getNMSPacket());
            signPacket.getTextLines();

            e.setCancelled(true);
        }
    }
}
