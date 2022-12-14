package com.nsgej.gestinapp.domain.model

import com.beust.klaxon.Klaxon
import com.nsgej.gestinapp.data.entities.TipoInventarioEntity

private val klaxon = Klaxon()

class TipoInventario(val id: Int = 0, val nombre: String, val estado: Boolean = true) {

    override fun toString(): String {
        return nombre
    }


    fun toJson() = klaxon.toJsonString(this)

    companion object {
        fun fromJson(json: String) = klaxon.parse<TipoInventario>(json)
    }
}

fun TipoInventarioEntity.toDomain() = TipoInventario(id, nombre, estado)
