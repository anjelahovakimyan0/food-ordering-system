package am.itspace.orderdataaccess.restaurant.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantEntityId implements Serializable {

    private UUID restaurantId;

    private UUID productId;

}
