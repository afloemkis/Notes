data class Comment(
    val nid: Int, //    Идентификатор заметки, к которой комментарий
    val id: Int, // Идентификатор комментария
    val date: Int, //    Дата создания комментария в формате Unixtime.
    val text: String, //   Текст комментария.
    val donut: DonutObjectComment? = null, //Информация о VK Donut.
    val replyToUser: Int? = null, // Идентификатор пользователя или сообщества, в ответ которому оставлен текущий комментарий (если применимо).
    val replyToComment: Int? = null, //Идентификатор комментария, в ответ на который оставлен текущий (если применимо).
    val parentsStack: Array<Int> = emptyArray(), // Массив идентификаторов родительских комментариев.
    val thread: ThreadObject? = null, // Информация о вложенной ветке комментариев, объект с полями:
    var deleted: Boolean = false //была ли комментарий удален
)

data class ThreadObject(//Информация о вложенной ветке комментариев, объект с полями
    val count: Int = 0, // количество комментариев в ветке
    val items: Array<Comment> = emptyArray(), //массив объектов комментариев к записи (только для метода wall.getComments).
    val can_post: Boolean = false,//может ли текущий пользователь оставлять комментарии в этой ветке.
    val show_reply_button: Boolean = false, //нужно ли отображать кнопку «ответить» в ветке.
    val groups_can_post: Boolean = false// могут ли сообщества оставлять комментарии в ветке.
)

data class DonutObjectComment( //Информация о VK Donut. Объект со следующими полями:
    val is_don: Boolean = false, //является ли комментатор подписчиком VK Donut.
    val placeholder: String = "no donut" //заглушка для пользователей, которые не оформили подписку VK Donut
)