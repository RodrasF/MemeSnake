package workspace.android.memesnake.repository.dataAccess.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import workspace.android.memesnake.repository.domain.User

@Dao
interface UserDao {

    @Query("SELECT * from USER ORDER BY login ASC")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * from USER WHERE login = :userLogin LIMIT 1")
    fun findByLogin(userLogin : String): LiveData<User>

    @Insert(onConflict = REPLACE)
    fun insert(user: User)

    @Query("DELETE FROM USER")
    fun deleteAll()
}