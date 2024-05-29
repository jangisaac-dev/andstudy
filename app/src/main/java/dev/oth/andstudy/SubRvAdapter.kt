package dev.oth.andstudy

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dev.oth.andstudy.databinding.RvItemBinding


class SubRvAdapter(
        private var activity: Activity,
        private var itemList: ArrayList<InstaModel> = arrayListOf(),
        private var onClickLike: ((Int) -> Unit)? = null,
        private var onClickComment: ((Int) -> Unit)? = null,
        private var onClickDM: ((Int) -> Unit)? = null,
        private var onClickBookMark: ((Int) -> Unit)? = null,
        private var onClickCommentDirect: ((Int) -> Unit)? = null,
        private var onClickCommentAll: ((Int) -> Unit)? = null,
        private var onClickMore: ((Int) -> Unit)? = null,
    ) : RecyclerView.Adapter<SubRvAdapter.InstaHolder>() {

    //holder class
    inner class InstaHolder(b: RvItemBinding) : RecyclerView.ViewHolder(b.root) {
        var rvBinding: RvItemBinding = b
    }

    //make holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstaHolder {
        return InstaHolder(
            RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    //item count
    override fun getItemCount(): Int {
        return itemList.count()
    }

    //actually draw function
    override fun onBindViewHolder(holder: InstaHolder, position: Int) {
        val model = itemList[position]

        //set like(favorite) image on/off
        holder.rvBinding.ivLike.setImageDrawable(
            if (model.isLiked) {
                ContextCompat.getDrawable(activity, R.drawable.baseline_favorite_24)
            }
            else {
                ContextCompat.getDrawable(activity, R.drawable.baseline_favorite_border_24)
            }
        )

        //set bookmark image on/off
        holder.rvBinding.ivBookMark.setImageDrawable(
            if (model.isBookMarked) {
                ContextCompat.getDrawable(activity, R.drawable.baseline_bookmark_24)
            }
            else {
                ContextCompat.getDrawable(activity, R.drawable.outline_bookmark_border_24)
            }
        )

        // set texts
        holder.rvBinding.tvLike.text = "좋아요 ${model.likeCnt}개"
        holder.rvBinding.tvId.text = model.id
        holder.rvBinding.tvFirstLine.text = model.description
        holder.rvBinding.tvCommentAll.text = "댓글 ${model.commentCnt}개 모두 보기"
        holder.rvBinding.tvDate.text = model.date

        //set image via glide
        Glide.with(activity.applicationContext)
            .load(model.imgUrl.first())
            .into(holder.rvBinding.ivMain)

        // set click events
        holder.rvBinding.ivLike.setOnClickListener {
            onClickLike?.invoke(position)
        }
        holder.rvBinding.ivComment.setOnClickListener {
            onClickComment?.invoke(position)
        }
        holder.rvBinding.ivDM.setOnClickListener {
            onClickDM?.invoke(position)
        }
        holder.rvBinding.ivBookMark.setOnClickListener {
            onClickBookMark?.invoke(position)
        }
        holder.rvBinding.tvCommentAll.setOnClickListener {
            onClickCommentAll?.invoke(position)
        }
        holder.rvBinding.tvComment.setOnClickListener {
            onClickCommentDirect?.invoke(position)
        }
        holder.rvBinding.tvMore.setOnClickListener {
            onClickMore?.invoke(position)
        }
    }


}