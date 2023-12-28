package com.aptech.spring01.service;

import com.aptech.spring01.models.Product;
import com.aptech.spring01.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<Product> search() {
        return productRepository.findAll();
    }

    public Product create(Product product) {
        try {
            productRepository.save(product);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return product;
    }

    public Product findById(int id) {
        return productRepository.findById(id).get();
    }

    public Product update(Integer id, Product product) {
        try {
            Product exited = findById(id);
            if (exited != null) {
                product.setId(exited.getId());
                productRepository.save(product);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return product;
    }

    public boolean delete(Integer id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
