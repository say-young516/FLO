package com.example.flo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.flo.databinding.ItemSongBinding

class LockerRVAdapter(private val albumList: ArrayList<Album>) : RecyclerView.Adapter<LockerRVAdapter.ViewHolder>() {
    interface MyItemClickListener{
        fun onItemClick(album:Album)
        fun onRemoveAlbum(position:Int)
    }
    //리스너 객체를 전달받는 함수랑 리스너 객체를 저장할 변수
    private lateinit var mItemClickListener: AlbumRVAdapter.MyItemClickListener

    fun setMyItemClickLister(itemClickListener: AlbumRVAdapter.MyItemClickListener){
        mItemClickListener=itemClickListener
    }
    fun addItem(album: Album){
        albumList.add(album)
        notifyDataSetChanged()
    }
    fun removeItem(position: Int){
        albumList.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LockerRVAdapter.ViewHolder {
        val binding : ItemSongBinding = ItemSongBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LockerRVAdapter.ViewHolder, position: Int) {
        holder.bind(albumList[position])
    }

    override fun getItemCount(): Int = albumList.size

    inner class ViewHolder(val binding: ItemSongBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(album : Album){
            binding.lockerTitleTv.text= album.title
            binding.lockerSingerTv.text= album.singer
            binding.lockerMainPictureRv.setImageResource(album.coverImg!!)
        }
    }

}