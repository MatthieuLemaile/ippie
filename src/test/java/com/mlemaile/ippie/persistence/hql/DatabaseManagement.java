package com.mlemaile.ippie.persistence.hql;

import java.io.File;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseManagement {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseManagement.class);

    private static IDatabaseTester databaseTester;
    // Initializing databaseTester
    static {
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(
                PropertiesConfiguration.class).configure(
                        params.properties().setFileName("database.properties"));
        Configuration config;
        try {
            config = builder.getConfiguration();
            String startUrl = config.getString("start-url");
            String host = config.getString("host");
            String database = config.getString("database");
            String user = config.getString("user");
            String password = config.getString("password");
            String zeroDataTimeBehavior = config.getString("zeroDateTimeBehavior");
            String url = startUrl + host + "/" + database + "?user=" + user + "&password="
                    + password + "&zeroDateTimeBehavior=" + zeroDataTimeBehavior;

            databaseTester = new JdbcDatabaseTester("org.mariadb.jdbc.Driver", url);
        } catch (ConfigurationException | ClassNotFoundException e) {
            LOGGER.error("Error while configuring the database",e);
            throw new TestDatabaseInitialisationException("Error while configuring the database",
                    e);
        }
    }

    private static IDataSet readDataSet () throws Exception {
        return new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/databaseTest.xml"));
    }

    public static void setUpDatabase () throws Exception {
        if (databaseTester == null) {
            RuntimeException re = new TestDatabaseInitialisationException(
                    "Can't initilize the test database");
            LOGGER.error("Failed to initialise the test database", re);
            throw re;
        }
        IDataSet dataSet = readDataSet();
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    public static void tearDownDatabase () throws Exception {
        databaseTester.onTearDown();
    }
}
