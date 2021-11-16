package com.example.flo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentAlbumBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class AlbumFragment : Fragment() {

    lateinit var binding: FragmentAlbumBinding
    private var gson: Gson = Gson()
    val information = arrayListOf("수록곡", "상세정보", "영상")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAlbumBinding.inflate(inflater, container, false)

        //Home에서 넘어온 데이터 받아오기
        val albumData = arguments?.getString("album")
        val album = gson.fromJson(albumData, Album::class.java)
        //Home에서 넘어온 데이터를 반영
        setInit(album)

        binding.albumArrowBeforeIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeFragment())
                .commitAllowingStateLoss()
        }
//    binding.albumListItem01Layout.setOnClickListener {
//        Toast.makeText(activity,"라일락",Toast.LENGTH_SHORT).show()
//    }
//        binding.albumListItem02Layout.setOnClickListener {
//            Toast.makeText(activity,"Flu",Toast.LENGTH_SHORT).show()
//        }
//        binding.albumListItem03Layout.setOnClickListener {
//            Toast.makeText(activity,"Coin",Toast.LENGTH_SHORT).show()
//        }
//        binding.albumListItem04Layout.setOnClickListener {
//            Toast.makeText(activity,"봄 안녕",Toast.LENGTH_SHORT).show()
//        }
//        fun setMixedStatus(MixedOn : Boolean){
//            if(MixedOn){
//                binding.albumMixOffBtnIv.visibility= View.GONE
//                binding.albumMixOnBtnIv.visibility=View.VISIBLE
//            }else{
//                binding.albumMixOffBtnIv.visibility= View.VISIBLE
//                binding.albumMixOnBtnIv.visibility=View.GONE
//            }
//        }
//        binding.albumMixOffBtnIv.setOnClickListener {
//            setMixedStatus(true)
//        }
//        binding.albumMixOnBtnIv.setOnClickListener {
//            setMixedStatus(false)
//        }
        var albumAdapter = AlbumViewpagerAdapter(this)
        binding.albumContentVp.adapter = albumAdapter
        TabLayoutMediator(binding.albumContentTb, binding.albumContentVp) { tab, position ->
            tab.text = information[position]
        }.attach()
        return binding.root
    }

    private fun setInit(album: Album) {
        binding.albumMainPictureRv.setImageResource(album.coverImg!!)
        binding.albumTitleTv.text = album.title.toString()
        binding.albumSingerTv.text = album.singer.toString()
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        goback = object: OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                (context as MainActivity).supportFragmentManager.beginTransaction()
//                    .replace(R.id.main_frame_fl, home())
//                    .commitAllowingStateLoss()
//
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(this, goback)
}