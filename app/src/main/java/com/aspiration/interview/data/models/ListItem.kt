package com.aspiration.interview.data.models

import java.util.*

data class ListItem(val type: Int, val post: Post? = null, val date: String? = null) {

    companion object {
        val POST = 0
        val DATE = 1
    }
}