package fr.lab.transactions.tx.audit;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@ApplicationScoped
public class AuditReaderService {

    private final AuditReader reader;

    public AuditReaderService(EntityManager entityManager) {
        reader = AuditReaderFactory.get(entityManager);
    }

    // TODO aie aie aie, comment faire ça ? :D
    public <T> Optional<T> findRollbackEntity(Class<T> classz, long revisionIdRollback) {
        List<Object[]> resultList = reader.createQuery()
                .forRevisionsOfEntity(classz, classz.getName(), false, true)
                .add(AuditEntity.property("transactionid").eq(revisionIdRollback))
                .getResultList();

        return Optional.empty();
    }

    private <T> OptionalLong findRollbackRevision(Class<T> classz, long entityId, long revisionIdRollback) {
        List<Number> revisions = reader.getRevisions(classz, entityId);

        OptionalLong maximum = revisions.stream()
                .mapToLong(Number::longValue)
                .max();

        if (maximum.isPresent() && maximum.getAsLong() == revisionIdRollback) {
            return revisions.stream()
                    .mapToLong(Number::longValue)
                    .filter(rev -> rev < revisionIdRollback)
                    .max();
        }

        return OptionalLong.empty();
    }
}
