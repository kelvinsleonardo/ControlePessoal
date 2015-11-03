package br.com.kelvinsantiago.model;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by labtime on 29/10/15.
 */
public class Teste {

    public static void main(String args[]){

        ArrayList<Usuario> listuser = new ArrayList<Usuario>();

        Usuario usuario = new Usuario();
        usuario.setCpf(123);
        usuario.setNome("Kelvin");
        Gson gson = new Gson();

        Usuario usuario2 = new Usuario();
        usuario2.setCpf(123456);
        usuario2.setNome("Rhuan Pablo");

        listuser.add(usuario);
        listuser.add(usuario2);

        String json = gson.toJson(listuser);
        System.out.println(json);

    }

}
