package com.renarosantos.integrationtest.soundDialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.renarosantos.integrationtest.R;

/**
 * Created by renarosantos on 25/08/16.
 */
public class AlarmSoundListDialog extends Dialog {


    private final Context mContext;
    private final LinearLayout mLayoutHolder;
    private final TextView mSelectAction;
    private final TextView mCancelAction;
    private AlarmSound[] mSounds;
    private AlarmSound mSelectedSound;
    private OnSoundClickListener mListener;
    private OnDialogClickListener mDialogListener;

    public AlarmSoundListDialog(final Context context) {
        super(context);
        mContext = context;
        setContentView(R.layout.sound_list_dialog);
        mLayoutHolder = (LinearLayout) findViewById(R.id.sound_list_container);
        mSelectAction = (TextView) findViewById(R.id.select_action);
        mCancelAction = (TextView) findViewById(R.id.cancel_action);
        mCancelAction.setOnClickListener(new OnCancelClicked());
        mSelectAction.setOnClickListener(new OnSelectClicked());

    }

    public AlarmSoundListDialog(final Context context, @NonNull final String title) {
        this(context);
        setTitle(title);
    }

    public void setOnSoundClicked(@NonNull final OnSoundClickListener listener) {
        mListener = listener;
    }

    public void setOnDialogClickListener(@NonNull final OnDialogClickListener listener) {
        mDialogListener = listener;
    }

    public void setSounds(@NonNull final AlarmSound[] sounds) {
        mSounds = sounds;
        mLayoutHolder.removeAllViews();
        final RadioGroup radioGroup = new RadioGroup(mContext);
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        RadioButton radioButton;
        for (final AlarmSound sound : sounds) {
            radioButton = new RadioButton(mContext);
            radioButton.setText(mContext.getText(sound.soundTitle()));
            radioButton.setOnClickListener(new OnSoundClicked(sound.key()));
            radioGroup.addView(radioButton);
        }

        mLayoutHolder.addView(radioGroup);
    }


    private class OnSoundClicked implements View.OnClickListener {

        private final int mKey;

        public OnSoundClicked(final int key) {
            mKey = key;
        }

        @Override
        public void onClick(final View v) {
            mSelectedSound = mSounds[mKey];
            mSelectAction.setEnabled(true);
            if (mListener != null && mSelectedSound != null) {
                mListener.onSoundClicked(mSelectedSound);
            }
        }
    }

    public interface OnSoundClickListener {
        void onSoundClicked(@NonNull final AlarmSound sound);
    }

    public interface OnDialogClickListener {
        void onSoundSelected(@NonNull final AlarmSound sound);

        void onCancel();
    }

    private class OnCancelClicked implements View.OnClickListener {
        @Override
        public void onClick(final View v) {
            dismiss();
            if (mDialogListener != null) {
                mDialogListener.onCancel();
            }
        }
    }

    private class OnSelectClicked implements View.OnClickListener {
        @Override
        public void onClick(final View v) {
            if (mDialogListener != null && mSelectedSound != null) {
                dismiss();
                mDialogListener.onSoundSelected(mSelectedSound);
            }
        }
    }
}
