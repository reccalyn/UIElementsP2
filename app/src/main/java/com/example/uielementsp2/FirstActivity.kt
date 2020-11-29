package com.example.uielementsp2


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.uielementsp2.databaseHandler.SongsDatabaseHandler
import com.example.uielementsp2.models.Song
import com.google.android.material.snackbar.Snackbar

val queuedSongs = ArrayList<String>() //Array where all the songs queued will be stored and will be passed to the Queue activity
//lateinit var queuedSongs = MutableList<Song>
lateinit var songsArray: MutableList<Song>
lateinit var songsAdapter: ArrayAdapter<Song>

class FirstActivity : AppCompatActivity() {

    lateinit var songsDatabaseHandler: SongsDatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)


        //Get the table handler
        songsDatabaseHandler = SongsDatabaseHandler(this)
        //get the records from the database
        songsArray = songsDatabaseHandler.read()

        //Map the views
        var songsListView  = findViewById<ListView>(R.id.songsListView)
        //Adapter for the list view
        songsAdapter = ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, songsArray)
        songsListView.adapter = songsAdapter

        //Register the context menu to the List View
        registerForContextMenu(songsListView)

    }
    //Context Menu
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val menuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo //Allows us to inherit from the class Adapterview.AdapterCOntextMenuInfo to get the position
        return when (item.itemId) {
            R.id.add_song_to_queue -> {
                val song = songsArray[menuInfo.position].title
                queuedSongs.add(song)
                val snackbar = Snackbar.make(findViewById(R.id.songsListView), "$song is added to the Queue.", Snackbar.LENGTH_LONG)
                snackbar.setAction("Queue", View.OnClickListener { //Lamda function
                    val intent = Intent(this, queuedSongs::class.java)
                    startActivity(intent)
                })
                snackbar.show()
                true
            }
            R.id.editSong -> {
                val intent = Intent (this, EditSongs::class.java)
                val song_id = songsArray[menuInfo.position].id
                intent.putExtra("song_id", song_id)
                startActivity(intent)
                true
            }
            R.id.deleteSong -> {
                val song = songsArray[menuInfo.position]
                if(songsDatabaseHandler.delete(song)){
                    songsArray.removeAt(menuInfo.position)
                    songsAdapter.notifyDataSetChanged()
                    Toast.makeText(this, "Song deleted.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> {
                return super.onContextItemSelected(item)
            }

        }

    }


    //Main Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.go_to_songs -> {
                startActivity(Intent(this, FirstActivity::class.java))
                true
            }
            R.id.go_to_album -> {
                startActivity(Intent(this, AlbumsActivity::class.java))
                true
            }
            R.id.go_to_queue -> {
                val intent = Intent(this, QueuedSongsActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.addASong -> {
                val intent = Intent(this, AddSongs::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)

        }


    }
}