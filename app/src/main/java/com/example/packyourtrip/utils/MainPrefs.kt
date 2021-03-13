package com.example.packyourtrip.utils

import com.chibatching.kotpref.KotprefModel

object MainPrefs : KotprefModel() {

    var userEmail by stringPrefVar()
}