package com.agentdid127.agentlib.spigot.util;

import org.bukkit.configuration.file.FileConfiguration;
import com.agentdid127.agentlib.util.Database;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseSpigot {

    /**
     * Gets Database for spigot
     * @param config
     * @param type
     * @param command
     * @param sort
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     */
    public static ArrayList<String> database(FileConfiguration config, String type, String command, String... sort) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {

        String connectionUrl = "jdbc:mysql://" + config.getString("sql.host") + ":" + config.getString("sql.port") + "/"
                + config.getString("sql.database") + "?useSSL=false";
        String user = config.getString("sql.user");
        String password = config.getString("sql.password");
        String[] db = {connectionUrl, user, password};
        return Database.database(db, type, command, sort);
    }
}
