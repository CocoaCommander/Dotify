package edu.uw.ryanl32.dotify

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import edu.uw.ryanl32.dotify.databinding.ItemSongBinding

class SongListAdapter(private var listOfSongs: List<Song>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {

    var onSongClickListener: (position: Int, song: Song) -> Unit = {_, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = ItemSongBinding.inflate(LayoutInflater.from(parent.context))
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = listOfSongs[position]
        with(holder.binding) {
            tvSongListArtistName.text = song.artist
            tvSongListSongName.text = song.title
            ivSongPic.setImageResource(song.smallImageID)

            itemRoot.setOnClickListener {
                onSongClickListener(position, song)
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfSongs.size
    }

    fun updateSongs(newListOfSongs: List<Song>) {
        this.listOfSongs = newListOfSongs

        notifyDataSetChanged()
    }

    class SongViewHolder(val binding: ItemSongBinding): RecyclerView.ViewHolder(binding.root)
}