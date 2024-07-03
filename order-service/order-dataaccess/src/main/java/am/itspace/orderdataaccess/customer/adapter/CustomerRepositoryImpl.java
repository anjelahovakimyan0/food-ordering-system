package am.itspace.orderdataaccess.customer.adapter;

import am.itspace.orderapplicationservice.ports.output.repository.CustomerRepository;
import am.itspace.orderdataaccess.customer.mapper.CustomerDataAccessMapper;
import am.itspace.orderdataaccess.customer.repository.CustomerJpaRepository;
import am.itspace.orderdomaincore.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;

    private final CustomerDataAccessMapper customerDataAccessMapper;

    @Override
    public Optional<Customer> findCustomer(UUID customerId) {
        return customerJpaRepository.findById(customerId)
                .map(customerDataAccessMapper::customerEntityToCustomer);
    }
}
