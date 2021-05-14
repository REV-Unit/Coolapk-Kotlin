package org.revunit.coolapkkt.ui.activity

import android.os.Bundle
import org.revunit.coolapkkt.databinding.ActivityCoolPicRecommendBinding
import org.revunit.coolapkkt.ui.fragment.CoolPicRecommendFragment

class CoolPicRecommendActivity : BaseActivity() {
    private lateinit var binding: ActivityCoolPicRecommendBinding
    private val recommendFragment by lazy { CoolPicRecommendFragment.newInstance() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoolPicRecommendBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setHomeButtonEnabled(true)
        supportFragmentManager.beginTransaction()
            .replace(binding.frameLayout.id, recommendFragment)
            .commitAllowingStateLoss()
    }
}