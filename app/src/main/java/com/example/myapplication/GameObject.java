package com.example.myapplication;

import android.graphics.Rect;

public class GameObject {
    //====================変数====================
    enum TYPE_ID{NONE, GROUND, WALL, PLAYER, ENEMY};
    protected  TYPE_ID m_type = TYPE_ID.NONE;
    protected boolean m_alive = true;

    protected Vector3 m_Position = new Vector3();
    protected Vector3 m_Velocity = new Vector3();

    protected Rect m_hitArea = new Rect();
    protected String m_imageName = "";

    //====================値渡し関数====================
    public void SetTag(TYPE_ID tag)
    {
        m_type = tag;
    }

    public TYPE_ID GetTag()
    {
        return m_type;
    }

    public void SetPos(float x, float y, float z)
    {
        m_Position.x = x;
        m_Position.y = y;
        m_Position.z = z;
    }

    public void SetMove(float x, float y, float z)
    {
        m_Velocity.x = x;
        m_Velocity.y = y;
        m_Velocity.z = z;
    }

    public void SetCollisionRect(int right, int bottom)
    {
        m_hitArea.left = -right / 2;
        m_hitArea.top = -bottom / 2;
        m_hitArea.right = right / 2;
        m_hitArea.bottom = bottom / 2;
    }

    public Rect GetHitArea()
    {
        Rect hitRect = new Rect();

        hitRect.left = m_hitArea.left + (int)m_Position.x;
        hitRect.right = m_hitArea.right + (int)m_Position.x;
        hitRect.top = m_hitArea.top + (int)m_Position.y;
        hitRect.bottom = m_hitArea.bottom + (int)m_Position.y;

        return hitRect;
    }

    public void SetImageName(String img)
    {
        m_imageName = img;
    }

    //矩形同士当たり判定
    public boolean HitCheck(Rect hit)
    {
        return m_hitArea.contains(hit);
    }
    //矩形・点の当たり判定
    public boolean HitCheck(int x, int y)
    {
        return m_hitArea.contains(x, y);
    }

    //====================メイン処理関数====================

    public void Init(){}
    public void Update(){}

    public void Draw()
    {
        App.Get().ImageMgr().Draw(m_imageName, m_Position.x, m_Position.y);
    }

    public void CollisionCheck(Rect a_hitArea){}
}
