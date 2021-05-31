package com.example.retrofit

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        setData()
    }

    private fun init() {
        adapter = RecyclerAdapter(items)

        binding.recycler.layoutManager = LinearLayoutManager(activity)
        binding.recycler.adapter = adapter
    }

    private fun setData() {
        CoroutineScope(Dispatchers.IO).launch {
            getCountries()
        }
    }

    private suspend fun getCountries() {
        val response = RetrofitService.retrofitService().getCountry()
        if (response.isSuccessful) {
            try {
                val countries = response.body()!!
                countries.forEach{
                    items.add(ItemModel(it.name, it.capital, it.borders, it.translations, it.languages))
                    d("item", it.toString())
                }
                d("responceBody", "${items.size}")
            } catch (e:Exception) {
                //Todo
            }
        }
    }


    // Parse manually
//    private suspend fun getCountries() {
//        val response = RetrofitService.retrofitService().getCountry()
//        if (response.isSuccessful) {
//            d("response", "${response.body()}")
//            response.body()?.let { parseJSON(it) }
//        } else {
//            //TODO
//        }
//    }

//    private fun parseJSON(response: String) {
//        try {
//            val countries = JSONArray(response)
//            for (i in 0 until countries.length()) {
//                val countryObject = countries.getJSONObject(i)
//                val name = countryObject.getString("name")
//                val capital = countryObject.getString("capital")
//                val borders = countryObject.get("borders") as List<String>
    //TODO
//                val translations = countryObject.get("translations") as List<Translation>???//
//                val languages = countryObject.getString("languages") as List<Language>
//                items.add(ItemModel(name, capital, borders, translations, languages))
//            }
//            d("items", items.toString())
//        } catch (e:JSONException) {
//            //TODO
//        }
//    }
}