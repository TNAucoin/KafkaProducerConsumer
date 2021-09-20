package com.github.tnaucoin.kafka.producer.model;

import com.github.tnaucoin.kafka.producer.enums.Color;
import com.github.tnaucoin.kafka.producer.enums.DesignType;
import com.github.tnaucoin.kafka.producer.enums.ProductType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private Color color;
    private ProductType productType;
    private DesignType designType;
}
