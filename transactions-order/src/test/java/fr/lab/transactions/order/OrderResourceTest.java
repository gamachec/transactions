package fr.lab.transactions.order;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import fr.lab.transactions.order.command.OrderCreation;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestHTTPEndpoint(OrderResource.class)
@QuarkusTestResource(H2DatabaseTestResource.class)
class OrderResourceTest {
    // @formatter:off

    @Test
    void testHelloEndpoint() {
        var orderCreation = OrderCreation.builder().productId(1).quantity(1).build();
        var expected = Order.builder().productId(1).quantity(1).build();

        Order actual = given()
            .contentType(ContentType.JSON)
            .when()
                .body(orderCreation)
                .post()
            .then()
                .statusCode(200)
                .extract().as(Order.class);

        assertThat(actual)
            .usingRecursiveComparison()
            .isEqualTo(expected);
    }
}