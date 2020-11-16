package com.example.uielementsp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView

class AlbumsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)

        val GridView = findViewById<GridView>(R.id.gridView)

        GridView.adapter = ImageAdapter(applicationContext)

        GridView.onItemClickListener = AdapterView.OnItemClickListener{ parent, v, position, id ->
            val intent = Intent(this, AlbumDetailsActivity::class.java)
            var songsToBeDisplayed = arrayListOf<String>()
            var uri: String = ""
            if (position == 0) {
                uri = "@drawable/ariana"
                songsToBeDisplayed.clear()
                songsToBeDisplayed.addAll(resources.getStringArray(R.array.arianag))
            } else if (position == 1) {
                uri = "@drawable/grande"
                songsToBeDisplayed.clear()
                songsToBeDisplayed.addAll(resources.getStringArray(R.array.grandeg))
            } else {
                uri = "@drawable/moira"
                songsToBeDisplayed.clear()
                songsToBeDisplayed.addAll(resources.getStringArray(R.array.moirag))
            }
            intent.putStringArrayListExtra("songs", songsToBeDisplayed )
            intent.putExtra("imageUri",  uri)
            intent.putExtra("position", position)
            startActivity(intent)
        }
    }
}