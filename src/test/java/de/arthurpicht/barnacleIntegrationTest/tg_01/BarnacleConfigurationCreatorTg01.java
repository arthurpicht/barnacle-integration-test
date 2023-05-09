package de.arthurpicht.barnacleIntegrationTest.tg_01;

import de.arthurpicht.barnacle.Const;
import de.arthurpicht.barnacle.configuration.BarnacleConfiguration;
import de.arthurpicht.barnacle.configuration.db.DbConnectionConfiguration;
import de.arthurpicht.barnacle.configuration.db.jdbc.JDBCConfiguration;
import de.arthurpicht.barnacle.configuration.db.jdbc.JDBCConfigurationBuilder;
import de.arthurpicht.barnacle.configuration.db.jdbc.single.SingleJDBCConnectionConfiguration;
import de.arthurpicht.barnacle.configuration.generator.GeneratorConfiguration;
import de.arthurpicht.barnacle.configuration.generator.GeneratorConfigurationBuilder;
import de.arthurpicht.utils.test.TestIds;

import java.util.ArrayList;
import java.util.List;

public class BarnacleConfigurationCreatorTg01 {

    public static GeneratorConfiguration createGeneratorConfiguration(Object testCase) {
        String testGroupId = TestIds.getTestGroupId(testCase);
        String testCaseId = TestIds.getTestCaseId(testCase);

        GeneratorConfigurationBuilder generatorConfigurationBuilder = new GeneratorConfigurationBuilder(
                "de.arthurpicht.barnacleIntegrationTest." + testGroupId + "." + testCaseId + ".persistence.vof",
                "de.arthurpicht.barnacleIntegrationTest." + testGroupId + "." + testCaseId + ".persistence.vo",
                "de.arthurpicht.barnacleIntegrationTest." + testGroupId + "." + testCaseId + ".persistence.vob",
                "de.arthurpicht.barnacleIntegrationTest." + testGroupId + "." + testCaseId + ".persistence.dao"
        );
        generatorConfigurationBuilder.withDialect(Const.Dialect.H2);
        generatorConfigurationBuilder.withSrcDir("src/main/java");
        generatorConfigurationBuilder.withSrcGenDir("src/main/java-gen");
        generatorConfigurationBuilder.withExecuteOnDb(true);

        return generatorConfigurationBuilder.build();
    }

}
