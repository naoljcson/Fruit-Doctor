package com.example.fruitdoc.model

data class ActiveProduct(
    var activeIngredient: Product,
    var tradeNames: MutableList<Product>
)