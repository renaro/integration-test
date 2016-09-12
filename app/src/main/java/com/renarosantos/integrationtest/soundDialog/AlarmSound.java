package com.renarosantos.integrationtest.soundDialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RawRes;
import android.support.annotation.StringRes;

import com.renarosantos.integrationtest.R;

/**
 * Created by renarosantos on 25/08/16.
 */
public enum AlarmSound {

    ALARM0(0, R.string.birl_sound_title, R.raw.birl),
    ALARM1(1, R.string.bass_sound_title, R.raw.bass),
    ALARM2(2, R.string.bell_sound_title, R.raw.bells),
    ALARM3(3, R.string.drumn_sound_title, R.raw.kick),
    ALARM4(4, R.string.drumn_sound_title, R.raw.kick),
    ALARM5(5, R.string.drumn_sound_title, R.raw.kick);


    private final int mKey;
    private final int mAudioResource;
    private final int mSoundTitle;

    AlarmSound(final int key, @StringRes final int soundTitle, @RawRes final int audioResource) {
        mKey = key;
        mAudioResource = audioResource;
        mSoundTitle = soundTitle;
    }


    public int key() {
        return mKey;
    }

    public int audioResource() {
        return mAudioResource;
    }

    public int soundTitle() {
        return mSoundTitle;
    }

    public static String[] stringValues(@NonNull final Context context) {
        final AlarmSound[] alarms = AlarmSound.values();
        String[] result = new String[alarms.length];
        for (final AlarmSound alarmSound : alarms) {
            result[alarmSound.key()] = context.getString(alarmSound.soundTitle());
        }
        return result;
    }

    @RawRes
    public static int getSound(final int key) {
        final AlarmSound[] alarms = AlarmSound.values();
        final AlarmSound alarm = alarms[key];
        if (alarm != null) {
            return alarm.audioResource();
        } else {
            throw new IllegalArgumentException("Sound key not valid");
        }
    }

}


