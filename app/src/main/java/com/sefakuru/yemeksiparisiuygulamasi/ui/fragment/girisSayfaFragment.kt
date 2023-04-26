package com.sefakuru.yemeksiparisiuygulamasi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.sefakuru.yemeksiparisiuygulamasi.R
import com.sefakuru.yemeksiparisiuygulamasi.databinding.FragmentGirisSayfaBinding
import com.sefakuru.yemeksiparisiuygulamasi.databinding.FragmentSepetBinding


class girisSayfaFragment : Fragment() {
  private lateinit var binding: FragmentGirisSayfaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_giris_sayfa, container, false)

        binding.buttonGiris.setOnClickListener {

            var kullaniciAdi=binding.kullaniciAdi.text.toString()
            var sifre=binding.sifre.text.toString()
            if(binding.sifre.length()> 8 ){
                Navigation.findNavController(it).navigate(R.id.action_girisSayfaFragment_to_anasayfaFragment)

                Snackbar.make(it,"${kullaniciAdi} Basariyla giris yaptiniz",Snackbar.LENGTH_LONG).show()

            }else
            {
                Snackbar.make(it,"${kullaniciAdi} sifreniz yanlıs",Snackbar.LENGTH_LONG).show()

            }

        }
        return binding.root


    }

}