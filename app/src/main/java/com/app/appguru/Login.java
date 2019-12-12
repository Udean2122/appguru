package com.app.appguru;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.appguru.Constant.Constant;
import com.app.appguru.Session.SessionManager;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText username, password;
    String Username, Password;
    Button masuk;
    ProgressBar progressBar;
    ImageView lihatPassword, hidePassword;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.sandi);
        lihatPassword = findViewById(R.id.lihatPasswordKu);
        hidePassword = findViewById(R.id.hidePasswordKu);
        masuk = findViewById(R.id.login);

        lihatPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                lihatPassword.setVisibility(View.GONE);
                hidePassword.setVisibility(View.VISIBLE);
            }
        });

        hidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                lihatPassword.setVisibility(View.VISIBLE);
                hidePassword.setVisibility(View.GONE);
            }
        });

        sessionManager = new SessionManager(Login.this);

        if (sessionManager.isLogged()){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            preferences = PreferenceManager.getDefaultSharedPreferences(Login.this);
            String usernameKu = preferences.getString("username", "");
            intent.putExtra("username", usernameKu);
            startActivity(intent);

        }

        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Username = username.getText().toString().trim();
                Password = password.getText().toString().trim();

                if (Username.isEmpty() || Password.isEmpty()){
                    username.setError("Username Harus Diisi");
                    password.setError("Password Harus Diisi");
                }else if (!Username.isEmpty() && !Password.isEmpty()){
                    loginProses(Username, Password);
                }

            }
        });
    }

    private void loginProses(final String e, final String s){
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.AKUN_GURU, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    String success = response;
                    String data = " Data Matched ";

                    if (success.equals(data.trim())){
                        Toast.makeText(Login.this, "Hai \nusername : "+e+"", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("username", e);
                        preferences = PreferenceManager.getDefaultSharedPreferences(Login.this);
                        editor = preferences.edit();
                        editor.putString("username", e);
                        editor.apply();
                        sessionManager.checkLogin(true);
                        startActivity(intent);
                    }else {
                        Toast.makeText(Login.this, "Username atau Password salah", Toast.LENGTH_LONG).show();
                        masuk.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Gagal Login", Toast.LENGTH_LONG).show();
                    masuk.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, "Gagal Register", Toast.LENGTH_SHORT).show();
                masuk.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        }){
            @Override
            protected Map<String, String> getParams(){
               Map<String, String> params = new HashMap<>();
               params.put("username", e);
               params.put("password", s);
               return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
