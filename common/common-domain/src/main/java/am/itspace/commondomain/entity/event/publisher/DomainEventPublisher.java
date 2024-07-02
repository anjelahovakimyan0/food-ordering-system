package am.itspace.commondomain.entity.event.publisher;

import am.itspace.commondomain.entity.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);

}
