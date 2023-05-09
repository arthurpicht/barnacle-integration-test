package de.arthurpicht.barnacleIntegrationTest.tg_01.tc_02;

import de.arthurpicht.barnacle.configuration.BarnacleConfiguration;
import de.arthurpicht.barnacle.configuration.db.DbConnectionConfiguration;
import de.arthurpicht.barnacle.configuration.db.jdbc.JDBCConfiguration;
import de.arthurpicht.barnacle.configuration.db.jdbc.JDBCConfigurationBuilder;
import de.arthurpicht.barnacle.configuration.db.jdbc.direct.DirectJDBCConnectionConfiguration;
import de.arthurpicht.barnacle.configuration.db.jdbc.single.SingleJDBCConnectionConfiguration;
import de.arthurpicht.barnacle.configuration.generator.GeneratorConfiguration;
import de.arthurpicht.barnacle.exceptions.DBConnectionException;
import de.arthurpicht.barnacle.generator.BarnacleGenerator;
import de.arthurpicht.barnacleIntegrationTest.tg_01.BarnacleConfigurationCreatorTg01;
import de.arthurpicht.barnacleIntegrationTest.utils.DbMeta;
import de.arthurpicht.utils.test.CleanUp;
import de.arthurpicht.utils.test.TestIds;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Programmatic call of barnacle.
 * Generate schema on db based on configured DirectJDBCConnection.
 * Test table creation.
 *
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCase0102 {

    @BeforeAll
    void prepare() {
        CleanUp.cleanTestPath(
                this,
                Paths.get("src/main/java-gen"),
                "de.arthurpicht.barnacleIntegrationTest");
    }

    @Test
    @Order(1)
    void generateSchema() {
        BarnacleConfiguration barnacleConfiguration = createConfiguration();
        BarnacleGenerator.process(barnacleConfiguration);
    }

    @Test
    @Order(2)
    void checkTableCreated() throws SQLException, DBConnectionException {
        BarnacleConfiguration barnacleConfiguration = createConfiguration();
        List<String> tableNameList = DbMeta.getTableNames(barnacleConfiguration, TestCase0102.class);
        Assertions.assertTrue(tableNameList.contains("DUMMY"));
    }

    public BarnacleConfiguration createConfiguration() {
        GeneratorConfiguration generatorConfiguration
                = BarnacleConfigurationCreatorTg01.createGeneratorConfiguration(this);
        List<DbConnectionConfiguration> dbConnectionConfigurationList = new ArrayList<>();
        dbConnectionConfigurationList.add(createDbConnectionConfiguration(this));
        return new BarnacleConfiguration(generatorConfiguration, dbConnectionConfigurationList);
    }

    private DbConnectionConfiguration createDbConnectionConfiguration(Object testCase) {
        String testGroupId = TestIds.getTestGroupId(testCase);
        String testCaseId = TestIds.getTestCaseId(testCase);
        JDBCConfiguration jdbcConfiguration = new JDBCConfigurationBuilder(
                "*",
                "org.h2.Driver",
                "jdbc:h2:./db/" + testGroupId + "_" + testCaseId,
                "sa",
                "sa"
        ).build();
        return new DirectJDBCConnectionConfiguration(jdbcConfiguration);
    }

}
