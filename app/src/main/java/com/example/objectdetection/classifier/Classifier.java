package com.example.objectdetection.classifier;

import android.content.Context;
import android.util.Log;
import com.example.objectdetection.R;
import org.opencv.objdetect.CascadeClassifier;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;


public class Classifier {

    private CascadeClassifier cascadeClassifier;               //用于在摄像头中检测目标

    public CascadeClassifier FrontFace(Context context) {
        try {
            InputStream is = context.getResources().openRawResource(R.raw.lbpcascade_frontalface);
            File cascadeDir = context.getDir("cascade", Context.MODE_PRIVATE);
            File mCascadeFile = new File(cascadeDir, "lbpcascade_frontalface.xml");
            FileOutputStream os = new FileOutputStream(mCascadeFile);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            is.close();
            os.close();
            cascadeClassifier = new CascadeClassifier(mCascadeFile.getAbsolutePath());
            if(cascadeClassifier.empty()){
                Log.i("Cascade Error","Cascading classifier failed to load");
                cascadeClassifier = null;
            }
        } catch (Exception e) {
            Log.e("OpenCVActivity", "Error loading cascade", e);
        }
        return cascadeClassifier;
    }

    public CascadeClassifier Eye(Context context) {
        try {
            InputStream is = context.getResources().openRawResource(R.raw.haarcascade_eye);
            File cascadeDir = context.getDir("cascade", Context.MODE_PRIVATE);
            File mCascadeFile = new File(cascadeDir, "haarcascade_eye.xml");
            FileOutputStream os = new FileOutputStream(mCascadeFile);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            is.close();
            os.close();
            cascadeClassifier = new CascadeClassifier(mCascadeFile.getAbsolutePath());
            if(cascadeClassifier.empty()){
                Log.i("Cascade Error","Cascading classifier failed to load");
                cascadeClassifier = null;
            }
        } catch (Exception e) {
            Log.e("OpenCVActivity", "Error loading cascade", e);
        }
        return cascadeClassifier;
    }

    public CascadeClassifier Glass(Context context) {
        try {
            InputStream is = context.getResources().openRawResource(R.raw.haarcascade_eye_tree_eyeglasses);
            File cascadeDir = context.getDir("cascade", Context.MODE_PRIVATE);
            File mCascadeFile = new File(cascadeDir, "haarcascade_eye_tree_eyeglasses.xml");
            FileOutputStream os = new FileOutputStream(mCascadeFile);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            is.close();
            os.close();
            cascadeClassifier = new CascadeClassifier(mCascadeFile.getAbsolutePath());
            if(cascadeClassifier.empty()){
                Log.i("Cascade Error","Cascading classifier failed to load");
                cascadeClassifier = null;
            }
        } catch (Exception e) {
            Log.e("OpenCVActivity", "Error loading cascade", e);
        }
        return cascadeClassifier;
    }

    public CascadeClassifier Nose(Context context) {
        try {
            InputStream is = context.getResources().openRawResource(R.raw.haarcascade_mcs_nose);
            File cascadeDir = context.getDir("cascade", Context.MODE_PRIVATE);
            File mCascadeFile = new File(cascadeDir, "haarcascade_mcs_leftear.xml");
            FileOutputStream os = new FileOutputStream(mCascadeFile);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            is.close();
            os.close();
            cascadeClassifier = new CascadeClassifier(mCascadeFile.getAbsolutePath());
            if(cascadeClassifier.empty()){
                Log.i("Cascade Error","Cascading classifier failed to load");
                cascadeClassifier = null;
            }
        } catch (Exception e) {
            Log.e("OpenCVActivity", "Error loading cascade", e);
        }
        return cascadeClassifier;
    }

    public CascadeClassifier Mouth(Context context) {
        try {
            InputStream is = context.getResources().openRawResource(R.raw.haarcascade_mcs_mouth);
            File cascadeDir = context.getDir("cascade", Context.MODE_PRIVATE);
            File mCascadeFile = new File(cascadeDir, "haarcascade_mcs_leftear.xml");
            FileOutputStream os = new FileOutputStream(mCascadeFile);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            is.close();
            os.close();
            cascadeClassifier = new CascadeClassifier(mCascadeFile.getAbsolutePath());
            if(cascadeClassifier.empty()){
                Log.i("Cascade Error","Cascading classifier failed to load");
                cascadeClassifier = null;
            }
        } catch (Exception e) {
            Log.e("OpenCVActivity", "Error loading cascade", e);
        }
        return cascadeClassifier;
    }

}
