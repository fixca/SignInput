package kr.teamcocoa.signinput.listener;

import io.github.retrooper.packetevents.event.PacketListenerAbstract;
import io.github.retrooper.packetevents.event.PacketListenerPriority;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.updatesign.WrappedPacketInUpdateSign;
import kr.teamcocoa.signinput.InputCallBack;
import kr.teamcocoa.signinput.SignInputManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SignPacketListener extends PacketListenerAbstract {

    public SignPacketListener() {
        super(PacketListenerPriority.HIGH);
    }

    @Override
    public void onPacketPlayReceive(PacketPlayReceiveEvent e) {
        Player player = e.getPlayer();

        if(e.getPacketId() == PacketType.Play.Client.UPDATE_SIGN) {
            Bukkit.getLogger().info(player.getName() + "sent Sign Update Packet!");
            if(!SignInputManager.getInstance().isPlayerPendingInput(player)) {
                Bukkit.getLogger().info(player.getName() + "sent sign update packet without being in pending list!");
                return;
            }

            WrappedPacketInUpdateSign signPacket = new WrappedPacketInUpdateSign(e.getNMSPacket());

            InputCallBack callBack = SignInputManager.getInstance().getModel(player).getCallBack();
            callBack.onInput(signPacket.getTextLines());

            SignInputManager.getInstance().setPlayerInputExecuted(player);

            Bukkit.getLogger().info(player.getName() + "'s callback was executed!");

            e.setCancelled(true);
        }
    }
}
