package com.sefakuru.yemeksiparisiuygulamasi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sefakuru.yemeksiparisiuygulamasi.data.entity.SepetYemek
import com.sefakuru.yemeksiparisiuygulamasi.repo.YemekRepository
import com.sefakuru.yemeksiparisiuygulamasi.retrofit.ApiUtils

class SepetViewModel:ViewModel() {
    val yemekRepo=YemekRepository()
    var yemekSepetListesi: MutableLiveData<List<SepetYemek>>

    init {
        sepetYemekYukle()
        yemekSepetListesi = yemekRepo.sepetYemekGetir()// bu kısım dogru mu
    }

    fun sil(sepet_yemek_id:Int,kullanici_adi:String){
       yemekRepo.sil(sepet_yemek_id,kullanici_adi)
    }

    fun  sepetYemekYukle(){
        yemekRepo.sepetYemekYukle("sefakuru")
    }
}