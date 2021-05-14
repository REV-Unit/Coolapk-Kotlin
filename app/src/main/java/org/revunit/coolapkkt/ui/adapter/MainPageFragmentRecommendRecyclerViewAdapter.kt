package org.revunit.coolapkkt.ui.adapter

import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import org.revunit.coolapkkt.R
import org.revunit.coolapkkt.databinding.ItemPicRecommendBinding
import org.revunit.coolapkkt.network.data.response.PicRecommendData

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
        val imageView = binding.picImage
        val layoutParams = imageView.layoutParams as ConstraintLayout.LayoutParams
        val imageSizeArray = item.label!!.split("x").map { it.toInt() }
        val imageWidth = imageSizeArray[0]
        val imageHeight = imageSizeArray[1]
        layoutParams.dimensionRatio = when {
            // 高度:宽度>=3:1时，设置 image view 的宽高比为1:3
            imageHeight / imageWidth >= 3 -> "1:3"
            // 宽度:高度>=2:1时，设置 image view 的宽高比为2:1
            imageWidth / imageHeight >= 2 -> "2:1"
            // 其他比例，直接使用原始比例
            else -> "$imageWidth:$imageHeight"
        }
        imageView.layoutParams = layoutParams

        binding.headerImageUrl = item.pic
        binding.content = item.message
        binding.nickname = item.username
        binding.avatarImageUrl = item.userAvatar
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
            Glide.with(imageView)
                .load(url)
                .priority(Priority.HIGH)
                .override(picWidth, picHeight)
                .into(imageView)
        }

        @JvmStatic
        @BindingAdapter("avatar")
        fun setAvatarImage(imageView: ImageView?, url: String?) {
            if (imageView == null || url == null) return
            Glide.with(imageView).load(url).into(imageView)
        }

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
    }
}