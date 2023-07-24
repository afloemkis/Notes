object NoteService {

    private var notes = mutableListOf<Note>() //использую подход

    // с хранением заметок в списке MutableList, и флаг deleted помогает обозначить удаленные заметки
    private var comments = mutableListOf<Comment>()


    //Создает новую заметку у текущего пользователя.
    fun addNote(
        title: String, //Заголовок заметки
        text: String, //Текст заметки
        privacy: Int = 0, //Уровень доступа к заметке Возможные значения:
        // 0 — все пользователи,
        // 1 — только друзья,
        // 2 — друзья и друзья друзей,
        // 3 — только пользователь.
        commentPrivacy: Int = 0, //Уровень доступа к комментированию заметки. Возможные значения:
        // 0 — все пользователи,
        // 1 — только друзья,
        // 2 — друзья и друзья друзей,
        // 3 — только пользователь.
        privacyView: String = "All", //Настройки приватности просмотра заметки
        privacyComment: String = "All", //Настройки приватности комментирования заметки
    ): Int {
        val newNote = Note(
            id = getNextId(),
            title = title,
            text = text,
            date = System.currentTimeMillis().toInt(),
            privacy = privacy,
            commentPrivacy = commentPrivacy,
            privacyView = privacyView,
            privacyComment = privacyComment
        )
        notes.add(newNote)
        return newNote.id
    }

    //Создает новый комментарий
    fun createComment(
        noteId: Int, //Идентификатор заметки
        replyTo: Int? = null, //Идентификатор пользователя, ответом на комментарий которого является добавляемый комментарий
        // (не передаётся, если комментарий не является ответом).
        message: String, //Текст комментария.
    ): Int {
        val newComment = Comment(
            nid = noteId,
            id = getNextCid(),
            date = System.currentTimeMillis().toInt(),
            text = message
        )
        comments.add(newComment)
        return newComment.id
    }

    //Удаляет заметку текущего пользователя
    //При удалении заметки комментарии к ней удаляются безвозвратно
    fun deleteNote(
        noteId: Int, //Идентификатор заметки
    ): Int {
        if (notes.removeIf { it.id == noteId }) {
            comments.removeAll { it.nid == noteId }
            return 1
        } else {
            throw NoteNotFoundException("Note with ID $noteId not found")
        }
    }

    //Удаляет комментарий (по факту изменяет значение флага deleted
    fun deleteComment(
        commentId: Int,
    ): Int {
        val comment = comments.find { it.id == commentId }
        if (comment != null) {
            comment.deleted = true
            return 1
        } else {
            throw CommentNotFoundException("Post with ID $commentId not found")
        }
    }

    fun editNote(
        noteId: Int,
        title: String, //Заголовок заметки
        text: String, //Текст заметки
        privacy: Int = 0, //Уровень доступа к заметке Возможные значения:
        // 0 — все пользователи,
        // 1 — только друзья,
        // 2 — друзья и друзья друзей,
        // 3 — только пользователь.
        commentPrivacy: Int = 0, //Уровень доступа к комментированию заметки. Возможные значения:
        // 0 — все пользователи,
        // 1 — только друзья,
        // 2 — друзья и друзья друзей,
        // 3 — только пользователь.
        privacyView: String = "All", //Настройки приватности просмотра заметки
        privacyComment: String = "All", //Настройки приватности комментирования заметки
    ): Int {
        val index = notes.indexOfFirst { it.id == noteId }
        if (index != -1) {
            notes[index] = Note(
                id = noteId,
                title = title,
                text = text,
                date = System.currentTimeMillis().toInt(),
                privacy = privacy,
                commentPrivacy = commentPrivacy,
                privacyView = privacyView,
                privacyComment = privacyComment
            )
            return 1
        } else {
            throw NoteNotFoundException("Note with ID $noteId not found")
        }
    }

    fun editComment(
        commentId: Int, //Идентификатор заметки
        message: String, //Текст комментария.
    ): Int {
        val index = comments.indexOfFirst { it.id == commentId }
        if (index != -1) {
            if (!comments[index].deleted) {
                comments[index] = Comment(
                    nid = comments[index].nid,
                    id = commentId,
                    date = System.currentTimeMillis().toInt(),
                    text = message
                )
                return 1
            } else {
                throw CommentDeletedException("Comment with ID $commentId is deleted")
            }
        } else {
            throw CommentNotFoundException("Comment with ID $commentId not found")
        }
    }

    fun getNotes(
        noteIds: Array<Int>,
        count: Int,//Количество заметок, информацию о которых необходимо получить.
    ): Array<Note> {
        var notesList = emptyArray<Note>()
        for (item in noteIds) {
            notesList += getNoteByID(item)
        }
        return notesList
    }

    fun getNoteByID(
        noteId: Int
    ): Note {
        val note = notes.find { it.id == noteId }
        return note ?: throw NoteNotFoundException("Note with ID $noteId not found")
    }

    fun getComments(
        noteId: Int,
    ): Array<Comment> {
        var commentsList = emptyArray<Comment>()
        for (item in comments) {
            if (item.nid == noteId && !item.deleted) commentsList += item
        }
        return commentsList
    }

    //Удаляет комментарий (по факту изменяет значение флага deleted
    fun restoreComment(
        commentId: Int,
    ): Int {
        val comment = comments.find { it.id == commentId }
        if (comment != null) {
            comment.deleted = false
            return 1
        } else {
            throw CommentNotFoundException("Post with ID $commentId not found")
        }
    }

    // Вспомогательная функция для получения следующего ID для новой заметки
    private fun getNextId(): Int {
        return notes.maxByOrNull { it.id }?.id?.plus(1) ?: 1
    }

    // Вспомогательная функция для получения следующего ID для нового комментария
    private fun getNextCid(): Int {
        return comments.maxByOrNull { it.id }?.id?.plus(1) ?: 1
    }

    //Exceptions

    class NoteNotFoundException(message: String) : RuntimeException(message)
    class CommentNotFoundException(message: String) : RuntimeException(message)
    class CommentDeletedException(message: String) : RuntimeException(message)

}