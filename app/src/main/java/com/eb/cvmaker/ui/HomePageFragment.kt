package com.eb.cvmaker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eb.cvmaker.databinding.FragmentHomePageBinding
import com.eb.cvmaker.replace
import com.eb.cvmaker.ui._Create.InformationsFragment
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomePageBinding.inflate(inflater, container, false)

        // Room'a templateleri kaydet



        binding.btnCreate.setOnClickListener {
            replace(InformationsFragment())
        }

 // Kaydedilen CV absolutepath'leri room'a kaydet. Roomdan verileri çek. Adaptera ekle.


        return binding.root
    }



}