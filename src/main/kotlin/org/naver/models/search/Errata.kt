package org.naver.models.search

data class Errata(val errata: String) {
    fun isErrata(): Boolean {
        if (errata != "") {
            return true
        } else {
            return false
        }
    }
}