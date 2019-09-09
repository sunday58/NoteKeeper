package com.example.android.notekeeper;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataManagerTest {

   private static DataManager sDataManager;

    @BeforeClass
    public static void classSetUp() throws Exception{
        sDataManager = DataManager.getInstance();
    }
    @Before
    public void setUp() throws  Exception{
        sDataManager.getNotes().clear();
       sDataManager.initializeExampleNotes();
    }

    @Test
    public void createNewNote() throws Exception{
        DataManager dm = DataManager.getInstance();
        final CourseInfo course = dm.getCourse("android_async");
       final String noteTitle = "Text note Title";
       final String noteText = "this is the body test of my test note";

       int noteIndex = dm.createNewNote();
       NoteInfo newNote = dm.getNotes().get(noteIndex);
       newNote.setCourse(course);
       newNote.setTitle(noteTitle);
       newNote.setText(noteText);

       NoteInfo compareNote = dm.getNotes().get(noteIndex);
      assertEquals(compareNote.getCourse(), course);
      assertEquals(compareNote.getTitle(), noteTitle);
      assertEquals(compareNote.getText(), noteText);
    }

    @Test
    public void findSimilarNotes() throws Exception{
        final DataManager dm = DataManager.getInstance();
        final CourseInfo course = dm.getCourse("android_async");
        final String noteTitle = "Text note Title";
        final String noteText1 = "this is the body test of my test note";
        final String noteText2 = "this is the body test of my second test note";

        int noteIndex1 = dm.createNewNote();
        NoteInfo newNote1 = dm.getNotes().get(noteIndex1);
        newNote1.setCourse(course);
        newNote1.setTitle(noteTitle);
        newNote1.setText(noteText1);

        int noteIndex2 = dm.createNewNote();
        NoteInfo newNote2 = dm.getNotes().get(noteIndex2);
        newNote2.setCourse(course);
        newNote2.setTitle(noteTitle);
        newNote2.setText(noteText2);

        int foundIndex1 = dm.findNote(newNote1);
        assertEquals(noteIndex1, foundIndex1);

        int foundIndex2 = dm.findNote(newNote2);
        assertEquals(noteIndex2, foundIndex2);
    }

    @Test
    public void createNewNoteOneStepCreation(){
        final CourseInfo course = sDataManager.getCourse("android_async");
        final String noteTitle = "Test note title";
        final String noteText = "this is the body of my test note";

        int noteIndex = sDataManager.createNewNote(course, noteTitle, noteText);

        NoteInfo compareNote = sDataManager.getNotes().get(noteIndex);
        assertEquals(compareNote.getCourse(), course);
        assertEquals(compareNote.getTitle(), noteTitle);
        assertEquals(compareNote.getText(), noteText);
    }
}