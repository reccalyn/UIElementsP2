package com.example.uielementsp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uielementsp2.databaseHandler.SongsDatabaseHandler
import com.example.uielementsp2.models.Song

class EditSongs : AppCompatActivity() {
    lateinit var title : EditText
    lateinit var artist : EditText
    lateinit var album : EditText
    lateinit var updateBtn: Button
    lateinit var song: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_songs)
        val song_id = intent.getIntExtra("song_id", 0)
        val databaseHandler = SongsDatabaseHandler (this)
        song = databaseHandler.readOne(song_id)



        title = findViewById(R.id.editTitleEditText)
        artist = findViewById( R.id.editArtistEditText)
        album = findViewById(R.id.editAlbumEditText)
        updateBtn = findViewById(R.id.updateBtn)



        title.setText(song.title)
        album.setText(song.album)
        artist.setText(song.artist)

        updateBtn.setOnClickListener {
            val title_string = title.text.toString()
            val artist_string = artist.text.toString()
            val album_string = album.text.toString()
            val updateSong = Song(id = song.id, title = title_string, artist = artist_string, album =  album_string)
            //save it to the database
            if (databaseHandler.update(updateSong)){
                Toast.makeText(this, "Song updated!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Oooops!", Toast.LENGTH_SHORT).show()
            }
            songsAdapter.notifyDataSetChanged()



        }


    }
}