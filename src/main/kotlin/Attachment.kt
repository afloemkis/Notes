// Класс Attachment
interface Attachment {
    val type: String
}

// Классы для хранения данных о вложениях
data class Photo(
    val id: Int, //Идентификатор фотографии
    val albumId: Int, //Идентификатор альбома, в котором находится фотография
    val ownerId: Int, //Идентификатор владельца фотографии
    //    val userId: Int //Идентификатор пользователя, загрузившего фото
    // (если фотография размещена в сообществе). Для фотографий,
    // размещенных от имени сообщества, userId = 100
    val text: String? = null, //Текст описания фотографии
    val date: Int, //Дата добавления в формате Unixtime
    val sizes: Array<Any> = emptyArray(), //Массив с копиями изображения в разных размерах. Каждый объект массива содержит следующие поля:
//type (string) — тип копии. См. Формат описания размеров фотографии.
//url (string) — URL копии.
//width (integer) — высота в px.
//height (integer) — ширина в px.
    val width: Int, //Ширина оригинала фотографии в пикселах
    val height: Int //Высота оригинала фотографии в пикселах
)

data class Audio(
    val id: Int, //Идентификатор аудио
    val ownerId: Int, //Идентификатор альбома, в котором находится фотография
    val artist: String, //Исполнитель
    val title: String, //Название композиции
    val duration: Int, //Длительность аудиозаписи в секундах
    val url: String, //Ссылка на mp3
    val lyricsId: Int? = null, //Идентификатор текста аудиозаписи (если доступно)
    val albumId: Int? = null, //Идентификатор альбома, в котором находится аудиозапись (если присвоен)
    val genreId: Int, //Идентификатор жанра из списка аудио жанров
    val date: Int, //Дата добавления
    val noSearch: Boolean = false, //истина, если включена опция «Не выводить при поиске»
    // Если опция отключена, поле не возвращается.
    val isHq: Boolean = false //истина, если аудио в высоком качестве
)

data class Video(
    val id: Int, //Идентификатор видеозаписи
    val ownerId: Int, //Идентификатор владельца видеозаписи
    val title: String, //Название видеозаписи
    val description: String? = null, //Текст описания видеозаписи
    val duration: Int, //Длительность ролика в секундах
    val image: Array<Any> = emptyArray(), //Изображение обложки. Содержит массив объектов с полями
    // height (integer) — высота изображения
    // url (string) — ссылка на изображение
    // width (integer) — ширина изображения
    // with_padding (integer) — поле возвращается, если изображение с отбивкой, всегда содержит 1
    val firstFrame: Array<Any> = emptyArray(), //Изображение первого кадра. Содержит массив объектов с полями
    // height (integer) — высота изображения
    // url (string) — ссылка на изображение
    // width (integer) — ширина изображения
    val date: Int, //Дата создания видеозаписи в формате Unixtime
    val addingDate: Int, //Дата добавления видеозаписи пользователем или группой в формате Unixtime
    val views: Int = 0, //Количество просмотров видеозаписи
    val localViews: Int = 0, //Если видео внешнее, количество просмотров ВКонтакте
    val comments: Int = 0, //Количество комментариев к видеозаписи.
    // Поле не возвращается, если комментарии недоступны
    val player: String, //URL страницы с плеером, который можно использовать для воспроизведения ролика
    //в браузере. Поддерживается flash и HTML5, плеер всегда масштабируется по размеру окна
    val platform: String? = null, //Название платформы (для видеозаписей, добавленных с внешних сайтов)
    val canAdd: Boolean = false, //Может ли пользователь добавить видеозапись к себе
    // false — не может добавить
    // true — может добавить
    val isPrivate: Boolean = true, //Поле возвращается, если видеозапись приватная
    // (например, была загружена в личное сообщение), всегда содержит true
    val accessKey: String, //Ключ доступа к объекту. Подробнее см. Ключ доступа к данным access_key
    val processing: Boolean = true, //Поле возвращается в том случае, если видеоролик находится в процессе обработки,
    // всегда содержит true
    val isFavorite: Boolean = false, //true, если объект добавлен в закладки у текущего пользователя
    val canComment: Boolean = false, //Может ли пользователь комментировать видео
    // false — не может комментировать
    // true — может комментировать
    val canEdit: Boolean = false, //Может ли пользователь редактировать видео
    // false — не может редактировать
    // true — может редактировать
    val canLike: Boolean = false, //Может ли пользователь добавить видео в список <<Мне нравится>>
    // false — не может добавить
    // true — может добавить
    val canRepost: Boolean = false, //Может ли пользователь сделать репост видео
    // false — не может сделать репост
    // true — может сделать репост
    val canSubscribe: Boolean = false, //Может ли пользователь подписаться на автора видео
    // false — не может подписаться
    // true — может подписаться
    val canAddToFaves: Boolean = false, //Может ли пользователь добавить видео в избранное
    // false — не может добавить
    // true — может добавить
    val canAttachLink: Boolean = false, //Может ли пользователь прикрепить кнопку действия к видео
    // false — не может прикрепить
    // true — может прикрепить
    val width: Int, //Ширина видео
    val height: Int, //Высота видео
    val userId: Int? = null, //Идентификатор пользователя, загрузившего видео, если оно было загружено в группу
    // одним из участников
    val converting: Boolean = false, //Конвертируется ли видео
    // false — не конвертируется
    // true — конвертируется
    val added: Boolean = false, //Добавлено ли видео в альбомы пользователя
    // false — не добавлено
    // true — добавлено
    val isSubscribed: Boolean = false, //Подписан ли пользователь на автора видео
    // false — не подписан
    // true — подписан
    val repeat: Boolean = true, //Поле возвращается в том случае, если видео зациклено, всегда содержит true
    val type: String, //Тип видеозаписи. Может принимать значения: video, music_video, movie.
    val balance: Int = 0, //Баланс донатов в прямой трансляции
    val liveStatus: String, //Статус прямой трансляции. Может принимать значения: waiting, started,
    // finished, failed, upcoming
    val live: Boolean = true, //Поле возвращается в том случае, если видеозапись является прямой трансляцией,
    // всегда содержит true. Обратите внимание, в этом случае в поле duration содержится значение 0.
    val upcoming: Boolean = true, //Поле свидетельствует о том, что трансляция скоро начнётся.
    // Для live =true
    val spectators: Int = 0, //Количество зрителей прямой трансляции
    val likes: LikesObject, //Содержит объект отметки «Мне нравится»
    val reposts: RepostsObject, //Содержит объект репоста
)

