package devandroid.rafael.myapplication.view;

// Classes que estão sendo usadas nessa View
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
import devandroid.rafael.myapplication.utils.Util;

public class MainActivity extends AppCompatActivity {
    Pessoa pessoa;
    Util util;
    PessoaController controller;
    SharedPreferences preferences;
    public static final String NOME_PREFERENCES = "pref_listavip";
    int before;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(NOME_PREFERENCES, 0);

        SharedPreferences.Editor listaVip = preferences.edit();

        pessoa = new Pessoa();

        util = new Util();
        
        controller = new PessoaController(listaVip, pessoa);


        pessoa.setPrimeiroNome(preferences.getString("primeiroNome", ""));
        pessoa.setSobreNome(preferences.getString("sobrenome", ""));
        pessoa.setCursoDescricao(preferences.getString("nomeCurso", ""));
        pessoa.setTelefoneContato(preferences.getString("telefone", ""));


        EditText editTextNome = findViewById(R.id.editTextNome);;
        EditText editTextSobrenome = findViewById(R.id.editTextSobreNome);;
        EditText editTextCurso = findViewById(R.id.editTextCurso);;
        EditText editTextTelefone = findViewById(R.id.editTextTelefone);;

        Button buttonSalvar = findViewById(R.id.buttonSalvar);;
        Button buttonLimpar = findViewById(R.id.buttonLimpar);;
        Button buttonEnviar = findViewById(R.id.buttonEnviar);;


        editTextNome.setText(pessoa.getPrimeiroNome());
        editTextSobrenome.setText(pessoa.getSobreNome());
        editTextCurso.setText(pessoa.getCursoDescricao());
        editTextTelefone.setText(pessoa.getTelefoneContato());

        util.changeButtonStatus(buttonEnviar, editTextNome, editTextSobrenome, editTextCurso, editTextTelefone);


        TextWatcher textWatcher = (new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                before = editTextTelefone.getText().length();
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {

                util.changeButtonStatus(buttonEnviar, editTextNome, editTextSobrenome, editTextCurso, editTextTelefone);

                if (editTextTelefone.getText().hashCode() == editable.hashCode()) {

                    String phoneNumber = editTextTelefone.getText().toString();

                    String formattedNumber = phoneNumber;

                    editTextTelefone.removeTextChangedListener(this);

                    if (formattedNumber.length()==2 && before<formattedNumber.length()){

                        formattedNumber = "("+phoneNumber+") ";

                    } else if (formattedNumber.length()==10 && before<formattedNumber.length()) {

                        formattedNumber += "-";
                    }

                    editTextTelefone.setText(formattedNumber);

                    editTextTelefone.addTextChangedListener(this);

                    editTextTelefone.setSelection(formattedNumber.length());
                }

            }
        });

        editTextNome.addTextChangedListener(textWatcher);
        editTextSobrenome.addTextChangedListener(textWatcher);
        editTextCurso.addTextChangedListener(textWatcher);
        editTextTelefone.addTextChangedListener(textWatcher);

        buttonLimpar.setOnClickListener(view -> {

            controller.limpar(editTextNome, editTextSobrenome, editTextCurso, editTextTelefone);
            Toast.makeText(MainActivity.this, "Dados limpados!", Toast.LENGTH_SHORT).show();
        });

        buttonEnviar.setOnClickListener(view -> {

            controller.enviar(editTextNome,editTextSobrenome, editTextCurso, editTextTelefone);
            Toast.makeText(MainActivity.this, "Dados enviados com sucesso!", Toast.LENGTH_SHORT).show();
        });

        buttonSalvar.setOnClickListener(view ->  {

            controller.salvar(editTextNome, editTextSobrenome, editTextCurso, editTextTelefone);
            Toast.makeText(MainActivity.this, "Dados:"+pessoa.toString(), Toast.LENGTH_SHORT).show();
        });


    }
}