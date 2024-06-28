package am.itspace.orderdomaincore.valueobject;

import am.itspace.commondomain.entity.valueObject.BaseId;

public class OrderItemId extends BaseId<Long> {

    public OrderItemId(Long value) {
        super(value);
    }
}
