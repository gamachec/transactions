package fr.lab.transactions.order;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import fr.lab.transactions.order.command.OrderCreation;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class OrderService {

    private final OrderMapper orderMapper;

    public Order createOrder(final OrderCreation orderCreation) {
        OrderEntity order = orderMapper.toOrderEntity(orderCreation);
        order.persist();
        return orderMapper.toOrder(order);
    }
}
