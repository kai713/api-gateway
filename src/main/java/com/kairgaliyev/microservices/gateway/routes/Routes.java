package com.kairgaliyev.microservices.gateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> productServiceRoutes() {
        //Functional endpoint
        // GatewayRouterFunctions.route("product_service") создает новый объект маршрута с именем "product_service"
        return GatewayRouterFunctions.route("product_service")
                //1)HandlerFunctions.http("http://localhost:8080")):
                // Определяет правило: если приходит запрос по пути /api/product,
                // то он перенаправляется на указанный адрес http://localhost:8080
                //2) RequestPredicates.path("/api/product"): Это условие, при котором выполняется маршрут.
                // Если путь в запросе совпадает с /api/product, применяется это правило.
                //3)HandlerFunctions.http("http://localhost:8080"):
                //Это обработчик, который перенаправляет запрос на указанный адрес, представляющий микросервис.
                .route(RequestPredicates.path("/api/product"), HandlerFunctions.http("http://localhost:8080"))
                .build();
                //http://localhost:9000/api/product
    }
    @Bean
    public RouterFunction<ServerResponse> orderServiceRoutes() {
        return GatewayRouterFunctions.route("order_service")
                .route(RequestPredicates.path("/api/order"), HandlerFunctions.http("http://localhost:8081"))
                .build();

    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoutes() {
        return GatewayRouterFunctions.route("inventory_service")
                .route(RequestPredicates.path("/api/inventory"), HandlerFunctions.http("http://localhost:8082"))
                .build();

    }
}
