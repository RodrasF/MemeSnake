package workspace.android.memesnake.repository

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import workspace.android.memesnake.repository.dataAccess.db.MemeDao
import workspace.android.memesnake.repository.dataAccess.db.UserDao
import workspace.android.memesnake.repository.dataAccess.sharedPrefs.UserSession
import workspace.android.memesnake.repository.domain.Meme
import workspace.android.memesnake.repository.domain.User
import workspace.android.memesnake.util.Resource


class Repository(context: Context, private val userDao : UserDao, private val memeDao : MemeDao) {

    private val TAG = Repository::class.java.simpleName

    private val session = UserSession(context)


    fun loadUser(userLogin: String): LiveData<Resource<User>> {
        TODO()
//        return object : NetworkBoundResource<User, User>() {
//            override fun saveCallResult(item: User) {
//                userDao.insert(item)
//            }
//
//            override fun shouldFetch(data: User?) = data == null
//
//            override fun loadFromDb() = userDao.findByLogin(userLogin)
//
//            override fun createCall() = getUser(userLogin)
//        }.asLiveData()
    }

    fun loadMemes(): LiveData<Resource<List<Meme>>> {
        TODO()
//            return object : NetworkBoundResource<List<Meme>, List<Meme>>() {
//            override fun saveCallResult(item: List<Meme>) {
//                memeDao.insertAllMemes(item)
//            }
//
//            override fun shouldFetch(data: List<Meme>?) = data.isNullOrEmpty()
//
//            override fun loadFromDb() = memeDao.getAllMemes()
//
//            override fun createCall() = MemeAPIClient.getMemes(10)
//        }.asLiveData()
    }


    private fun insertUser(user : User) {
        AsyncTask.execute{
            userDao.insert(user)
        }
    }


    fun validateUser(userLogin: String, password: String, cb: (Boolean)-> Unit){
        TODO()
//        MemeAPIClient.getAuthUser(password){
//            userDto ->
//
//            if(userDto == null){
//                Log.i(TAG,"Error! Invalid Credentials!")
//                cb(false)
//            }else {
//                session.createUserLoginSession(userLogin, password)
//                insertUser(userDto.toUser())
//                cb(true)
//            }
//        }
    }


    fun isLoggedIn(): Boolean {
        return session.isUserLoggedIn
    }

    fun logout() {
        AsyncTask.execute{
            userDao.deleteAll()
        }
        session.logoutUser()
    }

    fun getPrefsLogin(): String {
        return session.login
    }

    fun getPrefsPassword(): String {
        return session.password

    }
}