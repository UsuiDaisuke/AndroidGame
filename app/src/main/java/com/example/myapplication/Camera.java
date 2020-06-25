package com.example.myapplication;

import android.graphics.Matrix;

public class Camera {

    Vector3 m_Position = new Vector3();
    Vector3 m_targetPosition = new Vector3();
    Matrix m_Matrix = new Matrix();

    public void Init() {

    }

    public void SetTarget(final Vector3 targetPos)
    {
        m_targetPosition = targetPos;
    }

    public void Update()
    {
        m_Position.x += (m_targetPosition.x - m_Position.x) / 2;
        m_Position.y += 0;
        m_Position.z += (m_targetPosition.z - m_Position.z) / 2;

        m_Matrix.setTranslate(-m_Position.x - 360, -m_Position.z - 640);
        m_Matrix.postScale(-m_Position.y / 128 + 1, -m_Position.y / 128 + 1);
        m_Matrix.postTranslate(720, 1280);
    }

    public final Vector3 GetPosition()
    {
        return m_Position;
    }

    public final Matrix GetCameraMatrix()
    {
        return m_Matrix;
    }
}
