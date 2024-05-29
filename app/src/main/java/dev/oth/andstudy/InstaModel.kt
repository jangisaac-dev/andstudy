package dev.oth.andstudy

data class InstaModel(
    val imgUrl: ArrayList<String>,
    var likeCnt: Int,
    val id: String,
    val description: String,
    var commentCnt: Int,
    val date: String,
    var isLiked: Boolean,
    var isBookMarked: Boolean,
)
