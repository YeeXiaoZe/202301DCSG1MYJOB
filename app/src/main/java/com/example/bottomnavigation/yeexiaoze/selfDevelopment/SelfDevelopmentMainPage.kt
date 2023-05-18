package com.example.bottomnavigation.yeexiaoze.selfDevelopment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomnavigation.R
import com.example.bottomnavigation.yeexiaoze.general.Validation
import com.example.bottomnavigation.yeexiaoze.database.selfDevelopment.ChapterSQLiteHelper
import com.example.bottomnavigation.databinding.SelfDevelopmentMainPageBinding
import com.example.bottomnavigation.yeexiaoze.general.SelfDevChapterAdapter
import java.util.*
import kotlin.collections.ArrayList

class SelfDevelopmentMainPage : Fragment(R.layout.self_development_main_page) {
    private var _binding: SelfDevelopmentMainPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var validation: Validation
    private lateinit var chapterAdapter: SelfDevChapterAdapter
    private lateinit var chapterList: ArrayList<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("SelfDevelopmentMainPage", "${ChapterSQLiteHelper(requireContext()).getAttribute("title")}")

        _binding = SelfDevelopmentMainPageBinding.bind(view)
        validation = Validation()

        chapterList = ChapterSQLiteHelper(requireContext()).getAttribute("title")
        chapterAdapter = SelfDevChapterAdapter(requireContext(), chapterList)

        recyclerViewInit(chapterAdapter)
    }

    private fun recyclerViewInit(chapterAdapter: SelfDevChapterAdapter) {
        binding.selfDevChapter.layoutManager = LinearLayoutManager(requireContext())
        binding.selfDevChapter.adapter = chapterAdapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}