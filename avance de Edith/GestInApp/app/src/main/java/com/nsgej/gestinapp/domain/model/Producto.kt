package com.nsgej.gestinapp.domain.model

import com.beust.klaxon.Klaxon
import com.nsgej.gestinapp.data.entities.ProductoEntity

private val klaxon = Klaxon()

class Producto(val id: String, val idTipoProducto: Int, val codigoBarra: String, val descripcion: String, val marca: String, val estado: Boolean) {

    override fun toString(): String {
        return "Producto(id='$id', idTipoProducto=$idTipoProducto, codigoBarra='$codigoBarra', descripcion='$descripcion', marca='$marca', estado=$estado)"
    }

    fun toJson() = klaxon.toJsonString(this)

    companion object {
        fun fromJson(json: String) = klaxon.parse<Producto>(json)
    }

}

fun ProductoEntity.toDomain() = Producto(id, idTipoProducto, codigoBarra, descripcion, marca, estado)