package com.app.appguru;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Register extends AppCompatActivity {


    Spinner jenisKelamin1, latarBelakangPendidikanKu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        jenisKelamin1 = findViewById(R.id.JenisKelamin);
        latarBelakangPendidikanKu = findViewById(R.id.latarPendidikanKu);





        String[] jenisKelamin = new String[]{
                "Laki Laki",
                "Perempuan"
        };

        String[] latarBalakangPendidikan = new String[]{
                "D1",
                "D2",
                "D3",
                "D4",
                "S1",
                "S2",
                "S3"
        };

        final List<String> jenisKelaminList = new ArrayList<>(Arrays.asList(jenisKelamin));
        final List<String> latarBelakangPendidikanList = new ArrayList<>(Arrays.asList(latarBalakangPendidikan));

        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinnel_jenis_kelamin, jenisKelaminList){
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                return view;
            }
        };

        final ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(this, R.layout.spinnel_latar_pendidkan, latarBelakangPendidikanList){
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                return view;
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinnel_jenis_kelamin);
        spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinnel_latar_pendidkan);
        jenisKelamin1.setAdapter(spinnerArrayAdapter);

        jenisKelamin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        latarBelakangPendidikanKu.setAdapter(spinnerArrayAdapter2);

        latarBelakangPendidikanKu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
