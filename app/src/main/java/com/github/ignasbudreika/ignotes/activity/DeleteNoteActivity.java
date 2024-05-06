package com.github.ignasbudreika.ignotes.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.github.ignasbudreika.ignotes.MainActivity;
import com.github.ignasbudreika.ignotes.R;
import com.github.ignasbudreika.ignotes.data.NoteRepository;
import com.github.ignasbudreika.ignotes.model.Note;

import java.util.List;

public class DeleteNoteActivity extends AppCompatActivity {

    private NoteRepository noteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);
        this.noteRepository = NoteRepository.getNoteRepository(DeleteNoteActivity.this);

        Spinner spinner = findViewById(R.id.note_select);
        Button cancelDeleteButton = findViewById(R.id.cancel_delete_button);
        Button deleteButton = findViewById(R.id.delete_button);

        List<Note> notes = noteRepository.getNotes();

        SpinnerAdapter adapter = new ArrayAdapter<Note>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, notes);
        spinner.setAdapter(adapter);

        cancelDeleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeleteNoteActivity.this, MainActivity.class));
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Note note = (Note) spinner.getSelectedItem();
                if (note == null) {
                    Toast info = Toast.makeText(v.getContext(),
                            getResources().getText(R.string.delete_not_available),
                            Toast.LENGTH_SHORT);
                    info.show();

                    startActivity(new Intent(DeleteNoteActivity.this, MainActivity.class));
                    return;
                }

                noteRepository.removeNote(DeleteNoteActivity.this, note.getId());

                Toast info = Toast.makeText(v.getContext(),
                        getResources().getText(R.string.deleted),
                        Toast.LENGTH_SHORT);
                info.show();

                startActivity(new Intent(DeleteNoteActivity.this, MainActivity.class));
            }
        });
    }
}