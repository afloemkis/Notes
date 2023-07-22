import java.util.Objects

data class Post(
    val id: Int,
    val ownerId: Int, // wall owner ID
    val fromId: Int, //author ID
    val createdBy: Int?, //Admin ID (for community only)
    val date: Int,
    val replyOwnerID: Int? = null,
    val replyPostID: Int? = null,
    val friendsOnly: Boolean = false,
    val text: String,
    val comments: CommentObject? = null,
    val copyright: CopyrightObject, //the source of material
    val likes: LikeObject,
    val reposts: RepostObject,
    val views: ViewsObject,
    val postType: String,
    val attachments: Array<Attachment>,
    val signerID: Int? = null, //only for communities - author ID if posted from community and signed by user
    val copyHistory: CopyHistoryObject? = null,
    val canPin: Boolean = false,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val donut: DonutObject? = null,
    val postponedId: Int = 0
)

data class CommentObject(
    val count: Int,
    val canPost: Boolean = false,
    val groupsCanPost: Boolean = false,
    val canClose: Boolean = false,
    val canOpen: Boolean = false
)

data class CopyrightObject(
    val id: Int,
    val link: String,
    val name: String,
    val type: String
)

data class LikeObject(
    val count: Int,
    val userLikes: Boolean = false,
    val canLike: Boolean = false,
    val canPublish: Boolean = false
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

data class CopyHistoryObject(
    val type: Array<Post>
)

data class Geo(
    val type: String,
    val coordinates: String,
    val place: Place
)

data class Place(
    val geo: Geo
)

data class DonutObject(
    val isDonut: Boolean,
    val paidDuration: Int,
    val placeHolder: Objects,
    val canPublishFreeCopy: Boolean,
    val editMode: String
)





