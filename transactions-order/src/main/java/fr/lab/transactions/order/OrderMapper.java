package fr.lab.transactions.order;

import fr.lab.transactions.config.MapperConfiguration;
import fr.lab.transactions.order.command.OrderCreation;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface OrderMapper {

    OrderEntity toOrderEntity(OrderCreation orderCreation);

    Order toOrder(OrderEntity order);

}
