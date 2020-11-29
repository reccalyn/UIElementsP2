package com.example.uielementsp2

import com.example.uielementsp2.databaseHandler.SongsDatabaseHandler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uielementsp2.models.Song

class AddSongs : AppCompatActivity() {
    lateinit var title: EditText
    lateinit var author: EditText
    lateinit var album: EditText
    lateinit var addBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_songs)

        title = findViewById(R.id.titleEditText)
        author = findViewById(R.id.authorEditText)
        album = findViewById(R.id.albumEditText)
        addBtn = findViewById(R.id.addSongBtn)
        val databaseHandler = SongsDatabaseHandler(this)


        addBtn.setOnClickListener {
            val title_string = title.text.toString()
            val artist_string = author.text.toString()
            val album_string = album.text.toString()
            val song = Song(title = title_string, artist = artist_string, album =  album_string)
            if (databaseHandler.create(song)){
                Toast.makeText(this, "Song added.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Oooops!", Toast.LENGTH_SHORT).show()
            }
            songsAdapter.notifyDataSetChanged()
            clear()
        }

    }
    fun clear() {
        title.text.clear()
        author.text.clear()
        album.text.clear()
    }

}