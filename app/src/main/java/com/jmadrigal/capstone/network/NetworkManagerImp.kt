package com.jmadrigal.capstone.network

import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class NetworkManagerImp : NetworkManager {

    override suspend fun <T> get(url: String, parameters: Map<String, Any>): T {
        return suspendCoroutine { continuation ->
            val params: List<Pair<String, Any>> = parameters.map { Pair(it.key, it.value) }
            Fuel.get(url, params).responseString { result ->
                val gson = Gson()
                continuation.resume(gson.fromJson<T>(result.get(), object : TypeToken<T>() {}.type))
            }
        }
    }
}