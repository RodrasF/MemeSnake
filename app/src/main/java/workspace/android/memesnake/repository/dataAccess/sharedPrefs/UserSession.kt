package workspace.android.memesnake.repository.dataAccess.sharedPrefs

import android.content.Context
import android.content.SharedPreferences

private const val PREFS_NAME = "MemeSnakeSharedPrefs"

private const val PRIVATE_MODE = 0

private const val IS_USER_LOGIN = "IsUserLoggedIn"

private const val KEY_LOGIN = "userLogin"

private const val KEY_PASSWORD = "password"

class UserSession(context : Context) {

    // SharedPreferences reference
    private val prefs = context.getSharedPreferences(PREFS_NAME, PRIVATE_MODE)

    // Editor reference for Shared preferences
    private val editor: SharedPreferences.Editor = prefs.edit()

    //Create login session
    fun createUserLoginSession(userLogin: String, password: String) {
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true)

        // Storing name in preferences
        editor.putString(KEY_LOGIN, userLogin)

        // Storing password in preferences
        editor.putString(KEY_PASSWORD, password)

        // apply changes
        editor.apply()
    }

    // Check for login
    val isUserLoggedIn: Boolean
        get() = prefs.getBoolean(IS_USER_LOGIN, false)


    // Check for Login Name
    val login: String
        get() = prefs.getString(KEY_LOGIN,"Empty!")!!

    // Check for Password
    val password: String
        get() = prefs.getString(KEY_PASSWORD,"Empty!")!!

    /**
     * Clear session details
     */
    fun logoutUser() {

        // Clearing all user data from Shared Preferences
        editor.clear()
        editor.apply()
    }
}
