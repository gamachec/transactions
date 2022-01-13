package fr.lab.transactions.tx;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@SuppressWarnings("unused")
@Slf4j
@Priority(0)
@Interceptor
@DistributedTransaction
public class DistributedTransactionInterceptor {

    @AroundInvoke
    Object prepareTransaction(InvocationContext context) throws Exception {
        var transactionId = DistributedTransactionContext.getTransactionId();
        if (transactionId.isPresent()) {
            return proceedWithDistributedTransaction(context, transactionId.get());
        } else {
            log.info("Proceed without DTX");
            return context.proceed();
        }
    }

    private Object proceedWithDistributedTransaction(InvocationContext context, Long transactionId) throws Exception {
        log.info("DTX {} : start", transactionId);
        try {
            var result = context.proceed();
            log.info("DTX {} : success", transactionId);
            return result;
        } catch (Exception exception) {
            log.info("DTX {}: rollback", transactionId);
            throw exception;
        } finally {
            DistributedTransactionContext.reset();
        }
    }
}
