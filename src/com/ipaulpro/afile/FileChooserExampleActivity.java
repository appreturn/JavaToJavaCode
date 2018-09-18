


package com.ipaulpro.afile;

import android.app.*;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.mycompany.decompilejavacode.file.FileAsStrean;
import com.mycompany.decompilejavacode.MainActivity;

import java .io.File;


public class FileChooserExampleActivity extends Activity {

    private static final String TAG = "FileChooserExampleActivity";

    private static final int REQUEST_CODE = 6384; 
                                                  
    public   int duanint=300;

    private String Path;
    private String filesEndString;
    FileAsStrean fileasstream;
    private    int zhiyuanint=0;
    private  final String zhiyuanIntlujin="apkstojavacodezhiyuan/";
    private  final String zhiyuanIntname="0.txt";
    private  final String zhiyuanapsettingname="st.txt";
    private  final String zhiyuanapkreadname="s.txt";
  
    private final String zhiyuanerrorpath="se.txt";
    
    
    
    // private EditText javaCode;
    public  String root;
	private int errorint;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startPath();
  /*      if(errorint>0){
           fuweishujuerror(); 
            
        }*/
        if(errorint>5){
            printErrer("抱歉，反编译失败");
          //  fuweishuju();
            onDestroy();
        }
        if(zhiyuanint!=0){
            showChooser();
            return;
        }
        Button button = new Button(this);
       // button.setId(R.id.button1);
        button.setText("开始反编译之旅");
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fuweishuju();
                showChooser();
            }
        });

        setContentView(button);
    }
    public void printErrer(String errerString) {
        Toast.makeText(getApplicationContext(), errerString,
                       Toast.LENGTH_SHORT).show();

	}
    
    @Override
    protected void onDestroy() {

        super.onDestroy();
       // fuweishuju();
        System.exit(0);
	}
    private void fuweishuju(){
        
        fileasstream.writeFile(root + zhiyuanIntlujin, zhiyuanIntname, "0");
       // fileasstream.writeFile(root + zhiyuanIntlujin, zhiyuanapsettingname, "" + duanint);
        fileasstream.writeFile(root + zhiyuanIntlujin, zhiyuanerrorpath, ""+0);
        
    }
    private void fuweishujuerror(){
int zs=zhiyuanint*duanint;
duanint=duanint-100>0?duanint-100:duanint;
      zhiyuanint=zs/duanint;
fileasstream.writeFile(root + zhiyuanIntlujin, zhiyuanIntname, ""+zhiyuanint);
        fileasstream.writeFile(root + zhiyuanIntlujin, zhiyuanapsettingname, "" + duanint);
       // fileasstream.writeFile(root + zhiyuanIntlujin, zhiyuanerrorpath, ""+0);

    }
    
    private void startPath(){

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Path = Environment.getExternalStorageDirectory().getPath();
        } else {
            Path = java.io.File.separator;
        }   
        filesEndString = getString(R.string.file3);
        if (!Path.endsWith(filesEndString)) {
            Path = Path + filesEndString;
        }
      //  javaWriteCodePath = Path + getString(R.string.file_save_end_path);  
        root = Path;
        String zhiyuanIntlujin=root + this.zhiyuanIntlujin + zhiyuanIntname;
        String zhiyuanapkreadlujin=root + this.zhiyuanIntlujin + zhiyuanapkreadname;
        String zhiyuanapksettinglujin=root + this.zhiyuanIntlujin + zhiyuanapsettingname;
        String zhiyuanapkerrorlujin=root + this.zhiyuanIntlujin + "se.txt";
        
        fileasstream = new FileAsStrean();
        File fi=new File(zhiyuanIntlujin);
        File fs=new File(zhiyuanapkreadlujin);
        File ft=new File(zhiyuanapksettinglujin);
        File fe=new File(zhiyuanapkerrorlujin);
        
        if (fi.exists() && fs.exists() && ft.exists()) {
            zhiyuanint = Integer.parseInt(fileasstream.readFileToString(zhiyuanIntlujin));
            Path = fileasstream.readFileToString(zhiyuanapkreadlujin);
           // duanint = Integer.parseInt(fileasstream.readFileToString(zhiyuanapksettinglujin));
        }
        if(fe.exists()){
            errorint=Integer.parseInt(fileasstream.readFileToString(zhiyuanapkerrorlujin));
        }
    }
    
    private void showChooser() {
        
       
 try {
            startActivity(new Intent(this, MainActivity.class));
        } catch (ActivityNotFoundException e) {
            
        }
    }

   

}
