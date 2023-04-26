package com.sefakuru.yemeksiparisiuygulamasi.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import com.sefakuru.yemeksiparisiuygulamasi.R
import com.sefakuru.yemeksiparisiuygulamasi.data.entity.Yemek
import com.sefakuru.yemeksiparisiuygulamasi.databinding.FragmentAnasayfaBinding
import com.sefakuru.yemeksiparisiuygulamasi.ui.adapter.YemeklerAdapter
import com.sefakuru.yemeksiparisiuygulamasi.ui.viewmodel.AnaSayfaViewModel


class AnasayfaFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding:FragmentAnasayfaBinding
    private lateinit var viewModel: AnaSayfaViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa, container, false)
        binding.anasayfaFragment=this
        binding.anasayfaToolbarBaslik="Yemekler"

        val yemekListesi=ArrayList<Yemek>()

       /* val y1=Yemek(1,"Ayran","ayran",3)
        val y2=Yemek(2,"Baklava","baklava",12)
        val y3=Yemek(3,"Fanta","fanta",6)
        val y4=Yemek(4,"Kadayıf","kadayif",15)
        val y5=Yemek(5,"Kofte","kofte",27)
        val y6=Yemek(6,"Makarna","makarna",10)
        yemekListesi.add(y1)
        yemekListesi.add(y2)
        yemekListesi.add(y3)
        yemekListesi.add(y4)
        yemekListesi.add(y5)
        yemekListesi.add(y6)*/



        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarAnasayfa)

        observeLiveData()

        requireActivity().addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
              menuInflater.inflate(R.menu.toolbar_menu,menu)

                val item=menu.findItem(R.id.action_ara)
                val searchView=item.actionView as SearchView
                searchView.setOnQueryTextListener(this@AnasayfaFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
             return false
            }


        },viewLifecycleOwner,Lifecycle.State.RESUMED)

        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:AnaSayfaViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun observeLiveData(){
        viewModel.yemekListesi.observe(viewLifecycleOwner, Observer {
            val adapter=YemeklerAdapter(requireContext(),it)
            binding.yemeklerAdapter=adapter
        })
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        ara(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        ara(newText)
        return true
    }
    fun ara(aramaKelimesi:String){
       viewModel.ara(aramaKelimesi)
    }

  /*  override fun onResume() {
        super.onResume()
        viewModel.yemekYukle()
    }*/
}
