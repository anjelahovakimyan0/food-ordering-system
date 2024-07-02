package am.itspace.orderapplicationservice.ports.output.repository;

import am.itspace.orderdomaincore.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {

    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);

}
