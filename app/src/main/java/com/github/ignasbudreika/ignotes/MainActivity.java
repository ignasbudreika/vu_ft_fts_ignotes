package com.github.ignasbudreika.ignotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.github.ignasbudreika.ignotes.activity.AddNoteActivity;
import com.github.ignasbudreika.ignotes.activity.DeleteNoteActivity;
import com.github.ignasbudreika.ignotes.data.NoteRepository;
import com.github.ignasbudreika.ignotes.model.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NoteRepository noteRepository = NoteRepository.getNoteRepository(MainActivity.this);

        ListView notesList = findViewById(R.id.notesList);
        Button createNavButton = findViewById(R.id.create_nav_button);
        Button deleteNavButton = findViewById(R.id.delete_nav_button);

        List<Note> notes = noteRepository.getNotes();
        List<String> notesDisplay = new ArrayList<>();
        for (Note note: notes) {
            notesDisplay.add(note.toDisplay());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notesDisplay);
        adapter.setNotifyOnChange(true);
        notesList.setAdapter(adapter);

        createNavButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
            }
        });

        deleteNavButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DeleteNoteActivity.class));
            }
        });
    }
}
