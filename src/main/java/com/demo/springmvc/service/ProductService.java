package com.demo.springmvc.service;

import com.demo.springmvc.model.Product;

import java.util.List;

public interface ProductService {

  Product create(Product product);

  List<Product> findAll();

  Product findById(int id);

  void delete(int id);

  void update(Product product,int id);
}
