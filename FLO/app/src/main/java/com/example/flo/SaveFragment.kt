package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flo.databinding.FragmentDetailBinding
import com.example.flo.databinding.FragmentSaveBinding

class SaveFragment : Fragment(){
    lateinit var binding : FragmentSaveBinding
    private var albumDatas = ArrayList<Album>();
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaveBinding.inflate(inflater,container,false)

        albumDatas.apply {
            add(Album("Butter", "방탄소년단(BTS)",R.drawable.img_album_exp))
            add(Album("Lilac", "아이유(IU)",R.drawable.img_album_exp2))
            add(Album("Next Level", "에스파(AESPA)",R.drawable.img_album_exp3))
            add(Album("Boy with Luv", "방탄소년단(BTS)",R.drawable.img_album_exp4))
            add(Album("BBom BBom", "모모랜드(MOMOLAND)",R.drawable.img_album_exp5))
            add(Album("Weekend", "태연(Tae Yeaon)",R.drawable.img_album_exp6))
            add(Album("Butter", "방탄소년단(BTS)",R.drawable.img_album_exp))
            add(Album("Lilac", "아이유(IU)",R.drawable.img_album_exp2))
            add(Album("Next Level", "에스파(AESPA)",R.drawable.img_album_exp3))
            add(Album("Boy with Luv", "방탄소년단(BTS)",R.drawable.img_album_exp4))
            add(Album("BBom BBom", "모모랜드(MOMOLAND)",R.drawable.img_album_exp5))
            add(Album("Weekend", "태연(Tae Yeaon)",R.drawable.img_album_exp6))
            add(Album("Butter", "방탄소년단(BTS)",R.drawable.img_album_exp))
            add(Album("Lilac", "아이유(IU)",R.drawable.img_album_exp2))
            add(Album("Next Level", "에스파(AESPA)",R.drawable.img_album_exp3))
            add(Album("Boy with Luv", "방탄소년단(BTS)",R.drawable.img_album_exp4))
            add(Album("BBom BBom", "모모랜드(MOMOLAND)",R.drawable.img_album_exp5))
            add(Album("Weekend", "태연(Tae Yeaon)",R.drawable.img_album_exp6))
        }
        //더미데이터랑 Adapter 연결
        val lockerRVAdapter = LockerRVAdapter(albumDatas)
        //리사이클러뷰에 어댑터를 연결
        binding.saveMySongRv.adapter = lockerRVAdapter

        //레이아웃 매니저 설정
        binding.saveMySongRv.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL, false)

        return binding.root
    }
}