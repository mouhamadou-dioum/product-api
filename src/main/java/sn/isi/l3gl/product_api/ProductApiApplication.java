package sn.isi.l3gl.product_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"sn.isi.l3gl.product_api",
		"sn.isi.l3gl.demo"   // ✅ Package réel de product-core
})
public class ProductApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductApiApplication.class, args);
	}
}