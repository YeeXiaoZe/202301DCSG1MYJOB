package com.example.bottomnavigation.harrychiong

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation.R

class companyRecyclerAdapter(var mList:List<companyDetailData>): RecyclerView.Adapter<companyRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var companyImage: ImageView
        var companyTitle:TextView
        var companyDetail1:TextView
        var companyDetail2:TextView
        var applybutton: Button

        init {
            companyImage = itemView.findViewById(R.id.company_logo)
            companyTitle = itemView.findViewById(R.id.company_title)
            companyDetail1 = itemView.findViewById(R.id.company_detail1)
            companyDetail2 = itemView.findViewById(R.id.company_detail2)
            applybutton = itemView.findViewById(R.id.applyButton)
        }

    }


    fun setFilteredList(mList: List<companyDetailData>){
        this.mList = mList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): companyRecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.company_layout, parent, false)
        return  ViewHolder(v)
    }

    //will populate our dataview
    override fun onBindViewHolder(holder: companyRecyclerAdapter.ViewHolder, position: Int) {
        holder.companyTitle.text = mList[position].companyTitle
        holder.companyDetail1.text = mList[position].jobDetail1
        holder.companyDetail2.text = mList[position].jobDetail2
        holder.companyImage.setImageResource(mList[position].companyImage)
        //After press this button will pop up a dialog let user to upload file which mean resume and submit it to apply job
        holder.applybutton.setOnClickListener {
            val popUpFragment = PopUpFragment()
            popUpFragment.show((holder.itemView.context as AppCompatActivity).supportFragmentManager, "showPopUp")
        }
    }
    //return number of company size that here have
    override fun getItemCount(): Int {
        return mList.size
    }
}