package com.dopezebraevm.nursehomeassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.transition.Slide
import com.dopezebraevm.nursehomeassistant.view.auth.LoginFirstStepFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showFragment(LoginFirstStepFragment())

        bottom_navigation.setOnNavigationItemSelectedListener { item -> onSelectedItem(item) }
    }

    private fun onSelectedItem(item: MenuItem): Boolean {
        return true
    }


    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack()
    }


    public fun showFragment(fragment: Fragment, tag: String? = null) {
        val transaction = supportFragmentManager.beginTransaction()
        val currentFragment = supportFragmentManager.findFragmentById(R.id.content_frame)
        setupAnimation(fragment)
        transaction.add(R.id.content_frame, fragment, tag)
        try {
            transaction.commit()
        } catch (ignored: IllegalStateException) {
            val s = ""
        }
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