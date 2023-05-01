package com.example.objectdetectpy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    ImageView resultImage;
    Button captureBtn;
    public String imgString;

    private static final int RC_PIC_CODE=101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //variable to reference the Image view and capture button in the UI.
        resultImage=(ImageView) findViewById(R.id.result_image);
        captureBtn=(Button) findViewById(R.id.capture_btn);

        //Setting event listener on the capture button.
        captureBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                capture();
            }
        });
    }
    private void capture(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, RC_PIC_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RC_PIC_CODE){
            if (resultCode==RESULT_OK){
                //Getting bitmap data
                Bitmap bp=(Bitmap) data.getExtras().get("data");
                resultImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                //Code for conversion to grayscale.

                //Starting python interpreter
                if( !Python.isStarted()){
                    Python.start(new AndroidPlatform(this));
                }

                //Getting python interpreter instance
                final Python py = Python.getInstance();
            }
        }
    }

    //function to convert the bitmap to base 64 string.
    private String getStringImage(@org.jetbrains.annotations.NotNull Bitmap bitmap){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte [] imageBytes=baos.toByteArray();
        String encodedImage=android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}

