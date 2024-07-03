package am.itspace.orderdataaccess.restaurant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "order_restaurant_m_view", schema = "restaurant") //to use materialized view of restaurant customer in order layer,
                                                                //to query restaurant info in order domain layer
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(RestaurantEntityId.class) //this way I set RestaurantEntityId class as a primary key of RestaurantEntity class using @IdClass annotation
public class RestaurantEntity {

    @Id
    private UUID restaurantId;

    @Id
    private UUID productId;

    private String restaurantName;

    private boolean restaurantActive;

    private String productName;

    private BigDecimal productPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantEntity that = (RestaurantEntity) o;
        return Objects.equals(restaurantId, that.restaurantId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantId, productId);
    }
}
