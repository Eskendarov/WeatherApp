package ru.eskendarov.weatherapp;

import android.app.SearchManager;
import android.content.Context;
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
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.navigation.NavigationView;
import ru.eskendarov.weatherapp.fragments.CitiesListFragment;
import ru.eskendarov.weatherapp.fragments.SettingsFragment;

/**
 * @author Enver Eskendarov
 */
public final class MainActivity extends AppCompatActivity {

  private static final String TAG = "qqqq";
  @BindView(R.id.toolbar)
  Toolbar toolbar;
  @BindView(R.id.drawer_layout)
  DrawerLayout drawer;
  @BindView(R.id.nav_view)
  NavigationView navigationView;

  @Override
  public void onBackPressed() {
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
    logging("onBackPressed");
  }

  private void logging(final String message) {
    Log.d(TAG, String.format("%s", message));
  }

  @Override
  public boolean onCreateOptionsMenu(final Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    final SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
    final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
    searchView.setIconifiedByDefault(false);
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(final String query) {
        return true;
      }

      @Override
      public boolean onQueryTextChange(final String newText) {
        logging(newText);
        return true;
      }
    });
    logging("onCreateOptionsMenu");
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
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
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initialize();
    logging("onCreate");
  }

  private void initialize() {
    ButterKnife.bind(this);
    setNavigationListener();
    setSupportActionBar(toolbar);
    final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();
    getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.container, new CitiesListFragment())
            .commit();

  }

  private void setNavigationListener() {
    navigationView.setNavigationItemSelectedListener(item -> {
      switch (item.getItemId()) {
        case R.id.nav_set_loc: {
          toaster("nav set loc");
          break;
        }
        case R.id.nav_cities_list: {
          getSupportFragmentManager().popBackStack();
          getSupportFragmentManager()
                  .beginTransaction()
                  .replace(R.id.container, new CitiesListFragment())
                  .commit();
          break;
        }
        case R.id.nav_setting: {
          getSupportFragmentManager().popBackStack();
          getSupportFragmentManager()
                  .beginTransaction()
                  .addToBackStack(null)
                  .replace(R.id.container, new SettingsFragment())
                  .commit();
          break;
        }
        default: {
        }
      }
      drawer.closeDrawer(GravityCompat.START);
      logging("onNavigationItemSelected");
      return true;
    });
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }
}