package com.sefakuru.yemeksiparisiuygulamasi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sefakuru.yemeksiparisiuygulamasi.R
import com.sefakuru.yemeksiparisiuygulamasi.data.entity.Yemek
import com.sefakuru.yemeksiparisiuygulamasi.databinding.CardTasarimYemekBinding
import com.sefakuru.yemeksiparisiuygulamasi.ui.fragment.AnasayfaFragmentDirections


class YemeklerAdapter(var mContext:Context,var yemekListesi:List<Yemek>) :RecyclerView.Adapter<YemeklerAdapter.HolderClass>(){

    val RESIM_URL = "http://kasimadalan.pe.hu/yemekler/resimler/"

    inner class HolderClass(var binding:CardTasarimYemekBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderClass {
      val binding:CardTasarimYemekBinding=DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.card_tasarim_yemek,parent,false)
        return HolderClass(binding)
    }

    override fun getItemCount(): Int {

        return yemekListesi.size
    }

    override fun onBindViewHolder(holder: HolderClass, position: Int) {
        val yemek=yemekListesi.get(position)
        val t=holder.binding
        t.imageViewYemekCv.setImageResource(mContext.resources.getIdentifier(yemek.yemek_resim_adi,"drawable",mContext.packageName))
       t.yemekNesnesi=yemek
        Glide.with(holder.itemView).load(RESIM_URL+yemek.yemek_resim_adi).into(t.imageViewYemekCv)

        t.cardViewYemek.setOnClickListener {
        val gecis= AnasayfaFragmentDirections.detayGecis(yemekDetaya = yemek)
            Navigation.findNavController(it).navigate(gecis)
        }
    }
}