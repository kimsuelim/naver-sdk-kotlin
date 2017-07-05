package org.naver

import org.naver.models.map.*
import org.naver.deserializers.ResultDeserializer

import com.google.gson.GsonBuilder

class Map : Client() {
    fun geocode(query: String): Locations {
        val result = Connection.get("get", "/v1/map/geocode", query).body()?.string()
        val locations = decode(result)
        return locations
    }

    fun reverseGeocode(lat: String, lng: String): Locations {
        val query = "${lat},${lng}"
        val result = Connection.get("get", "/v1/map/reversegeocode", query).body()?.string()
        val locations = decode(result)
        return locations
    }

    private fun decode(result: String?): Locations {
        val gson = GsonBuilder()
                .registerTypeAdapter(Locations::class.java, ResultDeserializer<Locations>())
                .create()
        val locations = gson.fromJson(result, Locations::class.java)
        return locations
    }
}