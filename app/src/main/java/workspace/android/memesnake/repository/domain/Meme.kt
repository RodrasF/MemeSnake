package workspace.android.memesnake.repository.domain

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Meme")
data class Meme(
    @PrimaryKey @NonNull val url: String,
    val postLink: String,
    val subreddit: String,
    val title: String,
    val date: String? = "-")