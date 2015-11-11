package br.com.kelvinsantiago.controller;

import br.com.kelvinsantiago.dao.UsuarioDAO;
import br.com.kelvinsantiago.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    @RequestMapping(value = "/usuario", method = RequestMethod.GET)
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
    @RequestMapping(value = "/usuario/{cpf}", method = RequestMethod.GET)
    public ResponseEntity<Usuario> getUsuario(@PathVariable("cpf") long cpf) {

        System.out.println("Procurando usuario com CPF: " + cpf);

        Usuario usuario = usuarioDAO.buscarPelaMatricula(cpf);

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
     * @param ucBuilder
     * @return ResponseEntity<Usuario>
     */
    @RequestMapping(value = "/usuario", method = RequestMethod.POST)
    public ResponseEntity<Void> adicionarUsuario(@RequestBody Usuario usuario, UriComponentsBuilder ucBuilder) {

        System.out.println("Criando usuario: " + usuario.getNome());

        if (usuarioDAO.buscarPelaMatricula(usuario.getCpf()) != null) {
            System.out.println("O CPF " + usuario.getCpf() + " já está cadastrado.");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        usuarioDAO.adicionar(usuario);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/usuario/{cpf}").buildAndExpand(usuario.getCpf()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /**
     * Responsável por atualizar um usuario.
     * @author Kélvin Santiago
     * @param cpf
     * @param usuario
     * @return ResponseEntity<Usuario>
     */
    @RequestMapping(value = "/usuario/{cpf}", method = RequestMethod.PUT)
    public ResponseEntity<Usuario> updateUser(@PathVariable("cpf") long cpf, @RequestBody Usuario usuario) {

        System.out.println("Atualizando Usuario de CPF " + cpf);

        Usuario usuarioPesquisado = usuarioDAO.buscarPelaMatricula(cpf);

        if (usuarioPesquisado == null) {
            System.out.println("Usuario com CPF: " + cpf + " não encontrado");
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }
        usuarioDAO.editar(usuario);
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    /**
     * Responsável por atualizar um usuario.
     * @author Kélvin Santiago
     * @param cpf
     * @return ResponseEntity<Usuario>
     */
    @RequestMapping(value = "/usuario/{cpf}", method = RequestMethod.DELETE)
    public ResponseEntity<Usuario> deleteUser(@PathVariable("cpf") long cpf) {
        System.out.println("Procurando usuario com CPF: " + cpf);

        Usuario usuario = usuarioDAO.buscarPelaMatricula(cpf);
        if (usuario == null) {
            System.out.println("Usuario com CPF " + cpf + " não encontrado");
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }

        usuarioDAO.remover(cpf);
        return new ResponseEntity<Usuario>(HttpStatus.OK);
    }

    /**
     * Retorna Mensagem Methods.
     * @author Kélvin Santiago
     * @return String
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String initIndex(){
        return  "Buscar Todos - GET /usuario <br>" +
                "Buscar Especifico - GET /usuario/{cpf}<br>" +
                "Adicionar Novo - POST /usuario/<br>" +
                "Atualizar - PUT /usuario/{cpf}<br>" +
                "Deletar - DELETE /usuario/{cpf}<br>";
    }

}
