package com.example.uielementsp2.models

class AlbumSong  (var id: Int = 0 , var albumSong: String, var albumTitle: String  )  {
    override fun toString(): String {
        return "$albumSong"
    }
}