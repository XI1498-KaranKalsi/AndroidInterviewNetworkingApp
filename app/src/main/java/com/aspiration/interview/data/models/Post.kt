package com.aspiration.interview.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(@PrimaryKey val id: Long, val userId: Long, val title: String, val body: String)