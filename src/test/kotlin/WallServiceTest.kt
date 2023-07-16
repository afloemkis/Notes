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
            replyOwnerID = 1,
            text = "Test post",
            comments = CommentObject(count = 0, canPost = true, groupsCanPost = true),
            copyright = CopyrightObject(0, "link", "CR", "type"),
            likes = LikeObject(count = 0, userLikes = false, canLike = true, canPublish = true),
            reposts = RepostObject(0, false),
            views = ViewsObject(0),
            postType = "Type",
            attachments = emptyArray(),
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
            replyOwnerID = 1,
            text = "Existing post",
            comments = CommentObject(count = 0, canPost = true, groupsCanPost = true),
            copyright = CopyrightObject(0, "link", "CR", "type"),
            likes = LikeObject(count = 0, userLikes = false, canLike = true, canPublish = true),
            reposts = RepostObject(0, false),
            views = ViewsObject(0),
            postType = "Type",
            attachments = emptyArray()
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
            replyOwnerID = 1,
            text = "Nonexistent post",
            comments = CommentObject(0, canPost = true, groupsCanPost = true),
            copyright = CopyrightObject(0, "link", "CR", "type"),
            likes = LikeObject(0, false, canLike = true, canPublish = true),
            reposts = RepostObject(0, false),
            views = ViewsObject(0),
            postType = "Type",
            attachments = emptyArray()
        )

        val isUpdated = WallService.update(nonExistentPost)

        assertFalse(isUpdated)
    }
}