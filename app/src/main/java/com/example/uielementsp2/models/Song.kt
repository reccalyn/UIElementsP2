package com.example.uielementsp2.models

class Song (var id: Int = 0 , var title: String , var artist : String , var album : String) {
    override fun toString(): String {
        return "TITLE: $title \r ARTIST: $artist \r ALBUM: $album"
    }
}