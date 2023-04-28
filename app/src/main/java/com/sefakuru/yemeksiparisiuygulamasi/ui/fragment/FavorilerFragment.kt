package com.sefakuru.yemeksiparisiuygulamasi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sefakuru.yemeksiparisiuygulamasi.R
import com.sefakuru.yemeksiparisiuygulamasi.Util
import com.sefakuru.yemeksiparisiuygulamasi.data.entity.Yemek
import com.sefakuru.yemeksiparisiuygulamasi.databinding.FragmentFavorilerBinding
import com.sefakuru.yemeksiparisiuygulamasi.ui.adapter.FavorilerAdapter

class FavorilerFragment : Fragment() {
private lateinit var binding:FragmentFavorilerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_favoriler, container, false)
        binding.toolbarFavoriler.title="Favoriler"

        Util.bottomNavigationVisible(requireActivity())

        binding.rvFavori.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        val favorilerListe=ArrayList<Yemek>()

        val fy1 = Yemek(1,"Ayran","ayran",3)  //kullanici adi sil
        val fy2 = Yemek(2, "Fanta", "fanta",5)
        favorilerListe.add(fy1)
        favorilerListe.add(fy2)
        val adapter=FavorilerAdapter(requireContext(),favorilerListe)
        binding.rvFavori.adapter=adapter
        return binding.root
    }

}