package com.example.objectdetection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import com.example.objectdetection.classifier.Classifier;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener{

    private boolean mIsFrontCamera = false;                    //判断是否是前置摄像头
    private static JavaCameraView openCvCameraView;                   //JavaCameraView控件，显示摄像头拍摄画面
    private CascadeClassifier cascadeClassifier;               //用于在摄像头中检测目标
    private Mat grayImage;
    private int FaceSize;
    private Button change;                                     //切换前后置摄像头
    private Classifier classifier;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        public void onManagerConnected(int status) {
            switch (status) {
                case BaseLoaderCallback.SUCCESS:
                    break;
                default:
                    super.onManagerConnected(status);
                    break;
            }
        }
    };

    public void onCameraViewStarted(int width, int height) {
        grayImage = new Mat(height, width, CvType.CV_8UC4);
        FaceSize = (int) (height * 0.2);
    }

    public void onCameraViewStopped() {

    }

    public Mat onCameraFrame(Mat aInputFrame) {
        Imgproc.cvtColor(aInputFrame, grayImage, Imgproc.COLOR_RGBA2RGB);
        MatOfRect faces = new MatOfRect();
        if (cascadeClassifier != null) {
            cascadeClassifier.detectMultiScale(grayImage, faces, 1.1, 2, 2,
                    new Size(FaceSize, FaceSize), new Size());
        }
        Rect[] facesArray = faces.toArray();
        for (int i = 0; i < facesArray.length; i++) {
            Imgproc.rectangle(aInputFrame, facesArray[i].tl(), facesArray[i].br(), new Scalar(0, 255, 0, 255), 3);
        }
        return aInputFrame;
    }

    private void initView(){
        openCvCameraView = (JavaCameraView) findViewById(R.id.jcv);
        openCvCameraView.setCameraIndex(-1);
        openCvCameraView.setCvCameraViewListener(this);
        change = (Button)findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openCvCameraView.setVisibility(SurfaceView.GONE);
                openCvCameraView = (JavaCameraView) findViewById(R.id.jcv);
                if(!mIsFrontCamera)
                {
                    openCvCameraView.setCameraIndex(1);
                    mIsFrontCamera = true;
                }
                else
                {
                    openCvCameraView.setCameraIndex(-1);
                    mIsFrontCamera = false;
                }
                openCvCameraView.setVisibility(SurfaceView.VISIBLE);
                openCvCameraView.setCvCameraViewListener(MainActivity.this);
                openCvCameraView.enableView();
                mIsFrontCamera = true;
            }
        });
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        classifier = new Classifier();
    }

    protected void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug())
        {
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        }
        else
        {
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
        cascadeClassifier = classifier.FrontFace(this);
        openCvCameraView.enableView();
    }

    public void onPause() {
        super.onPause();
        if(openCvCameraView != null)
        {
            openCvCameraView.disableView();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if(openCvCameraView != null)
        {
            openCvCameraView.disableView();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.frontface:
                cascadeClassifier = classifier.FrontFace(this);
                openCvCameraView.enableView();
                break;
            case R.id.eye:
                cascadeClassifier = classifier.Eye(this);
                openCvCameraView.enableView();
                break;
            case R.id.glass:
                cascadeClassifier = classifier.Glass(this);
                openCvCameraView.enableView();
                break;
            case R.id.nose:
                cascadeClassifier = classifier.Nose(this);
                openCvCameraView.enableView();
                break;
            case R.id.mouth:
                cascadeClassifier = classifier.Mouth(this);
                openCvCameraView.enableView();
                break;
            default:
                break;
        }
        return true;
    }
}
