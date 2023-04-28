package com.sefakuru.yemeksiparisiuygulamasi.data.entity

data class CardResponse(var success:Int,
                        var sepet_yemekler:List<SepetYemek>):java.io.Serializable{
}