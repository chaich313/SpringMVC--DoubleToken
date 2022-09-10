package com.itranlin.reimu;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

/**
 * @author itranlin
 * @since 2022/6/8 14:56
 */
public class MainTest {
    private static final Pattern progressPattern = Pattern.compile("progress-\\d");

    public static void main(String[] args) throws URISyntaxException {
        System.out.println(progressPattern.matcher("progress-e").matches());

        URI url = new URI("http://www.runoob.com/index.html?language=cn#j2se");

        System.out.println("URL 为：" + url);
        System.out.println("getAuthority：" + url.getAuthority());
        System.out.println("getHost：" + url.getHost());
        System.out.println("getPath：" + url.getPath());
        System.out.println("getQuery：" + url.getQuery());
        System.out.println("getUserInfo：" + url.getUserInfo());
        System.out.println("getFragment：" + url.getFragment());
        System.out.println("getRawAuthority：" + url.getRawAuthority());
        System.out.println("getRawFragment：" + url.getRawFragment());
        System.out.println("getRawPath：" + url.getRawPath());
        System.out.println("getRawQuery：" + url.getRawQuery());
        System.out.println("getRawSchemeSpecificPart：" + url.getRawSchemeSpecificPart());
        System.out.println("getRawUserInfo：" + url.getRawUserInfo());
        System.out.println("getScheme：" + url.getScheme());
        URI uri = new URI(url.getScheme(), url.getAuthority(), url.getPath(),null,url.getFragment());
        System.out.println(uri);
    }
}
