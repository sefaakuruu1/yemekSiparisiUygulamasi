package com.sefakuru.yemeksiparisiuygulamasi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sefakuru.yemeksiparisiuygulamasi.data.entity.Yemek
import com.sefakuru.yemeksiparisiuygulamasi.repo.YemekRepository

class AnaSayfaViewModel:ViewModel() {

    val yemekRepo = YemekRepository()
    var yemekListesi: MutableLiveData<List<Yemek>>

    init {
        yemekYukle()
        yemekListesi = yemekRepo.yemekGetir()
    }

    fun yemekYukle(){
        yemekRepo.yemekYukle()
    }

    fun ara(aramaKelimesi:String){
     yemekRepo.ara(aramaKelimesi)
    }


}