package devandroid.rafael.myapplication.controller;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;

import devandroid.rafael.myapplication.model.Pessoa;
import devandroid.rafael.myapplication.view.MainActivity;


public class PessoaController {
    SharedPreferences.Editor listavip;
    Pessoa pessoa;
    public PessoaController(SharedPreferences.Editor shared, Pessoa pessoa){
        this.listavip = shared;
        this.pessoa = pessoa;
    }

    public void enviar(EditText nome, EditText sobrenome, EditText curso,EditText telefone){
        if(nome.getText().toString().isEmpty()){
            nome.setError("Digite seu nome");
        }
        if(sobrenome.getText().toString().isEmpty()){
            sobrenome.setError("Digite seu sobrenome");
        }
        if(curso.getText().toString().isEmpty()){
            curso.setError("Digite seu curso");
        }

        if(telefone.getText().toString().isEmpty()){
            telefone.setError("Digite seu número");
        }
        else if(telefone.getText().toString().length()<11){
            telefone.setError("Digite um número válido de 11 caracteres: ");
        }

        pessoa.setPrimeiroNome(nome.getText().toString());
        pessoa.setSobreNome(sobrenome.getText().toString());
        pessoa.setCursoDescricao(curso.getText().toString());
        pessoa.setTelefoneContato(telefone.getText().toString());

        listavip.putString("primeiroNome", pessoa.getPrimeiroNome());
        listavip.putString("sobrenome", pessoa.getSobreNome());
        listavip.putString("nomeCurso", pessoa.getCursoDescricao());
        listavip.putString("telefone", pessoa.getTelefoneContato());

        listavip.apply();

    }
    public void limpar(EditText nome, EditText sobrenome, EditText curso, EditText telefone){
        nome.setText("");
        sobrenome.setText("");
        curso.setText("");
        telefone.setText("");

        this.listavip.remove("primeiroNome");
        this.listavip.remove("sobrenome");
        this.listavip.remove("nomeCurso");
        this.listavip.remove("telefone");

        this.listavip.apply();

    }
    public void salvar(EditText nome, EditText sobrenome, EditText curso, EditText telefone){
        pessoa.setPrimeiroNome(nome.getText().toString());
        pessoa.setSobreNome(sobrenome.getText().toString());
        pessoa.setCursoDescricao(curso.getText().toString());
        pessoa.setTelefoneContato(telefone.getText().toString());

        listavip.putString("primeiroNome", pessoa.getPrimeiroNome());
        listavip.putString("sobrenome", pessoa.getSobreNome());
        listavip.putString("nomeCurso", pessoa.getCursoDescricao());
        listavip.putString("telefone", pessoa.getTelefoneContato());

        listavip.apply();
    }
}
