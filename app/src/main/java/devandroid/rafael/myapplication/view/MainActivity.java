package devandroid.rafael.myapplication.view;

//Clases do import sáo as que o ActivityMain precisa
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.internal.TextWatcherAdapter;

import devandroid.rafael.myapplication.R;
import devandroid.rafael.myapplication.controller.PessoaController;
import devandroid.rafael.myapplication.model.Curso;
import devandroid.rafael.myapplication.model.Pessoa;

public class MainActivity extends AppCompatActivity {

    Curso curso;
    Pessoa pessoa;

    PessoaController controller;

    SharedPreferences preferences;
    public static final String NOME_PREFERENCES = "pref_listavip";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new PessoaController();
        preferences = getSharedPreferences(NOME_PREFERENCES, 0);
        SharedPreferences.Editor listaVip = preferences.edit();

        curso = new Curso();
        pessoa = new Pessoa();

        pessoa.setPrimeiroNome(preferences.getString("primeiroNome", ""));
        pessoa.setSobreNome(preferences.getString("sobrenome", ""));
        pessoa.setCursoDescricao(preferences.getString("nomeCurso", ""));
        pessoa.setTelefoneContato(preferences.getString("telefone", ""));


        EditText editTextNome;
        EditText editTextSobrenome;
        EditText editTextCurso;
        EditText editTextTelefone;

        Button buttonSalvar;
        Button buttonLimpar;
        Button buttonEnviar;

        editTextNome = findViewById(R.id.editTextNome);
        editTextSobrenome = findViewById(R.id.editTextSobreNome);
        editTextCurso = findViewById(R.id.editTextCurso);
        editTextTelefone = findViewById(R.id.editTextTelefone);

        editTextNome.setText(pessoa.getPrimeiroNome());
        editTextSobrenome.setText(pessoa.getSobreNome());
        editTextCurso.setText(pessoa.getCursoDescricao());
        editTextTelefone.setText(pessoa.getTelefoneContato());

        buttonSalvar = findViewById(R.id.buttonSalvar);
        buttonLimpar = findViewById(R.id.buttonLimpar);
        buttonEnviar = findViewById(R.id.buttonEnviar);


        TextWatcher textWatcher = (new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editTextNome.getText().toString().length()>0 && editTextSobrenome.getText().toString().length()>0 && editTextCurso.getText().toString().length()>0  && editTextTelefone.getText().toString().length()>0){
                    buttonEnviar.setEnabled(true);
                }
                else{
                    buttonEnviar.setEnabled(false);
                }
            }
        });

        editTextNome.addTextChangedListener(textWatcher);
        editTextSobrenome.addTextChangedListener(textWatcher);
        editTextCurso.addTextChangedListener(textWatcher);
        editTextTelefone.addTextChangedListener(textWatcher);

        buttonLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextNome.setText("");
                editTextSobrenome.setText("");
                editTextCurso.setText("");
                editTextTelefone.setText("");

                listaVip.remove("primeiroNome");
                listaVip.remove("sobrenome");
                listaVip.remove("nomeCurso");
                listaVip.remove("telefone");

                listaVip.apply();
            }
        });

        buttonEnviar.setOnClickListener(view -> {
            if(editTextNome.getText().toString().isEmpty()){
                editTextNome.setError("Digite seu nome");
            }
            if(editTextSobrenome.getText().toString().isEmpty()){
                editTextSobrenome.setError("Digite seu sobrenome");
            }
            if(editTextCurso.getText().toString().isEmpty()){
                editTextCurso.setError("Digite seu curso");
            }

            if(editTextTelefone.getText().toString().isEmpty()){
                editTextTelefone.setError("Digite seu número");
            }
            else if(editTextTelefone.getText().toString().length()<11){
                editTextTelefone.setError("Digite um número válido de 11 caracteres: ");
            }

            pessoa.setPrimeiroNome(editTextNome.getText().toString());
            pessoa.setSobreNome(editTextSobrenome.getText().toString());
            pessoa.setCursoDescricao(editTextCurso.getText().toString());
            pessoa.setTelefoneContato(editTextTelefone.getText().toString());

            listaVip.putString("primeiroNome", pessoa.getPrimeiroNome());
            listaVip.putString("sobrenome", pessoa.getSobreNome());
            listaVip.putString("nomeCurso", pessoa.getCursoDescricao());
            listaVip.putString("telefone", pessoa.getTelefoneContato());

            listaVip.apply();

            Toast.makeText(MainActivity.this, "Enviado com Sucesso!!", Toast.LENGTH_SHORT).show();


        });

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pessoa.setPrimeiroNome(editTextNome.getText().toString());
                pessoa.setSobreNome(editTextSobrenome.getText().toString());
                pessoa.setCursoDescricao(editTextCurso.getText().toString());
                pessoa.setTelefoneContato(editTextTelefone.getText().toString());

                controller.save(pessoa);

                Toast.makeText(MainActivity.this, "Dados:"+pessoa.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        Log.i("POOAndroid",pessoa.toString());


    }
}