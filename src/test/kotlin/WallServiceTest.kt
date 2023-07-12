import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Test
    fun addPost_IdIsNotZero() {
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

        assertTrue(addedPost.id != 0)
    }

    @Test
    fun updatePost_ExistingId_ReturnsTrue() {
        val existingPost = Post(
            id = 1,
            ownerId = 1,
            fromId = 1,
            createdBy = 1,
            date = 123456789,
            text = "Existing post",
            comments = CommentObject(0, true, true),
            likes = LikeObject(0, false, true, true),
            reposts = RepostObject(0, false),
            views = ViewsObject(0)
        )
        WallService.add(existingPost)

        val updatedPost = existingPost.copy(text = "Updated post")
        val isUpdated = WallService.update(updatedPost)

        assertTrue(isUpdated)
    }

    @Test
    fun updatePost_NonexistentId_ReturnsFalse() {
        val nonExistentPost = Post(
            id = 2,
            ownerId = 1,
            fromId = 1,
            createdBy = 1,
            date = 123456789,
            text = "Nonexistent post",
            comments = CommentObject(0, true, true),
            likes = LikeObject(0, false, true, true),
            reposts = RepostObject(0, false),
            views = ViewsObject(0)
        )

        val isUpdated = WallService.update(nonExistentPost)

        assertFalse(isUpdated)
    }
}