package org.revunit.coolapkkt.ui.activity

import android.content.Intent
import android.os.Bundle
import org.revunit.coolapkkt.databinding.ActivityMainBinding
import org.revunit.coolapkkt.ui.fragment.CoolPicCategoryFragment
import org.revunit.coolapkkt.ui.fragment.CoolPicRecommendFragment

class MainActivity : BaseActivity() {
    private lateinit var mainViewBinding: ActivityMainBinding
    private val categoryFragment by lazy { CoolPicCategoryFragment.newInstance() }
    private val recommendFragment by lazy { CoolPicRecommendFragment.newInstance() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewBinding = ActivityMainBinding.inflate(layoutInflater)
        val rootView = mainViewBinding.root
        setContentView(rootView)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(mainViewBinding.fragmentMainPage.id, categoryFragment)
        if (mainViewBinding.fragmentMainPageRecommend != null) {
            fragmentTransaction.replace(
                mainViewBinding.fragmentMainPageRecommend!!.id,
                recommendFragment
            )
        }
        fragmentTransaction.commitAllowingStateLoss()
        mainViewBinding.gotoRecommendButton?.setOnClickListener {
            val intent = Intent(this, CoolPicRecommendActivity::class.java)
            startActivity(intent)
        }
    }
}