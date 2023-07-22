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

    @Test
    fun addComment_existingPost() {
        val existingPost = Post(
            id = 1,
            ownerId = 1,
            fromId = 1,
            createdBy = 1,
            date = 123456789,
            replyOwnerID = 1,
            text = "Existing post",
            copyright = CopyrightObject(0, "link", "CR", "type"),
            likes = LikeObject(count = 0, userLikes = false, canLike = true, canPublish = true),
            reposts = RepostObject(0, false),
            views = ViewsObject(0),
            postType = "Type",
            attachments = emptyArray()
        )
        WallService.add(existingPost)

        val comment = Comment(
            id = 1, //    Идентификатор комментария.
            fromId = 1, //    Идентификатор автора комментария.
            date = 12345678, //    Дата создания комментария в формате Unixtime.
            text = "Первый коммент", //   Текст комментария.
            replyToUser = 2 // Идентификатор пользователя или сообщества, в ответ которому оставлен текущий комментарий (если применимо).
        )

        val createdComment = WallService.createComment(1, comment)
        assertTrue(createdComment.id == 1)
    }

    @Test(expected = WallService.PostNotFoundException::class)
    fun addComment_throwException() {
        val comment = Comment(
            id = 6, //    Идентификатор комментария.
            fromId = 1, //    Идентификатор автора комментария.
            date = 12345678, //    Дата создания комментария в формате Unixtime.
            text = "Первый коммент", //   Текст комментария.
            replyToUser = 2 // Идентификатор пользователя или сообщества, в ответ которому оставлен текущий комментарий (если применимо).
        )
        WallService.createComment(1, comment)

    }
}