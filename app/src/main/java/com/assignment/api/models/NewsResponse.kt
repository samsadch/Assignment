package com.assignment.api.models

/**
 * @Author: Samsad Chalil Valappil
 * @Date: 14/08/2022
 */
data class NewsResponse(
    val copyright: String,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)