package com.example.myapplication;

import android.graphics.Rect;

public class GameObject {
    //====================変数====================
    enum TYPE_ID{NONE, GROUND, PLAYER, ENEMY};
    protected  TYPE_ID m_type = TYPE_ID.NONE;
    protected boolean m_alive = true;

    protected float m_posX;
    protected float m_posY;

    protected float m_moveX;
    protected float m_moveY;

    protected Rect m_hitArea = new Rect();
    protected String m_imageName = "Sprites/ball.png";

    //====================値渡し関数====================
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

    public void SetImageName(String img)
    {
        m_imageName = img;
    }

    public Rect GetHitArea()
    {
        return m_hitArea;
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
}
