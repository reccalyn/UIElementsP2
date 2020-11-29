package com.example.uielementsp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import com.example.uielementsp2.databaseHandler.SongsDatabaseHandler
import com.example.uielementsp2.models.Album
import com.example.uielementsp2.models.Song

class EditAlbum : AppCompatActivity() {

    lateinit var editAlbum: EditText
    lateinit var updateAlbumbtn: Button
    lateinit var date: String
    lateinit var songsDatabaseHandler: SongsDatabaseHandler
    lateinit var album : Album
    lateinit var datePicker: DatePicker
    var releaseDate: String = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_album)

        val album_id = intent.getIntExtra("album_id", 0)
        songsDatabaseHandler = SongsDatabaseHandler(this)
        album = songsDatabaseHandler.readOneAlbum(album_id)

        editAlbum = findViewById(R.id.editAlbumET)
        updateAlbumbtn = findViewById(R.id.updateAlbumBtn)

        editAlbum.setText(album.albumTitle)

        //Date picker
        datePicker = findViewById<DatePicker>(R.id.datePickerEdit) as DatePicker
        datePicker.init(2020, 11, 1, object: DatePicker.OnDateChangedListener{
            override fun onDateChanged(
                view: DatePicker?,
                year: Int,
                monthOfYear: Int,
                dayOfMonth: Int
            ) {
                releaseDate = "${datePicker.month + 1}/${datePicker.dayOfMonth}/${datePicker.year}"
            }
        })


        updateAlbumbtn.setOnClickListener{

            val title_string = editAlbum.text.toString()
            val releaseDate_string = releaseDate
            val updateAlbum = Album(id = album.id, albumTitle = title_string, releaseDate = releaseDate_string)
            //save it to the database
            if (songsDatabaseHandler.updateAlbum(updateAlbum)){
                Toast.makeText(this, "Album updated!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Oooops!", Toast.LENGTH_SHORT).show()
            }
            albumAdapter.notifyDataSetChanged()





        }








    }
}