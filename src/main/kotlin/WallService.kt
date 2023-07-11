object WallService {
    private var posts = emptyArray<Post>()

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
}