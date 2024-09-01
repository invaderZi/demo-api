package com.zetalabs.mood.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zetalabs.mood.app.model.Mood;
import com.zetalabs.mood.app.repository.MoodRepository;

@Service
public class MoodService {
	
	@Autowired
	private MoodRepository moodRepository;
	
	public List<Mood> listarTodos(){
		return moodRepository.findAll();
		
	}
	
	public Mood salvar(Mood mood) {
		
		return moodRepository.save(mood);
		
	}
	
	
}

