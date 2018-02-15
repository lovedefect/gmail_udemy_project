package com.appsenseca;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Created by appken on 2016-01-25.
 * This is to show you how to run test in a fixed order
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FixedOrderTest {
    @Test
    public void S01_Test() {
        System.out.println("first");
    }

    @Test
    public void S02_Test() {
        System.out.println("second");
    }
}