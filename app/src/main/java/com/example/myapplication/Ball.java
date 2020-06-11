package com.example.myapplication;

public class Ball extends GameObject {

    Ball()
    {
        m_type = TYPE_ID.PLAYER;
        SetPos(300, 500);
        SetImageName("Sprites/ball.png");
    }

    @Override
    public void Init() {
        super.Init();

    }

    @Override
    public void Update() {
        super.Update();
        if(Math.abs(m_moveX) <= 0.3)
        {
            m_moveX = 0;
        }
        if(Math.abs(m_moveY) <= 0.3)
        {
            m_moveY = 0;
        }

        //移動
        m_posX += m_moveX;
        m_posY += m_moveY;

    }
}
