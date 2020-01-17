package workspace.android.memesnake.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_profile_layout.*
import workspace.android.memesnake.R
import workspace.android.memesnake.util.Resource
import workspace.android.memesnake.repository.domain.User
import workspace.android.memesnake.util.viewModel
import workspace.android.memesnake.views.viewmodels.ProfileViewModel

const val KEY_LOGIN = "userLogin"

class ProfileFragment : Fragment() {

    private val TAG = ProfileFragment::class.java.simpleName

    private lateinit var model: ProfileViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.user_profile_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = this.viewModel()
        model.setLogin(arguments!!.getString(KEY_LOGIN))

        model.user.observe(this, Observer<Resource<User>>{
            if(it?.data != null) {
                user_profile_name.text = it.data.login
                user_profile_email.text = it.data.email
                user_profile_followers.text = it.data.followers.toString()
                Picasso.get().load(it.data.avatar_url).error(R.drawable.profile_icon).into(user_profile_picture)

            }
        })
    }
    companion object {

        @JvmStatic
        fun newInstance(userLogin: String) = ProfileFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_LOGIN, userLogin)
            }
        }
    }
}