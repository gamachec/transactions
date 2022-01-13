package fr.lab.transactions.order.command;

import lombok.Data;

@Data
public class OrderCreation {

    private long productId;

    private int quantity;
}
