package com.gka.gkamobile;

import android.content.Intent;
import android.os.Bundle;
import com.gka.gkamobile.db.GkaDatabase;
import com.gka.gkamobile.ui.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        GkaDatabase database = GkaDatabase.getInstance(this);

        if(database.userDao().getUserList().size() <= 0){
            Intent intent = new Intent(this, LoginActivity.class);;
            startActivity(intent);
            finish();
        }

       /*
        User u = new User();
        u.setId((int)(Math.random()));
        u.setUsername("test");
        u.setPassword("test");
        u.setMobilePhone("8089");
        u.setFullName("test test");
        u.setEmail("e@te.com");
        database.userDao().insertUser(u);

        for (User user : database.userDao().getUserList()){
            Toast.makeText(getApplicationContext(), "user " + user.getFullName(),Toast.LENGTH_LONG).show();
        }*/


    }

}
