package com.mycompany.decompilejavacode.file;

import java.io.*;



public class FileAsStrean
{
	public String readFileToString(final String filePath) {
		InputStream in = null;
		String  string ="";
		File file=new File(filePath);
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[(int)file.length()];
			in.read(buffer);
			string=new String( buffer,"UTF-8");
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			if (null != in) {
				try {
					in.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return   string;
	}


	/* 写文件 */
	public void writeFile(String path,String file, String text) {




		BufferedWriter bw = null;
		try {
			File saveFolder = new File(path);
			if (!saveFolder.exists()){
				saveFolder.mkdirs();
			}
			saveFolder= new File(path,file);
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saveFolder), "UTF-8"));
			bw.write(text);
			//System.out.println("保存在"+saveFolder.getAbsoluteFile());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {}
			}
		}
	}


}

