package com.kempo.easyride.util;

import com.kempo.easyride.model.RawParticipants;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by dileng on 10/12/17.
 */
public class RideParserTest extends TestCase
{
    private RideParser parser = new RideParser();

    public void testEntryUnparsedWhenCannotParseLabelColumn()
    {
        final String input = "Bilbo Baggins\t4 main street\thobbit" +
                "\nGandalf\t4 amin street\tdriver\t2";
        final RawParticipants result = parser.parseInitialRequest(input);
        Assert.assertEquals(1, result.getDrivers().size());
        Assert.assertEquals("Gandalf", result.getDrivers().get(0).getName());
        Assert.assertEquals("4 amin street", result.getDrivers().get(0).getAddress());
        Assert.assertEquals(2, result.getDrivers().get(0).getSpaces());
        Assert.assertEquals(1, result.getUnclassifieds().size());
        Assert.assertEquals("Bilbo Baggins\t4 main street\thobbit", result.getUnclassifieds().get(0));
    }

    public void testDriverWhenNoSpacesListedIsUnclassified()
    {
        final String input = "Bilbo\t4 main street\tdriver";
        final RawParticipants result = parser.parseInitialRequest(input);
        Assert.assertEquals(1, result.getUnclassifieds().size());
        Assert.assertEquals(input, result.getUnclassifieds().get(0));
    }

    public void testDriverWhenSpacesNotANumberIsUnclassified()
    {
        final String input = "Bilbo\t4 main street\tdriver\tnan";
        final RawParticipants result = parser.parseInitialRequest(input);
        Assert.assertEquals(1, result.getUnclassifieds().size());
        Assert.assertEquals(input, result.getUnclassifieds().get(0));
    }

    public void testDriverInputHappyPath()
    {
        final String input = "Bilbo\t4 main street\tdriver\t5";
        final RawParticipants result = parser.parseInitialRequest(input);
        Assert.assertEquals(0, result.getUnclassifieds().size());
        Assert.assertEquals(1, result.getDrivers().size());
        Assert.assertEquals(5, result.getDrivers().get(0).getSpaces());
    }

    public void testRider()
    {
        final String input = "Bilbo\t4 main street\trider";
        final RawParticipants result = parser.parseInitialRequest(input);
        Assert.assertEquals(0, result.getUnclassifieds().size());
        Assert.assertEquals(0, result.getDrivers().size());
        Assert.assertEquals(1, result.getRiders().size());
        Assert.assertEquals("Bilbo", result.getRiders().get(0).getName());
        Assert.assertEquals("4 main street", result.getRiders().get(0).getAddress());
    }
}