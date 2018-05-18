package com.winqoo.wikia.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
fun View.visible(): View {
    this.visibility = View.VISIBLE
    return this
}

fun View.invisible(): View {
    this.visibility = View.INVISIBLE
    return this
}

fun View.gone(): View {
    this.visibility = View.GONE
    return this
}

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}