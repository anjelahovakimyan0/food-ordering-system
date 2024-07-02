package am.itspace.orderapplicationservice.dto.message;

import am.itspace.commondomain.entity.valueObject.OrderApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class RestaurantApprovalResponse {

    private String id;

    private String sagaId;

    private String orderId;

    private String restaurantId;

    private Instant createdAt;

    private OrderApprovalStatus orderApprovalStatus;

    private List<String> failureMessages;

}
