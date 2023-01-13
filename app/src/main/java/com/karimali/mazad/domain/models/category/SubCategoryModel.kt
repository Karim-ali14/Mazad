package com.karimali.mazad.domain.models.category

typealias SubCategories = ArrayList<SubCategoryModel>

data class SubCategoryModel(
    val description: String? = null,
    val id: Int? = null,
    val list: Boolean? = null,
    val name: String? = null,
    val options: List<Option>? = arrayListOf(),
    val other_value: Any? = null,
    val parent: Any? = null,
    val slug: String? = null,
    val type: Any? = null,
    val value: String? = null
)

data class Option(
    val child: Boolean? = null,
    val id: Int? = null,
    val name: String? = null,
    val parent: Int? = null,
    val slug: String? = null
)