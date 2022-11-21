package com.example.week10;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int value = 0;
    private boolean running = false;

    TextView textView01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView01 = (TextView) findViewById(R.id.textView01);
        Button showBtn = (Button) findViewById(R.id.showBtn);

        // 버튼 이벤트 처리 : showBtn을 클릭하면 textView01에 value의 값을 표시한다.
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView01.setText("스레드에서 받은 값 : " + value);
            }
        });

    }

    /**
     * 화면 보일 때 스레드 시작
     */
    protected void onResume() {
        super.onResume();

        running = true;

        Thread thread1 = new BackgroundThread();
        thread1.start();
    }

    /**
     * 화면 안보일 때 스레드 중지
     */
    protected void onPause() {
        super.onPause();

        running = false;
        value = 0;
    }

    /**
     * 스레드 정의
     */
    class BackgroundThread extends Thread{
        public void run(){
            while(running){
                try{
                    Thread.sleep(1000);
                    value++;
                }catch(InterruptedException ex){
                    Log.e("SampleJavaThread", "Exception in thread", ex);
                }
            }
        }
    }

}
