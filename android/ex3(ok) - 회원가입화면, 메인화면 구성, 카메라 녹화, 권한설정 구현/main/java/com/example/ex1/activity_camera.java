package com.example.ex1;

import android.Manifest;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

public class activity_camera extends AppCompatActivity implements SurfaceHolder.Callback {


    private Camera camera;
    private MediaRecorder mediaRecoder;
    private SurfaceView surface;
    private SurfaceHolder surfaceHolder;
    private boolean recording=false;
    private Button btn_camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);


        TedPermission.with(this) //권한요청 targetsdk 22부터 필수임
                .setPermissionListener(permission)
                .setRationaleMessage("녹화를 위하여 권한을 허용해주세요.")
                .setDeniedMessage("권한이 거부되어 촬영을 종료합니다. 설정 > 권한에서 허용해주세요.")
                .setPermissions(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO)
                .check();

        btn_camera=(Button)findViewById(R.id.btn_camera);
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recording){ //영상 촬영 중일 시 녹화 종료
                    mediaRecoder.stop();
                    mediaRecoder.release();
                    camera.lock();
                    recording=false;
                }
                else{
                    runOnUiThread(new Runnable() {//쓰레드 사용
                        @Override
                        public void run() {
                            Toast.makeText(activity_camera.this, "녹화가 시작되었습니다.",Toast.LENGTH_SHORT);
                            try {
                                mediaRecoder=new MediaRecorder();
                                camera.unlock();
                                mediaRecoder.setCamera(camera);
                                mediaRecoder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);//녹화 시작시 삐소리
                                mediaRecoder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                                mediaRecoder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_720P));//화질설정
                                mediaRecoder.setOrientationHint(90);//90도로 각도 설정
                                mediaRecoder.setOutputFile("/sdcard/test.mp4");//파일 저장
                                mediaRecoder.setPreviewDisplay(surfaceHolder.getSurface());//화면에 카메라 송출
                                mediaRecoder.prepare();
                                mediaRecoder.start();
                                recording=true;
                            } catch (Exception e){
                                e.printStackTrace();
                                mediaRecoder.release();
                            }
                        }
                    });
                }
            }
        });

    }

    PermissionListener permission = new PermissionListener() {
        @Override
        public void onPermissionGranted() {//퍼미션이 허용되었을때
            Toast.makeText(activity_camera.this, "권한이 허용되었습니다", Toast.LENGTH_SHORT);

            camera= Camera.open();
            camera.setDisplayOrientation(90);
            surface=(SurfaceView)findViewById(R.id.surface);
            surfaceHolder=surface.getHolder();
            surfaceHolder.addCallback(activity_camera.this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {//퍼미션 거부되었을때때
            Toast.makeText(activity_camera.this, "권한이 거부되었습니다", Toast.LENGTH_SHORT);
        }
    };

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
    }

    private void refreshCamera(Camera camera) {
        if (surfaceHolder.getSurface()==null){
            return;
        }
        try{//카메라 초기화
            camera.stopPreview();
        } catch (Exception e){
            e.printStackTrace();
        }

        setCamera(camera);
    }

    private void setCamera(Camera cam) {
        camera = cam;
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        refreshCamera(camera);
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}