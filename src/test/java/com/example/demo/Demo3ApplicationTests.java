package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Demo3ApplicationTests {

    @Test
    public void contextLoads() {
        String name = "baris";
        Assert.assertEquals("baris",name);
    }

}
