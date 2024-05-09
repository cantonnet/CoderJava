package com.cantonnet.configuracion;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class ApiConfiguration {
	
	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Proyecto CoderHouse Java de Alejandro Cantonnet")
						.version("1.0.0")
						.description("Proyecto de api rest de administracion comercio documentada con Swagger"+"\n  "+" body ventas 'cliente\": {\"idCliente\": 1},\"productos\": [{\"idProducto\": 4,\"cantidad\": 2},{\"idProducto\": 4,\"cantidad\": 1}],\"totalProductos\": 3, \"totalVenta\": 100.0 }'"))
						.servers(List.of(new Server()
								.url("http://localhost:8080")));
	}
	// http://localhost:8080/swagger-ui/index.html#/
	
	/*
	 
	 
	 {
  "cliente": {"idCliente": 1},"productos": [{"idProducto": 4,"cantidad": 2},{"idProducto": 4,"cantidad": 1}],"totalProductos": 3, "totalVenta": 100.0 }
	 
	 
	 
	 
	 */

}
