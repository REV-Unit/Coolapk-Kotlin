package org.revunit.coolapkkt.ui.activity

import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.revunit.coolapkkt.Constants
import org.revunit.coolapkkt.CoolapkKotlinApplication
import org.revunit.coolapkkt.R
import org.revunit.coolapkkt.databinding.ActivityCoolPicDetailsBinding
import org.revunit.coolapkkt.network.Client
import org.revunit.coolapkkt.ui.adapter.CoolPicInfoViewPagerAdapter
import java.text.SimpleDateFormat
import java.util.*


class CoolPicDetailsActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {
    private lateinit var binding: ActivityCoolPicDetailsBinding
    private var imageCount = 0
    private var postId = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoolPicDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postId = intent.extras!!.getLong(Constants.IntentExtra.postId)
        loadCoolPicData()
    }

    private fun loadCoolPicData() {
        GlobalScope.launch(Dispatchers.Main) {
            val data = try {
                withContext(Dispatchers.IO) { Client.getPostInfoData(postId) }
            } catch (e: Exception) {
                null
            } ?: return@launch
            if (data.data == null) return@launch
            binding.avatarImageUrl = data.data!!.userAvatar
            binding.description = data.data!!.message
            binding.fromDevice = data.data!!.device_title
            binding.nickname = data.data!!.username
            binding.postTitle = data.data!!.title
            binding.postTimestamp = data.data!!.dateline
            binding.postIdLong = data.data!!.id.toLong()
            imageCount = data.data!!.picArr?.size ?: 0
            binding.picCountStr="1 / ${imageCount}"
            initViewPager(data.data!!.picArr as ArrayList<String>)
        }
    }

    private fun initViewPager(images: ArrayList<String>) {
        val adapter = CoolPicInfoViewPagerAdapter(images, supportFragmentManager)
        binding.images.adapter = adapter
        binding.images.addOnPageChangeListener(this)
    }

    companion object {
        @JvmStatic
        @BindingAdapter("postUserAvatar")
        fun setImage(imageView: ImageView?, url: String?) {
            if (imageView == null) return
            Glide.with(imageView).load(url).into(imageView)
        }

        @JvmStatic
        @BindingAdapter("postId")
        fun setPostId(textView: TextView?, postId: Long) {
            if (textView == null) return
            textView.text = setHighlightColor("帖子ID：", postId.toString())
        }

        @JvmStatic
        @BindingAdapter("picPixel")
        fun setPostPixel(textView: TextView?, pixel: String?) {
            if (textView == null || pixel == null) return
            textView.text = setHighlightColor("分辨率：", pixel)
        }

        @JvmStatic
        @BindingAdapter("fromDevice")
        fun setFromDevice(textView: TextView?, device: String?) {
            if (textView == null || device == null) return
            textView.text = setHighlightColor("来自：", device)
        }

        @JvmStatic
        @BindingAdapter("postTime")
        fun setPostTime(textView: TextView?, time: Long) {
            if (textView == null) return
            textView.text = setHighlightColor(
                "时间：",
                SimpleDateFormat.getDateTimeInstance().format(Date(time * 1000L))
            )
        }

        @JvmStatic
        @BindingAdapter("postDescription")
        fun setPostDescription(textView: TextView?, description: String?) {
            if (textView == null || description == null) return
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                textView.text = Html.fromHtml(description, HtmlCompat.FROM_HTML_MODE_LEGACY)
            } else {
                textView.text = Html.fromHtml(description)
            }
        }

        private fun setHighlightColor(prefix: String, highlightText: String): SpannableString {
            val start = prefix.length
            val sp = SpannableString(prefix + highlightText)
            sp.setSpan(
                ForegroundColorSpan(
                    CoolapkKotlinApplication.myApplicationContext.resources.getColor(
                        R.color.teal_700
                    )
                ),
                start,
                start + highlightText.length,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE
            )
            return sp
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        binding.picCountStr = "${position + 1} / $imageCount"
    }

    override fun onPageScrollStateChanged(state: Int) {
    }
}