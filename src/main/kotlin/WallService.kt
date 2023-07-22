object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()

    fun add(post: Post): Post {
        val newPost = post.copy(id = generateId())
        posts += newPost
        return newPost
    }

    fun update(post: Post): Boolean {
        val index = posts.indexOfFirst { it.id == post.id }
        if (index != -1) {
            posts[index] = post
            return true
        }
        return false
    }

    private fun generateId(): Int {
        return if (posts.isNotEmpty()) {
            posts.maxByOrNull { it.id }!!.id + 1
        } else {
            1
        }
    }

    class PostNotFoundException(message: String): RuntimeException(message)
    fun createComment(postId: Int, comment: Comment): Comment {
        val post = posts.find { it.id == postId }
        if (post != null) {
            val newComment = comment.copy(id = comments.size + 1)
            comments += newComment
            return newComment
        } else {
            throw PostNotFoundException("Post with ID $postId not found")
        }
    }
}