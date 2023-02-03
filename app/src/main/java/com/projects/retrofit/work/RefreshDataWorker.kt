package com.projects.retrofit.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.projects.retrofit.database.PostDatabase
import com.projects.retrofit.repository.Repository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "Retrofit Worker"
    }

    val WORK_NAME = "Retrofit Worker"

    override suspend fun doWork(): Result {
        val database = PostDatabase.getDatabase(applicationContext)
        val repository = Repository(database)
        return try {
            repository.refreshPosts()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}
