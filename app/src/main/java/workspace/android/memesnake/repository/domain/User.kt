package workspace.android.memesnake.repository.domain

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "USER")
data class User(
        @PrimaryKey @NonNull val login: String,
        val avatar_url: String,
        val email: String,
        var followers: Int)