package org.revunit.coolapkkt.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.revunit.coolapkkt.Constants
import org.revunit.coolapkkt.databinding.FragmentMainPageRecommendBinding
import org.revunit.coolapkkt.databinding.LayoutErrorDetailsBinding
import org.revunit.coolapkkt.network.Client
import org.revunit.coolapkkt.network.data.response.PicRecommendData
import org.revunit.coolapkkt.ui.activity.CoolPicDetailsActivity
import org.revunit.coolapkkt.ui.adapter.MainPageFragmentRecommendRecyclerViewAdapter
import org.revunit.coolapkkt.utils.ScaleUtils

class CoolPicRecommendFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var binding: FragmentMainPageRecommendBinding
    private lateinit var recommendAdapter: MainPageFragmentRecommendRecyclerViewAdapter
    private var page = 1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainPageRecommendBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipeRefreshLayout.setOnRefreshListener(this)
        binding.floatingActionButton.setOnClickListener{
            binding.recyclerView.smoothScrollToPosition(0)
        }
        initRecommendRecyclerView()
        initRecommendLoadMore()
        onRefresh()
    }

    override fun onRefresh() {
        GlobalScope.launch(Dispatchers.Main) {
            refreshRecommendData()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initRecommendRecyclerView() {
        recommendAdapter = MainPageFragmentRecommendRecyclerViewAdapter()
        recommendAdapter.animationEnable = true
        val recyclerView = binding.recyclerView

        recyclerView.post {
            // 获取recyclerView的大小
            // 单位 px像素
            val width = recyclerView.measuredWidth

            // image view 最小宽度400px
            val span = (ScaleUtils.px2dp(width) / ScaleUtils.px2dp(400)).coerceAtLeast(2)
            with(recyclerView) {
                val layoutManager =
                    StaggeredGridLayoutManager(
                        span,
                        StaggeredGridLayoutManager.VERTICAL
                    )
                this.layoutManager = layoutManager
                this.adapter = recommendAdapter
            }
        }

        recommendAdapter.setOnItemClickListener { adapter, _, position ->
            val data = adapter.data[position] as PicRecommendData.DataBean
            val bundle = Bundle().also {
                it.putLong(Constants.IntentExtra.postId, data.id!!.toLong())
            }
            val intent = Intent(requireActivity(), CoolPicDetailsActivity::class.java).also {
                it.putExtras(bundle)
            }
            startActivity(intent)
        }
    }

    private fun initRecommendLoadMore() {
        recommendAdapter.loadMoreModule.setOnLoadMoreListener {
            GlobalScope.launch(Dispatchers.Main) { loadRecommendData() }
        }
        recommendAdapter.loadMoreModule.isAutoLoadMore = true
        recommendAdapter.loadMoreModule.isEnableLoadMore = false
    }

    private suspend fun loadRecommendData() {
        val data = try {
            Client.getPicRecommendList(page)
        } catch (e: Exception) {
            e.printStackTrace()
            if (page==1){
                recommendAdapter.setEmptyView(getErrorLayout(e.toString()))
            }else{
                recommendAdapter.loadMoreModule.loadMoreFail()
            }
            null
        } ?: return
        if (page == 1) {//第一页
            recommendAdapter.setList(data.data)
        } else {
            recommendAdapter.addData(data.data)
        }
        page++
        recommendAdapter.loadMoreModule.isEnableLoadMore = true
        recommendAdapter.loadMoreModule.loadMoreComplete()
    }

    private suspend fun refreshRecommendData() {
        recommendAdapter.loadMoreModule.isEnableLoadMore = false
        binding.swipeRefreshLayout.isRefreshing=true
        page = 1
        loadRecommendData()
    }

    private fun getErrorLayout(msg: String): View {
        val bd = LayoutErrorDetailsBinding.inflate(layoutInflater)
        bd.errorDetails = msg
        return bd.root
    }

    companion object {
        @JvmStatic
        fun newInstance(): CoolPicRecommendFragment {
            val instance = CoolPicRecommendFragment()
            return instance
        }
    }
}