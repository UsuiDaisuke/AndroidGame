package com.example.myapplication;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

public class StageObject extends GameObject {

    private final int Map_W = 16;
    private final int Map_H = 16;
    private GameObject[][] Blocks = new GameObject[Map_W][Map_H];

    @Override
    public void Init() {
        super.Init();

        for (int y = 0;  y < Map_H; y++) {
            for (int x = 0; x < Map_W; x++) {
                Blocks[x][y] = new GameObject();
                Blocks[x][y].Init();
                Blocks[x][y].SetPos((x * 128) + 64,(y * 128) + 64);
                Blocks[x][y].SetCollisionRect(128, 128);
                if(x == 0 | y == 0){
                    Blocks[x][y].SetImageName("Sprites/Wall.png");
                    Blocks[x][y].SetTag(TYPE_ID.WALL);
                }else {
                    Blocks[x][y].SetImageName("Sprites/Floor.png");
                    Blocks[x][y].SetTag(TYPE_ID.GROUND);
                }
            }
        }
    }

    @Override
    public void Update() {
        super.Update();
    }

    protected Rect[] GetCollisionList()
    {
        ArrayList<Rect> cList = new ArrayList<Rect>();
        for (int y = 0;  y < Map_H; y++)
        {
            for (int x = 0;  x < Map_W; x++)
            {
                if(Blocks[x][y].GetTag() == TYPE_ID.WALL) {
                    cList.add(Blocks[x][y].GetHitArea());
                }
            }
        }


        return cList.toArray(new Rect[0]);
    }

    @Override
    public void Draw() {
        for (int y = 0;  y < Map_H; y++)
        {
            for (int x = 0;  x < Map_W; x++)
            {
                Blocks[x][y].Draw();
            }
        }
    }
}
