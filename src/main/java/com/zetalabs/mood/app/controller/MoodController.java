package com.zetalabs.mood.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zetalabs.mood.app.model.Mood;
import com.zetalabs.mood.app.model.DTO.MoodWithUserDTO;
import com.zetalabs.mood.app.service.MoodService;

@RestController
@RequestMapping("/api/mood")
public class MoodController {
	
	@Autowired
	private MoodService moodService;
	
	@GetMapping
	public ResponseEntity<List<Mood>> listarTodos(){
		List<Mood> moodList = moodService.listarTodos();
		return ResponseEntity.status(HttpStatus.OK).body(moodList);
	}
	
	
	@GetMapping("/friends")
	public ResponseEntity<List<MoodWithUserDTO>> listarTodosComNomesDosUsuarios(){
		List<MoodWithUserDTO> moodList = moodService.listarTodosJoinNomesDosUsuarios();
		return ResponseEntity.status(HttpStatus.OK).body(moodList);
	}
	
	
	
	@PostMapping
	public ResponseEntity<Mood> salvar(@RequestBody Mood mood){
		Mood novoMood = moodService.salvar(mood);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoMood);
	}
	
	@GetMapping("/user/{userId}")
    public ResponseEntity<List<Mood>> getMoodsByUserId(@PathVariable Long userId) {
        List<Mood> moods = moodService.getMoodsByUserId(userId);
        return ResponseEntity.ok(moods);
    }
	

}
