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
import com.jejujerry.google.sheet.dto.RealeItem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//Google Cloud Platform 사용자인증정보 > OAuth 2.0클라이언트ID - 다운로드
//클라이언트ID : 922109461574-710n26ujrou2dh5h9keul9knsn77lmo8.apps.googleusercontent.com
//클라이언트보안비밀번호 : umI1Xxjw3-5avX8Hk3sTZMRP
// JSON 다운로드  : 요건 src > main > java > resources 에다가 client_secret.json 두기

public class GSheetRealeLand_bak20221015 {

    //List<RealeItem> realeItems;

    //    private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
//    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
//    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private String APPLICATION_NAME = "Google Sheets API Java Quickstart";
    private JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private String TOKENS_DIRECTORY_PATH = "tokens";
    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
//    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
    //private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);

    private static final String CREDENTIALS_FILE_PATH = "/client_secret_20220527.json";

    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
//    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = GSheetRealeLand_bak20221015.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
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
     * @return
     */





    //public static void main(String... args) throws IOException, GeneralSecurityException {
    public List<RealeItem> getRealeItems(String sheetname) throws IOException, GeneralSecurityException {

        //System.out.println("getRealeLand 호출된");


        //List<RealeItem> realeItems = (List<RealeItem>) new RealeItem();

        List<RealeItem> realeItems = new ArrayList<RealeItem>();



        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        //final String spreadsheetId = "1BSVeRrF_MBxWkVdr8Wm3wrhZCD53bfE0dMLRYtMEeAE";

        //Sheets 식별자
        final String spreadsheetId = "1ZnpywI0OLrYBWuiGnvalp3oFoNBVc81XOKQMVtDBvkg";

        //final String range = "googlesheetapi!A1:W";
        //sheetname 매매_건축할토지 | 매매_농사할토지 | 매매_접수중토지 | 매매_상가주택 | 임대차_상가점포 | 임대차_123
        final String range = sheetname +"!A1:W";

        //System.out.println("getRealeLand 서비스생성전");


        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        //System.out.println("getRealeLand 리스펀스생성전");
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();

        //System.out.println("getRealeLand 밸류즈생성전");
        List<List<Object>> values = response.getValues();
        System.out.println("response.getValues() : " + response.getValues());


        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
        } else {

            int currentIndexOfItems = 0;
            for (List row : values) {
                System.out.println("currentIndexOfItems 매물순번 : " + currentIndexOfItems);
                if (currentIndexOfItems != 0){
                    //System.out.println("잘나오는지");

                    RealeItem realeItem = new RealeItem();
                    //System.out.println("realeItem.getRowIdx() : " + realeItem.getRowIdx() );
                    realeItem.setRowIdx("" + (currentIndexOfItems + 1));
                    System.out.println("realeItem.getRIdx() : " + realeItem.getRowIdx() );



                    realeItem.setRegDate((String)row.get(0));
                    realeItem.setAddrName((String) row.get(1));
                    realeItem.setAddrName2((String) row.get(2));
                    realeItem.setAddrName3((String) row.get(3));
                    realeItem.setTitle((String) row.get(4));
                    //System.out.println("row.get(4) : " + row.get(4));
                    realeItem.setPnupre((String) row.get(5));
                    //System.out.println("row.get(5) : " + row.get(5));

                    String adstatus = "<div style=\'padding:0.0px;font-size:0.5em;float:left;with:50%;color:#FF00FF\'> ★preADs rIdx" + realeItem.getRowIdx() + "</div>";
                    //if (row.get(6).toString().equals("Y") || row.get(6).toString().equals("말자") || row.get(6).toString().equals("말것") ) {
                    if ( !row.get(6).toString().equals("Y") ) {
                        adstatus = "<div style=\'padding:0.2px;font-size:0.5em;float:left;with:50%;\'>doneADs rIdx" + row.get(6).toString() + realeItem.getRowIdx() + "</div>";
                    }
                    realeItem.setAds(adstatus);

                    realeItem.setLink1((String) row.get(7));
                    realeItem.setSmeter((String) row.get(8));
                    realeItem.setPy((String) row.get(9));
                    realeItem.setAmt((String) row.get(10));
                    realeItem.setAmt2((String) row.get(11));
                    realeItem.setDang((String) row.get(12));
                    realeItem.setDang2((String) row.get(13));
                    realeItem.setCategory((String) row.get(14));
                    realeItem.setZone((String) row.get(15));
                    realeItem.setExplain((String) row.get(16));
                    realeItem.setEvaluation((String) row.get(17));
                    realeItem.setQuestion((String) row.get(18));
                    realeItem.setOwner((String) row.get(19));

//                    System.out.println("setAddrName : " + realeItem.getAddrName());
//                    System.out.println("setTitle : " + realeItem.getTitle());
//                    System.out.println("setAddrName2 : " + realeItem.getAddrName2());
//                    System.out.println("setAddrName3 : " + realeItem.getAddrName3());
//                    System.out.println("setEvaluation : " + realeItem.getEvaluation());

                    // 주소로 pnuCode 가져오기
                    //System.out.println("이게있어야해 : " + realeItem.getAddrName());
                    String pnuCode = getPNUCODEsimple(realeItem.getPnupre(), realeItem.getAddrName2());
                    realeItem.setPnuCode(pnuCode);
                    System.out.println("Title : " + realeItem.getTitle() );
                    System.out.println("PnuCode : " + realeItem.getPnuCode() );

                    realeItem.setPageName(sheetname);

                    realeItems.add(realeItem);

                }
                currentIndexOfItems++;

            }
        }

