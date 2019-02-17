package ru.eskendarov.weatherapp.audioplayer;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.RemoteViews;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import ru.eskendarov.weatherapp.MainActivity;
import ru.eskendarov.weatherapp.R;

public class AudioService extends Service {

  private static final int NOTIFICATION_ID = 12345;
  RemoteViews mContentView;
  private Player player;

  @Override
  public void onCreate() {
    super.onCreate();
    player = new Player(this, "music.mp3");
    ((App) getApplication()).setPlayer(player);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      createChannel((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE));
      showNotification("Player");
    }
  }

  @TargetApi(26)
  private void createChannel(final NotificationManager manager) {
    final String name = "player";
    final String description = "Our channel";
    final int importance = NotificationManager.IMPORTANCE_MIN;
    final NotificationChannel channel = new NotificationChannel(name, name, importance);
    channel.setDescription(description);
    channel.enableLights(false);
    channel.enableVibration(false);
    channel.setSound(null, null);
    channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
    channel.setShowBadge(false);
    manager.createNotificationChannel(channel);
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  private void showNotification(final String text) {
    final Intent notificationIntent = new Intent(this, MainActivity.class);
    final PendingIntent pendingIntent =
            PendingIntent.getActivity(this, 0, notificationIntent, 0);
    final Notification notification = new Notification.Builder(this, "player")
//            .setCustomContentView(mContentView)
            .setContentTitle("NOTIFICATION_ID: " + NOTIFICATION_ID)
            .setContentText(text)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setShowWhen(false)
            .setCategory(Notification.CATEGORY_TRANSPORT)
            .setStyle(new Notification.MediaStyle())
            .setWhen(System.currentTimeMillis())
            .setOnlyAlertOnce(true)
            .setPriority(Notification.PRIORITY_MIN)
            .build();
    startForeground(NOTIFICATION_ID, notification);
  }

  @Override
  public int onStartCommand(final Intent intent, final int flags, final int startId) {
    return Service.START_NOT_STICKY;
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    player.stopAndRelease();
    ((App) getApplication()).setPlayer(null);
  }

  @Override
  public IBinder onBind(@Nullable final Intent intent) {
    throw new UnsupportedOperationException("Not yet implemented");
  }
}