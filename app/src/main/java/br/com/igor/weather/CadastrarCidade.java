package br.com.igor.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.orm.SugarContext;

import br.com.igor.weather.model.Cidade;

public class CadastrarCidade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cidade);
        SugarContext.init(this);
    }

    public void CadastrarCidade(View view){
        EditText editTextCidade = (EditText) findViewById(R.id.editTextCadastrarCidade);
        String nomeCidade = editTextCidade.getText().toString();

        Cidade cidade = new Cidade(nomeCidade, null,null, null, null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
        cidade.save();

        Toast.makeText(this,nomeCidade,Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
