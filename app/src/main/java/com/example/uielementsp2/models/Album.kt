package com.example.uielementsp2.models

class Album (var id: Int = 0 , var albumTitle: String , var releaseDate : String ) {
    override fun toString(): String {
        return "$albumTitle"
    }
}