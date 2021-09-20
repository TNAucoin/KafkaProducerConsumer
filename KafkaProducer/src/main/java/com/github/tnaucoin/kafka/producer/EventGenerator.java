package com.github.tnaucoin.kafka.producer;

import com.github.javafaker.Faker;
import com.github.tnaucoin.kafka.producer.enums.Color;
import com.github.tnaucoin.kafka.producer.enums.DesignType;
import com.github.tnaucoin.kafka.producer.enums.ProductType;
import com.github.tnaucoin.kafka.producer.enums.UserId;
import com.github.tnaucoin.kafka.producer.model.Event;
import com.github.tnaucoin.kafka.producer.model.Product;
import com.github.tnaucoin.kafka.producer.model.User;

public class EventGenerator {
    private Faker faker = new Faker();

    public Event generateEvent() {
        return Event.builder()
                .user(generateRandomUser())
                .product(generateRandomObject())
                .build();
    }

    private Product generateRandomObject() {
        return Product.builder()
                .color(faker.options().option(Color.class))
                .designType(faker.options().option(DesignType.class))
                .productType(faker.options().option(ProductType.class))
                .build();
    }

    private User generateRandomUser() {
        return User.builder()
                .userId(faker.options().option(UserId.class))
                .username(faker.name().lastName())
                .dateOfBirth(faker.date().birthday())
                .build();
    }
}
