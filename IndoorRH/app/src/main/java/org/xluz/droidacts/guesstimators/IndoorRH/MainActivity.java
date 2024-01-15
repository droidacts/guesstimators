package org.xluz.droidacts.guesstimators.IndoorRH;
/**
 * Indoor relative humidity guesstimation
 * This app estimates the indoor humidity given the indoor & outdoor temperatures in addition to outdoor humidity
 * <p>
 * Copyright (c) 2024 Cecil Cheung, PhD
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * See, for example, "http://mozilla.org/MPL/2.0/".
 */

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.snackbar.Snackbar;
import android.app.AlertDialog;
import org.xluz.droidacts.guesstimators.IndoorRH.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAnchorView(R.id.fab)
                .setAction("Action", null).show()
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.defmenu0, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Menu0prefs) {
            Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(R.id.SecondFragment);
            return true;
        } else if (item.getItemId() == R.id.Menu0about) {
            AlertDialog.Builder aboutDialog = new AlertDialog.Builder(this);
            aboutDialog.setTitle(R.string.msg_about);
            aboutDialog.setMessage(R.string.msg_desc);
            aboutDialog.setPositiveButton("OK", null);
            aboutDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}