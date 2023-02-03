package com.projects.retrofit.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_table")
data class DatabasePost constructor (
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)


fun List<Post>.asDatabaseModel(): Array<DatabasePost> {
    return map {
        DatabasePost(
            id = it.id,
            userId = it.userId,
            title = it.title,
            body = it.body
        )
    }.toTypedArray()
}


fun List<DatabasePost>.asDomainModel(): List<Post> {
    return map {
        Post(
            id = it.id,
            userId = it.userId,
            title = it.title,
            body = it.body
        )
    }
}

