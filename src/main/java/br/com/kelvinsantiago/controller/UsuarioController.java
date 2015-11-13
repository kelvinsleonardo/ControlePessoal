package br.com.kelvinsantiago.controller;

import br.com.kelvinsantiago.dao.UsuarioDAO;
import br.com.kelvinsantiago.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDAO usuarioDAO;

    /**
     * Responsável por retornar JSON com todos os usuarios.
     * @author Kélvin Santiago
     * @return ResponseEntity<Usuario>
     */
    @RequestMapping(value = "/usuario", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Usuario>> getTodosUsuarios() {

        List<Usuario> usuarios = usuarioDAO.getTodosUsuarios();

        if(usuarios.isEmpty()){
            return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

    /**
     * Responsável por retornar JSON, de acordo com o cpf informado.
     * @author Kélvin Santiago
     * @param cpf
     * @return ResponseEntity<Usuario>
     */
    @RequestMapping(value = "/usuario/{cpf}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> getUsuario(@PathVariable("cpf") String cpf) {

        System.out.println("Procurando usuario com CPF: " + cpf);

        Usuario usuario = usuarioDAO.buscarPeloCPF(cpf);

        if (usuario == null) {
            System.out.println("Usuario com CPF:  " + cpf + " não encontrado.");
            return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    /**
     * Responsável por adicionar um novo usuario JSON.
     * @author Kélvin Santiago
     * @param usuario
     * @return ResponseEntity<Usuario>
     */
    @RequestMapping(value = "/usuario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> adicionarUsuario(@RequestBody Usuario usuario, UriComponentsBuilder ucBuilder) {
        System.out.println("Criando usuario: " + usuario.getNome());

        if (usuarioDAO.buscarPeloCPF(usuario.getCpf()) != null) {
            System.out.println("O CPF " + usuario.getCpf() + " já está cadastrado.");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        usuarioDAO.adicionar(usuario);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/usuario/{cpf}").buildAndExpand(usuario.getCpf()).toUri());
        System.out.println(headers);
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /*
     *  Atualizando usuario cadastrado
     */
    @RequestMapping(value = "/usuario/{cpf}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable("cpf") String cpf, @RequestBody Usuario usuario) {
        System.out.println("Atualizando Usuario de CPF " + cpf);

        Usuario usuarioPesquisado = usuarioDAO.buscarPeloCPF(cpf);

        if (usuarioPesquisado == null) {
            System.out.println("Usuario com CPF: " + cpf + " não encontrado");
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }
        usuario.setId(usuarioPesquisado.getId());
        usuarioDAO.editar(usuario);
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    /*
     * Removendo usuario
     */
    @RequestMapping(value = "/usuario/{cpf}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> deletarUsuario(@PathVariable("cpf") String cpf) {
        System.out.println("Procurando usuario com CPF: " + cpf);

        Usuario usuario = usuarioDAO.buscarPeloCPF(cpf);
        if (usuario == null) {
            System.out.println("Usuario com CPF " + cpf + " não encontrado");
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }

        usuarioDAO.remover(usuario.getId());
        return new ResponseEntity<Usuario>(HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String initIndex(){
        return  "Buscar Todos - GET /usuario <br>" +
                "Buscar Especifico - GET /usuario/{cpf}<br>" +
                "Adicionar Novo - POST /usuario/<br>" +
                "Atualizar - PUT /usuario/{cpf}<br>" +
                "Deletar - DELETE /usuario/{cpf}<br>";
    }

}
