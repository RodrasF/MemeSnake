package workspace.android.memesnake.views.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import workspace.android.memesnake.repository.Repository
import workspace.android.memesnake.util.Resource
import workspace.android.memesnake.repository.domain.User
import workspace.android.memesnake.util.AbsentLiveData

class ProfileViewModel(
        private val repo: Repository
) : ViewModel() {

    private val TAG = ProfileViewModel::class.java.simpleName

    private val _userLogin = MutableLiveData<String>()
    val userLogin: LiveData<String>
        get() = _userLogin

    val user: LiveData<Resource<User>> = Transformations
            .switchMap(_userLogin) { login ->
                if (login == null) {
                    AbsentLiveData.create()
                } else {
                    repo.loadUser(login)
                }
            }

    fun setLogin(login: String?) {
        if (_userLogin.value != login) {
            _userLogin.value = login
        }
    }
}
