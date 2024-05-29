package dev.oth.andstudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import dev.oth.andstudy.databinding.ActivitySubBinding
import java.text.SimpleDateFormat

class SubActivity : AppCompatActivity() {

    lateinit var binding: ActivitySubBinding

    lateinit var adapter: SubRvAdapter

    private var list = arrayListOf<InstaModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySubBinding.inflate(layoutInflater) // view binding init

        list = getInitialData() // make sample list
        initRvAdapter(list) // init recycler view adapter


        (binding.rvMain.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false // disable blink animation for update
        binding.rvMain.layoutManager = LinearLayoutManager(this) // set layout manager -> grid, linear ... etc
        binding.rvMain.adapter = adapter // set adapter

        setContentView(binding.root)
    }

    private fun getInitialData() : ArrayList<InstaModel> {
        val sdf = SimpleDateFormat("MM월dd일") // java default date parser
        val list = arrayListOf<InstaModel>()
        for (i in 0..<10) {
            list.add(
                InstaModel(
                    imgUrl = arrayListOf("https://picsum.photos/200/200"), // sample image url
                    likeCnt = i,
                    id = "thisIsId$i",
                    description = "This Is Description : $i",
                    commentCnt = i,
                    date = sdf.format(java.util.Date()),
                    isLiked = i % 2 == 0,
                    isBookMarked = i % 3 == 0,
                )
            )
        }
        return list
    }

    private fun initRvAdapter(itemList: ArrayList<InstaModel>) {
        adapter = SubRvAdapter(
                activity= this,
                itemList= itemList,

                // set click events
                onClickLike= {
                    println("onClickLike")
                    list[it].isLiked = !list[it].isLiked
                    if (list[it].isLiked) {
                        list[it].likeCnt += 1
                    }
                    else {
                        list[it].likeCnt -= 1
                    }
                    adapter.notifyItemChanged(it) // update adapter
                } ,
                onClickComment= {
                    println("onClickComment")
                    list[it].commentCnt += 1
                    adapter.notifyItemChanged(it)
                },
                onClickDM= {
                    println("onClickDM")
                },
                onClickBookMark= {
                    println("onClickBookMark")
                    list[it].isBookMarked = !list[it].isBookMarked
                    adapter.notifyItemChanged(it) // update adapter
                },
                onClickCommentDirect= {
                    println("onClickCommentDirect")
                },
                onClickCommentAll= {
                    println("onClickCommentAll")
                },
                onClickMore= {
                    println("onClickMore")
                },
            )
    }
}