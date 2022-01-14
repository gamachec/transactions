package fr.lab.transactions.tx.audit;

import fr.lab.transactions.tx.DistributedTransactionContext;
import org.hibernate.envers.RevisionListener;

public class TransactionalRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object entity) {
        Long transactionId = DistributedTransactionContext.getTransactionId()
                .orElseThrow(() -> new RuntimeException("You need a transaction to persist this object."));

        TransactionalRevisionEntity transactionalRevisionEntity = (TransactionalRevisionEntity) entity;
        transactionalRevisionEntity.setTransactionId(transactionId);
    }
}
