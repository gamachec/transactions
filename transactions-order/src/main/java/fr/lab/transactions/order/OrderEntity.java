package fr.lab.transactions.order;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ORDERS")
public class OrderEntity extends PanacheEntity {

    private long productId;

    private int quantity;
}

