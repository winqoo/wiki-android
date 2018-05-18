package com.winqoo.wikia.ui.common

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.winqoo.wikia.R
import com.winqoo.wikia.service.common.NoConnectivityException
import kotlinx.android.synthetic.main.layout_error.view.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by Michał Winkler
 * winklermichu@gmail.com
 */
class ErrorView : FrameLayout {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.layout_error, this)
    }

    fun bindThrowable(throwable: Throwable, action: () -> Unit) {
        if (throwable is NoConnectivityException) {
            tvErrorHeader.text = context.getString(R.string.global_whoops)
            tvErrorTitle.text = context.getString(R.string.global_connection_error)
            ivLogo.imageResource = R.drawable.ic_no_connection
            tvErrorSubmessage.text = context.getString(R.string.global_connection_error_check)
        } else {
            tvErrorHeader.text = context.getString(R.string.global_whatchout)
            tvErrorTitle.text = context.getString(R.string.global_server_error)
            ivLogo.imageResource = R.drawable.ic_no_server
            tvErrorSubmessage.text = context.getString(R.string.global_server_error_check)
        }

        btnErrorRefresh.onClick {
            action.invoke()
        }
    }

    fun bindThrowable(throwable: Throwable) {
        if (throwable is NoConnectivityException) {
            tvErrorHeader.text = context.getString(R.string.global_whoops)
            tvErrorTitle.text = context.getString(R.string.global_connection_error)
            ivLogo.imageResource = R.drawable.ic_no_connection
            tvErrorSubmessage.text = context.getString(R.string.global_connection_error_check)
        } else {
            tvErrorHeader.text = context.getString(R.string.global_whatchout)
            tvErrorTitle.text = context.getString(R.string.global_error_occured)
            ivLogo.imageResource = R.drawable.ic_no_server
            tvErrorSubmessage.text = throwable.message.toString()
        }
    }

    fun setAction(action: () -> Unit) {
        btnErrorRefresh.onClick {
            action.invoke()
        }
    }

}
