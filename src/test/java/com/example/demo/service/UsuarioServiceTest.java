package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.exception.EmailJaExistenteException;
import com.example.demo.exception.UsuarioNaoEncontradoException;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {


    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;
    
    ////////////////////// listartodos()
    
    @Test
    public void testListarTodos() {
        // Arrange
        Usuario usuario1 = new Usuario(true, "John Doe", "john@example.com");
        Usuario usuario2 = new Usuario(false, "Jane Doe", "jane@example.com");
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));

        // Act
        List<Usuario> usuarios = usuarioService.listarTodos();

        // Assert
        assertEquals(2, usuarios.size());
        assertEquals("John Doe", usuarios.get(0).getName());
        assertEquals("Jane Doe", usuarios.get(1).getName());
    }
    
    /////////////////////// salvar()
    
    @Test
    public void testSalvar_UsuarioNaoExiste() {
        // Arrange
        Usuario usuario = new Usuario(true, "John Doe", "john@example.com");
        when(usuarioRepository.findByEmail("john@example.com")).thenReturn(Optional.empty());
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // Act
        Usuario savedUsuario = usuarioService.salvar(usuario);

        // Assert
        assertEquals("John Doe", savedUsuario.getName());
        verify(usuarioRepository).save(usuario);
    }
    
    @Test
    public void testSalvar_EmailJaExistente() {
        // Arrange
        Usuario usuario = new Usuario(true, "John Doe", "john@example.com");
        when(usuarioRepository.findByEmail("john@example.com")).thenReturn(Optional.of(usuario));

        // Act & Assert
        assertThrows(EmailJaExistenteException.class, () -> usuarioService.salvar(usuario));
    }
    
    ///////////////////////// atualizar()
    
    @Test
    public void testAtualizar_UsuarioExistente() {
        // Arrange
        Long id = 1L;
        Usuario usuarioExistente = new Usuario(true, "John Doe", "john@example.com");
        usuarioExistente.setId(id);
        
        Usuario usuarioAtualizado = new Usuario(false, "John Smith", "johnsmith@example.com");
        usuarioAtualizado.setId(id);

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioRepository.findByEmail("johnsmith@example.com")).thenReturn(Optional.empty());
        when(usuarioRepository.save(usuarioExistente)).thenReturn(usuarioAtualizado);

        // Act
        Usuario atualizado = usuarioService.atualizar(id, usuarioAtualizado);

        // Assert
        assertEquals("John Smith", atualizado.getName());
        assertEquals("johnsmith@example.com", atualizado.getEmail());
        assertEquals(false, atualizado.isAdmin());
        verify(usuarioRepository).save(usuarioExistente);
    }

    @Test
    public void testAtualizar_EmailJaExistente() {
        // Arrange
        Long id = 1L;
        Usuario usuarioExistente = new Usuario(true, "John Doe", "john@example.com");
        usuarioExistente.setId(id);

        Usuario usuarioAtualizado = new Usuario(false, "John Smith", "johnsmith@example.com");
        usuarioAtualizado.setId(id);

        Usuario usuarioComEmailExistente = new Usuario(true, "Jane Doe", "johnsmith@example.com");
        usuarioComEmailExistente.setId(2L); // Diferente do ID do usuário que está sendo atualizado

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioRepository.findByEmail("johnsmith@example.com")).thenReturn(Optional.of(usuarioComEmailExistente));

        // Act & Assert
        assertThrows(EmailJaExistenteException.class, () -> usuarioService.atualizar(id, usuarioAtualizado));
    }

    @Test
    public void testAtualizar_UsuarioNaoEncontrado() {
        // Arrange
        Long id = 1L;
        Usuario usuarioAtualizado = new Usuario(false, "John Smith", "johnsmith@example.com");
        usuarioAtualizado.setId(id);

        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsuarioNaoEncontradoException.class, () -> usuarioService.atualizar(id, usuarioAtualizado));
    }
    
    ////////////////////////buscarporid()

    @Test
    public void testGetUsuarioById_UsuarioExists() {
        // Arrange
        Usuario usuario = new Usuario(true, "John Doe", "john@example.com");
        usuario.setId(1L);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        // Act
        Usuario foundUsuario = usuarioService.buscarPorId(1L);

        // Assert
        assertEquals("John Doe", foundUsuario.getName());
        assertEquals("john@example.com", foundUsuario.getEmail());
        assertEquals(true, foundUsuario.isAdmin());
    }

    @Test
    public void testGetUsuarioById_UsuarioNotFound() {
        // Arrange
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsuarioNaoEncontradoException.class, () -> usuarioService.buscarPorId(1L));
    }
    
    //////////// deletar()
    @Test
    public void testDeletar() {
        // Arrange
        Long id = 1L;

        // Act
        usuarioService.deletar(id);

        // Assert
        verify(usuarioRepository).deleteById(id);
    }
}
