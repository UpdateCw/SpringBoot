package com.androidmov.adManagement.common.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;

/**
 * Created by Administrator on 2017/6/8 0008.
 */
public class FTPUtil {
    private volatile static FTPClient ftpClient = new FTPClient();
    private volatile static String sysRootDir;

    public static synchronized boolean startFTPConnect() throws ConfigurationException, IOException {
        Configuration cfg = new PropertiesConfiguration("projectConfigs/appConfig.properties");
        ftpClient.connect(cfg.getString("ftpServerHost"),cfg.getInt("ftpServerPort"));
        ftpClient.login(cfg.getString("ftpUserName"),cfg.getString("ftpPassword"));
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.setControlEncoding(cfg.getString("ftpEncoding"));
        ftpClient.setBufferSize(81920);
        sysRootDir = cfg.getString("rootWorkingDir");
        return ftpClient.changeWorkingDirectory(sysRootDir);
    }

    public static synchronized boolean startFTPConnect(
            String host,Integer port,String userName, String password,String encoding,String rootDir
    ) throws ConfigurationException, IOException {
        ftpClient.connect(host,port);
        ftpClient.login(userName,password);
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.setControlEncoding(encoding);
        ftpClient.setBufferSize(81920);
        sysRootDir = rootDir;
        return ftpClient.changeWorkingDirectory(sysRootDir);
    }

    public static synchronized boolean closeFTPConnect() throws IOException {
        boolean result = false;
        result = ftpClient.logout();
        ftpClient.disconnect();
        return result;
    }

    public static synchronized boolean setRootDir(final String custRootPath) throws IOException {
        String path = null;
        if (custRootPath == null || custRootPath.equals("")){
            path = sysRootDir;
        }else {
            path = custRootPath;
        }
        ftpClient.enterLocalPassiveMode();
        return ftpClient.changeWorkingDirectory(path);
    }

    public static synchronized boolean mkdir(final String path) throws IOException {
        if (path == null || path.equals("")){
            return false;
        }
        ftpClient.enterLocalPassiveMode();
        boolean state =false;
        String[] dirs = path.split("/");
        for (String dir : dirs){
            if (!ftpClient.changeWorkingDirectory(dir)){
                if (ftpClient.makeDirectory(dir)){
                    if (!ftpClient.changeWorkingDirectory(dir)){
                        return false;
                    }else {
                        continue;
                    }
                }
            }
            state = true;
        }
        return state;
    }

    public static synchronized boolean upload(final String localFilePath,final String remoteFileName) throws IOException {
        if (localFilePath == null || localFilePath.equals("")){
            Log.record("localFilePath == null || localFilePath.equals(\"\")");
            return false;
        }
        ftpClient.enterLocalPassiveMode();
        boolean state = false;
        InputStream is = null;
        try {
            is = new FileInputStream(localFilePath);
            state = ftpClient.storeFile(remoteFileName,is);
            Log.record("ftpClient.storeFile(remoteFileName,is) : "+state);
        } catch (FileNotFoundException e) {
            Log.record("FTP upload FileNotFoundException");
            return false;
        }finally {
            if (is != null){
                is.close();
            }
        }
        return state;
    }

    /**
     *
     * @param file 上传的文件或文件夹
     * @throws Exception
     */
    public static synchronized void uploads(File file) throws Exception {
        if (file.isDirectory()) {
            ftpClient.enterLocalPassiveMode();
            ftpClient.makeDirectory(file.getName());
            ftpClient.changeWorkingDirectory(file.getName());
            String[] files = file.list();
            for (int i = 0; i < files.length; i++) {
                File file1 = new File(file.getPath() + "\\" + files[i]);
                if (file1.isDirectory()) {
                    uploads(file1);
                    ftpClient.changeToParentDirectory();
                } else {
                    File file2 = new File(file.getPath() + "\\" + files[i]);
                    FileInputStream input = new FileInputStream(file2);
                    ftpClient.storeFile(file2.getName(), input);
                    input.close();
                }
            }
        } else {
            File file2 = new File(file.getPath());
            FileInputStream input = new FileInputStream(file2);
            ftpClient.storeFile(file2.getName(), input);
            input.close();
        }

    }
    public  static synchronized boolean download(String remoteFileName, String localFileName,int notFoundLoop)
            throws IOException {
        boolean flag = false;
        File file = new File(localFileName);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            int count = 0;
            do {
                flag = ftpClient.retrieveFile(remoteFileName, fileOutputStream);
                if (flag || notFoundLoop <= 0){
                    break;
                }
                count++;
                Log.record("not find special file on ftp server , will try again in 10 min later");
                Thread.sleep(5*60*1000);
            }while (count < notFoundLoop);
        } catch (IOException e) {
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null){
                fileOutputStream.close();
            }
        }
        return flag;
    }
}
