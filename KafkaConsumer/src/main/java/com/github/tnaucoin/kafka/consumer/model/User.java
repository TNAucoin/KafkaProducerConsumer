package com.github.tnaucoin.kafka.consumer.model;

import com.github.tnaucoin.kafka.consumer.enums.UserId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private UserId userId;
    private List<PreferredProduct> preferredProductList;
    private List<String> suggestions;
    public User(UserId userId){
        this.userId = userId;
        this.preferredProductList = new ArrayList<>();
        this.suggestions = new ArrayList<>();
    }
}
