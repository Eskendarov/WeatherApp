package ru.eskendarov.weatherapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Enver Eskendarov
 */
public class MainActivity extends AppCompatActivity implements
    NavigationView.OnNavigationItemSelectedListener {

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    final FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(final View view) {
        Snackbar.make(view,
            "Send message to developer", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });
    final DrawerLayout drawer = findViewById(R.id.drawer_layout);
    final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar,
        R.string.navigation_drawer_open,
        R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();
    final NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
    final RecyclerView recyclerView = findViewById(R.id.recycler_view);
    recyclerView.setHasFixedSize(true);
    final LinearLayoutManager llm = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(llm);
    final RecViewAdapter adapter = new RecViewAdapter(getCitiesList());
    recyclerView.setAdapter(adapter);
    Log.d(getClass().getSimpleName(), String.format("onCreate: %s", ""));
  }

  private List<Cities> getCitiesList() {
    final List<Cities> citiesList = new ArrayList<>();
    for (int i = 0; i < getResources().getStringArray(R.array.cities).length; i++) {
      citiesList.add(new Cities(
          getResources().getStringArray(R.array.cities)[i],
          getResources().getStringArray(R.array.countries)[i],
          getResources().obtainTypedArray(R.array.cities_imgs)
              .getResourceId(i, -1)));
    }
    Log.d(getClass().getSimpleName(), String.format("getCitiesList: %s", ""));
    return citiesList;
  }

  @Override
  public void onBackPressed() {
    final DrawerLayout drawer = findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
    Log.d(getClass().getSimpleName(), String.format("onBackPressed: %s", ""));
  }

  @Override
  public boolean onCreateOptionsMenu(final Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    Log.d(getClass().getSimpleName(), String.format("onCreateOptionsMenu: %s", ""));
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(final MenuItem item) {
    switch (item.getItemId()) {
      case R.id.search: {
        Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
        break;
      }
      default: {
      }
    }
    Log.d(getClass().getSimpleName(), String.format("onOptionsItemSelected: %s", ""));
    return super.onOptionsItemSelected(item);
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
    switch (item.getItemId()) {
      case R.id.nav_set_loc: {
        Toast.makeText(this,
            "nav set loc", Toast.LENGTH_SHORT).show();
        break;
      }
      case R.id.nav_cities_list: {
        Toast.makeText(this,
            "nav cities list", Toast.LENGTH_SHORT).show();
        break;
      }
      case R.id.nav_setting: {
        Toast.makeText(this,
            "nav setting", Toast.LENGTH_SHORT).show();
        break;
      }
      case R.id.nav_share: {
        Toast.makeText(this,
            "nav share", Toast.LENGTH_SHORT).show();
        break;
      }
      case R.id.nav_send: {
        Toast.makeText(this,
            "nav send", Toast.LENGTH_SHORT).show();
        break;
      }
      default: {
      }
    }
    final DrawerLayout drawer = findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    Log.d(getClass().getSimpleName(), String.format("onNavigationItemSelected: %s", ""));
    return true;
  }
}