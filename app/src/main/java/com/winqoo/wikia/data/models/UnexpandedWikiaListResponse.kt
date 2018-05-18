package com.winqoo.wikia.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
class UnexpandedWikiaListResponse : Serializable {

    @SerializedName("batches")
    var batches: Int? = null

    @SerializedName("items")
    var items: Array<UnexpandedWikiaItem>? = null

    @SerializedName("total")
    var total: Int? = null

    @SerializedName("currentBatch")
    var currentBatch: Int? = null

    @SerializedName("next")
    var next: Int? = null

}