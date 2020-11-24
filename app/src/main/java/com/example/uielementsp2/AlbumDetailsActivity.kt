package com.example.uielementsp2

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView

class AlbumDetailsActivity : AppCompatActivity() {
    lateinit var adapter : ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)

        val uri = intent.getStringExtra("imageUri")

        val AlbumCover = findViewById<ImageView>(R.id.albumCover)

        val albumDetailsListView = findViewById<ListView>(R.id.albumDetailsListView)

        var imageResource = getResources().getIdentifier(uri, null, getPackageName())
        var res = getResources().getDrawable(imageResource)
        AlbumCover.setImageDrawable(res)

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
        val menuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.removeSong -> {
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setMessage("Do you want to remove this song from the Album?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", DialogInterface.OnClickListener {
                            dialog, which ->
                            val song = albumSongs[menuInfo.position]
                            albumSongs.removeAt(menuInfo.position)
                            adapter.notifyDataSetChanged()
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