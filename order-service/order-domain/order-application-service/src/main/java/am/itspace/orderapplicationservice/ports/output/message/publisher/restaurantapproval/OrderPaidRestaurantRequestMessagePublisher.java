package am.itspace.orderapplicationservice.ports.output.message.publisher.restaurantapproval;

import am.itspace.commondomain.entity.event.publisher.DomainEventPublisher;
import am.itspace.orderdomaincore.event.OrderPaidEvent;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
