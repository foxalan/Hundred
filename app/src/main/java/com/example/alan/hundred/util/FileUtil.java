package com.example.alan.hundred.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;



/**
 * 
 * @author Kenneth
 *
 */
public class FileUtil {

	static int countFiles = 0;

	/**
	 * 获取SD卡存储路径 注: 不推荐使用, android 4.0 以上获取到的路径为手机内存路径(内置SD卡)
	 */
	public static String getSDcardPath() {
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			// 若SD卡存在，返回SD卡根目录
			return Environment.getExternalStorageDirectory().getPath();
		}
		return null;
	}

	/**
	 * 获取机身存储路径, 即/data, 可以通过openFileInput,openFileOutput进行操作
	 */
	public static String getDataPath() {
		return Environment.getDataDirectory().getAbsolutePath();
	}

	/**
	 * 获取外置TF卡路径
	 */
	public static String getTFcardPath() {
		String path = null;
		try {
			InputStream ins = Runtime.getRuntime().exec("mount")
					.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					ins));
			String line = "";
			while ((line = reader.readLine()) != null) {
				if (line.contains("sdcard")) {
					if (line.contains("vfat") || line.contains("fuse")) {
						String split[] = line.split(" ");
						path = split[1];
					}
				}
			}
			reader.close();
			ins.close();

		} catch (IOException e) {
			return null;
		}
		return path;
	}

	/**
	 * 获取路径的存储空间大小
	 */
	@SuppressWarnings("deprecation")
	public static String getPathSizeInfo(String path) {
		if (null != path) {
			StatFs sf = new StatFs(path);
			long blockSize = sf.getBlockSize();
			long blockCount = sf.getBlockCount();
			long availCount = sf.getAvailableBlocks();
			return "共" + blockSize * blockCount / 1024 / 1024 + "MB" + ", 剩"
					+ availCount * blockSize / 1024 / 1024 + "MB";
		}
		return "未挂载";
	}

	/**
	 * 创建文件夹，若mkdirs一直false，需查看文件夹权限，可能sd目录和extsd目录的权限相同，
	 * 都是system，但用户组不一样，sd是sdcard_rw，外部sd是media_rw，修改extsd的用户组到sdcard_rw
	 */
	public static void mkdirs(String dirPath) {
		File dir = new File(dirPath);
		if (!dir.exists()) {
			if (dir.mkdirs() == false) {
				System.err.println("+++ " + dirPath + " 文件夹创建失败!");
			}
		}
	}

	/**
	 * 查找指定文件夾下的包含keyword的文件
	 * 
	 * @param folder
	 * @param keyword
	 * @return
	 */
	public static File[] searchFile(File folder, final String keyword) {
		File[] subFolders = folder.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if (pathname.isFile())
					countFiles++;
				if (pathname.isFile() && pathname.getName().contains(keyword)) {
					return true;
				}
				return false;
			}
		});

		return subFolders;
	}
	
	/**
	 * 查找指定文件夾下的满足正则表达式的文件
	 * 
	 * @param folder
	 * @param regexp
	 * @return
	 */
	public static File[] searchFileWithRegexp(File folder, final String regexp) {
		File[] subFolders = folder.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if (pathname.isFile())
					countFiles++;
				if (pathname.isFile() && pathname.getName()
						.toLowerCase(Locale.getDefault()).matches(regexp)) {
					return true;
				}
				return false;
			}
		});

		return subFolders;
	}
	
	// 拷贝文件
	public static void copyFile(String oldPath, String newPath) {
		InputStream inStream = null;
		FileOutputStream fs = null;
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				inStream = new FileInputStream(oldPath); // 读入原文件
				fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1024];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 將一個文件夾下的所有文件複製到指定的文件夾下
	 * 
	 * @author Janet
	 * @date 2013-05-04
	 * @param path
	 * @param copyPath
	 * @throws IOException
	 */
	public static void copy(String path, String copyPath) throws IOException {
		File filePath = new File(path);
		DataInputStream read;
		DataOutputStream write;
		if (filePath.isDirectory()) {
			File[] list = filePath.listFiles();
			System.out.println("dl video file size:" + list.length);
			for (int i = 0; i < list.length; i++) {
				String newPath = "", newCopyPath = "";
				if (path.endsWith(File.separator)) {
					newPath = path + list[i].getName();
				} else {
					newPath = path + File.separator + list[i].getName();
				}
				if (newCopyPath.endsWith(File.separator)) {
					newCopyPath = copyPath + list[i].getName();
				} else {
					newCopyPath = copyPath + File.separator + list[i].getName();
				}
				System.out.println("newPath:" + newPath);
				System.out.println("newCopyPath:" + newCopyPath);
				File newFile = new File(copyPath);
				if (!newFile.exists()) {
					newFile.mkdir();
				}
				copy(newPath, newCopyPath);
			}
		} else if (filePath.isFile()) {
			System.out.println("path:" + path);
			System.out.println("copyPath:" + copyPath);
			read = new DataInputStream(new BufferedInputStream(
					new FileInputStream(path)));
			write = new DataOutputStream(new BufferedOutputStream(
					new FileOutputStream(copyPath)));
			byte[] buf = new byte[1024 * 512];
			while (read.read(buf) != -1) {
				write.write(buf);
			}
			read.close();
			write.close();
		} else {
			/* System.out.println("请输入正确的文件名或路径名"); */
		}
	}

	/**
	 * 刪除指定目錄下的所有的文件
	 * 
	 * @author Janet
	 * @date 2013-05-04
	 * @param path
	 */
	public static void delAllFileInFolder(String path) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				return;
			}
			if (!file.isDirectory()) {
				return;
			}
			String[] tempList = file.list();

			File temp = null;
			for (int i = 0; i < tempList.length; i++) {
				if (path.endsWith(File.separator)) {
					temp = new File(path + tempList[i]);
				} else {
					temp = new File(path + File.separator + tempList[i]);
				}
				System.out.println(temp);
				if (temp.isFile()) {
					temp.delete();
				}
			}
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 生产文件 如果文件所在路径不存在则生成路径
	 * 
	 * @param fileName
	 *            文件名 带路径
	 * @param isDirectory
	 *            是否为路径
	 * @return
	 * @author Janet
	 * @date 2013-05-13
	 */
	public static File buildFile(String fileName, boolean isDirectory) {
		File target = new File(fileName);
		if (isDirectory) {
			target.mkdirs();
		} else {
			if (!target.getParentFile().exists()) {
				target.getParentFile().mkdirs();
				target = new File(target.getAbsolutePath());
			}
		}
		return target;
	}
	
	/**
	 * 拷贝工程中assets下的文件至data/data/.../files文件夹
	 */
