package com.winqoo.wikia.ui.common

import com.winqoo.wikia.data.models.ListItem

class LoadItem : ListItem {
    companion object {
        fun create(): LoadItem {
            return LoadItem()
        }
    }
}