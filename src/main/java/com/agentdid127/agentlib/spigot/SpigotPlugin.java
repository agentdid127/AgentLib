package com.agentdid127.agentlib.spigot;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class SpigotPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getLogger().log(Level.WARNING, "AgentLib Enabled");

    }

    @Override
    public void onDisable() {
        this.getLogger().log(Level.WARNING, "AgentLib Disabled");
    }
}
