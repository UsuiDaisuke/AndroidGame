package com.example.myapplication;


import android.graphics.Rect;

/*
* App
* リソース管理などのシステム面全般
* シングルトン
*
*/
public class App
{
    GameObject Player = new Ball();
    GameObject Wall = new GameObject();
    // ゲームの実装------------------------------------------------>
    // 特殊な事をしない限りはこの間を編集するだけのはず
    int se1 = 0;
    int se2 = 0;

    // アプリケーションが開始された時
    // 諸々の初期化は終わっているので、ここでロードをかけてもOK
    public void Start()
    {
        soundManager.PlayBGM("Sounds/bgm.mp3");
        se1 = soundManager.LoadSE("Sounds/se1.mp3");
        se2 = soundManager.LoadSE("Sounds/se2.mp3");

        Player.Init();
        Wall.Init();
        Wall.SetImageName("Sprites/Wall.png");
        Wall.SetPos(256, 256);
        Wall.SetCollisionRect(128, 128);
    }


    // 毎回呼び出される関数(30fps)
    Vector2 vp = new Vector2();
    Vector2 vm = new Vector2();
    public boolean Update()
    {
        // ゲームの更新
        Player.Update();
        Player.CollisionCheck(Wall.GetHitArea());

        // タッチの処理
        Pointer p = touchManager.GetTouch(); // ここでnullが帰る場合はタッチされていない
        if(p==null){ return true; }



        // 必ずゲーム更新の後に行う事
        touchManager.Update();
        return true;
    }

    public void SetSensorRotate(float SensorX, float SensorY)
    {
        Player.SetMove(-SensorX, SensorY);
    }

    // Androidから再描画命令を受けた時
    // ここ以外での描画は無視されます。
    // 早くシステムに返さないと行けないので、描画以外の余計なことはしない。
    // 30fpsで再描画以来はかけているが、呼び出される頻度は端末によります。
    // ここが安定して動いているとは思わないでください。
    public void Draw()
    {
        Player.Draw();

        Wall.Draw();
    }

    // ホームボタンなどを押して裏側へ回った時
    public void Suspend()
    {
        soundManager.StopBGM();
    }

    // 再度アクティブになった時
    public void Resume()
    {
        soundManager.PlayBGM("Sounds/bgm.mp3");
    }

    // <--------------------------------------------------ゲームの実装





    // アプリケーション大本
    private OriginalView view = null;
    public OriginalView GetView(){return view;}
    public void SetView(OriginalView _ov)
    {
        view = _ov;

        // viewがないと初期化出来ないもののインスタンス化
        touchManager = new TouchManager();


        Start();
    }

    // 解像度対応
    private float sdPar = 0; // システム座標→ディスプレイ座標変換用
    private float dsPar = 0; // ディスプレイ座標→システム座標変換用
    public float SD(){return sdPar;}
    public float DS(){return dsPar;}

    // システム画面サイズと実機画面サイズが出揃った段階で比率を計算
    public void SetSDPar(float _dp, float _sp)
    {
        sdPar = _dp/_sp;
        dsPar = _sp/_dp;
    }

    // リソース管理
    private ImageManager imageManage = new ImageManager();
    public ImageManager ImageMgr(){return imageManage;}
    private SoundManager soundManager = new SoundManager();
    public SoundManager SoundMgr(){return soundManager;}

    // タッチ管理
    private TouchManager touchManager = null;
    public TouchManager TouchMgr(){return touchManager;}

    //シングルトン実装
    private App() { }
    private static App app = new App();
    public static App Get()
    {
        return app;
    }
}
