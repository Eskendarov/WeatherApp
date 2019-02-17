package ru.eskendarov.weatherapp.audioplayer;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Button;

public class Player {

  private Button button;
  private AudioManager audioManager;
  private MediaPlayer mediaPlayer;

  Player(final Context context, final String fileName) {
    try {
      AssetFileDescriptor fileDescriptor =
              context.getAssets().openFd(fileName);
      audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
      mediaPlayer = new MediaPlayer();
      mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),
              fileDescriptor.getStartOffset(),
              fileDescriptor.getLength());
      mediaPlayer.setLooping(true);
      mediaPlayer.prepare();
    } catch (Exception e) {
      Log.d("player", "prepareAudio: ");
    }
  }

  public boolean isPlaying() {
    return mediaPlayer.isPlaying();
  }

  public void start() {
    mediaPlayer.start();
  }

  public void pause() {
    mediaPlayer.pause();
  }

  public void stopAndRelease() {
    mediaPlayer.stop();
    mediaPlayer.release();
  }
}