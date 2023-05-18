package com.example.bottomnavigation.yeexiaoze.general

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation.R
import com.example.bottomnavigation.yeexiaoze.database.selfDevelopment.ChapterSQLiteHelper
import com.example.bottomnavigation.yeexiaoze.database.selfDevelopment.SubchapterSQLiteHelper
import com.example.bottomnavigation.yeexiaoze.selfDevelopment.SelfDevelopmentSubchapterPage

class SelfDevChapterAdapter(private val context: Context, private var chapterList: ArrayList<String>) :
    RecyclerView.Adapter<SelfDevChapterAdapter.ChapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_of_chapter, parent, false)
        return ChapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        val currentItem = chapterList[position]
        holder.chapterTitle.text = currentItem

        val chapterHelper = ChapterSQLiteHelper(context)
        val subchapterHelper = SubchapterSQLiteHelper(context)

        holder.chapterTitle.setOnClickListener {
            //Get the record of all subchapters associated to a chapter
            val subchapterList = subchapterHelper.getRecords(subchapterHelper.conditionalGetAttribute("chapterID", "chapterID",
                chapterHelper.conditionalGetAttribute("chapterID", "title", currentItem)))

                //Intent of the subchapter page
                val intent = Intent(context, SelfDevelopmentSubchapterPage::class.java)
                intent.putExtra("subchapterList", subchapterList)
                context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return chapterList.size
    }

    /*
    fun setFilteredList(chapterList: ArrayList<String>)
    {
        this.chapterList = chapterList
        notifyDataSetChanged()
    }

     */

    inner class ChapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chapterTitle: Button = itemView.findViewById(R.id.button)
    }
}