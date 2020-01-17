package workspace.android.memesnake.util

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import workspace.android.memesnake.MemeSnakeApp
import workspace.android.memesnake.repository.Repository

val Application.repository: Repository
    get() = (this as MemeSnakeApp).repository

inline fun <reified T : ViewModel> FragmentActivity.viewModel(): T =
        ViewModelProvider(this, ViewModelFactory(this.application.repository)).get(T::class.java)

inline fun <reified T : ViewModel> Fragment.viewModel(): T =
        ViewModelProvider(this, ViewModelFactory(this.activity!!.application.repository)).get(T::class.java)

class ViewModelFactory(val repo: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repository::class.java).newInstance(repo)
    }
}