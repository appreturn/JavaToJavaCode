package com.mycompany.decompilejavacode;
import android.app.*;
import android.os.*;
import com.mycompany.decompilejavacode.file.FileAsStrean;
import android.widget.*;
import android.content.Intent;
import android.view.*;
import android.content.*;
import java.util.Vector;
import com.ipaulpro.afile.R;
import android.widget.*;
import android.widget.AdapterView.*;
import java.io.*;
import java.util.*;
import jadx.core.dex.nodes.ClassNode;
import jadx.api.*;
import jadx.api.JadxDecompiler;
import jadx.api.JavaClass;
import jadx.api.JavaPackage;
import jadx.core.utils.exceptions.DecodeException;
import jadx.core.utils.exceptions.JadxException;
import jadx.core.dex.visitors.SaveCode;
import java.io.File;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.Iterator;
import jadx.api.*;
import java.io.*;
import jadx.core.utils.exceptions.*;
import java.util.zip.*;
import java.util.Enumeration;
public class MainActivity extends  Activity {
	public   String Path;
	public  String javaWriteCodePath;
	public  final int SHOW_RESPONSE = 0;
	public  String javaCodeStr;
	private  String filesEndString;
	private  TextView textView;
private int errorint;
	public   int duanint=300;

	FileAsStrean fileasstream;
	private    int zhiyuanint=0;
	private  final String zhiyuanIntlujin="apkstojavacodezhiyuan/";
	private  final String zhiyuanIntname="0.txt";
	private  final String zhiyuanapsettingname="st.txt";
	private  final String zhiyuanapkreadname="s.txt";
	private  final String zhiyuanapkerrorname="se.txt";
	
    private EditText javaCode;
	public  String root;
	boolean congqi=false;
	Boolean guanbi=false;
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
				case SHOW_RESPONSE:
					String response = (String) msg.obj;
					if (response.length() > 500) {
						textView.setText(
							response.substring(0, 500));
					} else {
						textView.setText(response);}

					break;

