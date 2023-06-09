package com.sefakuru.yemeksiparisiuygulamasi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.sefakuru.yemeksiparisiuygulamasi.R
import com.sefakuru.yemeksiparisiuygulamasi.Util
import com.sefakuru.yemeksiparisiuygulamasi.data.entity.Yemek
import com.sefakuru.yemeksiparisiuygulamasi.databinding.FragmentDetayBinding
import com.sefakuru.yemeksiparisiuygulamasi.ui.viewmodel.DetayViewModel
import java.util.Objects


class DetayFragment : Fragment() {
    val RESIM_URL = "http://kasimadalan.pe.hu/yemekler/resimler/"
    private lateinit var viewModel: DetayViewModel
    private lateinit var binding: FragmentDetayBinding
    private lateinit var gelenYemek: Yemek

    var tiklanmaSayi = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detay, container, false)
        binding.detayFragment = this

        Util.bottomNavigationGone(requireActivity())

        binding.detayToolbarBaslik = "Yemek Detay"


        /*  var tiklanmaSayi = 0
           binding.buttonArttir.setOnClickListener {
                   tiklanmaSayi += 1
                   binding.textViewMiktar.text = tiklanmaSayi.toString()
               }

         binding.buttonAzalt.setOnClickListener {
               if (tiklanmaSayi >= 1) {
                   tiklanmaSayi -= 1
                   binding.textViewMiktar.text = tiklanmaSayi.toString()
               }
           }*/

        val bundle: DetayFragmentArgs by navArgs()
        gelenYemek = bundle.yemekDetaya



        binding.tiklanma = 1  // "1"
        binding.detayToolbarBaslik = gelenYemek.yemek_adi
        Glide.with(this).load(RESIM_URL + gelenYemek.yemek_resim_adi).into(binding.imageViewDetay)
        binding.imageViewDetay.setImageResource(
            resources.getIdentifier(
                gelenYemek.yemek_resim_adi,
                "drawable",
                requireContext().packageName
            )
        )
        binding.yemekNesnesi = gelenYemek
        //  binding.textViewFiyatDetay.text = gelenYemek.yemek_fiyat.toString()
        /*  binding.buttonSepeteEkle.setOnClickListener {

              /* val gecis=DetayFragmentDirections.sepetGecis(sepetteYemek = sepetYemek)
                Navigation.findNavController(it).navigate(gecis)

                */
              val yemek_adi = gelenYemek.yemek_adi
              val yemek_resim_adi = gelenYemek.yemek_resim_adi
              val yemek_fiyat = gelenYemek.yemek_fiyat
              val yemek_siparis_adet = tiklanmaSayi
              val kullanici_adi = "sefa"
              sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
              Snackbar.make(it, "${yemek_adi} sepete eklendi", Snackbar.LENGTH_LONG).show()
          }*/

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetayViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun sepeteEkle(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
    ) {
        viewModel.sepeteEkle(
            yemek_adi,
            yemek_resim_adi,
            yemek_fiyat,
            yemek_siparis_adet,
            "sefakuru"
        )
        Snackbar.make(binding.root, "Ürün sepete eklendi",Snackbar.LENGTH_SHORT).show()

        findNavController().navigateUp()
    }



    fun arttir() {
        tiklanmaSayi += 1
        binding.tiklanma = tiklanmaSayi   //.toString()
    }

    fun azalt() {
        if (tiklanmaSayi > 1) {
            tiklanmaSayi -= 1
            binding.tiklanma = tiklanmaSayi  //.toString()
        }else{
            tiklanmaSayi = 1
        }
    }

    fun geriButton(){
        findNavController().navigateUp()
    }

}
