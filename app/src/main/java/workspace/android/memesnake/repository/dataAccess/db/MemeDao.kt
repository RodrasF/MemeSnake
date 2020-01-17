package workspace.android.memesnake.repository.dataAccess.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import workspace.android.memesnake.repository.domain.Meme

@Dao
interface MemeDao {

    @Query("SELECT * from MEME ORDER BY date DESC")
    fun getAllMemes(): LiveData<List<Meme>>

    @Query("SELECT * from MEME WHERE url = :url LIMIT 1")
    fun getMeme(url : String): LiveData<Meme>

    @Insert(onConflict = REPLACE)
    fun insert(meme : Meme)

    @Insert(onConflict = REPLACE)
    fun insertAllMemes( memes : List<Meme>)

    @Query("DELETE FROM MEME")
    fun deleteAll()
}