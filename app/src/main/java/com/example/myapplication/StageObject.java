package com.example.myapplication;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Matrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StageObject extends GameObject {

    private final int Map_W = 64;
    private final int Map_H = 64;
    private int[][] m_BlockID = new int[Map_W][Map_H];
    private GameObject[][] Blocks = new GameObject[Map_W][Map_H];

    private int m_camLeft;
    private int m_camRight;
    private int m_camTop;
    private int m_camBottom;

    @Override
    public void Init() {
        super.Init();

        LoadMap("null");

        for (int y = 0;  y < Map_H; y++) {
            for (int x = 0; x < Map_W; x++) {
                Blocks[x][y] = new GameObject();
                Blocks[x][y].Init();
                Blocks[x][y].SetPos((x * 128) + 64,0, (y * 128) + 64);
                Blocks[x][y].SetCollisionRect(128, 128, 128);
                switch (m_BlockID[x][y]){
                    case 0:
                        Blocks[x][y].SetImageName("null");
                        Blocks[x][y].SetTag(TYPE_ID.GROUND);
                        break;
                    case 1:
                        Blocks[x][y].SetImageName("Sprites/Floor.png");
                        Blocks[x][y].SetTag(TYPE_ID.GROUND);
                        break;
                    case 2:
                        Blocks[x][y].SetImageName("Sprites/Wall.png");
                        Blocks[x][y].SetTag(TYPE_ID.WALL);
                        break;
                }
            }
        }
    }

    @Override
    public void Update() {

    }

    protected BoxCollider[] GetCollisionList()
    {
        ArrayList<BoxCollider> cList = new ArrayList<BoxCollider>();
        for (int y = m_camTop;  y < m_camBottom; y++)
        {
            for (int x = m_camLeft; x < m_camRight; x++)
            {
                if(Blocks[x][y].GetTag() == TYPE_ID.WALL) {
                    cList.add(Blocks[x][y].GetHitArea());
                }
            }
        }


        return cList.toArray(new BoxCollider[0]);
    }

    @Override
    public void Draw(Matrix a_CameraMatrix) {
        for (int y = m_camTop;  y < m_camBottom; y++)
        {
            for (int x = m_camLeft; x < m_camRight; x++)
            {
                if(m_BlockID[x][y] == 0){
                    break;
                }
                Blocks[x][y].Draw(a_CameraMatrix);
            }
        }
    }

    public void SetCameraPos(final Vector3 a_camera)
    {
        m_camLeft = (int)(a_camera.x - 360) / 128;
        if(m_camLeft < 0){
            m_camLeft = 0;
        }
        m_camRight = m_camLeft + 7;
        if(m_camRight > Map_W){
            m_camRight = Map_W;
        }

        m_camTop = (int)(a_camera.z - 640) / 128;
        if(m_camTop < 0){
            m_camTop = 0;
        }
        m_camBottom = m_camTop + 11;
        if(m_camBottom > Map_H){
            m_camBottom = Map_H;
        }
    }

    private void LoadMap(String _FileName){
        int x = 0;
        int y = 0;
        Context con = App.Get().GetContext();
        AssetManager am = con.getResources().getAssets();
        try {
            // CSVファイルの読み込み
            InputStream inputStream = am.open("Data/stage001.data");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferReader.readLine()) != null) {

                //カンマ区切りで１つづつ配列に入れる
                String[] RowData = line.split(",");

                //CSVの左([0]番目)から順番にセット
                for(String d : RowData) {
                    m_BlockID[x][y] = Integer.parseInt(d);
                    x++;
                }
                x = 0;
                y++;
            }
            bufferReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
