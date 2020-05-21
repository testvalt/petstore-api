package endPoint;

import model.Order;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.*;

public class StoreEndpoint {

    private final static String CREATE_PET_ORDER = "/store/order";
    private final static String GET_PET_ORDER_BY_ID = "/store/order/{id}";
    private final static String DELETE_PET_ORDER_BY_ID = "/store/order/{id}";
    private final static String GET_PET_INVENTORIES_BY_STATUS = "/store/inventory";

    static {
        SerenityRest.filters(new RequestLoggingFilter(LogDetail.ALL));
        SerenityRest.filters(new ResponseLoggingFilter(LogDetail.ALL));
    }

    private RequestSpecification given() {
        return SerenityRest
                .given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON)
                .log()
                .all();
    }

    @Step
    public ValidatableResponse createOrder(Order order) {
        return given()
                .body(order)
                .when()
                .post(CREATE_PET_ORDER)
                .then()
               // .body("id", is(order.getId()))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse getOrder(long orderId) {
        return given()
                .when()
                .get(GET_PET_ORDER_BY_ID, orderId)
                .then()
                .body("id", is(orderId))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse deleteOrder(long orderId) {
        return given()
                .when()
                .delete(DELETE_PET_ORDER_BY_ID, orderId)
                .then()
                .body("message", is(String.valueOf(orderId)))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse getPetInventoriesByStatus() {
        return given()
                .when()
                .get(GET_PET_INVENTORIES_BY_STATUS)
                .then()
                .statusCode(SC_OK);
    }
}
