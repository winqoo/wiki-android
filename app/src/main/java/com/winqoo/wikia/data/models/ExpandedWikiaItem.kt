package com.winqoo.wikia.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 *
 * Model for wikia Item with more details
 */
class ExpandedWikiaItem : Serializable, ListItem{

    @SerializedName("headline")
    var headline: String? = null

    @SerializedName("desc")
    var desc: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("stats")
    var stats: WikiaStats? = null

    @SerializedName("original_dimensions")
    var imageDimensions: ImageDimensions? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("image")
    var image: String? = null

    @SerializedName("flags")
    var flags: Array<String>? = null

    @SerializedName("domain")
    var domain: String? = null

    @SerializedName("wam_score")
    var wamScore: Float? = null

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("lang")
    var lang: String? = null

    @SerializedName("topUsers")
    var topUsers: Array<String>? = null

    @SerializedName("hub")
    var hub: String? = null

    @SerializedName("wordmark")
    var wordmark: String? = null

    @SerializedName("title")
    var title: String? = null

}