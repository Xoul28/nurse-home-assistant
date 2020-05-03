package com.dopezebraevm.nursehomeassistant

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.fragment.app.Fragment
import androidx.transition.Slide
import com.dopezebraevm.nursehomeassistant.view.auth.LoginFirstStepFragment
import com.dopezebraevm.nursehomeassistant.view.task.NewTaskVO
import com.dopezebraevm.nursehomeassistant.view.task.TaskBuilderFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showFragment(TaskBuilderFragment())
        //showFragment(LoginFirstStepFragment())

        bottom_navigation.setOnNavigationItemSelectedListener { item -> onSelectedItem(item) }
    }

    fun showBottomNavigationBar() {
        bottom_navigation.visibility = View.VISIBLE
    }

    private fun onSelectedItem(item: MenuItem): Boolean {
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    fun closeFragment(payload: Any? = null) {}

    fun showFragment(fragment: Fragment, tag: String? = null) {
        val transaction = supportFragmentManager.beginTransaction()
        val currentFragment = supportFragmentManager.findFragmentById(R.id.content_frame)
        setupAnimation(fragment)
        transaction.add(R.id.content_frame, fragment, tag)
        transaction.addToBackStack(null)
        try {
            transaction.commit()
        } catch (ignored: IllegalStateException) { }
    }

    private fun setupAnimation(fragment: Fragment) {
        val enterFade = Slide(Gravity.END)
        enterFade.startDelay = 80
        enterFade.duration = 200
        fragment.enterTransition = enterFade
    }

    private fun isSameFragment(
        newFragment: Fragment,
        newTag: String?,
        oldFragment: Fragment?
    ): Boolean {
        return if (newTag != null) newTag == oldFragment?.tag
        else oldFragment?.javaClass == newFragment.javaClass
    }
}