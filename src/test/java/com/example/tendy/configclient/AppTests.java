package com.example.tendy.configclient;

import org.junit.jupiter.api.Test;

/**
 * @author tendy
 * @create 2019-11-29 10:05
 **/
public class AppTests {

    @Test
    public void testMatcher() {
        try {
            System.out.println("\\".matches("\\\\"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
