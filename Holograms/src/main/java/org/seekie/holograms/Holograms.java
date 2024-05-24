package org.seekie.holograms;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.seekie.holograms.HologramsCommands.HologramsCommand;

public final class Holograms extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Enabled");

        getCommand("holograms").setExecutor(new HologramsCommand(this));

    }

    public static String colorize(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
