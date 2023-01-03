package com.karimali.mazad.domain.models.category

data class SubCategoryModel(
    val description: String,
    val id: Int,
    val list: Boolean,
    val name: String,
    val options: List<Option>,
    val other_value: Any,
    val parent: Any,
    val slug: String,
    val type: Any,
    val value: String
)

data class Option(
    val child: Boolean,
    val id: Int,
    val name: String,
    val parent: Int,
    val slug: String
)