package com.acceso.wfcore.apis;

import com.acceso.wfcore.utils.ErrorMessage;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfweb.utils.JsonResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

public class HttpAPI extends GenericAPI {

    public String GET(String url) {
        return GET(url, 10);
    }

    public String GET(String url, int timeout) {
        URL _url;
        HttpURLConnection conHTTP;
        HttpsURLConnection conHTTPS;
        String response = null;

        try {
            _url = new URL("http://example.com");
            if (_url.getProtocol().contentEquals("http")) {
                conHTTP = (HttpURLConnection) _url.openConnection();
                conHTTP.setConnectTimeout(timeout);
                conHTTP.setReadTimeout(timeout);
                conHTTP.setRequestMethod("GET");
                StringBuilder content;

                try ( BufferedReader in = new BufferedReader(
                        new InputStreamReader(conHTTP.getInputStream()))) {

                    String line;
                    content = new StringBuilder();

                    while ((line = in.readLine()) != null) {
                        content.append(line);
                        content.append(System.lineSeparator());
                    }
                }

                response = content.toString();
                conHTTP.disconnect();
            } else {
                conHTTPS = (HttpsURLConnection) _url.openConnection();
                conHTTPS.setConnectTimeout(timeout);
                conHTTPS.setReadTimeout(timeout);
                conHTTPS.setRequestMethod("GET");
                StringBuilder content;

                try ( BufferedReader in = new BufferedReader(
                        new InputStreamReader(conHTTPS.getInputStream()))) {

                    String line;
                    content = new StringBuilder();

                    while ((line = in.readLine()) != null) {
                        content.append(line);
                        content.append(System.lineSeparator());
                    }
                }

                response = content.toString();
                conHTTPS.disconnect();
            }

        } catch (Exception ep) {
            Util.toJSON(JsonResponse.defultJsonResponseERROR(new ErrorMessage(ErrorMessage.ERROR_TYPE_USER, ep.getMessage())));
        }

        return Util.toJSON(JsonResponse.defultJsonResponseOK(response));
    }

    public String POST(String url) {
        return POST(url, Values.HTTP_REQUEST_TIMEOUT, new HashMap<>());
    }

    public String POST(String url, int timeout) {
        return POST(url, timeout, new HashMap<>());
    }

    public String POST(String url, Map<String, String> params) {
        return POST(url, Values.HTTP_REQUEST_TIMEOUT, params);
    }

    public String POST(String url, int timeout, Map<String, String> params) {
        URL _url;
        HttpURLConnection conHTTP;
        HttpsURLConnection conHTTPS;
        String response = null;

        try {
            _url = new URL("http://example.com");
            if (_url.getProtocol().contentEquals("http")) {
                conHTTP = (HttpURLConnection) _url.openConnection();
                conHTTP.setConnectTimeout(timeout);
                conHTTP.setReadTimeout(timeout);
                conHTTP.setRequestMethod("POST");
                conHTTP.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                String cparams = "";
                for (HashMap.Entry param : params.entrySet()) {
                    cparams += param.getKey() + "=" + param.getValue() + "&";
                }

                OutputStreamWriter writer = new OutputStreamWriter(
                        conHTTP.getOutputStream());
                writer.write(cparams);
                writer.flush();

                StringBuilder content;

                try ( BufferedReader in = new BufferedReader(
                        new InputStreamReader(conHTTP.getInputStream()))) {

                    String line;
                    content = new StringBuilder();

                    while ((line = in.readLine()) != null) {
                        content.append(line);
                        content.append(System.lineSeparator());
                    }
                }

                response = content.toString();
                conHTTP.disconnect();
            } else {
                conHTTPS = (HttpsURLConnection) _url.openConnection();
                conHTTPS.setConnectTimeout(timeout);
                conHTTPS.setReadTimeout(timeout);
                conHTTPS.setRequestMethod("POST");
                conHTTPS.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                String cparams = "";
                for (HashMap.Entry param : params.entrySet()) {
                    cparams += param.getKey() + "=" + param.getValue() + "&";
                }

                OutputStreamWriter writer = new OutputStreamWriter(
                        conHTTPS.getOutputStream());
                writer.write(cparams);
                writer.flush();

                StringBuilder content;

                try ( BufferedReader in = new BufferedReader(
                        new InputStreamReader(conHTTPS.getInputStream()))) {

                    String line;
                    content = new StringBuilder();

                    while ((line = in.readLine()) != null) {
                        content.append(line);
                        content.append(System.lineSeparator());
                    }
                }

                response = content.toString();
                conHTTPS.disconnect();
            }

        } catch (Exception ep) {
            Util.toJSON(JsonResponse.defultJsonResponseERROR(new ErrorMessage(ErrorMessage.ERROR_TYPE_USER, ep.getMessage())));
        }

        return Util.toJSON(JsonResponse.defultJsonResponseOK(response));
    }

}
