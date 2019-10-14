package com.demo.springmvc.service;

import com.demo.springmvc.model.Category;

import java.util.List;

public interface CategoryService {
  Category create(Category category);

  Category findById(int id);

  List<Category> findAll();
}
