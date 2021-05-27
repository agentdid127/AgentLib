package com.agentdid127.agentlib.bungee;

import net.md_5.bungee.api.plugin.Plugin;
import java.util.logging.Level;

public class BungeePlugin extends Plugin {

    /**
     * When Plugin enables, run this
     */
    @Override
    public void onEnable() {
        getLogger().log(Level.WARNING, "AgentLib Enabled");

    }


    /**
     * Run this when plugin disables./
     */
    @Override
    public void onDisable() {
        getLogger().warning("AgentLib Disabled");
    }
}
