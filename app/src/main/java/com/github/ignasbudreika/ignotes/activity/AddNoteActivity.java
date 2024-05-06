package com.github.ignasbudreika.ignotes.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.ignasbudreika.ignotes.MainActivity;
import com.github.ignasbudreika.ignotes.R;
import com.github.ignasbudreika.ignotes.data.NoteRepository;
import com.github.ignasbudreika.ignotes.model.Note;

public class AddNoteActivity extends AppCompatActivity {

    private NoteRepository noteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        this.noteRepository = NoteRepository.getNoteRepository(AddNoteActivity.this);

        EditText nameInput = findViewById(R.id.name_input);
        EditText contentInput = findViewById(R.id.content_input);
        Button cancelCreateButton = findViewById(R.id.cancel_create_button);
        Button createButton = findViewById(R.id.create_button);

        cancelCreateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddNoteActivity.this, MainActivity.class));
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (nameInput.getText() == null || nameInput.getText().toString().trim().isEmpty()) {
                    Toast info = Toast.makeText(v.getContext(),
                            getResources().getText(R.string.name_required),
                            Toast.LENGTH_SHORT);
                    info.show();

                    return;
                }
                if (contentInput.getText() == null || contentInput.getText().toString().trim().isEmpty()) {
                    Toast info = Toast.makeText(v.getContext(),
                            getResources().getText(R.string.content_required),
                            Toast.LENGTH_SHORT);
                    info.show();

                    return;
                }

                noteRepository.addNote(AddNoteActivity.this, new Note(nameInput.getText().toString(), contentInput.getText().toString()));

                Toast info = Toast.makeText(v.getContext(),
                        getResources().getText(R.string.created),
                        Toast.LENGTH_SHORT);
                info.show();

                startActivity(new Intent(AddNoteActivity.this, MainActivity.class));
            }
        });
    }
}