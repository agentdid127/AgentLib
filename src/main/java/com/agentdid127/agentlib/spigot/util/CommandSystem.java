package com.agentdid127.agentlib.spigot.util;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class CommandSystem {

    //Instance Variables
    Plugin plugin;
    CommandExecutor commandExecutor;


    /**
     * Initialize variables
     *
     * @param plugin
     * @param commandExecutor
     */
    public CommandSystem(Plugin plugin, CommandExecutor commandExecutor) {
        this.plugin = plugin;
        this.commandExecutor = commandExecutor;
    }

    /**
     * Registers user commands, and sets permissions
     *
     * @param permission
     * @param aliases
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public void registerCommand(String permission, String description, String... aliases) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        PluginCommand command = getCommand(aliases[0], plugin);

        command.setPermission("studios." + permission);
        command.setDescription(description);
        command.setAliases(Arrays.asList(aliases));
        getCommandMap().register(plugin.getDescription().getName(), command);


        command.setExecutor(commandExecutor);
    }


    /**
     * gets command
     *
     * @param name
     * @param plugin
     * @return
     */
    private static PluginCommand getCommand(String name, Plugin plugin) {
        PluginCommand command = null;

        try {
            Constructor<PluginCommand> c = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            c.setAccessible(true);

            command = c.newInstance(name, plugin);
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return command;
    }


    /**
     * gets command map
     *
     * @return
     */
    private static CommandMap getCommandMap() {
        CommandMap commandMap = null;

        try {
            if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
                Field f = SimplePluginManager.class.getDeclaredField("commandMap");
                f.setAccessible(true);

                commandMap = (CommandMap) f.get(Bukkit.getPluginManager());
            }
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return commandMap;
    }
}