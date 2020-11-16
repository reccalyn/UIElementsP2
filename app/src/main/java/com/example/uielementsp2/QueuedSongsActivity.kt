package com.example.uielementsp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class QueuedSongsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queued_songs)

        val songsToBeDisplayedList = intent.getStringArrayListExtra("songs")
        val songsToBeDisplayedArray = songsToBeDisplayedList!!.toTypedArray()
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsToBeDisplayedArray)
        val queuedSongsListView = findViewById<ListView>(R.id.queuedSongs)
        queuedSongsListView.adapter = adapter
    }
}