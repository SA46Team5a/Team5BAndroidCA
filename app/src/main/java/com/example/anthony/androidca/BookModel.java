package com.example.anthony.androidca;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookModel extends HashMap<String,Object> {

    final static String baseURL = "http://172.27.240.226:8090/workhere/Service.svc/";

    public Employee(String name, String id, String address, String salary) {
        put("Name", name);
        put("Id", id);
        put("Salary", salary);
        put("Address", address);
    }

    public static List<String> list() {
        List<String> list = new ArrayList<String>();
        JSONArray a = JSONParser.getJSONArrayFromUrl(baseURL + "Employee");
        try {
            for (int i =0; i<a.length(); i++)
                list.add(a.getString(i));
        } catch (Exception e) {
            Log.e("Employee.list()", "JSONArray error");
        }
        return(list);
    }

    public static Employee getEmp(String eid) {
        JSONObject b = JSONParser.getJSONFromUrl(baseURL + "employee/" + eid);
        try {
            return new Employee(b.getString("Name"), b.getString("Id"),
                    b.getString("Address"), b.getString("Salary"));
        } catch (Exception e) {
            Log.e("Employee.getEmp()", "JSONArray error");
        }
        return(null);
    }
}
