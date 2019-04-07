package com.amath.spacetrader;

import com.amath.spacetrader.entity.Coordinate;
import com.amath.spacetrader.entity.SpaceBody;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MaxZuoUnitTest {
    //Max Zuo
    @Test
    public void testCoordinateEquals() {
        Coordinate c1 = new Coordinate(1.0, 1.0);
        Coordinate c2 = new Coordinate(1.0, 1.0);
        Coordinate c3 = new Coordinate(1.0, 2.0);
        Coordinate c4 = new Coordinate(2.0, 2.0);

        assertNotNull(c1);
        assertNotNull(c2);
        assertNotNull(c3);
        assertNotNull(c4);

        assertEquals(false, c1.equals(null));
        assertEquals(true, c1.equals(c1));

        assertEquals(true, c1.equals(c2));
        assertEquals(false, c1.equals(c3));
        assertEquals(false, c1.equals(c4));
    }

    //Max Zuo
    @Test
    public void testSpaceBodyOverlap() {
        //Testing the SpaceBody overlap method
        class SBody extends SpaceBody {
            SBody(Coordinate location, double radius) {
                super(location, radius);
            }

            boolean overlap(SpaceBody other) {
                return this.overlap(this.location, other);
            }

            void init(Coordinate location, double radius) {
                this.location = location;
                this.radius = radius;
            }
        }
        //Original Space Body
        SBody s1 = new SBody(new Coordinate(0, 0), 10);

        //Borders touch –should not count as overlap
        SpaceBody s2 = new SpaceBody(new Coordinate(11.0, 0.0), 1.0) {};

        //Borders just overlap
        SpaceBody s3 = new SpaceBody(new Coordinate(11.0, 0.0), 1.000000000001) {};

        //s1 is in s4
        SpaceBody s4 = new SpaceBody(new Coordinate(0.0, 10.0), 1000.0) {};

        //s5 is in s1
        SpaceBody s5 = new SpaceBody(new Coordinate(0.0, 5.0), 2.0) {};

        //s6 is nowhere near s1
        SpaceBody s6 = new SpaceBody(new Coordinate(10.0, 8.0), 2.0) {};

        //s7 is almost touching s1
        SpaceBody s7 = new SpaceBody(new Coordinate(11.0, 0.0), 0.99999999999) {};
        //s8 – reversed overlap
        Coordinate s8location = new Coordinate(20.0, 0);
        SBody s8 = new SBody(s8location, 5);

        assertNotNull(s1);
        assertNotNull(s2);
        assertNotNull(s3);
        assertNotNull(s4);
        assertNotNull(s5);


        assertEquals(false, s1.overlap(s2));
        assertEquals(true, s1.overlap(s3));

        assertEquals(true, s1.overlap(s1));
        assertEquals(true, s1.overlap(s4));
        assertEquals(true, s1.overlap(s5));
        assertEquals(false, s1.overlap(s6));
        assertEquals(false, s1.overlap(s7));
        while (s8location.getX() > -15) {
            s8.init(s8location, 5);
            assertEquals(true, s1.overlap(s8) == s8.overlap(s1));
            s8location.setX(s8location.getX() - 1.1);
        }
        assertEquals(false, s1.overlap(null));
        s1.init(null, 10);
        assertEquals(false, s1.overlap(s1));

    }
}
