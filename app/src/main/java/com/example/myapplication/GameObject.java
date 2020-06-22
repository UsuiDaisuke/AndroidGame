package com.example.myapplication;

import android.graphics.Rect;

public class GameObject {
    //====================変数====================
    enum TYPE_ID{NONE, GROUND, WALL, PLAYER, ENEMY};
    protected  TYPE_ID m_type = TYPE_ID.NONE;
    protected boolean m_alive = true;

    protected float m_posX;
    protected float m_posY;

    protected float m_moveX;
    protected float m_moveY;

    protected Rect m_hitArea = new Rect();
    protected String m_imageName = "Sprites/ball.png";

    //====================値渡し関数====================
    public void SetTag(TYPE_ID tag)
    {
        m_type = tag;
    }

    public TYPE_ID GetTag()
    {
        return m_type;
    }

    public void SetPos(int x, int y)
    {
        m_posX = x;
        m_posY = y;
    }

    public void SetMove(float x, float y)
    {
        m_moveX = x;
        m_moveY = y;
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

        hitRect.left = m_hitArea.left + (int)m_posX;
        hitRect.right = m_hitArea.right + (int)m_posX;
        hitRect.top = m_hitArea.top + (int)m_posY;
        hitRect.bottom = m_hitArea.bottom + (int)m_posY;

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
        App.Get().ImageMgr().Draw(m_imageName, m_posX, m_posY);
    }

    public void CollisionCheck(Rect a_hitArea){}
}
