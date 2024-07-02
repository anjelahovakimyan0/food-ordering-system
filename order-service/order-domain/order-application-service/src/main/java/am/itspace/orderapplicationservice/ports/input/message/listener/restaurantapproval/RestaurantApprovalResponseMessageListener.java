package am.itspace.orderapplicationservice.ports.input.message.listener.restaurantapproval;

import am.itspace.orderapplicationservice.dto.message.RestaurantApprovalResponse;

public interface RestaurantApprovalResponseMessageListener {

    void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse);

    void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);

}
