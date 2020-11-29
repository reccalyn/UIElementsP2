package com.example.uielementsp2

import android.content.DialogInterface
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import java.util.*

class AlbumDetailsActivity : AppCompatActivity() {

    lateinit var adapter : ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)

        val uri = intent.getStringExtra("imageUri")

        val AlbumCover = findViewById<ImageView>(R.id.albumCover)

        val albumDetailsListView = findViewById<ListView>(R.id.albumDetailsListView)

        val title = findViewById<TextView>(R.id.albumTitle)
        title.setText(intent.getStringExtra("albumTitle").toString())

        var imageResource = getResources().getIdentifier(uri, null, getPackageName()) //Gets the resource using the URI
        var res = getResources().getDrawable(imageResource) //Converting the image resource into a drawable file
        AlbumCover.setImageDrawable(res) //Attach/set the drawable to the Image view

        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, albumSongs)
        albumDetailsListView.adapter = adapter

        registerForContextMenu(albumDetailsListView)


    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_remove, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val menuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo //Allows us to inherit from the class Adapterview.AdapterCOntextMenuInfo to get the position
        return when (item.itemId) {
            R.id.removeSong -> {
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setMessage("Do you want to remove this song from the Album?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", DialogInterface.OnClickListener {
                            dialog, which ->
                        val song = albumSongs[menuInfo.position]
                        albumSongs.removeAt(menuInfo.position) //gets the position and remove
                        adapter.notifyDataSetChanged() //Notify the adapter
                    }).setNegativeButton( "No", DialogInterface.OnClickListener {
                            dialog, which ->
                        dialog.cancel()
                    })
                val alert = dialogBuilder.create()
                alert.setTitle("Remove Song")
                alert.show()


                true
            }
            else -> {
                return super.onContextItemSelected(item)
            }

        }

    }

}