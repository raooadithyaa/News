package com.example.news

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HomeFragment : Fragment(), itemclicked {
    private lateinit var recyclerView: RecyclerView;
    private lateinit var adapter: MyAdapter;
    private lateinit var dataCreation: DataCreation
    private var fragment: Fragment=WebviewFragment()
    private lateinit var args: Bundle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = MyAdapter(this)
        recyclerView.adapter = adapter
        dataCreation = DataCreation("12b04bcc7a9b4e3d9b2286ff1994e619", ApiProvider.provideNewsApi())

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val prevDate:String = LocalDateTime.now().minusDays(1).format(formatter)
        val current:String = LocalDateTime.now().format(formatter)
        lifecycleScope.launch {
            val list = dataCreation.getData(prevDate, current)
            withContext(Dispatchers.Main) {
                if (list != null) {
                    adapter.updateNews(list)
                }
            }
        }
    }

    override fun onitemclicked(item: FinalData) {
        args = Bundle().apply {
            putString("url", item.url)
        }
        fragment.arguments = args
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, fragment)
            ?.commit()
    }
}


