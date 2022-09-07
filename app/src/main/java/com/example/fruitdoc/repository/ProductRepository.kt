package com.example.fruitdoc.repository

import androidx.lifecycle.LiveData
import com.example.fruitdoc.dao.ProductDao
import com.example.fruitdoc.model.Product


class ProductRepository(private val productDao: ProductDao) {
    // get all the products
    fun allProducts(): LiveData<List<Product>> = productDao.allProducts()

    // get all the products by problem id
    fun productsByProblemId(problemId: Int): List<Product> = productDao.productsByProblemId(problemId)
}