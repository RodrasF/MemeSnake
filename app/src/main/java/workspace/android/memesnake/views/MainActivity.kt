package workspace.android.memesnake.views

import android.content.Intent
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.nav_drawer.*
import kotlinx.android.synthetic.main.nav_drawer_app_bar.*
import workspace.android.memesnake.R
import workspace.android.memesnake.views.viewmodels.MainViewModel
import workspace.android.memesnake.util.replaceFragment
import workspace.android.memesnake.util.viewModel


open class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val TAG = MainActivity::class.java.simpleName

    private lateinit var model: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.nav_drawer)
        setSupportActionBar(toolbar)

        model = this.viewModel()


        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        setRefreshButtonListener()

        //Initialize Home Page for the app - Being the Home page the Logged user profile.
        replaceFragment(supportFragmentManager, ProfileFragment.newInstance(model.getLogin()))
    }


    private fun setRefreshButtonListener() {
    }




    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            when (this.supportFragmentManager.findFragmentById(R.id.content)) {
                is ProfileFragment -> {
                    replaceFragment(supportFragmentManager,
                        ProfileFragment.newInstance(model.getLogin())
                    )
                }
                else -> super.onBackPressed()
            }
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.menu_profile -> {
                replaceFragment(supportFragmentManager,
                    ProfileFragment.newInstance(model.getLogin())
                )
            }
            R.id.menu_logout -> {
                model.logout()
                // Starting Login Activity
                startActivity(Intent(this, LoginActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
