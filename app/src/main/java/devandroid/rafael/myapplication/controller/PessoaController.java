package devandroid.rafael.myapplication.controller;

import android.util.Log;

import devandroid.rafael.myapplication.model.Pessoa;

public class PessoaController {
    
    public void save(Pessoa pessoa){
        Log.i("MVCController", "Salvo"+pessoa.toString());
    }
}
