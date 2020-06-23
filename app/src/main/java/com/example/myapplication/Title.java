package com.example.myapplication;

import android.graphics.Color;

public class Title extends SceneBase {
    @Override
    void Init()
    {

    }

    @Override
    void Update()
    {
        Pointer p = App.Get().TouchMgr().GetTouch();
        if(p != null)
        {
            if(p.OnUp())
            {
                App.Get().ChangeScene(new Game());
                return;
            }
        }
    }

    @Override
    void Draw()
    {
        App.Get().GetView().DrawString(300, 100, "TITLE", Color.GREEN);
    }
}
