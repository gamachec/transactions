package fr.lab.transactions.tx;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Slf4j
@Provider
public class DistributedTransactionRequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        var distributedTxId = containerRequestContext.getHeaderString("X-TxId");
        if (distributedTxId != null && !distributedTxId.isBlank()) {
            log.info("Extract DTX from headers");
            DistributedTransactionContext.setTransactionId(Long.parseLong(distributedTxId));
        }
    }
}
