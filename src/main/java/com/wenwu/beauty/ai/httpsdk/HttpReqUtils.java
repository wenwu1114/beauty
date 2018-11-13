package com.wenwu.beauty.ai.httpsdk;

import com.wenwu.beauty.ai.model.faceage.ParamsModel;
import com.wenwu.beauty.ai.utils.CommonTools;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

public class HttpReqUtils {

    /**
     * 创建客户端
     * @return
     * @throws Throwable
     */
    private CloseableHttpClient createSSLClientDefault() throws Throwable{
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                return true;
            }
        }).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {
            @Override
            public boolean verify(String hostName, SSLSession sslSession) {
                return true;
            }
        });
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }

    /**
     * 发送post请求
     * @param url
     * @param contentType
     * @return 返回json字符串
     * @throws Throwable
     */
    public String sendHttpPostReq(String url, Map<String,Object> paramsMap, String contentType) throws Throwable{
        CloseableHttpClient httpClient = createSSLClientDefault();
        HttpPost post = new HttpPost();
        post.setURI(new URI(url));
        post.addHeader("Content-Type", contentType);
        post.setEntity(new UrlEncodedFormEntity(CommonTools.getPostData(paramsMap),"UTF-8"));
        CloseableHttpResponse response = httpClient.execute(post);
        // System.out.println(post);
        System.out.println(EntityUtils.toString(post.getEntity()));
        String resultStr = EntityUtils.toString(response.getEntity());
        return resultStr;
    }
}
