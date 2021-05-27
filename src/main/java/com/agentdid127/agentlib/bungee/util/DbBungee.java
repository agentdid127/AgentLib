package com.agentdid127.agentlib.bungee.util;

import com.agentdid127.agentlib.util.Database;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbBungee {
    /**
     * Connects the Bungee Plugin to the Database.
     * @param configIn Server Config File
     * @param type SQL Query type
     * @param command SQL command
     * @return ResultSet results
     * @throws SQLException
     */
    public static ArrayList<String> database(File configIn, String type, String command, String... sort) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {

        Configuration config = ConfigurationProvider.getProvider(net.md_5.bungee.config.YamlConfiguration.class).load(configIn);
        String connectionUrl = "jdbc:mysql://" + config.getString("sql.host") + ":" + config.getString("sql.port") + "/"
                + config.getString("sql.database") + "?useSSL=false";
        String user = config.getString("sql.user");
        String password = config.getString("sql.password");
        String[] db = {connectionUrl, user, password};

        return Database.database(db, type, command, sort);
    }

    }