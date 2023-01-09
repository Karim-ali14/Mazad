package com.karimali.mazad.domain.models.category


data class CategoryModel(
    val ads_banners: List<AdsBanner>? = listOf(),
    val categories: List<Category>? = listOf(),
    val google_version: String? = null,
    val huawei_version: String? = null,
    val ios_version: String? = null,
    val statistics: Statistics = Statistics()
)

data class Category(
    val children: List<Children>? = listOf(),
    val circle_icon: String? = null,
    val description: Any? = null,
    val disable_shipping: Int? = null,
    val id: Int? = 0,
    val image: String? = null,
    val name: String? = null,
    val slug: String? = null
)

data class Children(
    val children: Any? = null,
    val circle_icon: String? = null,
    val description: Any? = null,
    val disable_shipping: Int? = 0,
    val id: Int? = 0,
    val image: String? = null,
    val name: String? = null,
    val slug: String? = null
)

data class AdsBanner(
    val duration: Int? = 0,
    val img: String? = null,
    val media_type: String? = null,
)

data class Statistics(
    val auctions: Int? = 0,
    val products: Int? = 0,
    val users: Int? = 0
)