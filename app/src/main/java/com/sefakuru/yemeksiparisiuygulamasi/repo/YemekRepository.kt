package com.sefakuru.yemeksiparisiuygulamasi.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sefakuru.yemeksiparisiuygulamasi.data.entity.CRUDCevap
import com.sefakuru.yemeksiparisiuygulamasi.data.entity.SepetYemek
import com.sefakuru.yemeksiparisiuygulamasi.data.entity.Yemek
import com.sefakuru.yemeksiparisiuygulamasi.data.entity.YemekCevap
import com.sefakuru.yemeksiparisiuygulamasi.retrofit.ApiUtils
import com.sefakuru.yemeksiparisiuygulamasi.retrofit.YemeklerDao
import org.json.JSONObject
import org.json.JSONTokener
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
        println("$kullanici_adi,$yemek_adi,$yemek_fiyat,$yemek_siparis_adet,$yemek_resim_adi")
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
     yemekDao.sepettenYemekSil(sepet_yemek_id,kullanici_adi).enqueue(object:Callback<CRUDCevap>{

         override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {
         }
         override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {

         }
     } )
    }


    fun yemekYukle() {
        yemekDao.getirYemekler().enqueue(object : Callback<YemekCevap> {
            override fun onResponse(call: Call<YemekCevap>, response: Response<YemekCevap>) {
                if (response.isSuccessful) {
                    yemekListesi.value = response.body()?.yemekler
                   // println(yemekListesi.value)
                }
            }
            override fun onFailure(call: Call<YemekCevap>, t: Throwable) {
            }
        })
    }




    fun sepetYemekYukle(kullanici_adi: String) {

        yemekDao.sepettekiYemekleriGetir(kullanici_adi).enqueue(object : Callback<CRUDCevap> {

            override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {

                if (response.isSuccessful) {
                    println(response.toString())
            //   yemekSepetListesi.value=response.body()
                }
            }
            override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {
            }
        })
    }
}