data class LikesObject(
    val count: Int, //количество лайков
    val userLikes: Boolean = false, //добавлено ли видео в список «Мне нравится» текущего пользователя
)

data class RepostsObject( //Содержит объект репоста
    val count: Int = 0, //счетчик общего количества репостов
    //Содержит сумму репостов на стену и в личные сообщения
    val wallCount: Int, //счетчик репостов на стену
    val mailCount: Int = 0, //счетчик репостов в личные сообщения
    val userReposted: Boolean = false //сделал ли текущий пользователь репост этого видео
)

data class File(
    val id: Int, //Идентификатор файла
    val ownerId: Int, //Идентификатор пользователя, загрузившего файл
    val title: String, //Название файла
    val size: Int, //Размер файла в байтах
    val ext: String, //Расширение файла
    val url: String, //Адрес файла, по которому его можно загрузить
    val date: Int, //Дата добавления в формате Unixtime
    val type: Int, //Тип файла.  Возможные значения:
    //1 — текстовые документы;
    // 2 — архивы;
    // 3 — gif;
    // 4 — изображения;
    // 5 — аудио;
    // 6 — видео;
    // 7 — электронные книги;
    // 8 — неизвестно
    //val preview: previewObject //Информация для предварительного просмотра файла. Не в этот раз
)

data class Link(
    val url: String, //URL ссылки
    val title: String, //Заголовок ссылки
    val caption: String, //Подпись ссылки (если имеется)
    val description: String, //Описание ссылки
//    val photo: object //Изображение превью, объект фотографии (если имеется). Не в этот раз
//    product object Информация о продукте (если имеется). Поле возвращается для ссылок на магазины,
//    например, AliExpress. Объект с единственным полем price (object), которое описано на отдельной странице.
// Button object Информация о кнопке для перехода (если имеется). Объект описан на отдельной странице.
    val previewPage: String, //Идентификатор вики-страницы с контентом для предпросмотра содержимого страницы
    //возвращается в формате "ownerIdPageId"
    val previewUrl: String //URL страницы с контентом для предпросмотра содержимого страницы
)

// Классы-наследники Attachment
data class PhotoAttachment(override val type: String = "photo", val photo: Photo) : Attachment

data class VideoAttachment(override val type: String = "video", val video: Video) : Attachment

data class AudioAttachment(override val type: String = "audio", val audio: Audio) : Attachment

data class FileAttachment(override val type: String = "file", val file: File) : Attachment

data class LinkAttachment(override val type: String = "link", val link: Link) : Attachment
