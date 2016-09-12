package com.zhangfan;


import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;
public class VistWeb {


    public  Timer executeContent(final String url) throws ParseException {


        Timer timer=new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {

                try {
                    URL u = new URL(url);
                    URLConnection rulConnection   = u.openConnection();
                    HttpURLConnection httpUrlConnection  =  (HttpURLConnection) rulConnection;
                    httpUrlConnection.setConnectTimeout(300000);
                    httpUrlConnection.setReadTimeout(300000);
                    httpUrlConnection.connect();
                    String code = new Integer(httpUrlConnection.getResponseCode()).toString();
                    String message = httpUrlConnection.getResponseMessage();
                    System.out.println("getResponseCode code ="+ code);
                    System.out.println("getResponseMessage message ="+ message);
                    if(!code.startsWith("2")){
                        /*************************************************/
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url("http://weixin.1-2.pub/api/send?user=zhangfanfan&msg="+url+"连接失败")
                                .build();

                        Response response = client.newCall(request).execute();
                        if (!response.isSuccessful()) {
                            throw new IOException("服务器端错误: " + response);
                        }

                        Headers responseHeaders = response.headers();
                        for (int i = 0; i < responseHeaders.size(); i++) {
                            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                        }

                        System.out.println(response.body().string());
                        System.out.println(url+"连接失败");
                        /*************************************************/
                    }else {
                        System.out.println("连接"+url+"正常");
                    }
                }catch(Exception ex){
                    System.out.println(ex.getMessage());

                }

            }

        }, 10000, 5000);
            return timer;
    }
}
