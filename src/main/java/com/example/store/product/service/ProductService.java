package com.example.store.product.service;

import com.example.grpc.MemberResponse;
import com.example.store.grpc.MemberGrpcClient;
import com.example.store.product.dto.BuyDto;
import com.example.store.product.dto.MemberDTO;
import com.example.store.product.entity.Product;
import com.example.store.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
//    private final WebClient webClient;
    private final MemberGrpcClient memberGrpcClient;
    public ProductService(MemberGrpcClient memberGrpcClient, ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.memberGrpcClient = memberGrpcClient;
    }

    @Transactional
    public List<Product> display() {

        saveProduct();

        return productRepository.findAll();
    }

//    @Transactional
    public void saveProduct() {
        for (int i = 0; i < 10; i++) {
            Product product = new Product();

            product.setName("Product " + i);

            productRepository.save(product);
        }
    }

    public Product buyProduct(BuyDto dto) {
        Product product = productRepository.findByName(dto.product_name);
//        getMember(dto.member_name);
        MemberResponse memberResponse = memberGrpcClient.getMember(dto.member_name);
        System.out.println(memberResponse);
        return product;
    }

//    private void getMember(String memberName){
//
//        WebClient.ResponseSpec responseSpec = webClient.get()
//                .uri(uriBuilder -> uriBuilder
//                        .path("/member")  // /member 경로 추가
//                        .queryParam("name", memberName)
//                        .build())
//                .retrieve();
//        System.out.println(responseSpec.bodyToMono(MemberDTO.class).block());
//    }
}
