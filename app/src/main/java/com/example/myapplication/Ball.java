package com.example.myapplication;

import android.graphics.Rect;

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

    @Override
    public void CollisionCheck(Rect a_hitArea) {
        super.CollisionCheck(a_hitArea);
        Vector2 targetPoint = new Vector2();

        //X座標の基準点
        if(m_posX < a_hitArea.left)
        {
            targetPoint.x = a_hitArea.left;
        }
        else if(m_posX > a_hitArea.right)
        {
            targetPoint.x = a_hitArea.right;
        }
        else
        {
            targetPoint.x = m_posX;
        }

        //Y座標の基準点
        if(m_posY < a_hitArea.top)
        {
            targetPoint.y = a_hitArea.top;
        }
        else if(m_posY > a_hitArea.bottom)
        {
            targetPoint.y = a_hitArea.bottom;
        }
        else
        {
            targetPoint.y = m_posY;
        }

        //当たり判定
        if (Vector2.Distance(m_posX, m_posY - m_velocityY, targetPoint.x, targetPoint.y) < 64) {
            if(m_posY < a_hitArea.top)
            {
                m_posY -= 4;
                m_posX -= m_velocityX;
            }
            else if(m_posY > a_hitArea.bottom)
            {
                m_posY += 4;
                m_posX -= m_velocityX;
            }
            else
            {
                m_posX -= m_velocityX;
                m_velocityX /= -2;
            }
        }
        else if (Vector2.Distance(m_posX - m_velocityX, m_posY, targetPoint.x, targetPoint.y) < 64) {
            if(m_posX < a_hitArea.left)
            {
                m_posX -= 4;
                m_posY -= m_velocityY;
            }
            else if(m_posX > a_hitArea.right)
            {
                m_posX += 4;
                m_posY -= m_velocityY;
            }
            else
            {
                m_posY -= m_velocityY;
                m_velocityY /= -2;
            }
        }
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