				default:
					break;
			}            
		}

	};


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {


			showExitMessage();


        }
        return true;
    }

    private long lastTime_;
    private void showExitMessage() {
        long time=System.currentTimeMillis();
        if (time - lastTime_ < 2000) {
            onDestroy();
        } else {
			printErrer("再按一次返回键退出");

            lastTime_ = time;
        }
    }

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		startPath();
       if(zhiyuanint==0){ 
        setContentView(R.layout.main);
        textView = (TextView )findViewById(R.id.mainTextView1); 
        textView.setText(getString(R.string.print7) + javaWriteCodePath);
        javaCode = (EditText) findViewById(R.id.mainEditText1);
		} else{
            setContentView(R.layout.main1);
            textView = (TextView )findViewById(R.id.mainTextView1); 
            textView.setText(getString(R.string.print7) + javaWriteCodePath);
            openClick(null);
        } 
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
        javaWriteCodePath = Path + getString(R.string.file_save_end_path);  
        root = Path;
        String zhiyuanIntlujin=/*root + this.zhiyuanIntlujin +*/ addstr(zhiyuanIntname);
        String zhiyuanapkreadlujin=/*root + this.zhiyuanIntlujin + */addstr( zhiyuanapkreadname);
        String zhiyuanapksettinglujin=/*root + this.zhiyuanIntlujin + */addstr(  zhiyuanapsettingname);
       String zhiyuanapkerrorlujin=addstr(zhiyuanapkerrorname);
        
        fileasstream = new FileAsStrean();
        File fi=new File(zhiyuanIntlujin);
        File fs=new File(zhiyuanapkreadlujin);
        File ft=new File(zhiyuanapksettinglujin);
        File fe=new File(zhiyuanapkerrorlujin);
        
        if (fi.exists() && fs.exists() && ft.exists()) {
            zhiyuanint = Integer.parseInt(fileasstream.readFileToString(zhiyuanIntlujin));
            Path = fileasstream.readFileToString(zhiyuanapkreadlujin);
            duanint = Integer.parseInt(fileasstream.readFileToString(zhiyuanapksettinglujin));
		}
        if(fe.exists()){
            errorint=Integer.parseInt(fileasstream.readFileToString(zhiyuanapkerrorlujin));
        }
    }
    private String addstr(String name){
        return root+zhiyuanIntlujin+name;
    }
	private boolean read(String javaCodePath) {
		if (javaCodePath.length() == 0) {
			printErrer(getString(R.string.errer4));
			return false;
		}
		if (!isapksroot(javaCodePath)) {
			printErrer("请输入正确的文件类型，只支持apk,dex,jar,class");
			return false;
		}

		return true;
	}

	private boolean isapksroot(String javaCodePath) {
		return javaCodePath.endsWith(".apk")
			|| javaCodePath.endsWith(".dex")
			|| javaCodePath.endsWith(".jar")
			|| javaCodePath.endsWith(".class");
	}

	public void printErrer(String errerString) {
		Toast.makeText(getApplicationContext(), errerString,
					   Toast.LENGTH_SHORT).show();

	}
	public  boolean runSteat=false;

	@Override
	protected void onDestroy() {

		super.onDestroy();
		System.exit(0);
	}
	public void openClick(View v) {
		if (runSteat) {
			printErrer(getString(R.string.errer5));
			return;
		}
		if (congqi) {
			printErrer("由于jadx有内存泄漏问题，请重启软件后继续反编译，希望大家理解");
			return;
		}
		if (guanbi) {
			printErrer("已反编译完成，请退出软件以释放内存，欢迎再次使用");
			return;
		}
		String javaCodeReadPath="";
		if (zhiyuanint == 0) {
			javaCodeReadPath = javaCode.getText().toString();
		} else {
			javaCodeReadPath = Path;
		}

		if (javaCodeReadPath.length() == 0) {
			printErrer(getString(R.string.errer4));
			return;
		}
		if (read(javaCodeReadPath)) {
			File f=new File(javaCodeReadPath);
			if (!f.exists()) {
				printErrer("文件没找到");
				return;}
			try {

				DecodeActivity dg=new DecodeActivity();
				dg.oneCreate(javaCodeReadPath);
				Path = javaCodeReadPath;
				runSteat = true;
			} catch (Exception e) {
				println(e.toString());
			}

		}
	}
	private  StringBuilder stringbuilder=new StringBuilder();
	private	 StringBuilder  strbf=new StringBuilder();
	private	 Message message = new Message();
	public  void println(String str) {
		strbf = new StringBuilder();
		strbf.append(str).append("\n").append(stringbuilder.toString());
		stringbuilder = strbf;
		message = new Message();
		message.what = SHOW_RESPONSE;
		message.obj = stringbuilder.toString();
		handler.sendMessage(message);
	}

	class DecodeActivity {
		private final JadxDecompiler decompiler  = new JadxDecompiler(zhiyuanint, duanint, MainActivity.this) ;
		private File openFile;
		public void oneCreate(String Path) {
			String childDir=root + getString(R.string.file_save_end_path);
			File f=new File(Path);
			if (f.exists()) {
				childDir = childDir + Path.substring(Path.lastIndexOf("/") + 1, Path.lastIndexOf(".")) + "/";
			}
			decompile(new File(Path), new File(childDir));
		}


		private void decompile(final File apkFile, final File outputDir) {
			if (!outputDir.exists()) {
				outputDir.mkdirs();
			}
			new DecodeThread(apkFile, outputDir).start();
		}

		class DecodeThread extends Thread {
			private File apkFile_;
			private File dexFile_;
			private File outDir_;
			private DecodeThread(File apkFile, File outDir) {
				apkFile_ = apkFile;
				outDir_ = outDir;
				dexFile_ = new File(outDir, "classes_tmp.dex");
			}
			@Override
			public void run() {
				try {
                    long l=System.currentTimeMillis();
                    println("文件"+Path+"读取中，请稍等");
                    openFile(apkFile_);
					long fileb=System.currentTimeMillis();
                    println("文件读取完成，总共用时"+(fileb-l)+"毫秒");
                    println("现在继续第"+(zhiyuanint+1)+"次反编译");
                  l=System.currentTimeMillis();
                    int pi=getClassessize();
							decompiler.index = zhiyuanint * duanint;
					decompiler.setOutputDir(outDir_);
					ThreadPoolExecutor ex =(ThreadPoolExecutor)
						decompiler.getSaveExecutor();
					ex.shutdown();
					while (ex.isTerminating()) {
						if (ex.isTerminated()) {
							break;
						}
						println("当前线程数/" + (zhiyuanint * duanint + ex.getCompletedTaskCount()));
						Thread.sleep(500);
					}
					long j=System.currentTimeMillis();
					println("这次文件反编译用时" + (j - l) + "毫秒");
					println("已经完成了" + (zhiyuanint + 1) + "次反编译");
					/*if (zhiyuanint > 5) {
						println("给你个赞，真是个有耐心的小伙子？小MM");
					}*/
					println("已经反编译" + decompiler.jk(0) + "个文件");
					println("总共有" + pi + "类文件");
					if (decompiler.index >= pi) {
						fileasstream.writeFile(root + zhiyuanIntlujin, zhiyuanIntname, "0");
						fileasstream.writeFile(root + zhiyuanIntlujin, zhiyuanapsettingname, "" + duanint);
                        fileasstream.writeFile(root + zhiyuanIntlujin, zhiyuanapkerrorname, ""+0);
						
						guanbi = true;
                        //runSteat = false;
                     //this.join();
                         println("恭喜你反编译成功");
                        //Thread.sleep(10000);
                        //onDestroy();
					} else {
						fileasstream.writeFile(root + zhiyuanIntlujin, zhiyuanapkreadname, Path);
						zhiyuanint++;
						fileasstream.writeFile(root + zhiyuanIntlujin, zhiyuanIntname, "" + (zhiyuanint));
						fileasstream.writeFile(root + zhiyuanIntlujin, zhiyuanapsettingname, "" + duanint);
						congqi = true;
						println("5秒后将重启软件后继续反编译");
                        Thread.sleep(5000);
                        onDestroy();
                        }
					runSteat = false;
				} catch (Exception e) {
					println("很抱歉这次反编译失败，请多试几次" );
                    fileasstream.writeFile(root + zhiyuanIntlujin, zhiyuanapkerrorname, ""+(errorint+1));
                  // onDestroy(); 
                    }

			}

			public void openFile(File file) {
				openFile = file;
				try {
					decompiler.loadFile(file);
				} catch (Exception e) {} 
			}
			public  List<JavaClass> getClasses() {
				return decompiler.getClasses();
			}
			private int getClassessize() {
				return getClasses().size();
			}
			public List<JavaPackage> getPackages() {
				return decompiler.getPackages();
			}

			public List<ResourceFile> getResources() {
				return decompiler.getResources();
			}

			public File getOpenFile() {
				return openFile;
			}

		}
	}
}



