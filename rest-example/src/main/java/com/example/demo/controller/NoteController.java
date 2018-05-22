package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Note;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.NoteRepository;

import io.swagger.annotations.Api;

@RestController
@Api(value="MrIDController")
public class NoteController {

	@Autowired
	NoteRepository noteRepository;
	
	//get all notes
	@GetMapping("/notes")
	public List<Note> getAllNotes(){
		return noteRepository.findAll();
	}
	
	//create new note
	@PostMapping("/notes")
	public Note createNote(@Valid @RequestBody Note note) {
		return noteRepository.save(note); 
	}
	
	//get a single note
	@GetMapping("/notes/{id}")
	public Note getNoteById(@PathVariable(value = "id") Long id) {
		return noteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id",id));
		
	}
	
	//update a note
	@PutMapping("/notes/{id}")
	public Note updateNote(@PathVariable(value="id") Long id, @Valid @RequestBody Note noteDetailes) {
		Note note = noteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
		
		note.setTitle(noteDetailes.getTitle());
		note.setContent(noteDetailes.getContent());
		return updateNote(id, noteDetailes);
		
		
	}
	//delete a  note
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<Note> deleteNote(@PathVariable(value = "id") Long id){
		Note note = noteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
		noteRepository.delete(note);
		
		
		return ResponseEntity.ok().build();
	}
	
	
}
