package com.example.fetchsubmission

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PullData {
    suspend fun getData(): String {
        return withContext(Dispatchers.IO) {
            val call = ApiClient.apiService.fetchData("hiring.json")
            try {
                val response = call.execute()
                if (response.isSuccessful) {
                    println("Server contacted and has file")
                    val inputStream = response.body()?.byteStream()
                    inputStream?.bufferedReader()?.use { it.readText() }
                } else {
                    println("Server contact failed: ${response.message()}")
                    null
                }
            } catch (e: Exception) {
                println("Error retrieving file: ${e.message}")
                null
            }.toString()
        }
    }

    fun parseData(data: String): List<GroupedItem> {
        try {
            val gson = Gson()
            val itemType = object : TypeToken<List<Item>>() {}.type
            val items: List<Item> = gson.fromJson(data, itemType)
            return items
                .filter {!it.name.isNullOrBlank()}
                .sortedWith(compareBy({it.listId}, {it.id}, {it.name}))
                .groupBy {it.listId}
                .map {GroupedItem(it.key, it.value)}
        } catch (e: Exception) {
            return emptyList<GroupedItem>()
        }

    }


}

data class Item(
    val listId: String,
    val name: String?,
    val id: Int
)

data class GroupedItem(
    val listId: String,
    val items: List<Item>
)