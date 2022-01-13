package fr.lab.transactions.order;

import fr.lab.transactions.order.command.OrderCreation;
import fr.lab.transactions.tx.DistributedTransaction;
import fr.lab.transactions.tx.RollbackListener;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@RequiredArgsConstructor
@DistributedTransaction
@Transactional(rollbackOn = Exception.class)
public class OrderService {

    private final OrderMapper orderMapper;

    public Order createOrder(final OrderCreation orderCreation) {
        OrderEntity order = orderMapper.toOrderEntity(orderCreation);
        order.persist();
        return orderMapper.toOrder(order);
    }

    // TODO Marche pas pour les mise à jour, il faudrait de l'audit :)
    @RollbackListener
    public void onRollback(long transactionId) {
        var ordersEntities = OrderEntity.findByTransactionId(transactionId);
        ordersEntities.forEach(PanacheEntityBase::delete);
    }
}
