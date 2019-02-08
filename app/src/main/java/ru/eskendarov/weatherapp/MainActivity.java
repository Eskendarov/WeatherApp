package ru.eskendarov.weatherapp;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Enver Eskendarov
 */
public final class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

  @BindView(R.id.toolbar)
  Toolbar toolbar;
  @BindView(R.id.drawer_layout)
  DrawerLayout drawer;
  @BindView(R.id.nav_view)
  NavigationView navigationView;
  @BindView(R.id.recycler_view)
  RecyclerView recyclerView;
  @BindArray(R.array.cities)
  String[] cities;
  @BindArray(R.array.countries)
  String[] countries;
  @BindArray(R.array.cities_imgs)
  TypedArray imageIndex;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();
    navigationView.setNavigationItemSelectedListener(this);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(new RecycleViewAdapter(getCitiesList()));
    logging("onCreate");
  }

  private List<City> getCitiesList() {
    final List<City> cityList = new ArrayList<>();
    for (int i = 0; i < cities.length; i++) {
      cityList.add(new City(cities[i], countries[i],
              imageIndex.getResourceId(i, -1)));
    }
    cityList.sort(Comparator.comparing(City::getName));
    logging("getCitiesList");
    return cityList;
  }

  private void logging(final String message) {
    Log.d(getClass().getSimpleName(), String.format("%s", message));
  }

  @Override
  public void onBackPressed() {
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
    logging("onBackPressed");
  }

  @Override
  public boolean onCreateOptionsMenu(final Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    final MenuItem searchItem = menu.findItem(R.id.search);
    final SearchView searchView = (SearchView) searchItem.getActionView();
    logging(searchView.getQuery().toString());
    // Не могу получить текст из поиска ¯\_(ツ)_/¯
    logging("onCreateOptionsMenu");
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(final MenuItem item) {
    switch (item.getItemId()) {
      case R.id.search: {
        toaster("search");
        break;
      }
      default: {
      }
    }
    logging("onOptionsItemSelected");
    return super.onOptionsItemSelected(item);
  }

  private void toaster(final String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
    switch (item.getItemId()) {
      case R.id.nav_set_loc: {
        toaster("nav set loc");
        break;
      }
      case R.id.nav_cities_list: {
        toaster("nav cities list");
        break;
      }
      case R.id.nav_setting: {
        toaster("nav setting");
        break;
      }
      case R.id.nav_share: {
        toaster("nav share");
        break;
      }
      case R.id.nav_send: {
        toaster("nav send");
        break;
      }
      default: {
      }
    }
    drawer.closeDrawer(GravityCompat.START);
    logging("onNavigationItemSelected");
    return true;
  }
}