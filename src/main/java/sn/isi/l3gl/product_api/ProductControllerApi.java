package sn.isi.l3gl.product_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// ✅ Imports depuis product-core (package sn.isi.l3gl.demo)
import sn.isi.l3gl.demo.Product;
import sn.isi.l3gl.demo.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v2/products")   // ⚠️ chemin différent pour éviter conflit avec product-core
public class ProductControllerApi {

    private final ProductService productService;

    public ProductControllerApi(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> listProducts() {
        return ResponseEntity.ok(productService.listProducts());
    }

    @PostMapping("/random")
    public ResponseEntity<Product> createRandomProduct() {
        return new ResponseEntity<>(
                productService.createRandomProduct(),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateQuantity(
            @PathVariable Long id,
            @RequestParam Integer quantity) {
        return ResponseEntity.ok(
                productService.updateQuantity(id, quantity));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<Long> countLowStockProducts() {
        return ResponseEntity.ok(
                productService.countLowStockProducts());
    }
}