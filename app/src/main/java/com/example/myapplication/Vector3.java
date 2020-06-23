package com.example.myapplication;


/*
 ベクトル計算クラス
 */
public class Vector3 {
    public float x = 0;
    public float y = 0;
    public float z = 0;

    // このベクトルに対する処理----------------------------------------------->
    public Vector3(){}
    public Vector3(float _x, float _y, float _z)
    {
        x = _x;
        y = _y;
        z = _z;
    }

    // ベクトルの加算
    public void Add(Vector3 va)
    {
        x += va.x;
        y += va.y;
        z += va.z;
    }
    public void Clear(){x = y = z = 0;}

    // 自分のベクトルの長さを返す
    public float Length(){return Distance(Zero, this);}

    // 正規化
    public void Nomalize()
    {
        float len = Length();
        x = x/len;
        y = y/len;
        z = z/len;
    }

    // ベクトルの計算---------------------------------------------------->
    // 二点間の距離を求める
    public static float Distance(Vector3 p1, Vector3 p2)
    {
        return (float) Math.sqrt( (p1.x-p2.x)*(p1.x-p2.x) + (p1.y-p2.y)*(p1.y-p2.y) + (p1.z-p2.z)*(p1.z-p2.z));
    }
    public static float Distance(float p1x,float p1y, float p1z,float p2x, float p2y,float p2z)
    {
        return (float) Math.sqrt( (p1x-p2x)*(p1x-p2x) + (p1y-p2y)*(p1y-p2y) + (p1z-p2z)*(p1z-p2z));
    }

    // v1→v2ベクトルを返す
    public static Vector3 To(Vector3 v1, Vector3 v2) {
        return new Vector3(v2.x - v1.x, v2.y - v1.y, v2.z - v1.z);
    }

    public static final Vector3 Zero = new Vector3(0,0,0);
}
