package com.bezkoder.spring.files.upload.db.message;



public class ResponseFile {
  private String name;
  private String path;
  public ResponseFile(String name, String url) {
    this.name = name;
    this.path = url;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getPath() {
    return path;
  }
  public void setPath(String url) {
    this.path = path;
  }
}