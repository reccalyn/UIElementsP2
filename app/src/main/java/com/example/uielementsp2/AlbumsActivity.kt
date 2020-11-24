package com.example.uielementsp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView

var albumSongs = ArrayList<String>()
var albumURI = String

class AlbumsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)

        val GridView = findViewById<GridView>(R.id.gridView) as GridView
        GridView.adapter = ImageAdapter(applicationContext)
        GridView.onItemClickListener = AdapterView.OnItemClickListener{parent, v, position, id ->
            val intent = Intent(this, AlbumDetailsActivity::class.java)
            var uri: String
            if (position == 0) {
                uri = "@drawable/ariana"
                albumSongs.clear()
                albumSongs.addAll(resources.getStringArray(R.array.arianag))
            } else if (position == 1) {
                uri = "@drawable/grande"
                albumSongs.clear()
                albumSongs.addAll(resources.getStringArray(R.array.grandeg))
            } else {
                uri = "@drawable/moira"
                albumSongs.clear()
                albumSongs.addAll(resources.getStringArray(R.array.moirag))
            }
            intent.putExtra("imageUri",  uri)
            startActivity(intent)


        }



    }




}