package com.example.myapplication;

import android.graphics.Rect;

public class Ball extends GameObject {

    private float m_MoveX = 0.0f;
    private float m_MoveY = 0.0f;


    Ball()
    {
        m_type = TYPE_ID.PLAYER;
        SetPos(300, 500, 0);
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

    @Override
    public void CollisionCheck(Rect a_hitArea) {
        super.CollisionCheck(a_hitArea);
        Vector2 targetPoint = new Vector2();

        //X座標の基準点
        if(m_Position.x < a_hitArea.left)
        {
            targetPoint.x = a_hitArea.left;
        }
        else if(m_Position.x > a_hitArea.right)
        {
            targetPoint.x = a_hitArea.right;
        }
        else
        {
            targetPoint.x = m_Position.x;
        }

        //Y座標の基準点
        if(m_Position.y < a_hitArea.top)
        {
            targetPoint.y = a_hitArea.top;
        }
        else if(m_Position.y > a_hitArea.bottom)
        {
            targetPoint.y = a_hitArea.bottom;
        }
        else
        {
            targetPoint.y = m_Position.y;
        }

        //当たり判定
        if (Vector2.Distance(m_Position.x, m_Position.y - m_MoveY, targetPoint.x, targetPoint.y) < 64) {
            if(m_Position.y < a_hitArea.top)
            {
                m_Position.y -= 4;
                m_Position.x -= m_MoveX;
            }
            else if(m_Position.y > a_hitArea.bottom)
            {
                m_Position.y += 4;
                m_Position.x -= m_MoveX;
            }
            else
            {
                m_Position.x -= m_MoveX;
                m_MoveX /= -2;
            }
        }
        else if (Vector2.Distance(m_Position.x - m_MoveX, m_Position.y, targetPoint.x, targetPoint.y) < 64) {
            if(m_Position.x < a_hitArea.left)
            {
                m_Position.x -= 4;
                m_Position.y -= m_MoveY;
            }
            else if(m_Position.x > a_hitArea.right)
            {
                m_Position.x += 4;
                m_Position.y -= m_MoveY;
            }
            else
            {
                m_Position.y -= m_MoveY;
                m_MoveY /= -2;
            }
        }
    }

    private void Move()
    {
        m_Velocity.x /= 5;
        m_Velocity.y /= 5;

        //傾きの遊び
        if(Math.abs(m_Velocity.x) <= 0.1)
        {
            m_Velocity.x = 0;
        }
        if(Math.abs(m_Velocity.y) <= 0.1)
        {
            m_Velocity.y = 0;
        }

        //限界移動速度
        if(Math.abs(m_MoveX) < 10)
        {
            m_MoveX += m_Velocity.x;
        }
        if(Math.abs(m_MoveY) < 10)
        {
            m_MoveY += m_Velocity.y;
        }

        //減速処理
        if(m_MoveX > 0.1f)
        {
            m_MoveX -= 0.1f;
        }
        if(m_MoveX < -0.1f)
        {
            m_MoveX += 0.1f;
        }
        if(Math.abs(m_MoveX) < 0.1f)
        {
            m_MoveX = 0;
        }

        if(m_MoveY > 0.1f)
        {
            m_MoveY -= 0.1f;
        }
        if(m_MoveY < -0.1f)
        {
            m_MoveY += 0.1f;
        }
        if(Math.abs(m_MoveY) < 0.1f)
        {
            m_MoveY = 0;
        }

        //移動
        m_Position.x += m_MoveX;
        m_Position.y += m_MoveY;
    }
}
