package com.danthought.mp3encoder;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    // Used to load the 'audioencoder-lib' library on application startup.
    static {
        System.loadLibrary("audioencoder-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File music = getExternalStorageDir("mp3encoder");
                String pcmPath = new File(music, "vocal.pcm").getAbsolutePath();
                String mp3Path = new File(music, "vocal.mp3").getAbsolutePath();

                try {
                    copyRAWtoExternal(R.raw.vocal, pcmPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                int audioChannels = 2;
                int bitRate = 128 * 1024;
                int sampleRate = 44100;
                Log.i(TAG, "pcmPath " + pcmPath);
                Log.i(TAG, "mp3Path " + mp3Path);
                int ret = init(pcmPath, audioChannels, bitRate, sampleRate, mp3Path);
                if (ret >= 0) {
                    Log.i(TAG, "Encode Mp3 Begin");
                    encode();
                    destroy();
                    Log.i(TAG, "Encode Mp3 Success");
                } else {
                    Log.i(TAG, "Encode Initialized Failed...");
                }
            }
        });
    }

    private File getExternalStorageDir(String albumName) {
        File file = new File(Environment.getExternalStorageDirectory(), albumName);
        if (!file.mkdirs()) {
            Log.e(TAG, "Directory not created");
        }
        return file;
    }

    private void copyRAWtoExternal(int id, String path) throws Exception {
        InputStream in = getResources().openRawResource(id);
        FileOutputStream out = new FileOutputStream(path);
        byte[] buff = new byte[1024];
        int read = 0;
        try {
            while ((read = in.read(buff)) > 0) {
                out.write(buff, 0, read);
            }
        } finally {
            in.close();
            out.close();
        }
    }

    /**
     * A native method that is implemented by the 'audioencoder-lib' native library,
     * which is packaged with this application.
     */
    public native int init(String pcmPath, int audioChannels, int bitRate, int sampleRate, String mp3Path);
    public native void encode();
    public native void destroy();
}
