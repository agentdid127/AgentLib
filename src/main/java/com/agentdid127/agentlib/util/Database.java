package com.agentdid127.agentlib.util;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Database {

    /**
     * Grabs info from the database.
     * @param config Server Config File
     * @param type SQL Query type
     * @param command SQL command
     * @return ResultSet results
     * @throws SQLException
     */
    public static ArrayList<String> database(String[] config, String type, String command, String... sort) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        String connectionUrl= config[0];
        String user = config[1];
        String password = config[2];

        ArrayList<String> out = new ArrayList<String>();
        if (type.equals("select")) {
            try (
                    Connection connection = DriverManager.getConnection(connectionUrl, user, password);
                    Statement statement = connection.createStatement();) {

                // Create and execute a SELECT SQL statement.
                String selectSql = command;
                ResultSet resultSet = statement.executeQuery(selectSql);

                while (resultSet.next())
                {
                    String items = "";
                    for (String item : sort) {

                        items += resultSet.getString(item) + ",";
                    }
                    if (!items.equals(""))
                        items = items.substring(0, items.length() -1);
                    out.add(items);

                }
                // Print results from select statement

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (type.equals("insert")) {
            try (Connection connection = DriverManager.getConnection(connectionUrl, user, password);
                 PreparedStatement prepsInsertProduct = connection.prepareStatement(command, Statement.RETURN_GENERATED_KEYS);) {

                prepsInsertProduct.execute();
                // Retrieve the generated key from the insert.
                ResultSet resultSet = prepsInsertProduct.getGeneratedKeys();


                while (resultSet.next())
                {
                    String items = "";
                    for (String item : sort) {

                        items += resultSet.getString(item) + ",";
                    }
                    if (!items.equals(""))
                        items = items.substring(0, items.length() -1);
                    out.add(items);

                }
                // Print the ID of the inserted row.
            }
            // Handle any errors that may have occurred.
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("update")) {
            try (Connection connection = DriverManager.getConnection(connectionUrl, user, password);
                 PreparedStatement prepsUpdateProduct = connection.prepareStatement(command, Statement.RETURN_GENERATED_KEYS);) {

                prepsUpdateProduct.execute();
                // Retrieve the generated key from the insert.
                ResultSet resultSet = prepsUpdateProduct.getGeneratedKeys();


                while (resultSet.next())
                {
                    String items = "";
                    for (String item : sort) {

                        items += resultSet.getString(item) + ",";
                    }
                    if (!items.equals(""))
                        items = items.substring(0, items.length() -1);
                    out.add(items);

                }
                // Print the ID of the inserted row.
            }
            // Handle any errors that may have occurred.
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        //System.out.println(out);
        return out;
    }

}