        //System.out.println("getRealeLand 리턴");
        return realeItems;
    }

    public String getPNUCODEsimple(String pnupre, String addrName2)  throws IOException, GeneralSecurityException {
        String rvPNU = pnupre + "1";

        String[] jibun = addrName2.split("-");

        //System.out.println("jibun : " + jibun);
        if(jibun.length > 0 && !jibun[0].toString().equals("")){
            //System.out.println("jibun[0] : " + jibun[0]); //4자리 : 본번
            rvPNU += padLeftZeros(jibun[0], 4);
        }

        if(jibun.length > 1 && !jibun[1].toString().equals("")) {
            //System.out.println("jibun[1] : " + jibun[1]); //4자리 : 부번
            rvPNU += padLeftZeros(jibun[1], 4);
        }else{
            rvPNU += "0000";
        }

        return rvPNU;
    }
    public String getPNUwithLOOP(String addrName, String addrName2)  throws IOException, GeneralSecurityException {

        String rvPNU = "NOTHING";

        System.out.println("getPNUtemp()  파라미터 addrName addrName2 로 호출 : " + addrName + " " + addrName2);
        List<RealeItem> realeItems = new ArrayList<RealeItem>();


        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        //Sheets 식별자
        final String spreadsheetId = "1ZnpywI0OLrYBWuiGnvalp3oFoNBVc81XOKQMVtDBvkg";
        final String range = "pnucode!A1:C";

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
            var pnumatchedflag = false;

            for (List row : values) {
                //System.out.println("row.get(2).toString() : " + row.get(1).toString());
                //System.out.println("addrName : " + addrName);
                if (row.get(1).toString().contains(addrName)){
                    //System.out.println("매치되는 동 PNU 찾았음 ");
                    //System.out.println("contains : " + row.get(0) );
                    rvPNU = (String)row.get(0); //10자리 : 시도(2) 시군구(3) 읍면동(3) 리(2)
                    rvPNU += "1"; //1자리 : 필지구분 1일반 2산
                    String[] jibun = addrName2.split("-");


                    //System.out.println("jibun : " + jibun);
                    if(jibun.length > 0 && !jibun[0].toString().equals("")){
                        //System.out.println("jibun[0] : " + jibun[0]); //4자리 : 본번
                        rvPNU += padLeftZeros(jibun[0], 4);
                    }
                    if(jibun.length > 1 && !jibun[1].toString().equals("")) {
                        //System.out.println("jibun[1] : " + jibun[1]); //4자리 : 부번
                        rvPNU += padLeftZeros(jibun[1], 4);
                        pnumatchedflag = true;
                    }else{
                        rvPNU += "0000";
                        pnumatchedflag = true;
                    }

                }

                if(pnumatchedflag) break;

            }
        }
        return rvPNU;

    }
    public String getPNUwithQuery(String addrName)  throws IOException, GeneralSecurityException {

        //HERE!! 아래 링크 참조해서 query match lookup 잘 찾아보자
        //https://stackoverflow.com/questions/49161249/google-sheets-api-how-to-find-a-row-by-value-and-update-its-content

        System.out.println("getPNU 호출된");
        //List<RealeItem> realeItems = new ArrayList<RealeItem>();



        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        //final String spreadsheetId = "1BSVeRrF_MBxWkVdr8Wm3wrhZCD53bfE0dMLRYtMEeAE";

        //Sheets 식별자
        //final String spreadsheetId = "1ZnpywI0OLrYBWuiGnvalp3oFoNBVc81XOKQMVtDBvkg";
        final String spreadsheetId = "1ZnpywI0OLrYBWuiGnvalp3oFoNBVc81XOKQMVtDBvkg";



        //https://docs.google.com/spreadsheets/d/1ZnpywI0OLrYBWuiGnvalp3oFoNBVc81XOKQMVtDBvkg/edit#gid=193089992
        // HERE!!!!!!!!!!!!!!! 20220612
        //String pnucodeQueryString = '=Query(A1:C,'Select A where B contains "강정동" ')"

        //final String range = "Class pnucode!A1:C";
        //final String range = "pnucode!A1:C";
        //final String range = "=Query(pnucode!A1:C,Select A where B contains '강정동' )";

        //final String range = "=Query(pnucode!A1:C,Select A where A = 5011010100 )";
        //final String range = "=Query(pnucode!A1:C,\"Select A where A = 5011010100 \" )";


        //final String range = "=Query(pnucode!A1:C,\"Select A where B contains '강정동' \" )";
        //final String range = "=Query(pnucode!A[1]:A[3],\"Select A where B contains '강정동' \" )";
        final String range = "=Query(pnucode!A1:C,\"Select A where B contains '강정동' \" )";


        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        System.out.println("getRealeLand 리스펀스생성전");
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();


        //ValueRange result = service.spreadsheets().values().get( spreadsheetId, "=QUERY(pnucode!A1:C,SELECT A WHERE B contains 'aaa')" ).execute();

        //요거잠시히든시키자
        System.out.println("getRealeLand 밸류즈생성전");
        List<List<Object>> values = response.getValues();



        //System.out.println("getRealeLand PUNCODE 호출했음!!!!!");
        //ValueRange result = service.spreadsheets().values().get( spreadsheetId, "=Query(A1:C,\"Select A where B contains '강정동' \"),1" ).execute();
        //ValueRange result = service.spreadsheets().values().get( spreadsheetId, "=pnucode!Query(A1:C,\"Select A where B contains '강정동' \"),0" ).execute();
        //ValueRange result = service.spreadsheets().values().get( spreadsheetId, "=Query(pnucode!A1:C,\"Select A where B contains '강정동' \")" ).execute();

        //ValueRange result = service.spreadsheets().values().get( spreadsheetId, "=Query(A1:C,\"Select A where B contains '강정동'\") ").execute();


        //ValueRange result = service.spreadsheets().values().get( spreadsheetId, "=Query(A1:C,Select A where B contains '강정동'),1" ).execute();
        //ValueRange result = service.spreadsheets().values().get( spreadsheetId, "=Query(A1:C,\"Select A where B contains '강정동' \"),0" ).execute();

        //"=QUERY(pnucode!A1:C, \"Select B where G contains "" + "강정동" +\")"

        //System.out.println( "result.getValues() : " + result.getValues() );
        //System.out.println("getRealeLand PUNCODE 호출결과는 위와같음!!!!!");

        //풀어라
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
        } else {

        }

        return "";
    }


    public String padLeftZeros(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);

        return sb.toString();
    }
}
