package com.example.m7_geeco_in.models


/*    INGRESSOS    */
data class Ingressos(
    val title:String,
    val description:String,
    val amount:Int,
    val date:String,
    val id:Int
)

data class IngresRequest(
    val title: String,
    val description: String,
    val amount: Int,
    val date: String
)

/*    DESPESSES    */
data class Despesses(
    val title:String,
    val description:String,
    val amount:Int,
    val date:String,
    val id:Int
)
data class DespesaRequest(
    val title: String,
    val description: String,
    val amount: Int,
    val date: String
)