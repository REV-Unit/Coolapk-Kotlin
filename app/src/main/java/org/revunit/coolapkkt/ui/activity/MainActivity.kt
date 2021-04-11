package org.revunit.coolapkkt.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.revunit.coolapkkt.data.MainPageCoolPicCategory
import org.revunit.coolapkkt.databinding.ActivityMainBinding
import org.revunit.coolapkkt.databinding.LayoutErrorDetailsBinding
import org.revunit.coolapkkt.network.Client
import org.revunit.coolapkkt.network.data.response.PicIndexData
import org.revunit.coolapkkt.ui.adapter.MainPageFragmentRecommendRecyclerViewAdapter
import org.revunit.coolapkkt.ui.adapter.MainPageRecyclerViewAdapter
import org.revunit.coolapkkt.utils.DeviceUtils

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var mainViewBinding: ActivityMainBinding
    private lateinit var categoryAdapter: MainPageRecyclerViewAdapter
    private lateinit var recommendAdapter: MainPageFragmentRecommendRecyclerViewAdapter
    private var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewBinding = ActivityMainBinding.inflate(layoutInflater)
        val rootView = mainViewBinding.root
        setContentView(rootView)

        mainViewBinding.swipeRefreshLayout.setOnRefreshListener(this)
        initCategoryRecyclerView()
        initRecommendRecyclerView()
        initRecommendLoadMore()
        onRefresh()
    }

    override fun onRefresh() {
        GlobalScope.launch(Dispatchers.Main) {
            loadCategoryData()
            refreshRecommendData()
            mainViewBinding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initCategoryRecyclerView() {
        categoryAdapter = MainPageRecyclerViewAdapter()
        with(mainViewBinding.fragmentMainPage.fragmentMainPageRecyclerView) {
            val layoutManager =
                GridLayoutManager(
                    this@MainActivity,
                    if (DeviceUtils.isScreenOrientationLandscape()) 2 else 1,
                    GridLayoutManager.VERTICAL,
                    false
                )
            this.layoutManager = layoutManager
            this.adapter = categoryAdapter
        }
    }

    private fun initRecommendRecyclerView() {
        recommendAdapter = MainPageFragmentRecommendRecyclerViewAdapter()
        recommendAdapter.animationEnable = true
        with(mainViewBinding.fragmentMainPageRecommend.recyclerView) {
            val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            this.layoutManager = layoutManager
            this.adapter = recommendAdapter
        }
    }

    private suspend fun loadCategoryData() {
        mainViewBinding.swipeRefreshLayout.isRefreshing = true
        val data = try {
            withContext(Dispatchers.IO) { Client.getPicDataList("酷图", 1) }
        } catch (e: Exception) {
            categoryAdapter.setEmptyView(getErrorLayout(e.toString()))
            e.printStackTrace()
            null
        }
        if (data!!.status != 0) {
            categoryAdapter.setEmptyView(getErrorLayout(data.message!!))
        } else {
            categoryAdapter.setList(convertData(data))
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
        page = 1
        loadRecommendData()
    }

    private fun getErrorLayout(msg: String): View {
        val bd = LayoutErrorDetailsBinding.inflate(layoutInflater)
        bd.errorDetails = msg
        return bd.root
    }

    private fun convertData(data: PicIndexData?): ArrayList<MainPageCoolPicCategory> {
        val list = ArrayList<MainPageCoolPicCategory>()
        if (data == null) return list
        list.add(MainPageCoolPicCategory(headerString = "类型", entryData = null))
        data.data?.get(1)?.entities?.forEach {
            list.add(MainPageCoolPicCategory(headerString = null, entryData = it))
        }
        list.add(MainPageCoolPicCategory(headerString = "分辨率", entryData = null))
        data.data?.get(2)?.entities?.forEach {
            list.add(MainPageCoolPicCategory(headerString = null, entryData = it))
        }
        return list
    }

}