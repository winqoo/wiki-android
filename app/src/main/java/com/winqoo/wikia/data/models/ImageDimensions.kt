package com.winqoo.wikia.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
class ImageDimensions : Serializable {

    @SerializedName("width")
    var width: Int? = null

    @SerializedName("height")
    var height: Int? = null

}