package br.com.kelvinsantiago.controller;


import br.com.kelvinsantiago.dao.UsuarioDAO;
import br.com.kelvinsantiago.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioDAO usuarioDAO;

    @Autowired
    public UsuarioController (UsuarioDAO usuarioDAO){
        this.usuarioDAO = usuarioDAO;
    }


    @RequestMapping(value = "/{name}", produces = "application/json; charset=UTF-8")
    public @ResponseBody String digaBoasVindas(@PathVariable String name) {
        String result = "O serviço " + name + " não existe";
        return result;
    }


    @RequestMapping(value = "listausuarios", produces = "application/json; charset=UTF-8")
    public @ResponseBody List<Usuario> getListaUsuarios() {
        List<Usuario> usuarioslista = new ArrayList<Usuario>();

        usuarioslista = usuarioDAO.getTodosUsuarios();

        return usuarioslista;
    }

    @RequestMapping(value = "/painel")
    public ModelAndView initUsuario(){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("listadeusuarios", usuarioDAO.getTodosUsuarios());
        return mv;
    }

    @RequestMapping(value = "/")
    public String initIndex(){
        return "index";
    }

    @RequestMapping(value = "adicionar")
    public String adicionarUsuario(@ModelAttribute Usuario usuario){
        usuarioDAO.adicionar(usuario);
        return "forward:/usuario/painel";
    }

    @RequestMapping(value = "remover")
    public String removerUsuario(@ModelAttribute Usuario usuario){
        usuarioDAO.remover(usuario);
        return "forward:/usuario/painel";
    }

}
