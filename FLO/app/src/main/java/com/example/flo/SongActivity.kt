package com.example.flo

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivitySongBinding
import com.google.gson.Gson

//AppCompatActivity: 안드로이드에서 activity 기능 사용할 수 있도록 만들어둔 클래스
//코틀린에서 다른 클래스 상속받을 때는 반드시 소괄호 써주기
class SongActivity : AppCompatActivity() {

    //변수 선언 방법
    //var 변수명 : 먼저 선언하고 나중에 값 변경 가능
    //var 변수명 : Int = 2 이런식으로 원하는 자료형 넣기
    //val 변수명 : 처음 선언했으면 나중에 값 변경 불가능

    //binding 사용 방법
   lateinit var binding : ActivitySongBinding //타입
    //lateinit: 선언은 지금 할 건데 나중에 꼭 초기화 해줄게!
    private lateinit var player:Player
   private val song: Song =Song()
    //미디어 플레이어
    private var mediaPlayer: MediaPlayer?=null

    private var gson : Gson = Gson()
    private val handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) { //액티비티가 처음 실행될 때 반드시 실행되는 함수
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater) //activitybinding 을 초기화하는 방법
        //inflate: xml에 표기된 레이아웃들을 메모리에 객체화시키는 행동. 이렇게 쓴당! (나중에 찾아보기)
        setContentView(binding.root)

        initSong()
        player=Player(song.playTime,song.isPlaying)
        player.start()
        //root: 최상단 뷰를 마음대로 쓸거야
        if(intent.hasExtra("title")&&intent.hasExtra("singer")){
            binding.songPlayerTitleTv.text=intent.getStringExtra("title")
            binding.songPlayerSingerTv.text=intent.getStringExtra("singer")
        }
        binding.songDownIb.setOnClickListener{
            finish()
        }
        binding.songPlayBtnIv.setOnClickListener{
            setPlayerStatus(false)
            player.isPlaying=true
            song.isPlaying=true
            mediaPlayer?.start()
        }
        binding.songPauseBtnIv.setOnClickListener {
            setPlayerStatus(true)
            player.isPlaying=false
            song.isPlaying=false
            mediaPlayer?.pause()
        }

    }
    private fun initSong() {
        if (intent.hasExtra("title") && intent.hasExtra("singer") && intent.hasExtra("second")&& intent.hasExtra("isPlaying") && intent.hasExtra(
                "playTime")&& intent.hasExtra("music")
        ) {
            song.title = intent.getStringExtra("title")!!
            song.singer = intent.getStringExtra("singer")!!
            song.playTime = intent.getIntExtra("playTime", 0)
            song.second=intent.getIntExtra("second",0)
            song.isPlaying = intent.getBooleanExtra("isPlaying", false)
            song.music=intent.getStringExtra("music")!!
            val music = resources.getIdentifier(song.music,"raw",this.packageName)
            binding.songEndTimeTv.text =
                String.format("%02d:%02d", song.playTime / 60, song.playTime % 60)
            binding.songPlayerTitleTv.text = song.title
            binding.songPlayerSingerTv.text = song.singer
            mediaPlayer=MediaPlayer.create(this,music)
        }
    }
    fun setPlayerStatus(isPLaying : Boolean){
        if(isPLaying){
            binding.songPauseBtnIv.visibility= View.GONE
            binding.songPlayBtnIv.visibility=View.VISIBLE
            player.isPlaying=true
        }else{
            binding.songPlayBtnIv.visibility= View.GONE
            binding.songPauseBtnIv.visibility=View.VISIBLE
        }
    }
    inner class Player(private val playTime:Int,var isPlaying:Boolean) : Thread(){
        private var second=0
        override fun run() {
            while(true){
                if(second >=playTime){
                    break
                }
                if(isPlaying){
                sleep(1000)
                second++
                runOnUiThread{
                     binding.songPlayerBarSb.progress=second*1000/playTime
                    binding.songStartTimeTv.text=String.format("%02d:%02d",second/60,second%60)
                }}
        }
        }

    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.pause() //미디어 플레이어 중지
        player.isPlaying=false // 스레드 중지
        song.isPlaying=false
        song.second=(binding.songPlayerBarSb.progress*song.playTime)/1000
        setPlayerStatus(true) //정지상태일때의 이미지로 전환
        //sharedPreferences
        val sharedPreferences = getSharedPreferences("song", MODE_PRIVATE)
        val editor = sharedPreferences.edit() //sharedPreferences 조작할 때 사용을 한다
//        editor.putString("title", song.title)
        //Gson
        val json = gson.toJson(song)
        editor.putString("song",json)
        editor.apply()

    }

    override fun onDestroy() {
        super.onDestroy()
        player.interrupt() // 스레드 해제
        mediaPlayer?.release() // 미디어 플레이어가 갖고 있던 리소스 해제
        mediaPlayer = null //미디어플레이어 해제
    }
}