package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements SensorEventListener {

    private SensorManager sensorManager;
    private float sensorX;
    private float sensorY;
    private float sensorZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // 横画面にするか縦画面にするか
        //noinspection AndroidLintSourceLockedOrientationActivity
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 縦画面固定
        // 縦画面にしたい場合は引数に「ActivityInfo.SCREEN_ORIENTATION_PORTRAIT」を指定
        // 横画面にしたい場合は引数に「ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE」を指定
        // ちなみに上のコメントは、画面固定の警告を無視するものです。


        // 自分で作成したビューの設定
        OriginalView v = new OriginalView(this);
        setContentView(v);  // 使用するview(ウィンドウ)の設定


        // 描画設定
        App.Get().SetView(v);


        // 画面サイズ取得
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);


        // 解像度対応のため、画面サイズと基準になるサイズ(内部で使用する横幅)を設定
        // _spの値が画面上部の幅(横画面にした場合、長いほうが基準になるので値を増やす)
        App.Get().SetSDPar(point.x, 720);


        // ゲーム用にウィンドウ表示部の設定
        int uiOptions =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION     // 通知バー(上の時間とか)を消す
                |View.SYSTEM_UI_FLAG_FULLSCREEN         // ナビゲーションバー(下の操作ボタン)を消す
                |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;  // アプリ優先モード(上下のスワイプが無い限りフルスクリーンを続行し、しばらくしたらフルスクリーンに戻る)
        getWindow().getDecorView().setSystemUiVisibility(uiOptions); // 現在の画面(このアプリ)に設定


        // ゲームの開始
        v.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.Get().Resume();

        // Listenerの登録
        Sensor accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener((SensorEventListener) this, accel, SensorManager.SENSOR_DELAY_NORMAL);
        //sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_FASTEST);
        //sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_GAME);
        //sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        App.Get().Suspend();

        // Listenerを解除
        sensorManager.unregisterListener((SensorEventListener) this);

        // データ保存先の作成
        SharedPreferences dataStore = getSharedPreferences(
                "SaveData",
                MODE_PRIVATE
        );

        // データを保存するクラス作成
        SharedPreferences.Editor editor = dataStore.edit();

        // データの保存
        editor.putInt("Test", 100);

        // データ保存の終了
        editor.commit();


        int test = dataStore.getInt("Test", -1);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            sensorX = event.values[0];
            sensorY = event.values[1];
            sensorZ = event.values[2];

            App.Get().SetSensorRotate(sensorX, sensorY);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
