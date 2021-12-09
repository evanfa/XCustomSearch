package utils.process;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex_Utility {

    List<String> listInLetters;
    List<String> listOutLetters;

    public List<String> getListInLetters() {
        return listInLetters;
    }

    public void setListInLetters(String listInLetters) {
        this.listInLetters.add(listInLetters);
    }

    public List<String> getListOutLetters() {
        return listOutLetters;
    }

    public void setListOutLetters(String listOutLetters) {
        this.listInLetters.add(listOutLetters);
    }

    /**
     * Function search string in list. Validate the pattern and exclude the "Anexos" location if is found.
     * Execute the copy of found files.
     * <p>
     * Validate if the file name exist. Getting the file name from "FILE" Class from gotten filepath.
     *
     * @param strToSearch
     * @param listToFind
     * @param destinationPath
     * @return void
     */
    public void isStringContainedInListAndCopy(String strToSearch, List<String> listToFind, String destinationPath) {

        for (int i = 0; i < listToFind.size(); i++) {

            Pattern pattern = Pattern.compile(strToSearch);
            Matcher m = pattern.matcher(listToFind.get(i));

            Pattern patternAvoid = Pattern.compile("ANEXOS", Pattern.CASE_INSENSITIVE);
            Matcher matcherAvoid = patternAvoid.matcher(listToFind.get(i));

            while (m.find()) {

                //Validate if file already exist

                if (!new File(destinationPath.concat(new File(listToFind.get(i)).getName())).exists()) {

                    if (!matcherAvoid.find()) {

//                        FilesCopier_Utility getCopyFile = new FilesCopier_Utility();
//                        getCopyFile.generateCopyFileInPath(listToFind.get(i), destinationPath);

                        System.out.println("File Copied: " + listToFind.get(i));
                    }
                }
            }
        }
    }

    /**
     * Get String to Search and Search coincidences in listToFind
     *
     * @param strToSearch
     * @param listToFind
     * @return String
     */
    public String isStringContainedInList(String strToSearch, List<String> listToFind) {

        Pattern pattern = Pattern.compile(strToSearch);
        String foundFilePath = "";

        for (String value : listToFind) {
            Matcher m = pattern.matcher(value);

            if (m.find()) {
                foundFilePath = value;
            }
        }
        return foundFilePath;
    }

    /**
     * Get String to Search and Search coincidences in listToFind
     *
     * @param strToSearch
     * @param listToFind
     * @return boolean
     */
    public Boolean isRegexContainedInList(String strToSearch, List<String> listToFind) {

        Pattern pattern = Pattern.compile(strToSearch);
        boolean flagConfirm = false;

        for (String value : listToFind) {
            Matcher m = pattern.matcher(value);

            if (m.find()) {
                //System.out.println("String Found: "+value);
                flagConfirm = true;
            }
        }
        return flagConfirm;
    }

    /**
     * @param strToSearch
     * @param toFind
     * @return Boolean
     */
    public static Boolean isRegexContainedIntoSingleString(String strToSearch, String toFind) {

        Pattern pattern = Pattern.compile(strToSearch);
        Matcher m = pattern.matcher(toFind);
        boolean flagExist = false;

        while (m.find()) {
            //System.out.println("Pattern found from " + m.start() + " to " + (m.end()-1));
            flagExist = true;
        }
        return flagExist;
    }

    /**
     * @param strToSearch
     * @param toFindArray
     */
    public void isRegexContainedIntoExhaustiveSearch_CSV(String strToSearch, CharSequence toFindArray) {

        for (int i = 0; i < toFindArray.length(); i++) {

            //System.out.println("Searching in: "+toFindArray[i]);
            //isRegexContainedIntoSingleString("TXTL-TGNH-CNA-0000-0165",toFindArray[i]);

            Pattern pattern = Pattern.compile(strToSearch);
            //Matcher m = pattern.matcher(toFindArray);
            Matcher m = pattern.matcher(toFindArray);
            while (m.find()) {
                System.out.println("Pattern found from " + m.start() + " to " + (m.end() - 1));
            } // End While
        } //End For

    }

    public boolean findCurrentIncidenceInStringMatches(String textToReview, String regexCondition) {
        System.out.println(textToReview);
        return textToReview.matches(regexCondition);
    }

    public static String findCurrentIncidenceInStringMatches1(String textToReview, String regexCondition) {
        String rtnStr = null;
        Pattern pattern = Pattern.compile(regexCondition); //,Pattern.CASE_INSENSITIVE
        Matcher matcher = pattern.matcher(textToReview);

        if (matcher.find()) {
            System.out.println(matcher.group(0) + " => " + matcher.group());

        }
        return rtnStr;
    }


    /**
     * Function that returns a List of Strings with contains the input parameter
     *
     * @param inputList
     * @param inputSearchParam
     * @return
     */
    public static List<String> getListIfCoindicendeFound(List<String> inputList, String inputSearchParam) {
        List<String> tempList = new ArrayList<String>();

        Regex_Utility frU = new Regex_Utility();

        for (int i = 0; i < inputList.size(); i++) {
            if (frU.findCurrentIncidenteInString(inputList.get(i), inputSearchParam) != null) {

                File tmp = new File(inputList.get(i));
                tempList.add(tmp.getName());
            }
        }
        return tempList;
    }

    /**
     * Function that returns a nested key and filename into Hash Map if the string contains the input parameter
     *
     * @param inputList
     * @param inputSearchParam
     * @return
     */
    public static HashMap<Integer, String> getHashIfCoincidenceFound(List<String> inputList, String inputSearchParam) {
        HashMap<Integer, String> map = new HashMap<>();
        Regex_Utility frU = new Regex_Utility();
        for (int i = 0; i < inputList.size(); i++) {
            if (frU.findCurrentIncidenteInString(inputList.get(i), inputSearchParam) != null) {
                File tmp = new File(inputList.get(i));
                map.put(i, tmp.getName());
            }
        }
        return map;
    }

    /**
     * Function that returns a Array List of Records that contains index, filename, path of the inputList that contains
     * the parameter searched.
     *
     * @return
     */
  /*  public static ArrayList<Record> getArrayListResultsIfCoincidenceFound(List<String> inputList,String inputSearchParam){
        ArrayList<Record> rcdS = new ArrayList<Record>();
        Regex_Utility frU = new Regex_Utility();

        for (int i = 0; i < inputList.size(); i++) {
            if (frU.findCurrentIncidenteInString(inputList.get(i), inputSearchParam) != null) {
                File tmp = new File(inputList.get(i));
                Record rd = new Record(i,tmp.getName(),tmp.getAbsolutePath(),false);
                rcdS.add(rd);
            }
        }
        return rcdS;
    }*/

    //------------ Working and Tested ----------------------------
    public String findCurrentIncidenteInString(String textToReview, String regexCondition) {
        String rtnStr = null;
        //Pattern pattern = Pattern.compile(regexCondition); //,Pattern.CASE_INSENSITIVE
        Pattern pattern = Pattern.compile(regexCondition,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(textToReview);
        if (matcher.find()) {
            rtnStr = matcher.group(0);
            //this.setListOutLetters(matcher.group(2));
            //this.setListInLetters(matcher.group(3));
        }
        return rtnStr;
    }

    public String findCurrentIncidenteInString_Insensitive(String textToReview, String regexCondition) {
        String rtnStr = null;
        Pattern pattern = Pattern.compile(regexCondition, Pattern.CASE_INSENSITIVE); //
        Matcher matcher = pattern.matcher(textToReview);
        if (matcher.find()) {
            rtnStr = matcher.group(0);
        }
        return rtnStr;
    }

    public String findCurrentIncidencesSiglasInString_ByGroupNum(String regex, String str, int groupId) {
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(str);
        String rstString = "";

        while (matcher.find()) {
            //System.out.println("Full match: " + matcher.group(0));
            //for (int i = 1; i <= matcher.groupCount(); i++) {
            //  System.out.println("Group " + i + ": " + matcher.group(i));
            //}
            rstString = matcher.group(groupId);
        }
        return rstString;
    }

    /**
     * Function that reformat english date format 'month_name dd, yyyy' or 'yyyy-dd-month_name' to sql date format 'yyyy-mm-dd'
     * @param textToReview
     * @return
     */
    public static String fixEnglishDateFormatToSQLDateFormat(String textToReview){
        String rstString = "";
        String montString = "";
        List<String> lstMonths = Arrays.asList("January","February","March","April","May","June","July","August","September","October","November","December","Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre",
                "Jan","Ene","Feb","Mar","Apr","Abr","May","Jun","Jul","Aug","Ago","Sep","Oct","Nov","Dec","Dic");

        final Pattern pattern = Pattern.compile(DefaultsLoader.REGEX_DATE_ENG,Pattern.CASE_INSENSITIVE);
        final Pattern patternInv = Pattern.compile(DefaultsLoader.REGEX_DATE_RENG, Pattern.CASE_INSENSITIVE);
        final Pattern patternddmmyy = Pattern.compile(DefaultsLoader.REGEX_DATE_DDMMYY, Pattern.CASE_INSENSITIVE);
        final Pattern patternddmmmmyy = Pattern.compile(DefaultsLoader.REGEX_DATE_DDMMMMYY, Pattern.CASE_INSENSITIVE);

        final Matcher matcher = pattern.matcher(textToReview);
        final Matcher matcherInv = patternInv.matcher(textToReview);
        final Matcher matcherDMY = patternddmmyy.matcher(textToReview);
        final Matcher matcherDMMMMY = patternddmmmmyy.matcher(textToReview);

        //System.out.println("Date: "+textToReview);
        if(matcher.find()){
            //System.out.println("Group 2: "+matcher.group(2));
            //System.out.println("Group 3: "+matcher.group(3));
            //System.out.println("Group 4: "+matcher.group(4));

            for(String lst:lstMonths){
                if(matcher.group(2).equals(lst)){
                    if(lst == "January"||lst == "Enero"||lst == "Ene"||lst == "Jan"){
                        montString="01";
                    }
                    if(lst == "February"||lst == "Febrero"||lst == "Feb"){
                        montString="02";
                    }
                    if(lst == "March"||lst == "Marzo"||lst == "Mar"){
                        montString="03";
                    }
                    if(lst == "April"||lst == "Abril"||lst == "Apr"||lst == "Abr"){
                        montString="04";
                    }
                    if(lst == "May"||lst == "Mayo"){
                        montString="05";
                    }
                    if(lst == "June"||lst == "Junio"||lst == "Jun"){
                        montString="06";
                    }
                    if(lst == "July"||lst == "Julio"||lst == "Jul"){
                        montString="07";
                    }
                    if(lst == "August"||lst == "Agosto"||lst == "Aug"||lst == "Ago"){
                        montString="08";
                    }
                    if(lst == "September"||lst == "Septiembre"||lst == "Sep"){
                        montString="09";
                    }
                    if(lst == "October"||lst == "Octubre"||lst == "Oct"){
                        montString="10";
                    }
                    if(lst == "November"||lst == "Noviembre"||lst == "Nov"){
                        montString="11";
                    }
                    if(lst == "December"|| lst == "Diciembre" || lst == "Decembere"||lst == "Dic"||lst == "Dec"){
                        montString="12";
                    }
                }
            }

            if(matcher.group(2).length() == 4){
                rstString = rstString.concat("20"+matcher.group(4)).concat("-").concat(montString).concat("-").concat(matcher.group(3));
                //System.out.println("Date Converted 4: "+rstString);
            }/*else if(matcher.group(2).length() == 2){
				rstString = rstString.concat("20"+matcher.group(4)).concat("-").concat(montString).concat("-").concat(matcher.group(3));
				System.out.println("Date Converted 2: "+rstString);
			}*/

        }else if(matcherInv.find()){
            for(String lst:lstMonths){
                if(matcherInv.group(4).equals(lst)){
                    if(lst == "January"||lst == "Enero"||lst == "Ene"||lst == "Jan"){
                        montString="01";
                    }
                    if(lst == "February"||lst == "Febrero"||lst == "Feb"){
                        montString="02";
                    }
                    if(lst == "March"||lst == "Marzo"||lst == "Mar"){
                        montString="03";
                    }
                    if(lst == "April"||lst == "Abril"||lst == "Abr"||lst == "Apr"){
                        montString="04";
                    }
                    if(lst == "May"||lst == "Mayo"){
                        montString="05";
                    }
                    if(lst == "June"||lst == "Junio"||lst == "Jun"){
                        montString="06";
                    }
                    if(lst == "July"||lst == "Julio"||lst == "Jul"){
                        montString="07";
                    }
                    if(lst == "August"||lst == "Agosto"||lst == "Ago"||lst == "Aug"){
                        montString="08";
                    }
                    if(lst == "September"||lst == "Septiembre"||lst == "Sep"){
                        montString="09";
                    }
                    if(lst == "October"||lst == "Octubre"||lst == "Oct"){
                        montString="10";
                    }
                    if(lst == "November"||lst == "Noviembre"||lst == "Nov"){
                        montString="11";
                    }
                    if(lst == "December"|| lst == "Diciembre" || lst == "Decembere"||lst == "Dec"||lst == "Dic"){
                        montString="12";
                    }
                }
            }
            //System.out.println("Length: "+matcher.group(2).length());
            if(matcher.group(2).length() == 4){
                //System.out.println(">>>>>LENG>>>> "+matcher.group(2).length());
                rstString = rstString.concat(matcherInv.group(2)).concat("-").concat(montString).concat("-").concat(matcherInv.group(3));
                System.out.println("Date Converted: "+rstString);
            }else if(matcher.group(2).length() == 2){
                rstString = rstString.concat("20"+matcherInv.group(2)).concat("-").concat(montString).concat("-").concat(matcherInv.group(3));
                System.out.println("Date Converted: "+rstString);
                //System.out.println(">>>>>LENG>>>> "+matcher.group(2).length());
            }
        }else if(matcherDMY.find()){
            rstString = rstString.concat("20"+matcherDMY.group(2)).concat("-").concat(matcherDMY.group(3)).concat("-").concat(matcherDMY.group(4));
        }else if(matcherDMMMMY.find()){
            for(String lst:lstMonths){
                if(matcherDMMMMY.group(3).equals(lst)){
                    if(lst == "January"||lst == "Enero"||lst == "Ene"||lst == "Jan"){
                        montString="01";
                    }
                    if(lst == "February"||lst == "Febrero"||lst == "Feb"){
                        montString="02";
                    }
                    if(lst == "March"||lst == "Marzo"||lst == "Mar"){
                        montString="03";
                    }
                    if(lst == "April"||lst == "Abril"||lst == "Apr"||lst == "Abr"){
                        montString="04";
                    }
                    if(lst == "May"||lst == "Mayo"){
                        montString="05";
                    }
                    if(lst == "June"||lst == "Junio"||lst == "Jun"){
                        montString="06";
                    }
                    if(lst == "July"||lst == "Julio"||lst == "Jul"){
                        montString="07";
                    }
                    if(lst == "August"||lst == "Agosto"||lst == "Aug"||lst == "Ago"){
                        montString="08";
                    }
                    if(lst == "September"||lst == "Septiembre"||lst == "Sep"){
                        montString="09";
                    }
                    if(lst == "October"||lst == "Octubre"||lst == "Oct"){
                        montString="10";
                    }
                    if(lst == "November"||lst == "Noviembre"||lst == "Nov"){
                        montString="11";
                    }
                    if(lst == "December"|| lst == "Diciembre" || lst == "Decembere"||lst == "Dec"||lst == "Dic"){
                        montString="12";
                    }
                }
            }

            rstString = rstString.concat("20"+matcherDMMMMY.group(4)).concat("-").concat(montString).concat("-").concat(matcherDMMMMY.group(2));

        }else{
            rstString = textToReview;
        }
        return rstString;
    }

}
