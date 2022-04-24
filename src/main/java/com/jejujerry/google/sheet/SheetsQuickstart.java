package com.jejujerry.google.sheet;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;


//Google Cloud Platform 사용자인증정보 > OAuth 2.0클라이언트ID - 다운로드
//클라이언트ID : 922109461574-710n26ujrou2dh5h9keul9knsn77lmo8.apps.googleusercontent.com
//클라이언트보안비밀번호 : umI1Xxjw3-5avX8Hk3sTZMRP
// JSON 다운로드  : 요건 src > main > java > resources 에다가 client_secret.json 두기

public class SheetsQuickstart {
    private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
    //private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
    //private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    //private static final String CREDENTIALS_FILE_PATH = "/certification.json";
    private static final String CREDENTIALS_FILE_PATH = "/client_secret.json";
    //private static final String CREDENTIALS_FILE_PATH = "/client_secret_2.json";
    //private static final String CREDENTIALS_FILE_PATH = "/jejujerry-reale-30950144104d.json";
    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = SheetsQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();

        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    /**
     * Prints the names and majors of students in a sample spreadsheet:
     * https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
     */
    public static void main(String... args) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        //final String spreadsheetId = "1BSVeRrF_MBxWkVdr8Wm3wrhZCD53bfE0dMLRYtMEeAE";

        //Sheets 식별자
        final String spreadsheetId = "1ZnpywI0OLrYBWuiGnvalp3oFoNBVc81XOKQMVtDBvkg";

        //final String range = "Class Data!A2:E";
        //Sheet 식별자
        //final String range = "googlesheetapi!A2:E";
        final String range = "googlesheetapi!A1:W";

        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
        } else {
            //System.out.println("Name, Major");
//            for (List row : values) {
//                // Print columns A and E, which correspond to indices 0 and 4.
//                //System.out.printf("%s, %s, %s, %s, %s, %s\n", row.get(0), row.get(4));
//                //System.out.printf(row.get(0) + " , " + row.get(2) + " , " + row.get(3) + " , " + row.get(4));
//                System.out.printf("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s" +
//                                "%s, %s, %s, %s, %s, %s, %s, %s, %s, %s" +
//                                "%s, %s, %s\n",
//                        row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5), row.get(6), row.get(7), row.get(8), row.get(9),
//                        row.get(10), row.get(11), row.get(12), row.get(13), row.get(14), row.get(15), row.get(16), row.get(17), row.get(18), row.get(19),
//                        row.get(20), row.get(21), row.get(22)
//                );
//            }
            for (List row : values) {
                //System.out.println("잘나오는지");
                System.out.printf("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s" +
                                "%s, %s, %s, %s, %s, %s, %s, %s, %s, %s" +
                                "%s, %s, %s\n",
                        row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5), row.get(6), row.get(7), row.get(8), row.get(9),
                        row.get(10), row.get(11), row.get(12), row.get(13), row.get(14), row.get(15), row.get(16), row.get(17), row.get(18), row.get(19),
                        row.get(20), row.get(21), row.get(22)
                );
            }
        }
    }
}
