package org.revunit.coolapkkt.ui.adapter

import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import org.revunit.coolapkkt.R
import org.revunit.coolapkkt.databinding.ItemPicRecommendBinding
import org.revunit.coolapkkt.network.data.response.PicRecommendData
import org.revunit.coolapkkt.utils.DeviceUtils
import org.revunit.coolapkkt.utils.ScaleUtils

class MainPageFragmentRecommendRecyclerViewAdapter(
    private val mData: ArrayList<PicRecommendData.DataBean> = ArrayList()
) : BaseQuickAdapter<PicRecommendData.DataBean, BaseViewHolder>(
    R.layout.item_pic_recommend,
    mData
), LoadMoreModule {
    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: PicRecommendData.DataBean) {
        val binding = DataBindingUtil.getBinding<ItemPicRecommendBinding>(holder.itemView)!!
        binding.headerImageUrl = item.pic
        binding.content = item.message
        binding.nickname = item.username
        binding.avatarImageUrl = item.userAvatar
        with(item.label!!.split("x")) {
            binding.headerImageWidth = this[0].toInt()
            binding.headerImageHeight = this[1].toInt()
        }
    }

    fun addData(newData: List<PicRecommendData.DataBean>?) {
        if (newData == null) return
        val index = mData.size + 1
        mData.addAll(newData)
        notifyItemInserted(index)
    }

    companion object {
        @JvmStatic
        @BindingAdapter(
            "headerImage",
            "headerImageWidth",
            "headerImageHeight",
        )
        fun setHeaderImage(
            imageView: ImageView?,
            url: String?,
            picWidth: Int,
            picHeight: Int,
        ) {
            if (imageView == null || url == null) return
            val h = calculateImageHeight(intArrayOf(picWidth, picHeight))
            val lp = imageView.layoutParams
            lp.height =h.coerceAtMost(imageView.maxHeight)
            imageView.layoutParams = lp
            setImage(imageView, url)
        }

        @JvmStatic
        @BindingAdapter("avatar")
        fun setAvatarImage(imageView: ImageView?, url: String?) = setImage(imageView, url)

        @JvmStatic
        @BindingAdapter("content")
        fun setCoolPicDescription(textView: TextView?, text: String?) {
            if (textView == null) return
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textView.text = Html.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
            } else {
                textView.text = Html.fromHtml(text)
            }
        }

        private fun setImage(imageView: ImageView?, url: String?) {
            if (imageView == null || url == null) return
            Glide.with(imageView).load(url).into(imageView)
        }

        private fun calculateImageHeight(imageSize: IntArray): Int {
            val width = imageSize[0]
            val height = imageSize[1]
            var w = if (DeviceUtils.isTabletDevice()) {
                ScaleUtils.getScreenWidthPixels() * 0.7
            } else {
                ScaleUtils.getScreenWidthPixels()
            }.toInt() - 8 * 4
            w /= 2
            val h = height * w / width.toFloat()
            return h.toInt()
        }
    }
}