//	public static void copyAssetFileToFiles(String fileName) {
//		try {
//			InputStream is = MyApp.getContext().getAssets().open(fileName);
//			byte[] buffer = new byte[is.available()];
//			is.read(buffer);
//			is.close();
//
//			File of = new File(MyApp.getContext().getFilesDir() + "/"
//					+ fileName);
//			of.createNewFile();
//			FileOutputStream os = new FileOutputStream(of);
//			os.write(buffer);
//			os.close();
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//	}

	/**
	 * 拷贝工程中assets下的文件至desDirPath文件夹
	 */
	public static void copyAssetFileToFiles(Context context, String filePath, String desDirPath) {
		try {
			InputStream is = context.getAssets().open(filePath);
			byte[] buffer = new byte[is.available()];
			is.read(buffer);
			is.close();

			File of = new File(desDirPath + "/" + FileUtil.getFileName(filePath));
			of.createNewFile();
			FileOutputStream os = new FileOutputStream(of);
			os.write(buffer);
			os.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * 拷贝工程中assets下的文件夹至data/data/.../files文件夹
	 */
//	public static void copyAssetDirToFiles(String dirName) {
//		String fileDir = MyApp.getContext().getFilesDir() + "/" + dirName;
//		System.out.println(fileDir);
//		mkdirs(fileDir);
//		AssetManager assetManager = MyApp.getContext().getAssets();
//
//		try {
//			String[] children = assetManager.list(dirName);
//			if (children.length != 0) {
//				for (String child : children) {
//					child = dirName + '/' + child;
//					String[] grandChildren = assetManager.list(child);
//					if (0 == grandChildren.length) {
//						copyAssetFileToFiles(child);
//					} else {
//						copyAssetDirToFiles(child);
//					}
//				}
//				System.out.println("+++ /assets/" + dirName + "文件夹拷贝完成");
//			} else {
//				System.out.println("+++ /assets/" + dirName + "文件夹子文件个数为0");
//			}
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//	}

	/**
	 * 拷贝工程中assets下的文件夹至desDirPath文件夹
	 */
	public static void copyAssetDirToFiles(Context context, String dirName, String desDirPath) {
		mkdirs(desDirPath);
		AssetManager assetManager = context.getAssets();
		try {
			String[] children = assetManager.list(dirName);
			if (children.length != 0) {
				for (String child : children) {
					child = dirName + '/' + child;
					String[] grandChildren = assetManager.list(child);
					if (0 == grandChildren.length) {
						copyAssetFileToFiles(context, child, desDirPath);
					} else {
						copyAssetDirToFiles(context, child, desDirPath);
					}
				}
				System.out.println("+++ /assets/" + dirName + "文件夹拷贝完成");
			} else {
				System.out.println("+++ /assets/" + dirName + "文件夹子文件个数为0");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void copyDataBaseDir(Context context, String dirName){
		System.out.println("dirdir:"+context.getFilesDir() + "/" + dirName);
		try {
			copy(context.getFilesDir() + "/" + dirName, "/data/data/cn.fxn.fsvmpczr_ui/databases/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getFileName(String filePath)
	{
		return filePath.substring(
				filePath.lastIndexOf(System.getProperty("file.separator")) + 1);
	}
	
	public static String readFile(String filePath)
	{
		File file = new File(filePath);
		FileInputStream fis = null;
		if (file.exists())
		{
			try {
				fis = new FileInputStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				StringBuilder sb = new StringBuilder();
				String line = null;
				// 循环读取文件内容
				while ((line = br.readLine()) != null)
				{
					sb.append(line + "\n");
				}
				// 关闭资源
				br.close();
				return sb.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			System.err.println("+++ " + filePath + "文件不存在");
		}
		return null;
	}
	
	public static void writeFile(String filePath, String content)
	{
		File file = new File(filePath);
		if (file.exists())
		{
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(file);
				fos.write(content.getBytes());  
			    fos.close();  
			} catch (Exception e) {
				e.printStackTrace();
			}  
		}
		else
		{
			System.err.println("+++ " + filePath + "文件不存在");
		}
	}
	
	public static void replaceLine(String filePath, String oldContent, String newContent)
	{
		String content = readFile(filePath);
		if (null != content)
		{
			content = content.replace(oldContent, newContent);
			// 如果替换最后一行为""，会有两个回车换行符
			if (content.endsWith("\n\n"))
			{
				// 去除最后的回车换行符
				content = content.substring(0, content.length() - 1);
			}
			writeFile(filePath, content);
		}
	}
	
}
