package com.winqoo.wikia.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 *
 * model for WikiaItem simplified
 */
class UnexpandedWikiaItem : Serializable, ListItem {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("domain")
    var domain: String? = null

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("language")
    var language: String? = null

    @SerializedName("hub")
    var hub: String? = null

    @SerializedName("topic")
    var topic: String? = null

}