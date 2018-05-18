package com.winqoo.wikia.service.common

import java.io.IOException

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
class NoConnectivityException : IOException() {

    override val message: String
        get() = "No connectivity exception"

}