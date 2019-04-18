package com.divide2.core.data;

/**
 * @author bvvy
 * @date 2018/7/17
 */
public class FileVO {
    private String url;

    private FileVO(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "FileVO{" +
                "url='" + url + '\'' +
                '}';
    }

    public static FileVO of(String url) {
        return new FileVO(url);
    }
}
