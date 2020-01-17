package workspace.android.memesnake.views

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.login.*
import workspace.android.memesnake.R
import workspace.android.memesnake.util.viewModel
import workspace.android.memesnake.views.viewmodels.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private val TAG = LoginActivity::class.java.simpleName

    private lateinit var model : LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG,"Starting LoginActivity")
        setContentView(R.layout.login)

        model = this.viewModel()

        model.isLoggedIn().observe(this, Observer<Boolean> {
            if(it){
                Log.i(TAG, "Logged in!")
                startActivity(Intent(this@LoginActivity, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }else{
                Log.i(TAG, "Not Logged in!")
                login_loading_circle.visibility = View.GONE
            }
        })

        loginButton.setOnClickListener {

            val userLogin = user_login.text.toString()
            val password = access_token.text.toString()


            if(TextUtils.isEmpty(userLogin) || TextUtils.isEmpty(password)){
                Toast.makeText(this, R.string.login_fields_warning,Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            login_loading_circle.visibility = View.VISIBLE
            model.login(userLogin, password)
        }
    }
}
