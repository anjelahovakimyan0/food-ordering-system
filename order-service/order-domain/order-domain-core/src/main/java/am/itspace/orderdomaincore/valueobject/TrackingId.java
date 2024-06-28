package am.itspace.orderdomaincore.valueobject;

import am.itspace.commondomain.entity.valueObject.BaseId;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {

    public TrackingId(UUID value) {
        super(value);
    }
}
