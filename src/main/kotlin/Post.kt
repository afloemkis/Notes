data class Post(
    val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Int,
    val date: Int,
    val text: String,
    val comments: CommentObject,
    val likes: LikeObject,
    val reposts: RepostObject,
    val views: ViewsObject,
    val canPin: Boolean = false,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val postponedId: Int = 0
)

data class CommentObject(
    val count: Int,
    val canPost: Boolean,
    val groupsCanPost: Boolean
)

data class LikeObject(
    val count: Int,
    val userLikes: Boolean,
    val canLike: Boolean,
    val canPublish: Boolean
)

data class RepostObject(
    val count: Int,
    val userReposted: Boolean
)

data class ViewsObject(
    val count: Int
)

data class Views(
    val count: Int
)

data class PostSource(
    val type: String
)

data class Attachment(
    val type: Array<Any>
)


data class Geo(
    val type: String,
    val coordinates: String,
    val place: Place
)

data class Place(
    val geo: Geo
)







