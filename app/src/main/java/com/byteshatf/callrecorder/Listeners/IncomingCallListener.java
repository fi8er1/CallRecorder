package com.byteshatf.callrecorder.Listeners;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.byteshatf.callrecorder.AppGlobals;
import com.byteshatf.callrecorder.CallRecording;
import com.byteshatf.callrecorder.Helpers;

public class IncomingCallListener extends PhoneStateListener {

    CallRecording callRecording = new CallRecording();
    Helpers helpers = new Helpers(AppGlobals.getContext());

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);
        switch (state) {
            case TelephonyManager.CALL_STATE_RINGING:

                break;
            case TelephonyManager.CALL_STATE_IDLE:
                if (CallRecording.isRecording) {
                    callRecording.stopRecording();
                }
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                if (helpers.isSpecialRecordingEnabled()) {
                    callRecording.startRecord();
                    System.out.println("okay");
                }
                break;
        }
    }

    public BroadcastReceiver mOutgoingCall = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            System.out.println(number);
        }
    };
}