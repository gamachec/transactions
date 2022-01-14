package fr.lab.transactions.tx.audit;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "REVISIONS")
@RevisionEntity(TransactionalRevisionListener.class)
public class TransactionalRevisionEntity extends DefaultRevisionEntity {

    private Long transactionId;
}
