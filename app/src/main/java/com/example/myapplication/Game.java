package com.example.myapplication;

import android.graphics.Color;
import android.graphics.Rect;

public class Game extends SceneBase {

    GameObject Player = new Ball();
    StageObject Stage = new StageObject();

    @Override
    void Init()
    {
        Player.Init();
        Stage.Init();
    }

    @Override
    void Update()
    {
        Vector3 rot = App.Get().GetSensorRotate();
        Player.SetMove(rot.x, rot.y, rot.z);

        Player.Update();

        for(Rect b: Stage.GetCollisionList())
        {
            Player.CollisionCheck(b);
        }
    }

    @Override
    void Draw()
    {
        Stage.Draw();
        Player.Draw();

        App.Get().GetView().DrawString(300, 100, "GAME", Color.GREEN);
    }
}
