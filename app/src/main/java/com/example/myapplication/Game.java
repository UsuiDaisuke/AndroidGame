package com.example.myapplication;

import android.graphics.Color;

public class Game extends SceneBase {

    GameObject m_Player = new Ball();
    StageObject m_Stage = new StageObject();
    Camera m_Camera = new Camera();

    @Override
    void Init()
    {
        m_Player.Init();
        m_Stage.Init();
    }

    @Override
    void Update()
    {
        Vector3 rot = App.Get().GetSensorRotate();
        m_Player.SetMove(rot.x, 0, rot.y);

        m_Player.Update();

        for(BoxCollider b: m_Stage.GetCollisionList())
        {
            m_Player.CollisionCheck(b);
        }

        m_Camera.SetTarget(m_Player.GetPos());
        m_Camera.Update();
        m_Stage.SetCameraPos(m_Camera.GetPosition());
    }

    @Override
    void Draw()
    {
        m_Stage.Draw(m_Camera.GetCameraMatrix());
        m_Player.Draw(m_Camera.GetCameraMatrix());

        App.Get().GetView().DrawString(300, 100, "GAME", Color.GREEN);
    }
}
