package com.winqoo.wikia.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
class WikiaStats : Serializable {

    @SerializedName("users")
    var users: Int? = null

    @SerializedName("articles")
    var articles: Int? = null

    @SerializedName("pages")
    var pages: Int? = null

    @SerializedName("admins")
    var admins: Int? = null

    @SerializedName("edits")
    var edits: Int? = null

    @SerializedName("videos")
    var videos: Int? = null

    @SerializedName("images")
    var images: Int? = null

}