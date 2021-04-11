package org.revunit.coolapkkt.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import org.revunit.coolapkkt.R
import org.revunit.coolapkkt.data.MainPageCoolPicCategory
import org.revunit.coolapkkt.databinding.ItemPicCategoryBinding

class MainPageRecyclerViewAdapter(
    private val mData: ArrayList<MainPageCoolPicCategory> = ArrayList()
) :
    BaseSectionQuickAdapter<MainPageCoolPicCategory, BaseViewHolder>(
        R.layout.layout_main_page_recycler_view_header,
        R.layout.item_pic_category,
        mData
    ) {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: MainPageCoolPicCategory) {
        val binding = DataBindingUtil.getBinding<ItemPicCategoryBinding>(holder.itemView)!!
        binding.imageUrl = item.entryData!!.pic
        binding.description = item.entryData.title
    }

    override fun convertHeader(helper: BaseViewHolder, item: MainPageCoolPicCategory) {
        helper.setText(R.id.header, item.headerString)
    }

    fun updateData(newData: List<MainPageCoolPicCategory>?) {
        mData.clear()
        if (newData != null) {
            mData.addAll(newData)
        }
        notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        @BindingAdapter("categoryImage")
        fun setImage(imageView: ImageView?, url: String?) {
            if (imageView == null || url == null) return
            Glide.with(imageView).load(url).into(imageView)
        }
    }
}