package am.itspace.orderdataaccess.customer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "order_customer_m_view", schema = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerEntity {

    @Id
    private UUID id;

}
