package org.naver.models.search

data class Adult(val adult: String) {
    fun isAdult(): Boolean {
        if (adult == "1") {
            return true
        } else {
            return false
        }
    }
}