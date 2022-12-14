package kr.teamcocoa.signinput;

import kr.teamcocoa.signinput.packets.PacketUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class SignInputManager {

    private static SignInputManager instance;

    public static SignInputManager getInstance() {
        if(instance == null) {
            instance = new SignInputManager();
        }
        return instance;
    }

    private HashMap<UUID, SignInputModel> onPendingInputList = new HashMap<>();

    public boolean isPlayerPendingInput(Player player) {
        return onPendingInputList.containsKey(player.getUniqueId());
    }

    public SignInputModel getModel(Player player) {
        return onPendingInputList.getOrDefault(player.getUniqueId(), null);
    }

    public void setPlayerInputExecuted(Player player) {
        onPendingInputList.remove(player);
    }

    public void startInput(SignInputModel model) {
        Player player = model.getPlayer();

        if(isPlayerPendingInput(player)) {
            return;
        }

        onPendingInputList.put(player.getUniqueId(), model);

        PacketUtils.sendSignInputPacket(player, model.getLines());
    }

}
