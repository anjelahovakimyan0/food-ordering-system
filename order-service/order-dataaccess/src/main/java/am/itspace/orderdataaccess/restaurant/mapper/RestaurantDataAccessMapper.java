package am.itspace.orderdataaccess.restaurant.mapper;

import am.itspace.commondomain.entity.valueObject.Money;
import am.itspace.commondomain.entity.valueObject.ProductId;
import am.itspace.commondomain.entity.valueObject.RestaurantId;
import am.itspace.orderdataaccess.restaurant.entity.RestaurantEntity;
import am.itspace.orderdataaccess.restaurant.exception.RestaurantDataAccessException;
import am.itspace.orderdomaincore.entity.Product;
import am.itspace.orderdomaincore.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestaurantDataAccessMapper {

    public List<UUID> restaurantToRestaurantProducts(Restaurant restaurant) {
        return restaurant.getProducts().stream()
                .map(product -> product.getId().getValue())
                .collect(Collectors.toList());
    }

    public Restaurant restaurantEntityToRestaurant(List<RestaurantEntity> restaurantEntities) {
        RestaurantEntity restaurantEntity = restaurantEntities.stream().findFirst()
                .orElseThrow(() -> new RestaurantDataAccessException("Restaurant could not be found!"));

        List<Product> restaurantProducts = restaurantEntities.stream().map(entity ->
                        new Product(new ProductId(entity.getProductId()),
                                entity.getProductName(), new Money(entity.getProductPrice())))
                .collect(Collectors.toList());

        return Restaurant.builder()
                .restaurantId(new RestaurantId(restaurantEntity.getRestaurantId()))
                .products(restaurantProducts)
                .active(restaurantEntity.isRestaurantActive())
                .build();
    }
}
