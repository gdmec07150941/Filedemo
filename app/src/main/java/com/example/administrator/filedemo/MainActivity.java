package com.example.administrator.filedemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private File fPhonedirectory;
    private File fExternalStoragePublicDirectory;
    private File fExternalStorageDirectory;
    private File fDataStorage;
    private File fDownloadCacheDirectory;
    private File fRootDirectory;
    private String name, path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.result);

        fPhonedirectory = this.getFilesDir();
        fExternalStoragePublicDirectory =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        fExternalStorageDirectory = Environment.getExternalStorageDirectory();
        fDataStorage = Environment.getDataDirectory();
        fDownloadCacheDirectory = Environment.getDownloadCacheDirectory();
        fRootDirectory = Environment.getRootDirectory();
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_REMOVED)) {
            Button btn = (Button) findViewById(R.id.externalStorageDiectory);
            btn.setEnabled(false);
        }
    }
    public void phoneDicectory(View view){
        path = fPhonedirectory.getPath();
        try {
            FileOutputStream fos = openFileOutput("text.txt", MODE_PRIVATE);
            fos.write("hello".getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        listFiles(path);
    }


    public void extrenalStoragePublicDirectory(View view){
        path = fExternalStoragePublicDirectory.getAbsolutePath();
        listFiles(path);
    }
    public void externalStorageDiectory(View view){
        path = fExternalStorageDirectory.getAbsolutePath();
        listFiles(path);
    }
    public void dateStorage(View view){
        path = fDataStorage.getAbsolutePath();
        listFiles(path);
    }
    public void downloadCacheDirectory(View view){
        path = fDownloadCacheDirectory.getAbsolutePath();
        listFiles(path);
    }
    public void rootDiectory(View view){
        path = fRootDirectory.getAbsolutePath();
        listFiles(path);
    }
    private boolean listFiles(String path) {
        name = "路径:"+path+"\n文件清单:\n";
        File file = new File(path);
        if (file.listFiles() != null && file.listFiles().length > 0) {
            for (File file1 : file.listFiles()) {
                path = file1.getAbsolutePath();
                name += file1.getName() + "\n";
            }
        }
        tv1.setText(name);
        return true;
    }
}
