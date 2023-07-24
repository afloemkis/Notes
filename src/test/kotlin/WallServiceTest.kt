import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Test
    fun addNote_addFirstNote() {
        val addedNote = NoteService.addNote("Заметка 1", "Текст заметки 1")
        assertEquals(addedNote, 1)
    }

    @Test
    fun createComment_addFirstComment() {
        val addedNote = NoteService.addNote("Заметка 1", "Текст заметки 1")
        val createdComment = NoteService.createComment(addedNote, null,"Комментарий 1")
        assertTrue(createdComment != null)
    }

    @Test
    fun deleteNote_delete() {
        val addedNote = NoteService.addNote("Заметка 1", "Текст заметки 1")
        val result = NoteService.deleteNote(addedNote)
        assertEquals(result, 1)
    }

    @Test (expected = NoteService.NoteNotFoundException::class)
    fun deleteNote_throwException() {
        NoteService.deleteNote(25)
    }

    @Test
    fun deleteComment_existingComment() {
        val addedNote = NoteService.addNote("Заметка 1", "Текст заметки 1")
        val createdComment = NoteService.createComment(addedNote, null,"Комментарий 1")
        val result = NoteService.deleteComment(createdComment)
        assertEquals(result, 1)
    }

    @Test (expected = NoteService.CommentNotFoundException::class)
    fun deleteComment_ThrowException(){
        NoteService.deleteComment(9)
    }

    @Test
    fun editNote_edited() {
        val addedNote = NoteService.addNote("Заметка 1", "Текст заметки 1")
        val result = NoteService.editNote(addedNote, "Заметка 1", "Текст заметки 2")
        assertEquals(result,1)
    }

    @Test (expected = NoteService.NoteNotFoundException::class)
    fun editNote_ThrowException(){
        NoteService.editNote(9,"неважно", "неважно")
    }

    @Test
    fun editComment_edited() {
        val addedNote = NoteService.addNote("Заметка 1", "Текст заметки 1")
        val createdComment = NoteService.createComment(addedNote, null,"Комментарий 1")
        val result = NoteService.editComment(createdComment, "Комментарий 2")
        assertEquals(result,1)
    }

    @Test (expected = NoteService.CommentNotFoundException::class)
    fun editComment_ThrowNotFoundException(){
        NoteService.editComment(9,"неважно")
    }

    @Test (expected = NoteService.CommentDeletedException::class)
    fun editComment_ThrowDeletedException() {
        val addedNote = NoteService.addNote("Заметка 1", "Текст заметки 1")
        val createdComment = NoteService.createComment(addedNote, null,"Комментарий 1")
        NoteService.deleteComment(createdComment)
        val result = NoteService.editComment(createdComment, "неважно")
    }

    @Test
    fun testGetNotes() {
        NoteService.addNote("Заметка 1", "Текст заметки 1")
        NoteService.addNote("Заметка 2", "Текст заметки 2")

        val noteIds = arrayOf(1, 2)
        val result = NoteService.getNotes(noteIds, 2)
        assertEquals(2, result.size)
    }

    @Test
    fun testGetNoteByID() {
        val result = NoteService.addNote("Заметка 1", "Текст заметки 1")

        val retrievedNote = NoteService.getNoteByID(result)
        assertEquals(result, retrievedNote.id)
    }

    @Test (expected = NoteService.NoteNotFoundException::class)
    fun GetNoteByID_ThrowException(){
        NoteService.getNoteByID(77)
    }

    @Test
    fun testGetComments() {
        val addedNote = NoteService.addNote("Заметка 1", "Текст заметки 1")
        val comment1 = NoteService.createComment(addedNote, null,"Комментарий 1")
        val comment2 = NoteService.createComment(addedNote, null,"Комментарий 2")
        val comment3 = NoteService.createComment(addedNote, null,"Комментарий 3")

        val result = NoteService.getComments(addedNote)
        assertEquals(3, result.size)
    }

    @Test
    fun testRestoreComment() {
        val addedNote = NoteService.addNote("Заметка 1", "Текст заметки 1")
        val createdComment = NoteService.createComment(addedNote, null,"Комментарий 1")
        NoteService.deleteComment(createdComment)

        val result = NoteService.restoreComment(createdComment)
        assertEquals(result, 1)
    }

    @Test (expected = NoteService.CommentNotFoundException::class)
    fun restoreComment_ThrowException(){
        NoteService.restoreComment(9)
    }

}

