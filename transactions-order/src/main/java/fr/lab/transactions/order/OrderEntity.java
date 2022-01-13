package fr.lab.transactions.order;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "ORDER")
public class OrderEntity extends PanacheEntity {

    private long productId;

    private int quantity;
}
