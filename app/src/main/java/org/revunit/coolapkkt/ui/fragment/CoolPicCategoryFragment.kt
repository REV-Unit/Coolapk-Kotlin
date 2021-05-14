package org.revunit.coolapkkt.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.revunit.coolapkkt.data.MainPageCoolPicCategory
import org.revunit.coolapkkt.databinding.FragmentMainPageBinding
import org.revunit.coolapkkt.databinding.LayoutErrorDetailsBinding
import org.revunit.coolapkkt.network.Client
import org.revunit.coolapkkt.network.data.response.PicIndexData
import org.revunit.coolapkkt.ui.adapter.MainPageRecyclerViewAdapter

class CoolPicCategoryFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var binding: FragmentMainPageBinding
    private lateinit var categoryAdapter: MainPageRecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipeRefreshLayout.setOnRefreshListener(this)
        initCategoryRecyclerView()
        onRefresh()
    }

    override fun onRefresh() {
        GlobalScope.launch(Dispatchers.Main) {
            loadCategoryData()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initCategoryRecyclerView() {
        categoryAdapter = MainPageRecyclerViewAdapter()
        with(binding.fragmentMainPageRecyclerView) {
            val layoutManager =
                GridLayoutManager(
                    requireActivity(),
                    2,
                    GridLayoutManager.VERTICAL,
                    false
                )
            this.layoutManager = layoutManager
            this.adapter = categoryAdapter
        }
    }

    private suspend fun loadCategoryData() {
        binding.swipeRefreshLayout.isRefreshing = true
        val data = try {
            withContext(Dispatchers.IO) { Client.getPicDataList("酷图", 1) }
        } catch (e: Exception) {
            categoryAdapter.setEmptyView(getErrorLayout(e.toString()))
            e.printStackTrace()
            null
        }?:return
        if (data.status != 0) {
            categoryAdapter.setEmptyView(getErrorLayout(data.message!!))
        } else {
            categoryAdapter.setList(convertData(data))
        }
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

    companion object {
        @JvmStatic
        fun newInstance(): CoolPicCategoryFragment {
            val instance = CoolPicCategoryFragment()
            return instance
        }
    }
}