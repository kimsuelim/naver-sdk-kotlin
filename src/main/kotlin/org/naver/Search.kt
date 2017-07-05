package org.naver

import org.naver.models.search.*
import com.google.gson.GsonBuilder

class Search : Client() {
    fun blog(query: String): Blogs {
        val result = Connection.get("get", "/v1/search/blog", query).body()?.string()
        val blogs = decodeJson(body = result, modelClass = Blogs::class.java)
        return blogs
    }

    fun news(query: String): News {
        val result = Connection.get("get", "/v1/search/news", query).body()?.string()
        val gson = GsonBuilder()
                .setDateFormat("EEEE, dd MMM yyyy HH:mm:ss")
                .create()
        val news = gson.fromJson(result, News::class.java)
        return news
    }

    fun book(query: String): Books {
        val result = Connection.get("get", "/v1/search/book", query).body()?.string()
        val books = decodeJson(body = result, modelClass = Books::class.java)
        return books
    }

    fun adult(query: String): Adult {
        val result = Connection.get("get", "/v1/search/adult", query).body()?.string()
        val adult = decodeJson(body = result, modelClass = Adult::class.java)
        return adult
    }

    fun encyclopedia(query: String): Encyclopedias {
        val result = Connection.get("get", "/v1/search/encyc", query).body()?.string()
        val encyclopedias = decodeJson(body = result, modelClass = Encyclopedias::class.java)
        return encyclopedias
    }

    fun movie(query: String): Movies {
        val result = Connection.get("get", "/v1/search/movie", query).body()?.string()
        val movies = decodeJson(body = result, modelClass = Movies::class.java)
        return movies
    }

    fun cafearticle(query: String): Cafearticles {
        val result = Connection.get("get", "/v1/search/cafearticle", query).body()?.string()
        val cafearticles = decodeJson(body = result, modelClass = Cafearticles::class.java)
        return cafearticles
    }

    fun jisigin(query: String): Jisigins {
        val result = Connection.get("get", "/v1/search/kin", query).body()?.string()
        val jisigins = decodeJson(body = result, modelClass = Jisigins::class.java)
        return jisigins
    }

    fun place(query: String): Places {
        val result = Connection.get("get", "/v1/search/local", query).body()?.string()
        val places = decodeJson(body = result, modelClass = Places::class.java)
        return places
    }

    fun errata(query: String): Errata {
        val result = Connection.get("get", "/v1/search/errata", query).body()?.string()
        val errata = decodeJson(body = result, modelClass = Errata::class.java)
        return errata
    }

    fun webkr(query: String): Webkrs {
        val result = Connection.get("get", "/v1/search/webkr", query).body()?.string()
        val webkrs = decodeJson(body = result, modelClass = Webkrs::class.java)
        return webkrs
    }

    fun image(query: String): Images {
        val result = Connection.get("get", "/v1/search/image", query).body()?.string()
        val images = decodeJson(body = result, modelClass = Images::class.java)
        return images
    }

    fun shop(query: String): Shops {
        val result = Connection.get("get", "/v1/search/shop", query).body()?.string()
        val shops = decodeJson(body = result, modelClass = Shops::class.java)
        return shops
    }

    fun doc(query: String): Docs {
        val result = Connection.get("get", "/v1/search/doc", query).body()?.string()
        val docs = decodeJson(body = result, modelClass = Docs::class.java)
        return docs
    }
}