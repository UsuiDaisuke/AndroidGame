package com.example.myapplication;

import android.graphics.Matrix;
import android.graphics.Rect;

public class GameObject {
    //====================変数====================
    enum TYPE_ID{NONE, GROUND, WALL, PLAYER, ENEMY};
    protected  TYPE_ID m_type = TYPE_ID.NONE;
    protected boolean m_alive = true;
    float s = 1.0f;

    protected Vector3 m_Position = new Vector3();
    protected Vector3 m_Velocity = new Vector3();
    protected Vector3 m_Rotation = new Vector3();
    protected Vector3 m_Scale = new Vector3();

    protected Matrix m_Mat = new Matrix();

    protected Rect m_2DhitArea = new Rect();
    protected BoxCollider m_hitArea = new BoxCollider();
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

    public Vector3 GetPos()
    {
        return m_Position;
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

    public void SetCollisionRect(int right, int bottom, int front)
    {
        m_hitArea.left = -right / 2;
        m_hitArea.right = right / 2;
        m_hitArea.top = -bottom / 2;
        m_hitArea.bottom = bottom / 2;
        m_hitArea.back = -front / 2;
        m_hitArea.front = front / 2;
    }

    public BoxCollider GetHitArea()
    {
        BoxCollider hitRect = new BoxCollider();

        hitRect.left = m_hitArea.left + (int)m_Position.x;
        hitRect.right = m_hitArea.right + (int)m_Position.x;
        hitRect.top = m_hitArea.top + (int)m_Position.y;
        hitRect.bottom = m_hitArea.bottom + (int)m_Position.y;
        hitRect.back = m_hitArea.back + (int)m_Position.z;
        hitRect.front = m_hitArea.front + (int)m_Position.z;

        return hitRect;
    }

    public void SetImageName(String img)
    {
        m_imageName = img;
    }

    //矩形同士当たり判定
    public boolean HitCheck(Rect hit)
    {
        return m_2DhitArea.contains(hit);
    }
    //矩形・点の当たり判定
    public boolean HitCheck(int x, int y)
    {
        return m_2DhitArea.contains(x, y);
    }

    //====================メイン処理関数====================

    public void Init(){}
    public void Update(){}

    public void Draw()
    {
        m_Mat.setTranslate(m_Position.x - 64, m_Position.z - 64);

        App.Get().ImageMgr().Draw(m_imageName, m_Mat);
    }

    public void Draw(Matrix a_CameraMatrix)
    {
        m_Mat.set(a_CameraMatrix);
        m_Mat.postTranslate(m_Position.x - 64, m_Position.z - 64);

        App.Get().ImageMgr().Draw(m_imageName, m_Mat);
    }

    public void CollisionCheck(BoxCollider a_hitArea){}
}
