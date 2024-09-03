package com.zetalabs.mood.app.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zetalabs.mood.app.model.Mood;
import com.zetalabs.mood.app.model.Usuario;
import com.zetalabs.mood.app.model.DTO.MoodWithUserDTO;
import com.zetalabs.mood.app.repository.MoodRepository;
import com.zetalabs.mood.app.repository.UsuarioRepository;

@Service
public class MoodService {
	
	@Autowired
	private MoodRepository moodRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository; 
	 
	 
	public List<Mood> listarTodos(){
		return moodRepository.findAll();
		
	}
	
	public Mood salvar(Mood mood) {
		
		return moodRepository.save(mood);
		
	}
	
   public List<Mood> getMoodsByUserId(Long userId) {
	        List<Mood> moods = moodRepository.findByUserId(userId);
	        return moods.stream().collect(Collectors.toList());
	    }
   
   public List<MoodWithUserDTO> listarTodosJoinNomesDosUsuarios() {
       List<Mood> moods = moodRepository.findAll();
       return moods.stream()
           .map(mood -> {
               Usuario usuario = usuarioRepository.findById(mood.getUserId()).orElse(null);
               String name = usuario != null ? usuario.getName() : "Usuário Desconhecido";
               return new MoodWithUserDTO(mood, name);
           })
           .collect(Collectors.toList());
   }
	
	
}

