package workspace.android.memesnake.views.viewmodels

import androidx.lifecycle.MutableLiveData
import workspace.android.memesnake.repository.Repository
import androidx.lifecycle.ViewModel


class LoginViewModel(
        private val repo : Repository
) : ViewModel() {

    private val TAG = LoginViewModel::class.java.simpleName

    private val loggedIn = MutableLiveData<Boolean>()

    init{
        loggedIn.value = repo.isLoggedIn()
    }

    fun login(userLogin: String, password: String) {
        repo.validateUser(userLogin,password){
            loggedIn.postValue(it)
        }
    }

    fun isLoggedIn(): MutableLiveData<Boolean>{
        return loggedIn
    }
}