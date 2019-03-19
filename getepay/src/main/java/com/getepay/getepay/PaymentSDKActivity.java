package com.getepay.getepay;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PaymentSDKActivity extends AppCompatActivity {


    public static TextView data;

    ProgressDialogFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_sdk);

        fragment = new ProgressDialogFragment();

        fragment.show(getFragmentManager(), "Loading...");

        final Request request = (Request) getIntent().getSerializableExtra("request");

        String[] str = {request.getAgentId(), request.getMerchantOrderNo(), request.getTxnAmount()};
        final String SIGNATURE = signatureGeneration(request.getPassword(), str);

        WebView wv = (WebView) findViewById(R.id.webview);

        if (isNullOrEmpty(request.getAgentId())) {
            showDialogBOX("Error.... Field is Empty");
        }

        if (isNullOrEmpty(request.getMerchantOrderNo())) {
            showDialogBOX("Error.... Field is Empty");
        }

        if (isNullOrEmpty(request.getTxnAmount())) {
            showDialogBOX("Error.... Field is Empty");
        }

        if (isNullOrEmpty(request.getPassword())) {
            showDialogBOX("Error.... Field is Empty");
        }
        if (isNullOrEmpty(request.getAgentName())) {
            showDialogBOX("Error.... Field is Empty");
        }

        if (isNullOrEmpty(request.getUdf1())) {
            showDialogBOX("Error.... Field is Empty");
        }
        if (isNullOrEmpty(request.getUdf2())) {
            showDialogBOX("Error.... Field is Empty");
        }
        if (isNullOrEmpty(request.getUdf3())) {
            showDialogBOX("Error.... Field is Empty");
        }
        if (isNullOrEmpty(request.getUdf4())) {
            showDialogBOX("Error.... Field is Empty");
        }
        if (isNullOrEmpty(request.getUdf5())) {
            showDialogBOX("Error.... Field is Empty");
        }

        if (isNullOrEmpty(request.getTxndatetime())) {
            showDialogBOX("Error.... Field is Empty");

        }

        if (isNullOrEmpty(SIGNATURE)) {
            showDialogBOX("Error.... Field is Empty");
        }

        WebView webView = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        final String finalURL = "javascript: {" +
                "document.getElementById('agentId').value = '" + request.getAgentId() + "';" +
                " document.getElementById('merchantOrderNo').value = '" + request.getMerchantOrderNo() + "';" +
                " document.getElementById('txnAmount').value = '" + request.getTxnAmount() + "';" +
                " document.getElementById('agentName').value = '" + request.getAgentName() + "';" +
                " document.getElementById('udf1').value = '" + request.getUdf1() + "';" +
                " document.getElementById('udf2').value = '" + request.getUdf2() + "';" +
                " document.getElementById('udf3').value = '" + request.getUdf3() + "';" +
                " document.getElementById('udf4').value = '" + request.getUdf4() + "';" +
                " document.getElementById('udf5').value = '" + request.getUdf5() + "';" +
                " document.getElementById('txndatetime').value = '" + request.getTxndatetime() + "';" +
                " document.getElementById('signature').value = '" + SIGNATURE + "';" +
                " document.getElementById('fl1').submit(); };";

        Log.d("finalURL", "onCreate: " + finalURL);

        webView.setWebViewClient(new WebViewClient() {
            /**
             * Pass the field to perform any action onPageFinish
             * @param view
             * @param url
             */
            public void onPageFinished(WebView view, String url) {
                if (Build.VERSION.SDK_INT >= 19) {
                    view.evaluateJavascript(finalURL, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                            Log.d("onReceiveValue", "onReceiveValue: " + s);
                            fragment.dismiss();
                        }
                    });
                }
                if (url != null && !url.contains("atomtech.in") && url.contains("paynetz/status")) {
                    //String[] str = {agentId, merchantOrderNo, txnAmount};
                    //String q = signatureGeneration("f403b6e381524cfcc0d9cc62fa747e5f", str);
//                                startActivity(new Intent(getApplicationContext(), AfterWebviewFinish.class));
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://35.200.153.165:8080/getepay/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    RetrofitInterface service = retrofit.create(RetrofitInterface.class);

                    Call<PaymentResult> call = service.getStringScalar(new Payment(request.getAgentId(), request.getMerchantOrderNo()));
                    call.enqueue(new Callback<PaymentResult>() {
                        @Override
                        public void onResponse(Call<PaymentResult> call, Response<PaymentResult> response) {
                            Intent intent = new Intent();
                            intent.putExtra("response", response.body());
                            setResult(Activity.RESULT_OK, intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<PaymentResult> call, Throwable t) {
                            System.out.println("result negetive=>" + t.getMessage());
                            t.printStackTrace();
                        }
                    });
                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });

        webView.loadUrl("file:///android_asset/getepay.html");
    }

    private void showDialogBOX(String s) {
        Toast.makeText(this, "" + s, Toast.LENGTH_SHORT).show();
    }

    private static boolean isNullOrEmpty(String str) {
        if (str != null && !str.isEmpty())
            return false;
        return true;
    }


    public static String signatureGeneration(String password, String[] param) {
        return getEncodedValueWithSha2(password, param);
    }

    public static String getEncodedValueWithSha2(String hashKey, String... param) {
        String resp = null;
        StringBuilder sb = new StringBuilder();
        for (String s : param) {
            sb.append(s);
        }

        try {
            System.out.println("[getEncodedValueWithSha2]String to Encode =" + sb.toString());
            resp = byteToHexString(encodeWithHMACSHA2(sb.toString(), hashKey));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resp;
    }

    /*
     * Hashing using key with HMACSHA512
     */
    public static byte[] encodeWithHMACSHA2(String text, String keyString)
            throws java.security.NoSuchAlgorithmException, java.security.InvalidKeyException,
            java.io.UnsupportedEncodingException {

        java.security.Key sk = new javax.crypto.spec.SecretKeySpec(keyString.getBytes("UTF-8"), "HMACSHA512");
        javax.crypto.Mac mac = javax.crypto.Mac.getInstance(sk.getAlgorithm());
        mac.init(sk);

        byte[] hmac = mac.doFinal(text.getBytes("UTF-8"));

        return hmac;
    }

    /*
     * Convert from byte array to HexString
     */
    public static String byteToHexString(byte byData[]) {
        StringBuilder sb = new StringBuilder(byData.length * 2);
        for (int i = 0; i < byData.length; i++) {
            int v = byData[i] & 0xff;
            if (v < 16)
                sb.append('0');
            sb.append(Integer.toHexString(v));
        }

        return sb.toString();
    }

    void take() {
    }

}
