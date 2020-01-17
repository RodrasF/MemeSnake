package workspace.android.memesnake.views.viewmodels

import androidx.lifecycle.ViewModel
import workspace.android.memesnake.repository.Repository

class MainViewModel(
        private val repo : Repository
) : ViewModel(){

    private val TAG = MainViewModel::class.java.simpleName


    fun logout(){
        repo.logout()
    }


    fun getLogin(): String {
        return repo.getPrefsLogin()

    }

    fun getPassword(): String {
        return repo.getPrefsPassword()
    }
}