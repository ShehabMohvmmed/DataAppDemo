package com.projects.retrofit.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.projects.retrofit.model.DatabasePost
import com.projects.retrofit.model.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPosts(vararg posts: DatabasePost)

//    @Query("SELECT * FROM post_table ORDER BY id Asc")
//    fun getAllPosts(): LiveData<List<DatabasePost>>

    @Query("SELECT * FROM post_table ORDER BY id Asc")
    fun getAllPosts(): LiveData<List<DatabasePost>>

    @Query("SELECT * FROM post_table WHERE id <= :number ORDER BY id =:order")
    fun getFirst50Posts(number: Int,order:String): LiveData<List<DatabasePost>>

    @Query("SELECT * FROM post_table WHERE id>50 ORDER BY id Asc")
    fun getLast51Posts(): LiveData<List<DatabasePost>>

}