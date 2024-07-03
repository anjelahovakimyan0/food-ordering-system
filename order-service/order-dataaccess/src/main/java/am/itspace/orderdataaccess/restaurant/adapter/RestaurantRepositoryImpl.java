package am.itspace.orderdataaccess.restaurant.adapter;

import am.itspace.orderapplicationservice.ports.output.repository.RestaurantRepository;
import am.itspace.orderdataaccess.restaurant.entity.RestaurantEntity;
import am.itspace.orderdataaccess.restaurant.mapper.RestaurantDataAccessMapper;
import am.itspace.orderdataaccess.restaurant.repository.RestaurantJpaRepository;
import am.itspace.orderdomaincore.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final RestaurantJpaRepository restaurantJpaRepository;

    private final RestaurantDataAccessMapper restaurantDataAccessMapper;

    @Override
    public Optional<Restaurant> findRestaurantInformation(Restaurant restaurant) {
        List<UUID> restaurantProducts = restaurantDataAccessMapper.restaurantToRestaurantProducts(restaurant);
        Optional<List<RestaurantEntity>> restaurantEntities = restaurantJpaRepository
                .findByRestaurantIdAndProductIdIn(restaurant.getId().getValue(), restaurantProducts);

        return restaurantEntities.map(restaurantDataAccessMapper::restaurantEntityToRestaurant);
    }
}
