package kr.teamcocoa.signinput;

import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.settings.PacketEventsSettings;
import io.github.retrooper.packetevents.utils.server.ServerVersion;
import org.bukkit.plugin.java.JavaPlugin;

public class SignInput extends JavaPlugin {

    private static SignInput instance;

    @Override
    public void onLoad() {
        PacketEvents.create(this);
        PacketEventsSettings settings = PacketEvents.get().getSettings();

        settings
                .fallbackServerVersion(ServerVersion.v_1_18_2)
                .checkForUpdates(false)
                .bStats(true);
        PacketEvents.get().load();

    }

    @Override
    public void onEnable() {

    }

    public static SignInput getInstance() {
        return instance;
    }
}
