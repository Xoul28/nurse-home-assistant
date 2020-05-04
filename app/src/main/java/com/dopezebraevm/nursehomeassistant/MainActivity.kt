package com.dopezebraevm.nursehomeassistant

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.transition.Slide
import com.dopezebraevm.nursehomeassistant.view.ArticaleFragment
import com.dopezebraevm.nursehomeassistant.view.MainFragment
import com.dopezebraevm.nursehomeassistant.view.auth.LoginFirstStepFragment
import com.dopezebraevm.nursehomeassistant.view.encyclopedia.EncyclopediaFragment
import com.dopezebraevm.nursehomeassistant.view.indicators.PainFragment
import com.dopezebraevm.nursehomeassistant.view.plan.CreatePlanFragment
import com.dopezebraevm.nursehomeassistant.view.task.NewTaskVO
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showFragment(ArticaleFragment())
//        if (App.get(this).prefHelper.isNotFirstStart()) showFragment(MainFragment.newInstance())
//        else showFragment(LoginFirstStepFragment())

        bottom_navigation.setOnNavigationItemSelectedListener { item -> onSelectedItem(item) }
    }

    fun showBottomNavigationBar() {
        bottom_navigation.visibility = View.VISIBLE
    }

    fun hideBottomNavigationBar() {
        bottom_navigation.visibility = View.GONE
    }

    private fun onSelectedItem(item: MenuItem): Boolean {
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    fun closeFragment(newTaskVO: NewTaskVO) {
        supportFragmentManager.popBackStack()
        val fragments = supportFragmentManager.fragments
        fragments.forEach {
            when (it) {
                is MainFragment -> {
                    it.addTask(newTaskVO)
                    return@forEach
                }
                is CreatePlanFragment -> {
                    it.addTask(newTaskVO)
                    return@forEach
                }
            }
        }
    }

    fun closeFragment() {
        supportFragmentManager.popBackStack()
    }

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