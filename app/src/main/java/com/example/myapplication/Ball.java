package com.example.myapplication;

public class Ball extends GameObject {

    private float m_velocityX = 0.0f;
    private float m_velocityY = 0.0f;


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

        Move();
    }

    private void Move()
    {
        m_moveX /= 5;
        m_moveY /= 5;

        //傾きの遊び
        if(Math.abs(m_moveX) <= 0.1)
        {
            m_moveX = 0;
        }
        if(Math.abs(m_moveY) <= 0.1)
        {
            m_moveY = 0;
        }

        //限界移動速度
        if(Math.abs(m_velocityX) < 10)
        {
            m_velocityX += m_moveX;
        }
        if(Math.abs(m_velocityY) < 10)
        {
            m_velocityY += m_moveY;
        }

        //減速処理
        if(m_velocityX > 0.1f)
        {
            m_velocityX -= 0.1f;
        }
        if(m_velocityX < -0.1f)
        {
            m_velocityX += 0.1f;
        }
        if(Math.abs(m_velocityX) < 0.1f)
        {
            m_velocityX = 0;
        }

        if(m_velocityY > 0.1f)
        {
            m_velocityY -= 0.1f;
        }
        if(m_velocityY < -0.1f)
        {
            m_velocityY += 0.1f;
        }
        if(Math.abs(m_velocityY) < 0.1f)
        {
            m_velocityY = 0;
        }

        //移動
        m_posX += m_velocityX;
        m_posY += m_velocityY;
    }
}
