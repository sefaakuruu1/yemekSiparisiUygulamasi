package com.sefakuru.yemeksiparisiuygulamasi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.sefakuru.yemeksiparisiuygulamasi.R
import com.sefakuru.yemeksiparisiuygulamasi.data.entity.SepetYemek
import com.sefakuru.yemeksiparisiuygulamasi.databinding.FragmentSepetBinding
import com.sefakuru.yemeksiparisiuygulamasi.ui.adapter.SepetYemeklerAdapter
import com.sefakuru.yemeksiparisiuygulamasi.ui.viewmodel.SepetViewModel


class SepetFragment : Fragment() {
private lateinit var binding: FragmentSepetBinding
private lateinit var viewModel: SepetViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sepet, container, false)
        binding.sepetFragment=this
        binding.sepetToolbarBaslik = "Sepetiniz"

       viewModel.yemekSepetListesi.observe(viewLifecycleOwner){
           val adapter = SepetYemeklerAdapter(requireContext(),it,viewModel)
           binding.sepetYemeklerAdapter=adapter

       }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:SepetViewModel by viewModels()
        viewModel=tempViewModel
    }

    override fun onResume() {
        super.onResume()
    viewModel.sepetYemekYukle()
    }

}