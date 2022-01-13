package fr.lab.transactions.order;

import fr.lab.transactions.tx.PanacheTransactionalEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ORDERS")
public class OrderEntity extends PanacheTransactionalEntity {

    private long productId;

    private int quantity;

    public static List<OrderEntity> findByTransactionId(long transactionId) {
        return list("transactionId", transactionId);
    }
}

