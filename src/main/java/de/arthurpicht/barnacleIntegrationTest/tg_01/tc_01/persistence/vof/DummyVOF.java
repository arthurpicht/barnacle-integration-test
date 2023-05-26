package de.arthurpicht.barnacleIntegrationTest.tg_01.tc_01.persistence.vof;

import de.arthurpicht.barnacle.annotations.Annotations.Barnacle;
import de.arthurpicht.barnacle.annotations.Annotations.ColumnName;
import de.arthurpicht.barnacle.annotations.Annotations.PrimaryKey;

@Barnacle
public class DummyVOF {

    @Barnacle
    @ColumnName("id")
    @PrimaryKey
    protected String id;

    @Barnacle
    protected String text;

}
