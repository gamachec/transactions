package fr.lab.transactions.order;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.lab.transactions.order.command.OrderCreation;
import lombok.RequiredArgsConstructor;

@Path("/order")
@RequiredArgsConstructor
public class OrderResource {

    private final OrderService orderService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Order createOrder(final OrderCreation orderCreation) {
        return orderService.createOrder(orderCreation);
    }
}