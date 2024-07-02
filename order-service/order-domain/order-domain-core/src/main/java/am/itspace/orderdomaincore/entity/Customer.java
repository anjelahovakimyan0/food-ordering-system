package am.itspace.orderdomaincore.entity;

import am.itspace.commondomain.entity.entity.AggregateRoot;
import am.itspace.commondomain.entity.valueObject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {

    public Customer() {
    }

    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }
}
