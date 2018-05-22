package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Note;


@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{

}