package com.example.store.product.repository;

import com.example.store.product.entity.Product;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
    private final EntityManager em;

    public List<Product> findAll() {
        return em.createQuery("select p from Product p", Product.class).getResultList();
    }

    public void save(Product product) {
        em.merge(product);
    }

    public Product findByName(String name) {
        return em.createQuery("select p from Product p where name=:name", Product.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
