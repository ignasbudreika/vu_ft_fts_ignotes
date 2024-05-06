package com.github.ignasbudreika.ignotes.data;

import android.content.Context;

import com.github.ignasbudreika.ignotes.model.Note;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class NoteRepository {

    private static NoteRepository noteRepository;

    private static final String NOTES_FILE_NAME = "notes.json";

    private List<Note> notes;

    public static NoteRepository getNoteRepository(Context context) {
        if (noteRepository == null) {
            noteRepository = new NoteRepository(context);
        }

        return noteRepository;
    }

    private NoteRepository(Context context) {
        this.notes = new ArrayList<>();

        try {
            FileInputStream fileIn = context.openFileInput(NOTES_FILE_NAME);
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[8];
            StringBuilder json = new StringBuilder();
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                json.append(readstring);
            }
            InputRead.close();

            this.notes = new Gson().fromJson(json.toString(), new TypeToken<ArrayList<Note>>(){}.getType());
        } catch (IOException ignored) {}
    }

    public List<Note> getNotes() {
        return this.notes;
    }

    public void addNote(Context context, Note note) {
        try {
            this.notes.add(note);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(NOTES_FILE_NAME, Context.MODE_PRIVATE));
            outputStreamWriter.write(new Gson().toJson(this.notes));
            outputStreamWriter.close();
        } catch (IOException ignored) {}
    }

    public void removeNote(Context context, String id) {
        int index = -1;

        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }

        if (index > -1) {
            try {
                this.notes.remove(index);

                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(NOTES_FILE_NAME, Context.MODE_PRIVATE));
                outputStreamWriter.write(new Gson().toJson(this.notes));
                outputStreamWriter.close();
            } catch (IOException ignored) {}
        }
    }
}
