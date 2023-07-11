fun main() {

    val post = Post(
        id = 0,
        ownerId = 1,
        fromId = 1,
        createdBy = 1,
        date = 123456789,
        text = "Test post",
        comments = CommentObject(0, true, true),
        likes = LikeObject(0, false, true, true),
        reposts = RepostObject(0, false),
        views = ViewsObject(0)
    )

    val addedPost = WallService.add(post)

    print(post.id)

}