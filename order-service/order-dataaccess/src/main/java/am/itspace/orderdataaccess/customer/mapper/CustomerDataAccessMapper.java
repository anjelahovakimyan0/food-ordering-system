package am.itspace.orderdataaccess.customer.mapper;

import am.itspace.commondomain.entity.valueObject.CustomerId;
import am.itspace.orderdataaccess.customer.entity.CustomerEntity;
import am.itspace.orderdomaincore.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {

    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()));
    }
}
