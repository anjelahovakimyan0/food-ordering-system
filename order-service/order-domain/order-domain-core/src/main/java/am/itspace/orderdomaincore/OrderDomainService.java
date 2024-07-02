package am.itspace.orderdomaincore;

import am.itspace.orderdomaincore.entity.Order;
import am.itspace.orderdomaincore.entity.Restaurant;
import am.itspace.orderdomaincore.event.OrderCancelledEvent;
import am.itspace.orderdomaincore.event.OrderCreatedEvent;
import am.itspace.orderdomaincore.event.OrderPaidEvent;

import java.util.List;

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);

    OrderPaidEvent payOrder(Order order);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order order, List<String> failureMessages);

}
