package com.aspiration.interview.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aspiration.interview.data.models.Post
import io.reactivex.Single


@Dao
interface PostDao {

    @Query("SELECT * FROM post")
    fun getAll(): List<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<Post>)
}