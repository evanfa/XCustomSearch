package app.controller;

import java.io.File;
import java.util.ArrayList;

import app.model.Record;
import utils.process.Regex_Utility;

public class ControllerItems {
	private ArrayList<String> lst = new ArrayList<String>();
	private ArrayList<Record> itemListResults = new ArrayList<>();
	
	public ArrayList<String> getLst() {
		return lst;
	}
	
	public void setItemListResults(ArrayList<String> lst, String parameter) {
		this.itemListResults = getArrayListResultsIfCoincidenceFound(lst,parameter);
	}

	public ArrayList<Record> getItemListResults() {
		return itemListResults;
	}

	public ControllerItems() {
		lst.add("http://test.com/perro_chihuahua");
		lst.add("http://test.com/perro_maltes");
		lst.add("http://test.com/perro_callejero");
		lst.add("http://test2.com/gato_french");
		lst.add("http://test2.com/gato_callejero");
		lst.add("http://test2.com/gato_chihuahua");
		lst.add("http://test2.com/gato_french");
		lst.add("http://test2.com/gato_maltes");
		lst.add("http://test2.com/gato_callejero");
		lst.add("http://test2.com/gato_maltes");
		lst.add("http://test.com/perro_chihuahua");
		lst.add("http://test.com/perro_french");
		lst.add("http://test.com/perro_callejero");
		lst.add("http://test.com/perro_maltes");
		lst.add("http://test.com/perro_chihuahua");
		lst.add("http://test.com/perro_french");
		lst.add("http://test2.com/gato_callejero");
		lst.add("http://test2.com/gato_maltes");
		lst.add("http://test2.com/gato_chihuahua");
		lst.add("http://test2.com/gato_french");
		lst.add("http://test.com/perro_french");
		lst.add("http://test.com/perro_callejero");		
		lst.add("http://test2.com/gato_chihuahua");
		lst.add("http://test2.com/gato_maltes");
		lst.add("http://test2.com/gato_callejero");
		lst.add("http://test.com/perro_maltes");
		lst.add("http://test.com/perro_callejero");
		lst.add("http://test.com/perro_maltes");
	}
	
	public static ArrayList<Record> getArrayListResultsIfCoincidenceFound(ArrayList<String> inputList, String inputSearchParam) {
        ArrayList<Record> rcdS = new ArrayList<Record>();
        Regex_Utility frU = new Regex_Utility();
        for (int i = 0; i < inputList.size(); i++) {
        	
        	System.out.println("Record: "+inputList.get(i)+"  Value: "+inputSearchParam);
        	
            if (frU.findCurrentIncidenteInString(inputList.get(i), inputSearchParam) != null) {
                File tmp = new File(inputList.get(i));             
                Record rd = new Record(i, tmp.getName(), tmp.getAbsolutePath(),false);
                rcdS.add(rd);
            }
        }
        return rcdS;
    }

}

