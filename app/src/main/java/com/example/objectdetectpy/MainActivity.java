package com.example.objectdetectpy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
}

