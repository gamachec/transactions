package fr.lab.transactions.tx;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PanacheTransactionalEntity extends PanacheEntity {

    public Long transactionId;

    @Override
    public void persist() {
        this.transactionId = DistributedTransactionContext.getTransactionId()
                .orElseThrow(() -> new RuntimeException("You need a transaction to persist this object."));
        super.persist();
    }
}
