package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.EmailJaExistenteException;
import com.example.demo.exception.UsuarioNaoEncontradoException;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

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

        usuarioExistente.setName(usuarioAtualizado.getName());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setAdmin(usuarioAtualizado.isAdmin());

        return usuarioRepository.save(usuarioExistente);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
        		.orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    @Transactional
    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
