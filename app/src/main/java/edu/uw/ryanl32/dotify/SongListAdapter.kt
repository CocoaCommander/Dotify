package edu.uw.ryanl32.dotify

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import edu.uw.ryanl32.dotify.databinding.ItemSongBinding

class SongListAdapter(private var listOfSongs: List<Song>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {

    private val mutableSongs = mutableListOf<Song>()

    var onSongClickListener: (position: Int, song: Song) -> Unit = {_, _ -> }

    init {
        mutableSongs.addAll(listOfSongs)
    }

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
        val diffCallback = SongDiffCallback(this.mutableSongs, newListOfSongs)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.mutableSongs.clear()
        notifyItemRangeRemoved(0, listOfSongs.size)
        this.mutableSongs.addAll(newListOfSongs)
        notifyItemRangeInserted(0, newListOfSongs.size)
        this.listOfSongs = newListOfSongs
        diffResult.dispatchUpdatesTo(this)

    }

    class SongViewHolder(val binding: ItemSongBinding): RecyclerView.ViewHolder(binding.root)

    class SongDiffCallback(
        private val oldList: List<Song>,
        private val newList: List<Song>
    ) : DiffUtil.Callback() {
        override fun getNewListSize(): Int = newList.size
        override fun getOldListSize(): Int = oldList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].smallImageID == newList[newItemPosition].smallImageID
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].title == newList[newItemPosition].title
        }
    }
}