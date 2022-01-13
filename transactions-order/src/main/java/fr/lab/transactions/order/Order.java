package fr.lab.transactions.order;

import lombok.Data;

@Data
public class Order {

    private long productId;

    private int quantity;
}
