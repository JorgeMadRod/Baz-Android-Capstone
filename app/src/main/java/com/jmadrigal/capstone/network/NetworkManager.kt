package com.jmadrigal.capstone.network

interface NetworkManager {

    suspend fun <T> get(url: String, parameters: Map<String, Any>): T
}