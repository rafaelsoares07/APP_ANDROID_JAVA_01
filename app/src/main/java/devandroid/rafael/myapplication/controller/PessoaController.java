package devandroid.rafael.myapplication.controller;

import android.util.Log;
import android.widget.EditText;

import devandroid.rafael.myapplication.model.Pessoa;

public class PessoaController {
    
    public void limpar(EditText nome, EditText sobrenome, EditText curso, EditText telefone){
        nome.setText("");
        sobrenome.setText("");
        curso.setText("");
        telefone.setText("");
    }
}
