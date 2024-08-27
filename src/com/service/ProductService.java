package com.service;

import com.bean.Product;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private List<Product> productList;

    public ProductService() {
        this.productList = new ArrayList<>();
    }

    public ProductService(List<Product> initialProducts) {
        this.productList = initialProducts != null ? initialProducts : new ArrayList<>();
    }

    public String addProduct(Product product) {
        for (Product p : productList) {
            if (p.getPid() == product.getPid()) {
                return "Product with this ID already exists.";
            }
        }
        productList.add(product);
        return "Product added successfully.";
    }

    public List<Product> findAllProducts() {
        return new ArrayList<>(productList);
    }

    public String deleteProduct(int pid) {
        Product productToRemove = null;
        for (Product p : productList) {
            if (p.getPid() == pid) {
                productToRemove = p;
                break;
            }
        }
        if (productToRemove != null) {
            productList.remove(productToRemove);
            return "Product deleted successfully.";
        }
        return "Product not found.";
    }

    public String updateProduct(Product updatedProduct) {
        for (Product p : productList) {
            if (p.getPid() == updatedProduct.getPid()) {
                p.setPrice(updatedProduct.getPrice());
                return "Product price updated successfully.";
            }
        }
        return "Product not found.";
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
