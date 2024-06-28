package am.itspace.orderdomaincore.entity;

import am.itspace.commondomain.entity.entity.AggregateRoot;
import am.itspace.commondomain.entity.valueObject.*;
import am.itspace.orderdomaincore.exception.OrderDomainException;
import am.itspace.orderdomaincore.valueobject.OrderItemId;
import am.itspace.orderdomaincore.valueobject.StreetAddress;
import am.itspace.orderdomaincore.valueobject.TrackingId;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class Order extends AggregateRoot<OrderId> {

    private final CustomerId customerId;

    private final RestaurantId restaurantId;

    private final StreetAddress deliveryAddress;

    private final Money price;

    private final List<OrderItem> items;


    private TrackingId trackingId;

    private OrderStatus orderStatus;

    private List<String> failureMessage;

    public void validateOrder() {
        validateInitialOrder();
        validateTotalPrice();
        validateItemsPrice();
    }

    public void initializeOrder() {
        setId(new OrderId(UUID.randomUUID()));
        trackingId = new TrackingId(UUID.randomUUID());
        orderStatus = OrderStatus.PENDING;
        initializeOrderItems();
    }

    private void initializeOrderItems() {
        long itemId = 1;

        for (OrderItem orderItem : items) {
            orderItem.initializeOrderItem(super.getId(), new OrderItemId(itemId++));
        }
    }

    private void validateInitialOrder() {
        if (orderStatus != null || getId() != null) {
            throw new OrderDomainException("Order is not in correct state for initialization");
        }
    }


    private void validateTotalPrice() {
        if (price == null || !price.isGreaterThanZero()) {
            throw new OrderDomainException("Total price must be greater than zero!");
        }
    }

    private void validateItemsPrice() {
        Money orderItemsTotal = items.stream().map(orderItem -> {
            validateItemPrice(orderItem);
            return orderItem.getSubTotal();
        }).reduce(Money.ZERO, Money::add);

        if (price != orderItemsTotal) {
            throw new OrderDomainException("Total price " + price.getAmount() +
                    " is not equal to Order items total: " + orderItemsTotal.getAmount() + "!");
        }
    }

    private void validateItemPrice(OrderItem orderItem) {
        if (!orderItem.isPriceValid()) {
            throw new OrderDomainException("Order item price: " + orderItem.getPrice().getAmount()
                    + " is not valid for product " + orderItem.getProduct().getId().getValue());
        }
    }
}
