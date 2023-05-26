package de.arthurpicht.barnacleIntegrationTest.utils;

import de.arthurpicht.barnacle.configuration.BarnacleConfiguration;
import de.arthurpicht.barnacle.connectionManager.ConnectionManager;
import de.arthurpicht.barnacle.exceptions.DBConnectionException;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbMeta {

    public static List<String> getTableNames(BarnacleConfiguration barnacleConfiguration, Class<?> aClass)
            throws DBConnectionException, SQLException {

        ConnectionManager.initialize(barnacleConfiguration);
        List<String> tableNameList = new ArrayList<>();
        try (Connection connection = ConnectionManager.openConnection(aClass)) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            try (ResultSet resultSet
                         = databaseMetaData.getTables(
                    null, null, null, new String[]{"TABLE"})){
                while(resultSet.next()) {
                    tableNameList.add(resultSet.getString("TABLE_NAME"));
                }
            }
        }
        return tableNameList;
    }

}
