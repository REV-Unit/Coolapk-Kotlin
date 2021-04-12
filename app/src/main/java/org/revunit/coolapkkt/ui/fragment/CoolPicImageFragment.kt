package org.revunit.coolapkkt.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import org.revunit.coolapkkt.databinding.LayoutCoolPicInfoImageBinding

class CoolPicImageFragment(val imageUrl: String) : Fragment() {
    private lateinit var binding: LayoutCoolPicInfoImageBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutCoolPicInfoImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView = binding.image
        Glide.with(imageView).load(imageUrl).into(imageView)
    }
}