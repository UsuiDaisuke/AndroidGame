package com.example.myapplication;

import android.graphics.Rect;

public class Ball extends GameObject {

    private float m_MoveX = 0.0f;
    private float m_MoveY = 0.0f;
    private float m_MoveZ = 0.0f;

    private boolean m_HitX = false;
    private boolean m_HitY = false;
    private boolean m_HitZ = false;

    Ball()
    {
        m_type = TYPE_ID.PLAYER;
        SetPos(300, 0, 500);
        SetImageName("Sprites/ball.png");
    }

    @Override
    public void Init() {
        super.Init();
    }

    @Override
    public void Update() {
        super.Update();

        if(m_HitX == true)
        {
            m_MoveX /= -2;
            m_HitX = false;
        }
        if(m_HitY == true)
        {
            m_MoveY = 0;
            m_HitY = false;
        }
        if(m_HitZ == true)
        {
            m_MoveZ /= -2;
            m_HitZ = false;
        }

        Move();
    }

    @Override
    public void CollisionCheck(BoxCollider a_hitArea) {
        super.CollisionCheck(a_hitArea);
        Vector3 targetPoint = new Vector3();

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

        //Z座標の基準点
        if(m_Position.z < a_hitArea.back)
        {
            targetPoint.z = a_hitArea.back;
        }
        else if(m_Position.z > a_hitArea.front)
        {
            targetPoint.z = a_hitArea.front;
        }
        else
        {
            targetPoint.z = m_Position.z;
        }

        //当たり判定
        if (Vector3.Distance(m_Position.x, m_Position.y - m_MoveY, m_Position.z - m_MoveZ
                , targetPoint.x, targetPoint.y, targetPoint.z) < 64) {
            if(m_Position.y < a_hitArea.top)
            {
                m_Position.x -= m_MoveX;
            }
            else if(m_Position.y > a_hitArea.bottom)
            {
                m_Position.x -= m_MoveX;
            }
            else
            {
                m_Position.x -= m_MoveX;
            }
            m_HitX = true;
        }
        else if (Vector3.Distance(m_Position.x - m_MoveX, m_Position.y, m_Position.z - m_MoveZ
                , targetPoint.x, targetPoint.y, targetPoint.z) < 64) {
            if(m_Position.x < a_hitArea.left)
            {
                m_Position.y -= m_MoveY;
            }
            else if(m_Position.x > a_hitArea.right)
            {
                m_Position.y -= m_MoveY;
            }
            else
            {
                m_Position.y -= m_MoveY;
            }
            m_HitY = true;
        }
        else if (Vector3.Distance(m_Position.x - m_MoveX, m_Position.y - m_MoveY, m_Position.z
                , targetPoint.x, targetPoint.y, targetPoint.z) < 64) {
            if(m_Position.x < a_hitArea.left)
            {
                m_Position.z -= m_MoveZ;
            }
            else if(m_Position.x > a_hitArea.right)
            {
                m_Position.z -= m_MoveZ;
            }
            else
            {
                m_Position.z -= m_MoveZ;
            }
            m_HitZ = true;
        }
    }

    private void Move()
    {
        m_Velocity.x /= 5;
        m_Velocity.z /= 5;

        //傾きの遊び
        if(Math.abs(m_Velocity.x) <= 0.1)
        {
            m_Velocity.x = 0;
        }
        if(Math.abs(m_Velocity.z) <= 0.1)
        {
            m_Velocity.z = 0;
        }

        //限界移動速度
        if(Math.abs(m_MoveX) < 10)
        {
            m_MoveX += m_Velocity.x;
        }
        if(Math.abs(m_MoveY) < 10)
        {
            m_MoveZ += m_Velocity.z;
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

        if(m_MoveZ > 0.1f)
        {
            m_MoveZ -= 0.1f;
        }
        if(m_MoveZ < -0.1f)
        {
            m_MoveZ += 0.1f;
        }
        if(Math.abs(m_MoveZ) < 0.1f)
        {
            m_MoveZ = 0;
        }

        //移動
        m_Position.x += m_MoveX;
        m_Position.y += m_MoveY;
        m_Position.z += m_MoveZ;
    }
}
