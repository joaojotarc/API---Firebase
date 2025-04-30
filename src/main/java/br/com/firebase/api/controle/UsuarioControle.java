package br.com.firebase.api.controle;

import br.com.firebase.api.dao.Usuario;
import br.com.firebase.api.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioControle {

    @Autowired
    private UsuarioRepositorio repositorio;

    // Cria um usuário e retorna um JSON contendo o id gerado.
    @PostMapping
    public ResponseEntity<Map<String, String>> criarUsuario(@RequestBody Usuario usuario) {
        String id = repositorio.salvar(usuario);
        Map<String, String> response = new HashMap<>();
        response.put("id", id);
        return ResponseEntity.ok(response);
    }

    // Lista todos os usuários.
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = repositorio.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    // Busca um usuário pelo id.
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable String id) {
        Usuario usuario = repositorio.buscarPorId(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    // Atualiza o usuário e retorna uma mensagem de sucesso em JSON.
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> atualizarUsuario(@PathVariable String id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        repositorio.atualizar(usuario);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuário atualizado com sucesso");
        return ResponseEntity.ok(response);
    }

    // Deleta o usuário e retorna uma mensagem de sucesso em JSON.
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletarUsuario(@PathVariable String id) {
        repositorio.deletar(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuário deletado com sucesso");
        return ResponseEntity.ok(response);
    }
}
