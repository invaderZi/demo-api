package com.zetalabs.mood.app.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zetalabs.mood.app.exception.usuario.EmailJaExistenteException;
import com.zetalabs.mood.app.exception.usuario.UsernameJaExistenteException;
import com.zetalabs.mood.app.exception.usuario.UsuarioNaoEncontradoException;
import com.zetalabs.mood.app.model.Usuario;
import com.zetalabs.mood.app.repository.UsuarioRepository;
import com.zetalabs.mood.app.service.UsuarioService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {


    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;
    
    private LocalDateTime timestamp = LocalDateTime.now();
	
    
    ////////////////////// listartodos()
    
    @Test
    public void testListarTodos() {
    	
        // Arrange
    	
        Usuario usuario1 = new Usuario("fulano silva",  "fulano",  "fulano@mail.com", timestamp, timestamp );
        Usuario usuario2 = new Usuario("cicrano silva",  "cicrano",  "cicrano@mail.com", timestamp, timestamp );
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));

        // Act
        List<Usuario> usuarios = usuarioService.listarTodos();

        // Assert
        assertEquals(2, usuarios.size());
        assertEquals("fulano silva", usuarios.get(0).getName());
        assertEquals("cicrano silva", usuarios.get(1).getName());
    }
    
    /////////////////////// salvar()
    
    @Test
    public void testSalvar_UsuarioNaoExiste() {
        // Arrange
        Usuario usuario = new Usuario("fulano silva",  "fulano",  "fulano@mail.com", timestamp, timestamp );
        when(usuarioRepository.findByEmail("fulano@mail.com")).thenReturn(Optional.empty());
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // Act
        Usuario savedUsuario = usuarioService.salvar(usuario);

        // Assert
        assertEquals("fulano silva", savedUsuario.getName());
        verify(usuarioRepository).save(usuario);
    }
    
    @Test
    public void testSalvar_EmailJaExistente() {
        // Arrange
        Usuario usuario = new Usuario("fulano silva",  "fulano",  "fulano@mail.com", timestamp, timestamp );
        when(usuarioRepository.findByEmail("fulano@mail.com")).thenReturn(Optional.of(usuario));

        // Act & Assert
        assertThrows(EmailJaExistenteException.class, () -> usuarioService.salvar(usuario));
    }
    
    @Test
    public void testSalvar_UsernameJaExistente() {
        // Arrange
        Usuario usuario = new Usuario("fulano silva",  "fulano",  "fulano@mail.com", timestamp, timestamp );
        when(usuarioRepository.findByUsername("fulano")).thenReturn(Optional.of(usuario));

        // Act & Assert
        assertThrows(UsernameJaExistenteException.class, () -> usuarioService.salvar(usuario));
    }
    
    
    ///////////////////////// atualizar()
    
    
    @Test
    public void testAtualizar_UsuarioNaoEncontrado() {
        // Arrange
        Long id = 1L;
        Usuario usuarioAtualizado = new Usuario("fulano silva",  "fulano",  "fulano@mail.com", timestamp, timestamp );
        usuarioAtualizado.setId(id);

        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsuarioNaoEncontradoException.class, () -> usuarioService.atualizar(id, usuarioAtualizado));
    }
    
    @Test
    public void testAtualizar_EmailJaExistente() {
        // Arrange
    	
        Long id = 1L;
        Usuario usuarioExistente = new Usuario("fulano silva",  "fulano",  "fulano@mail.com", timestamp, timestamp );
        usuarioExistente.setId(id);

        Usuario usuarioAtualizado = new Usuario("fulano silva",  "fulano",  "emailjaexistente@mail.com", timestamp, timestamp );
        usuarioAtualizado.setId(id);

        Usuario usuarioComEmailExistente =new Usuario("cicrano silva",  "cicrano",  "emailjaexistente@mail.com", timestamp, timestamp );
        usuarioComEmailExistente.setId(2L); // Diferente do ID do usuário que está sendo atualizado

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioRepository.findByEmail("emailjaexistente@mail.com")).thenReturn(Optional.of(usuarioComEmailExistente));

        // Act & Assert
        assertThrows(EmailJaExistenteException.class, () -> usuarioService.atualizar(id, usuarioAtualizado));
    }
    
    @Test
    public void testAtualizar_UsernameJaExistente() {
        // Arrange
    	
        Long id = 1L;
        Usuario usuarioExistente = new Usuario("fulano silva",  "fulano",  "fulano@mail.com", timestamp, timestamp );
        usuarioExistente.setId(id);

        Usuario usuarioAtualizado = new Usuario("fulano silva",  "usernameexistente",  "fulano@mail.com", timestamp, timestamp );
        usuarioAtualizado.setId(id);

        Usuario usuarioComUsernameExistente =new Usuario("cicrano silva",  "usernameexistente",  "cicrano@mail.com", timestamp, timestamp );
        usuarioComUsernameExistente.setId(2L); // Diferente do ID do usuário que está sendo atualizado

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioRepository.findByUsername("usernameexistente")).thenReturn(Optional.of(usuarioComUsernameExistente));

        // Act & Assert
        assertThrows(UsernameJaExistenteException.class, () -> usuarioService.atualizar(id, usuarioAtualizado));
    }

    
    @Test
    public void testAtualizar_UsuarioExistente() {
        // Arrange
        Long id = 1L;
        Usuario usuarioExistente = new Usuario("fulano silva",  "fulano",  "fulano@mail.com", timestamp, timestamp );
        usuarioExistente.setId(id);
        
        Usuario usuarioAtualizado = new Usuario("fulano silva",  "fulanoEditado",  "fulanoEditado@mail.com", timestamp, timestamp );
        usuarioAtualizado.setId(id);

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioRepository.findByEmail("fulanoEditado@mail.com")).thenReturn(Optional.empty());
        when(usuarioRepository.save(usuarioExistente)).thenReturn(usuarioAtualizado);

        // Act
        Usuario atualizado = usuarioService.atualizar(id, usuarioAtualizado);

        // Assert
        assertEquals("fulano silva", atualizado.getName());
        assertEquals("fulanoEditado@mail.com", atualizado.getEmail());
        verify(usuarioRepository).save(usuarioExistente);
    }



    
    ////////////////////////buscarporid()

    @Test
    public void testGetUsuarioById_UsuarioExists() {
        // Arrange
        Usuario usuario = new Usuario("fulano silva",  "fulano",  "fulano@mail.com", timestamp, timestamp );
        usuario.setId(1L);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        // Act
        Usuario foundUsuario = usuarioService.buscarPorId(1L);

        // Assert
        assertEquals("fulano silva", foundUsuario.getName());
        assertEquals("fulano@mail.com", foundUsuario.getEmail());
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
