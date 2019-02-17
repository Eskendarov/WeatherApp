package ru.eskendarov.weatherapp.audioplayer;

import android.app.Application;
import android.content.Intent;
import android.os.Build;

public class App extends Application {

  private Player player;

  @Override
  public void onCreate() {
    super.onCreate();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      startForegroundService(new Intent(this, AudioService.class));
    } else {
      startService(new Intent(this, AudioService.class));
    }
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(final Player player) {
    this.player = player;
  }
}