package com.github.tnaucoin.kafka.consumer;

import com.github.tnaucoin.kafka.consumer.model.PreferredProduct;
import com.github.tnaucoin.kafka.consumer.model.User;
import com.github.tnaucoin.kafka.consumer.service.UserDB;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class SuggestionEngine {
    private UserDB userDB = new UserDB();

    public void processSuggestion(String userId, String product){
        String[] valueSplit = product.split(",");
        String productType = valueSplit[0];
        String productColor = valueSplit[1];
        String productDesign = valueSplit[2];

        log.info("User with ID: " + userId + " showed interest over " + productType +
                " of color " + productColor + " and design " + productDesign);
        User user = userDB.findById(userId);
        // update user's preferred products
        user.getPreferredProductList().add(new PreferredProduct(productColor,productType,productDesign));
        // generate a list of suggestions
        user.setSuggestions(generateSuggestions(user.getPreferredProductList()));

        userDB.save(user);
    }

    private List<String> generateSuggestions(List<PreferredProduct> preferredProductList) {
        return Arrays.asList("TSHIRT,BLUE","DESIGN,ORANGE,ROCKET","TSHIRT,PURPLE,CAR");
    }
}
