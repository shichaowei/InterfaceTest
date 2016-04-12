package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 使用json-lib构造和解析Json数据
 * 
 * @author Alexia
 * @date 2013/5/23
 *
 */
public class JsonTest {

	
	/**
	 * 将Map转化为Json
	 * 
	 * @param map
	 * @return String
	 */
	public static <T> String mapToJson(Map<String, T> map) {
		 Gson gson = new Gson();
		 String jsonStr = gson.toJson(map);
		 return jsonStr;
	}



    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("name", "Alexia");
        map1.put("sex", "female");
        map1.put("age", "23");
        System.out.println(mapToJson(map1));
    }

}