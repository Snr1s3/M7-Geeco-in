package com.example.m7_geeco_in.ingressos

data class Ingres(val income_id:Int, val idUsuari:Int, val noms:String, val imports:Int)

class IngressosProvider{
    companion object {
        val Ingressos:List<Ingres> = listOf(
            Ingres(1, 1, "Alba", 1000),
            Ingres(2, 2, "Dani", 2000),
            Ingres(3, 3, "Karolayn", 3000),
        )
    }
}