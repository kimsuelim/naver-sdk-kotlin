package org.naver

class Naver(val clientId: String, val clientSecret: String) {
    init {
        Configuration.configure(clientId = clientId, clientSecret = clientSecret)
    }

    fun search(): Search {
        return Search()
    }

    fun map(): Map {
        return Map()
    }

    fun shortUrl(): ShortUrl {
        return ShortUrl()
    }

    fun papago(): Papago {
        return Papago()
    }

    fun voice(): Voice {
        return Voice()
    }

    fun vision(): Vision {
        return Vision()
    }
}