package com.projects.retrofit

import android.app.Application
import androidx.lifecycle.*
import com.projects.retrofit.repository.Repository
import kotlinx.coroutines.launch
import com.projects.retrofit.database.PostDatabase.Companion.getDatabase
import com.projects.retrofit.model.Post

class MainViewModel(application: Application): AndroidViewModel(application) {
    enum class PostsFilters { ALL, FIRST50, LAST51}

    private var filterPosts = MutableLiveData(PostsFilters.ALL)


    private var _navigateToDetailFragment = MutableLiveData<Post?>()
    val navigateToDetailFragment: LiveData<Post?>
        get() = _navigateToDetailFragment

    fun onSelectedPost(post: Post) {
        _navigateToDetailFragment.value = post
    }

    fun onPostNavigated() {
        _navigateToDetailFragment.value = null
    }


//    fun statusImage(): Boolean {
//
//    }


    val postsList = Transformations.switchMap(filterPosts) {
        when(it!!){
            PostsFilters.FIRST50 -> repository.first50Posts
            PostsFilters.LAST51 -> repository.last51Posts
            else -> repository.posts
        }
    }

//    fun statusImage(): Boolean{
//        var status = true
//        postsList.value?.forEach{ i ->
//            val value = i.id % 2
//            if (value == 0){
//                status = true
//            }else{
//                if (i.id % 2 != 0){
//                    status = false
//                }
//            }
//        }
//        return status
//    }

    var status = false

    fun onFilterSelected(filter: PostsFilters) {
        filterPosts.postValue(filter)
    }


    private val database = getDatabase(application)
    private val repository = Repository(database)
//    val posts = repository.posts
//    val first50Posts = repository.first50Posts
//    val last51Posts = repository.last51Posts
//    init {
//        viewModelScope.launch {
//            repository.refreshPosts()
//        }
//    }
    private var _onlineStatus = MutableLiveData<Boolean?>()
    val onlineStatus: LiveData<Boolean?>
            get() = _onlineStatus

    init {
        viewModelScope.launch {
            try {
                repository.refreshPosts()
                _onlineStatus.value = true
            }catch (e: Exception){
                _onlineStatus.value = false
            }
        }
    }


    fun getPosts() {

    }

    fun refreshPostsAgain() {
        viewModelScope.launch {
            repository.refreshPosts()
        }
    }

    //    fun getPostsList(order:String): LiveData<List<Post>> {
//        val postsList = Transformations.switchMap(filterPosts) {
//            when(it!!){
//                PostsFilters.FIRST50 -> repository.getFirst50Posts(order)
//                PostsFilters.LAST51 -> repository.last51Posts
//                else ->repository.posts
//            }
//        }
//        return postsList
//    }







class Factory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(app) as T
        }
        throw IllegalArgumentException("viewModel instance created error")
    }
}


}