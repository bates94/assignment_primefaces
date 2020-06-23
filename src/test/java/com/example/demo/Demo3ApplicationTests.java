package com.example.demo;

import com.example.demo.models.AddressDetails;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.utils.AddressType;
import org.hibernate.criterion.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo3ApplicationTests {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void contextLoads() {
        String name = "baris";
        Assert.assertEquals("baris",name);
    }

    @Test
    public void orderRepositoryTest() {
        AddressDetails bad = new AddressDetails();
        bad.setAddressType(AddressType.BILLING_ADDRESS);
        bad.setUserName("barisates");
        orderRepository.save(bad);
    }

}
