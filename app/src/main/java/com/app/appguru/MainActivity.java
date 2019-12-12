package com.app.appguru;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.app.appguru.Fragment.FragmentHome;
import com.app.appguru.Fragment.FragmentProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    FrameLayout frame;
    BottomNavigationView bottomNavigationView;
    private long bakPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frame = findViewById(R.id.frameKu);
        bottomNavigationView = findViewById(R.id.bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        loadFragment (new FragmentHome());

    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameKu, fragment)
                    .commit();

            return true;
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        if (bakPressedTime + 2000 > System.currentTimeMillis()){
            tekanLagi();

            return;
        }else {
            Toast.makeText(getApplicationContext(), "Tekan Sekali Lagi Untuk Keluar",Toast.LENGTH_SHORT).show();
        }

        bakPressedTime = System.currentTimeMillis();
    }

    private void tekanLagi(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int userId = menuItem.getItemId();
        Fragment f = null;

        switch (userId){

            case R.id.home:
                f = new FragmentHome();
                break;


            case R.id.profile:
                f = new FragmentProfile();
                break;
        }
        return loadFragment(f);
    }
}
