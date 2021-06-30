package com.project.storage.item.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

    Logger log = LoggerFactory.getLogger(LoggingRequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        traceRequest(request, body);
        final ClientHttpResponse response = execution.execute(request, body);
        traceResponse(response);
        return response;
    }
    private void traceRequest(HttpRequest request, byte[] body) throws IOException {

        log.info("===========================request begin================================================");
        log.info("URI         : {}", request.getURI());
        log.info("Method      : {}", request.getMethod());
        log.info("Headers     : {}", request.getHeaders());
        log.info("Request body: {}", new String(body, "UTF-8"));
        log.info("==========================request end================================================");
    }
    private void traceResponse(ClientHttpResponse response) throws IOException {
        log.info("============================response begin==========================================");
        log.info("Status code  : {}", response.getStatusCode());
        log.info("Status text  : {}", response.getStatusText());
        log.info("Headers      : {}", response.getHeaders());
        StringBuilder body=new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
            String line = bufferedReader.readLine();
            while (line != null) {
                body.append(line);
                body.append('\n');
                line = bufferedReader.readLine();
            }
        } catch (Exception k) {
            k.printStackTrace();
        }
        log.info("Response body: {}", body==null?"":body.toString());
        log.info("=======================response end=================================================");
    }
}