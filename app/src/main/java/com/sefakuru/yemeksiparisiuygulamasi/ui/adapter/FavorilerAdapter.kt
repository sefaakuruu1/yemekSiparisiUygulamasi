package com.sefakuru.yemeksiparisiuygulamasi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sefakuru.yemeksiparisiuygulamasi.R
import com.sefakuru.yemeksiparisiuygulamasi.data.entity.Yemek
import com.sefakuru.yemeksiparisiuygulamasi.databinding.CardTasarimFavoriBinding

class FavorilerAdapter(var mContext: Context, var FavorilerListesi:List<Yemek>):
    RecyclerView.Adapter<FavorilerAdapter.SatirTasarimTutucu>() {

    inner class SatirTasarimTutucu(var binding: CardTasarimFavoriBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatirTasarimTutucu {
        val binding: CardTasarimFavoriBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.card_tasarim_favori,parent,false)
        return SatirTasarimTutucu(binding)
    }

    override fun getItemCount(): Int {
        return FavorilerListesi.size
    }

    override fun onBindViewHolder(holder: SatirTasarimTutucu, position: Int) {
        val favoriYemek=FavorilerListesi.get(position)
        val t=holder.binding

        t.tvCardFavAd.text=favoriYemek.yemek_adi

        t.ivCardFav.setImageResource(mContext.resources.getIdentifier(favoriYemek.yemek_resim_adi,"drawable",mContext.packageName))

        t.tvCardFavFiyat.text="${favoriYemek.yemek_fiyat}"

    }


}