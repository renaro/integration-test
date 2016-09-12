package com.renarosantos.integrationtest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.renarosantos.integrationtest.soundDialog.AlarmSound;
import com.renarosantos.integrationtest.soundDialog.AlarmSoundListDialog;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements AlarmSoundListDialog.OnSoundClickListener,
        AlarmSoundListDialog.OnDialogClickListener {

    private AlarmManager alarmMgr;
    private AlarmSoundListDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDialog = new AlarmSoundListDialog(this, getString(R.string.sound_dialog_title));
        mDialog.setSounds(AlarmSound.values());
        mDialog.setOnSoundClicked(this);
        mDialog.setOnDialogClickListener(this);
        mDialog.show();

    }


    /*
    * not used yet
    * */
    private void createNotificationTask() {
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);

        final PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() +
                        3 * 1000, alarmIntent);
    }

    @Override
    public void onSoundClicked(@NonNull final AlarmSound sound) {
        final Uri parse = Uri.parse("android.resource://com.renarosantos.integrationtest/" + sound.audioResource());
        try {
            final MediaPlayer mPlayer = new MediaPlayer();
            mPlayer.setDataSource(this, parse);
            mPlayer.prepare();
            mPlayer.start();
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(final MediaPlayer mp) {
                    mPlayer.release();
                }
            });
        } catch (IOException e) {

        }


    }

    @Override
    public void onSoundSelected(@NonNull final AlarmSound sound) {
        Toast.makeText(this, "Selecionou " + sound.soundTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel() {
        Toast.makeText(this, "Cancelou ", Toast.LENGTH_SHORT).show();
    }
}
