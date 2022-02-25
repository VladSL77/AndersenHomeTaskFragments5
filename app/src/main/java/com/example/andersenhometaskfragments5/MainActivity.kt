package com.example.andersenhometaskfragments5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.andersenhometaskfragments5.FragmentDetail.Companion.FRAGMENT_DETAIL_TAG
import com.example.andersenhometaskfragments5.FragmentList.Companion.FRAGMENT_LIST_TAG
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), FragmentList.InfoClickListener,
    FragmentDetail.SaveButtonClickListener {

    private lateinit var list: ArrayList<String>
    private var isTablet = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isTablet = resources.getBoolean(R.bool.isTablet)

        list = arrayListOf(
            "FirstName1",
            "LastName1",
            "111",
            "FirstName2",
            "LastName2",
            "222",
            "FirstName3",
            "LastName3",
            "333"
        )

        if (isTablet) {

            if (supportFragmentManager.findFragmentByTag(FRAGMENT_DETAIL_TAG) == null) {
                supportFragmentManager.beginTransaction().run {
                    replace(
                        R.id.fragmentContainer2,
                        FragmentDetail.newInstance(list, index = 0),
                        FRAGMENT_DETAIL_TAG
                    )
                    commit()
                }
            }

        }

        if (supportFragmentManager.findFragmentByTag(FRAGMENT_LIST_TAG) == null) {
            supportFragmentManager.beginTransaction().run {
                replace(R.id.fragmentContainer1, FragmentList.newInstance(list), FRAGMENT_LIST_TAG)
                commit()
            }
        }
    }

    override fun onInfoClicked(list: ArrayList<String>, index: Int) {

        if (isTablet) {
            supportFragmentManager.beginTransaction().run {
                replace(
                    R.id.fragmentContainer2,
                    FragmentDetail.newInstance(list, index),
                    FRAGMENT_DETAIL_TAG
                )
                addToBackStack(FRAGMENT_DETAIL_TAG)
                commit()
            }
        } else {
            supportFragmentManager.beginTransaction().run {
                replace(
                    R.id.fragmentContainer1,
                    FragmentDetail.newInstance(list, index),
                    FRAGMENT_DETAIL_TAG
                )
                addToBackStack(FRAGMENT_DETAIL_TAG)
                commit()
            }
        }

    }

    override fun onSaveButtonClicked(list: ArrayList<String>) {

        supportFragmentManager.beginTransaction().run {
            replace(R.id.fragmentContainer1, FragmentList.newInstance(list), FRAGMENT_LIST_TAG)
            commit()
        }

    }

    override fun onBackPressed() {
        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer1) as? BackPressedListener
        if (currentFragment?.onBackPressedClicked() != false) {
            super.onBackPressed()
        } else {
            moveTaskToBack(true)
            exitProcess(-1)
        }
    }

}