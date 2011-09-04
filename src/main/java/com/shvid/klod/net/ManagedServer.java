package com.shvid.klod.net;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public abstract class ManagedServer {

  private final static Logger log = Logger.getLogger(ManagedServer.class);
  
  protected abstract void startServer(String bindHost, int bindPort, String serverDir);
  
  public void start(String server, String[] args) throws Exception {

    Properties klodProperties = readFileFromClassPath(Constants.KLOD_PROPERTIES);
    for (Map.Entry<Object, Object> entry : klodProperties.entrySet()) {
      System.setProperty(entry.getKey().toString(), entry.getValue().toString());
    }

    if (args.length == 0) {
      throw new IllegalArgumentException("expected first parameter");
    }

    String binDir = args[0];
    if (binDir.endsWith(File.separator)) {
      binDir = binDir.substring(0, binDir.length() - 1);
    }
      
    String userDir = System.getProperty("user.dir");
    if (!binDir.equals(userDir)) {
      System.setProperty("user.dir", binDir);
    }
      
    String portKey = "klod." + server + ".port";
    String dirKey = "klod." + server + ".dir";
      
    Integer port = Integer.getInteger(portKey);
    if (port == null) {
      throw new IllegalArgumentException("property '" + portKey + "' is empty");
    }
      
    String bindHost = System.getProperty("klod.hostname");
    if (bindHost == null) {
      bindHost = InetAddress.getLocalHost().getHostName();
    }
    
    int bindPort = findFreePort(bindHost, port, 1000);
    int dirNum = bindPort - port + 1;  
      
    String serverDir = System.getProperty(dirKey);
    if (serverDir == null) {
      throw new IllegalArgumentException("property '" + dirKey + "' is empty");
    }
    
    serverDir = serverDir + dirNum;
    
    File serverDirFile = new File(serverDir);
    if (!serverDirFile.exists()) {
      if (!serverDirFile.mkdir()) {
        throw new IOException("fail to create dir " + serverDirFile);
      }
    }
    
    String patternKey = "klod." + server + ".log.pattern";
    String levelKey = "klod." + server + ".log.level";
    String maxBackupIndexKey = "klod." + server + ".log.max-backup-index";
    String maxFileSizeKey = "klod." + server + ".log.max-file-size";

    String fileLog = serverDirFile.getAbsolutePath()+ File.separatorChar + server + ".log";
    RollingFileAppender rollingAppender = new RollingFileAppender(new PatternLayout(System.getProperty(patternKey, "%m%n")), fileLog, true); 
    rollingAppender.setThreshold(Level.toLevel(System.getProperty(levelKey, "ALL")));
    rollingAppender.setMaxBackupIndex(Integer.getInteger(maxBackupIndexKey, 20));
    rollingAppender.setMaxFileSize(System.getProperty(maxFileSizeKey, "10MB")); 
    
    log.removeAllAppenders();
    log.addAppender(rollingAppender);
    
    log.info("Startup Banner for " + server + " at " + new Date() + '\n' + getFormattedProperties());
    log.info("Bind " + server + " at " + bindHost + "[" + bindPort + "]");
    
    String processId = ManagementFactory.getRuntimeMXBean().getName();
    int idx = processId.indexOf('@');
    if (idx != -1) {
      processId = processId.substring(0, idx);
    }
    
    String filePid = serverDirFile.getAbsolutePath()+ File.separatorChar + server + ".pid";
    FileOutputStream fout = new FileOutputStream(filePid);
    try {
      fout.write(processId.getBytes());
    }
    finally {
      fout.close();
    }
    
    startServer(bindHost, bindPort, serverDir);
  }

  private static String getFormattedProperties() {
    List<String> keys = new ArrayList<String>();
    for (Enumeration<Object> i = System.getProperties().keys(); i.hasMoreElements();) {
      String key = i.nextElement().toString();
      if (key.startsWith("klod.")) {
        keys.add(key);
      }
    }
    Collections.sort(keys);
    
    StringBuilder props = new StringBuilder();
    for (String key : keys) {
      props.append(key);
      props.append('=');
      props.append(System.getProperty(key));
      props.append('\n');
    }
    
    return props.toString();
  }
  
  public static Properties readFileFromClassPath(String cfgFile) throws IOException {
    Properties cfg = new Properties();
    if (cfgFile == null) {
      return cfg;
    }
    InputStream stream = null;
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    URL url = classLoader.getResource(cfgFile);
    if (url == null) {
      url = ClassLoader.getSystemResource(cfgFile);
    }
    if (url == null) {
      throw new FileNotFoundException("File not found in classpath " + cfgFile);
    }
    stream = url.openStream();      
    try {
        cfg.load(stream);
    } finally {
      if (stream != null) {
        stream.close();
      }
    }
    return cfg;
  }
  
  public static String getLocalHostName() {
    try {
      return InetAddress.getLocalHost().getHostName();
    }
    catch(Exception e) {
      return "localhost";
    }
  }

  public static int findFreePort(String host, int startPort, int poolSize) throws IOException {
    int port = startPort;
    for (; port != startPort + poolSize; ++port) {
      if (isPortFree(host, port)) {
        break;
      }
    }
    if (port == startPort + poolSize) {
      throw new IOException("All Ports are busy");
    }
    return port;
  }
  
  public static boolean isPortFree(String host, int port) {
    try {
      Socket socket = new Socket(host, port);
      socket.close();
      return false;
    } catch (ConnectException e) {
      return true;
    } catch (SocketException e) {
      if (e.getMessage().equals("Connection reset by peer")) {
        return true;
      }
      throw new RuntimeException(e);
    } catch (UnknownHostException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
}
