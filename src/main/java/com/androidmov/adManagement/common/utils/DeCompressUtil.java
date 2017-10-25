package com.androidmov.adManagement.common.utils;

import java.io.*;

import com.androidmov.adManagement.common.constants.ResultCode;
import com.androidmov.adManagement.common.exceptions.ValidException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;

/**
 * 解压缩工具包
 */
public final class DeCompressUtil {

    /**
     * 解压缩
     */
    public static void deCompress(String sourceFile, String destDir) throws Exception {
	// 保证文件夹路径最后是"/"或者"\"
	char lastChar = destDir.charAt(destDir.length() - 1);
	if (lastChar != '/' && lastChar != '\\') {
	    destDir += File.separator;
	}
	// 根据类型，进行相应的解压缩
	String type = sourceFile.substring(sourceFile.lastIndexOf(".") + 1);
	if (type.equals("zip")) {
	    DeCompressUtil.unzip(sourceFile, destDir);
	} else if (type.equals("rar")) {
	   // DeCompressUtil.unrar(sourceFile, destDir);
	} else {
	    throw new ValidException(ResultCode.COMPRESS_FILE_FAIL);
	}
    }

    /**
     * 解压zip格式压缩包 对应的是ant.jar
     */
    private static void unzip(String sourceZip, String destDir) throws Exception {
		try {
			Project p = new Project();
			Expand e = new Expand();
			e.setProject(p);
			e.setSrc(new File(sourceZip));
			e.setOverwrite(false);
			e.setDest(new File(destDir));
			e.setEncoding("gbk");
			e.execute();
		} catch (Exception e) {
			throw new ValidException(ResultCode.COMPRESS_FILE_FAIL);
		}
    }

    /**
     * 解压rar格式压缩包。 对应的是java-unrar-0.3.jar，但是java-unrar-0.3.jar又会用到commons-logging-1.1.1.jar
     */
   /* private static void unrar(String sourceRar, String destDir) throws Exception {
	Archive a = null;
	FileOutputStream fos = null;
	try {
	    a = new Archive(new File(sourceRar));
	    FileHeader fh = a.nextFileHeader();
	    while (fh != null) {
		if (!fh.isDirectory()) {
		    // 1 根据不同的操作系统拿到相应的 destDirName 和 destFileName
		    String compressFileName = fh.getFileNameString().trim();
		    String destFileName = "";
		    String destDirName = "";
		    // 非windows系统
		    if (File.separator.equals("/")) {
			destFileName = destDir + compressFileName.replaceAll("\\\\", "/");
			destDirName = destFileName.substring(0, destFileName.lastIndexOf("/"));
			// windows系统
		    } else {
			destFileName = destDir + compressFileName.replaceAll("/", "\\\\");
			destDirName = destFileName.substring(0, destFileName.lastIndexOf("\\"));
		    }
		    // 2创建文件夹
		    File dir = new File(destDirName);
		    if (!dir.exists() || !dir.isDirectory()) {
			dir.mkdirs();
		    }
		    // 3解压缩文件
		    fos = new FileOutputStream(new File(destFileName));
		    a.extractFile(fh, fos);
		    fos.close();
		    fos = null;
		}
		fh = a.nextFileHeader();
	    }
	    a.close();
	    a = null;
	} catch (Exception e) {
	    throw e;
	} finally {
	    if (fos != null) {
		try {
		    fos.close();
		    fos = null;
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	    if (a != null) {
		try {
		    a.close();
		    a = null;
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	}
    }*/
}