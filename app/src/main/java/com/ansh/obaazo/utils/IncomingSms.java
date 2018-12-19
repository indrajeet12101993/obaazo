package com.ansh.obaazo.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.ansh.obaazo.listener.SmsListener;

public class IncomingSms extends BroadcastReceiver {
    private final String TAG = IncomingSms.class.getSimpleName();
    private SmsListener mSmsListener;

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (Object aPdusObj : pdusObj) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) aPdusObj);
                    String senderNum = currentMessage.getDisplayOriginatingAddress();
                    String message = currentMessage.getDisplayMessageBody();
                    Log.e(TAG, "inside incomingSMS : " + senderNum);
                    try {
                        if (senderNum.equals("HP-SALAPP")) {
                            // OtpVerification Sms = new OtpVerification();
                            String verificationCode = getVerificationCode(message);
                            Log.e(TAG, "resived msg is : " + verificationCode);
                            //  Sms.reciveSms(verificationCode);
                            //    smsListener.messageReceived(verificationCode);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Getting the OTP from sms message body
     * ':' is the separator of OTP from the message
     *
     * @param message
     * @return
     */
    private String getVerificationCode(String message) {
        String code = null;
        int index = message.indexOf(":");

        if (index != -1) {
            int start = index + 2;
            int length = 6;
            code = message.substring(start, start + length);
            return code;
        }

        return code;
    }


    public void bindListener(SmsListener smsListener) {
        mSmsListener = smsListener;
    }


}