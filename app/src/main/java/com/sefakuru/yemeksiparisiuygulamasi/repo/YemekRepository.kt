package com.sefakuru.yemeksiparisiuygulamasi.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sefakuru.yemeksiparisiuygulamasi.data.entity.*
import com.sefakuru.yemeksiparisiuygulamasi.retrofit.ApiUtils
import com.sefakuru.yemeksiparisiuygulamasi.retrofit.YemeklerDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemekRepository {
    var yemekDao: YemeklerDao
    var yemekListesi: MutableLiveData<List<Yemek>>
    var yemekSepetListesi: MutableLiveData<List<SepetYemek>>

    init {
        yemekDao = ApiUtils.getYemeklerDao()
        yemekListesi = MutableLiveData()
        yemekSepetListesi = MutableLiveData()// bu kısım dogru mu
    }

    fun yemekGetir(): MutableLiveData<List<Yemek>> {
        return yemekListesi
    }

    fun sepetYemekGetir(): MutableLiveData<List<SepetYemek>> {
        println(yemekSepetListesi)
        return yemekSepetListesi
    }

    fun ara(aramaKelimesi: String) {
        Log.e("Kişi Ara", aramaKelimesi)
    }

    fun sepeteEkle(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) {

        var eklendiMi = false

        yemekDao.sepettekiYemekleriGetir(kullanici_adi).enqueue(object : Callback<CardResponse> {
            override fun onResponse(call: Call<CardResponse>, response: Response<CardResponse>) {
                if (response.isSuccessful) {
                    val mevcutListe = response.body()!!.sepet_yemekler

                    if (!mevcutListe.isEmpty()) {
                        for (sepetYemek in mevcutListe) {
                            if (sepetYemek.yemek_adi == yemek_adi) {
                                sil(sepetYemek.sepet_yemek_id, kullanici_adi)
                                val eskiAdet = sepetYemek.yemek_siparis_adet
                                val toplamAdet = eskiAdet + yemek_siparis_adet
                                eklendiMi = true
                                yemekEkleFun(
                                    yemek_adi,
                                    yemek_resim_adi,
                                    yemek_fiyat,
                                    toplamAdet,
                                    kullanici_adi
                                )
                            }
                        }

                        if (!eklendiMi){
                            yemekEkleFun(
                                yemek_adi,
                                yemek_resim_adi,
                                yemek_fiyat,
                                yemek_siparis_adet,
                                kullanici_adi
                            )
                        }
                    }
                }
            }

            override fun onFailure(call: Call<CardResponse>, t: Throwable) {
                eklendiMi = true
                yemekEkleFun(
                    yemek_adi,
                    yemek_resim_adi,
                    yemek_fiyat,
                    yemek_siparis_adet,
                    kullanici_adi
                )
            }
        })



    }

    fun yemekEkleFun(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) {
        yemekDao.yemekEkle(
            yemek_adi,
            yemek_resim_adi,
            yemek_fiyat,
            yemek_siparis_adet,
            kullanici_adi
        ).enqueue(object : Callback<CRUDCevap> {
            override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {
            }

            override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {
            }
        })
    }

    fun sil(sepet_yemek_id: Int, kullanici_adi: String) {
        yemekDao.sepettenYemekSil(sepet_yemek_id, kullanici_adi)
            .enqueue(object : Callback<CRUDCevap> {

                override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {
                    sepetYemekYukle(kullanici_adi)
                }

                override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {

                }
            })
    }


    fun yemekYukle() {
        yemekDao.getirYemekler().enqueue(object : Callback<YemekCevap> {
            override fun onResponse(call: Call<YemekCevap>, response: Response<YemekCevap>) {
                if (response.isSuccessful) {
                    yemekListesi.value = response.body()?.yemekler
                }
            }

            override fun onFailure(call: Call<YemekCevap>, t: Throwable) {
            }
        })
    }


    fun sepetYemekYukle(kullanici_adi: String) {
        yemekDao.sepettekiYemekleriGetir(kullanici_adi).enqueue(object : Callback<CardResponse> {
            override fun onResponse(call: Call<CardResponse>, response: Response<CardResponse>) {
                if (response.isSuccessful) {
                    // var nameListe=ArrayList<String>()
                    yemekSepetListesi.value = response.body()?.sepet_yemekler
                }
            }

            override fun onFailure(call: Call<CardResponse>, t: Throwable) {
            }
        })
    }

}