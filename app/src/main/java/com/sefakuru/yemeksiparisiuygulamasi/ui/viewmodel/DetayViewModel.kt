package com.sefakuru.yemeksiparisiuygulamasi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sefakuru.yemeksiparisiuygulamasi.repo.YemekRepository

class DetayViewModel:ViewModel() {
val yemekRepo=YemekRepository()

    fun sepeteEkle(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi:String

    ) {
        yemekRepo.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
    }


}