package com.zetalabs.mood.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zetalabs.mood.app.exception.usuario.EmailJaExistenteException;
import com.zetalabs.mood.app.exception.usuario.UsernameJaExistenteException;
import com.zetalabs.mood.app.exception.usuario.UsuarioNaoEncontradoByUsernameException;
import com.zetalabs.mood.app.exception.usuario.UsuarioNaoEncontradoException;
import com.zetalabs.mood.app.model.Usuario;
import com.zetalabs.mood.app.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
    	
    	 // Verifica se o e-mail já existe
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new EmailJaExistenteException(usuario.getEmail());
        }
        
        // Verifica se o username já existe
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new UsernameJaExistenteException(usuario.getUsername());
        }

        // Salva o usuário
        return usuarioRepository.save(usuario);
    }
 
    @Transactional
    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
        
     // Verificar se o novo e-mail já está em uso por outro usuário
        usuarioRepository.findByEmail(usuarioAtualizado.getEmail())
                .ifPresent(usuarioComEmail -> {
                    if (!usuarioComEmail.getId().equals(id)) {
                        throw new EmailJaExistenteException(usuarioAtualizado.getEmail());
                    }
                });
        
     // Verificar se o novo username já está em uso por outro usuário
        usuarioRepository.findByUsername(usuarioAtualizado.getUsername())
                .ifPresent(usuarioComUsername -> {
                    if (!usuarioComUsername.getId().equals(id)) {
                        throw new UsernameJaExistenteException(usuarioAtualizado.getUsername());
                    }
                });

        usuarioExistente.setName(usuarioAtualizado.getName());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setUsername(usuarioAtualizado.getUsername());

        return usuarioRepository.save(usuarioExistente);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
        		.orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }
    
    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username)
        		.orElseThrow(() -> new UsuarioNaoEncontradoByUsernameException(username));
    }

    @Transactional
    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
