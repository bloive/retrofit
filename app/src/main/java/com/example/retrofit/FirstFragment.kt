package com.example.retrofit

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.databinding.FragmentFirstBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private lateinit var adapter: RecyclerAdapter
    private var items = mutableListOf<ItemModel>()
    private val viewModel: CountriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewModel.init()
        initRecycler()
        observes()

        binding.refresh.setOnRefreshListener {
            adapter.clearData()
            viewModel.init()
        }
    }

    private fun initRecycler() {
        adapter = RecyclerAdapter(items)
        binding.recycler.layoutManager = LinearLayoutManager(activity)
        binding.recycler.adapter = adapter
    }

    private fun observes() {
        viewModel._loadingLiveData.observe(viewLifecycleOwner, {
            binding.refresh.isRefreshing = it
        })
        viewModel._countriesLiveData.observe(viewLifecycleOwner, {
            adapter.setData(it.toMutableList())
        })
    }
}