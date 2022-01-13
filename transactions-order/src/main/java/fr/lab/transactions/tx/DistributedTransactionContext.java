package fr.lab.transactions.tx;

import java.util.Optional;

public class DistributedTransactionContext {

    private static final ThreadLocal<TransactionContext> CTX = ThreadLocal.withInitial(TransactionContext::new);

    public static void reset() {
        CTX.set(new TransactionContext());
    }

    public static void setTransactionId(long id) {
        CTX.get().id = id;
    }

    public static Optional<Long> getTransactionId() {
        return Optional.ofNullable(CTX.get().id);
    }

    private static class TransactionContext {
        Long id;
    }
}
