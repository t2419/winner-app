package com.winner.component;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);
        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) throws IOException {
        System.out.println("Request Method: " + request.getMethod());
        System.out.println("Request URI: " + request.getURI());
        System.out.println("Request Headers: " + request.getHeaders());
        System.out.println("Request Body: " + new String(body, "UTF-8"));
    }

    private void logResponse(ClientHttpResponse response) throws IOException {
        System.out.println("Response Status: " + response.getStatusCode());
        System.out.println("Response Headers: " + response.getHeaders());
//        System.out.println("Response Body: " + getResponseBody(response));
    }

    private String getResponseBody(ClientHttpResponse response) throws IOException {
        // 确保response的内容输入流可以被多次读取
        StringBuffer stringBuffer = new StringBuffer();
//        response.getBody().forEach((byteBuffer) -> stringBuffer.append(new String(byteBuffer.array(), "UTF-8")));
        return stringBuffer.toString();
    }
}