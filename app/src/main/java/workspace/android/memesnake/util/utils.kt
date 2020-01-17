package workspace.android.memesnake.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import workspace.android.memesnake.R

fun addFragmentToStack(fm : FragmentManager, newFragment : Fragment) {

    val ft = fm.beginTransaction()
    with(ft){
        replace(R.id.content, newFragment)
        setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        addToBackStack(null)
        commit()
    }
}

fun replaceFragment(fm: FragmentManager, newFragment: Fragment) {

    val ft = fm.beginTransaction()
    ft.replace(R.id.content, newFragment)
    ft.commit()
}