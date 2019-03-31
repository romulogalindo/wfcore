package com.acceso.wfcore.apis;

import com.acceso.wfcore.kernel.ApplicationManager;
import com.acceso.wfcore.listerners.WFCoreListener;
import com.acceso.wfcore.utils.ErrorMessage;
import com.acceso.wfcore.utils.Util;
import com.acceso.wfcore.utils.ValpagJson;
import com.acceso.wfcore.utils.Values;
import com.acceso.wfweb.dtos.ValpagDTO;
import com.acceso.wfweb.utils.JsonResponse;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ShellAPI extends GenericAPI {

    public String EXEC(String command) {
        return EXEC(command, Values.SHELL_REQUEST_TIMEOUT);
    }

    public String EXEC(String command, int timeout) {
        try {
            Process process = Runtime.getRuntime().exec(command);

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

//            int exitVal = process.waitFor(timeout, java.util.concurrent.TimeUnit.SECONDS);
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Success!");
                System.out.println(output);
                System.exit(0);
            } else {
                System.exit(0);
            }
            return new Gson().toJson(JsonResponse.defultJsonResponseOK(output));
        } catch (Exception ep) {
            return new Gson().toJson(JsonResponse.defultJsonResponseERROR(new ErrorMessage(ErrorMessage.ERROR_TYPE_USER, ep.getMessage())));
        }

    }

}
