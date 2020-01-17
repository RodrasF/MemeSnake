package workspace.android.memesnake

import android.app.Application
import workspace.android.memesnake.repository.Repository
import androidx.room.Room
import com.google.firebase.FirebaseApp
import workspace.android.memesnake.repository.dataAccess.db.AppRoomDatabase
import workspace.android.memesnake.repository.dataAccess.HttpRequests


class MemeSnakeApp : Application() {

    lateinit var repository: Repository
        private set

    private lateinit var appRoomDatabase: AppRoomDatabase

    override fun onCreate() {
        super.onCreate()

        HttpRequests.init(this)

        FirebaseApp.initializeApp(this)

        appRoomDatabase = Room.databaseBuilder(this, AppRoomDatabase::class.java, "MemeSnake_database").build()

        repository = Repository(this,appRoomDatabase.userDao(), appRoomDatabase.memeDao())
    }
}