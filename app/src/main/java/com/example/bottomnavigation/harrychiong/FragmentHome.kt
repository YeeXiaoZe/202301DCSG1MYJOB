package com.example.bottomnavigation.harrychiong

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation.R
import java.util.Locale


class FragmentHome : Fragment(R.layout.activity2_main) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: companyRecyclerAdapter
    private lateinit var searchView: SearchView
    private var mList = ArrayList<companyDetailData>()


    // Do any other initialization or manipulation of the ImageView and textView here
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("Main Activity", "Testing")
        //this is our app logo
        val joblogo = view.findViewById<ImageView>(R.id.joblogo)

        //search bar show in output
        searchView = view.findViewById(R.id.search_view)
        recyclerView = view.findViewById(R.id.recyclerView)

        recyclerView.setHasFixedSize(true)
        //The recycler view show every company job detail
        recyclerView.layoutManager = LinearLayoutManager(context)

        //This add every company's job detail to recycler view
        addDataToList()
        adapter = companyRecyclerAdapter(mList)
        recyclerView.adapter = adapter



        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }
    //for compare search bar that you write, if you write mcd and you will found mcd in the recycler view otherwise will data not found
    private fun filterList(query: String?){
        if(query != null){
            val filteredList = ArrayList<companyDetailData>()
            for(i in mList){
                //This function will lowercase the company title to compare the search bar that you search.
                if(i.companyTitle.toLowerCase(Locale.ROOT).contains(query)){
                    filteredList.add(i)
                }
            }
            //If cant found that you search in search bar will show no data found
            if(filteredList.isEmpty()){
                Toast.makeText(context,"No Data found", Toast.LENGTH_SHORT).show()
            }else{
                adapter.setFilteredList(filteredList)
            }
        }
    }

    //This purpose are add every company's job detail to recycler view
    private fun addDataToList() {
        mList.add(companyDetailData("Mcdonald","Crew Restaurant (McDonald’s Kota Kinabalu)","Monthly Salary MYR 1,100 - MYR 1,500", R.drawable.mcd_logo))
        mList.add(companyDetailData("EverKicthen","Admin Assistant(KL's Petaling Jaya)","Monthly Salary MYR 2,000 - MYR 2,800", R.drawable.eklogo))
        mList.add(companyDetailData("Marry Brown","Kitchen Crew (Kangar)","Monthly Salary MYR 1,500 - MYR 1,600", R.drawable.marry_brown_logo))
        mList.add(companyDetailData("Aeon","Mall Leasing & Marketing Manager(Cheras)","Monthly Salary MYR 5,000 - MYR 7,000", R.drawable.aeonlogo))
        mList.add(companyDetailData("Popular Book","PR & Events Executive","Monthly Salary MYR 3,000 - MYR 4,200", R.drawable.popular_logo))
        mList.add(companyDetailData("BURGER KING","Supply Chain Executive","Monthly Salary MYR 2,000 - MYR 4,000", R.drawable.burgerkinglogo))
        mList.add(companyDetailData("Texas Chicken","Crew Restaurant","Monthly Salary MYR 1,500 - MYR 1,600", R.drawable.texaschickenlogo))
        mList.add(companyDetailData("KFC","KFC Restaurant Crew","Monthly Salary MYR 1,500 - MYR 1,600", R.drawable.kfclogo))
    }
}