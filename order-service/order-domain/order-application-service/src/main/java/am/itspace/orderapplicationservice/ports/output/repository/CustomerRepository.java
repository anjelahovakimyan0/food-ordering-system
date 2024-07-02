package am.itspace.orderapplicationservice.ports.output.repository;

import am.itspace.orderdomaincore.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Optional<Customer> findCustomer(UUID customerId);

}
