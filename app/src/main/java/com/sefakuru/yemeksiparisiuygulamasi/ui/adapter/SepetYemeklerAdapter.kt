package com.sefakuru.yemeksiparisiuygulamasi.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.sefakuru.yemeksiparisiuygulamasi.R
import com.sefakuru.yemeksiparisiuygulamasi.data.entity.SepetYemek
import com.sefakuru.yemeksiparisiuygulamasi.databinding.CardTasarimSepetBinding
import com.sefakuru.yemeksiparisiuygulamasi.ui.viewmodel.SepetViewModel


class SepetYemeklerAdapter(
    var mContext: Context,
    var sepetYemekListesi: List<SepetYemek>,
    var viewModel: SepetViewModel
) :
    RecyclerView.Adapter<SepetYemeklerAdapter.SatirTasarimTutucu>() {

    inner class SatirTasarimTutucu(var binding: CardTasarimSepetBinding) :
        RecyclerView.ViewHolder(binding.root)

    val RESIM_URL = "http://kasimadalan.pe.hu/yemekler/resimler/"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatirTasarimTutucu {
        val binding: CardTasarimSepetBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.card_tasarim_sepet,
            parent,
            false
        )
        return SatirTasarimTutucu(binding)
    }

    override fun getItemCount(): Int {
        return sepetYemekListesi.size
    }

    override fun onBindViewHolder(holder: SatirTasarimTutucu, position: Int) {
        val sepetYemek = sepetYemekListesi.get(position)
        val t = holder.binding
        t.sepetYemekNesnesi = sepetYemek
        t.ivSepetResim.setImageResource(
            mContext.resources.getIdentifier(
                sepetYemek.yemek_resim_adi,
                "drawable",
                mContext.packageName
            )
        )
        Glide.with(holder.itemView).load(RESIM_URL + sepetYemek.yemek_resim_adi)
            .into(t.ivSepetResim)
        t.ivSepetSil.setOnClickListener {
            Snackbar.make(it, "${sepetYemek.yemek_adi} silinsin mi?", Snackbar.LENGTH_LONG)
                .setAction("EVET") {
                    sil(sepetYemek.sepet_yemek_id, sepetYemek.kullanici_adi)
                    sonUrunKontrol()
                    notifyDataSetChanged()
                }.show()
        }

    }

    fun sil(sepet_yemek_id: Int, kullanici_adi: String) {
        viewModel.sil(sepet_yemek_id, kullanici_adi)
    }

   fun sonUrunKontrol(){
        if (sepetYemekListesi.size == 1) {
            sepetYemekListesi = emptyList()
        }
   }

}