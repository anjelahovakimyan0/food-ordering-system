package am.itspace.orderdomaincore.entity;

import am.itspace.commondomain.entity.entity.BaseEntity;
import am.itspace.commondomain.entity.valueObject.Money;
import am.itspace.commondomain.entity.valueObject.OrderId;
import am.itspace.orderdomaincore.valueobject.OrderItemId;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItem extends BaseEntity<OrderItemId> {

    private OrderId orderId;

    private final Product product;

    private final int quantity;

    private final Money price;

    private final Money subTotal; // quantity * price

    boolean isPriceValid() {
        return price.isGreaterThanZero()
                && price.equals(product.getPrice())
                && price.multiply(quantity).equals(subTotal);
    }

    private OrderItem(OrderId orderId, Product product, int quantity, Money price, Money subTotal) {
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.subTotal = subTotal;
    }

    void initializeOrderItem(OrderId orderId, OrderItemId orderItemId) {
        this.orderId = orderId;
        super.setId(orderItemId);
    }
}
