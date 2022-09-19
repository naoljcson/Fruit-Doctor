package com.example.fruitdoc.model

data class Notifications(
    var title: String,
    var img_url: String?,
    var body: MutableList<String>
)