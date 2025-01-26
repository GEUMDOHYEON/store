package com.example.store.product.controller;

import com.example.store.product.dto.BuyDto;
import com.example.store.product.entity.Product;
import com.example.store.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/display")
    public ResponseEntity<List<Product>> display() {
        List<Product> productList = productService.display();
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @PostMapping("/buy")
    public ResponseEntity<Long> buy(@RequestBody BuyDto dto){
        long startTime = System.currentTimeMillis();

        productService.buyProduct(dto);

        long endTime = System.currentTimeMillis();
        long requestTime = endTime - startTime;
        System.out.println(requestTime);
        return ResponseEntity.status(HttpStatus.CREATED).body(requestTime);
    }
}
