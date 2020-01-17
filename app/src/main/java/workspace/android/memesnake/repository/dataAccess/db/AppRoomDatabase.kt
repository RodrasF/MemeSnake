package workspace.android.memesnake.repository.dataAccess.db

import androidx.room.Database
import androidx.room.RoomDatabase
import workspace.android.memesnake.repository.domain.Meme
import workspace.android.memesnake.repository.domain.User


@Database(entities = [User::class, Meme::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun memeDao(): MemeDao
}