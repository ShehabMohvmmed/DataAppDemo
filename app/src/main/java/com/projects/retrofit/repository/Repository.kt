package com.projects.retrofit.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.projects.retrofit.`object`.RetrofitInstance
import com.projects.retrofit.database.PostDatabase
import com.projects.retrofit.model.Post
import com.projects.retrofit.model.asDatabaseModel
import com.projects.retrofit.model.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


//for fetching from network and storing it on disk
class Repository(private val database: PostDatabase) {

    val posts: LiveData<List<Post>> = Transformations.map(database.postsDao().getAllPosts()){
        it.asDomainModel()
    }

//    fun getFirst50Posts(order: String): LiveData<List<Post>> {
//        val first50Posts: LiveData<List<Post>> = Transformations.map(database.postsDao().getFirst50Posts(50, order)){
//            it.asDomainModel()
//        }
//        return first50Posts
//    }

    val first50Posts: LiveData<List<Post>> = Transformations.map(database.postsDao().getFirst50Posts(50,"DESC")){
        it.asDomainModel()
    }

    val last51Posts: LiveData<List<Post>> = Transformations.map(database.postsDao().getLast51Posts()){
        it.asDomainModel()
    }

    suspend fun refreshPosts() {
        withContext(Dispatchers.IO) {
            val result = RetrofitInstance.api.getPosts()
            database.postsDao().insertPosts(*result.asDatabaseModel())
        }
    }





}