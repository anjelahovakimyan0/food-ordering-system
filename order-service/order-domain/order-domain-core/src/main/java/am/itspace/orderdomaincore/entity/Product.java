package am.itspace.orderdomaincore.entity;

import am.itspace.commondomain.entity.entity.BaseEntity;
import am.itspace.commondomain.entity.valueObject.Money;
import am.itspace.commondomain.entity.valueObject.ProductId;
import lombok.Getter;

@Getter
public class Product extends BaseEntity<ProductId> {

    private String name;

    private Money price;

    public Product(ProductId productId) {
        super.setId(productId);
    }

    public Product(ProductId productId, String name, Money price) {
        super.setId(productId);
        this.name = name;
        this.price = price;
    }

    public void updateWithConfirmedNameAndPrice(String name, Money price) {
        this.name = name;
        this.price = price;
    }
}
