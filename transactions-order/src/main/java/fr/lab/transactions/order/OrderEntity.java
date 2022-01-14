package fr.lab.transactions.order;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Audited
@Table(name = "ORDERS")
public class OrderEntity extends PanacheEntity {

    private long productId;

    private int quantity;
}

