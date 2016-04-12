package unittest;

import java.util.Map;

import com.google.gson.Gson;

public class Map2Json {
	
	
	/**
	 * ��Mapת��ΪJson
	 * 
	 * @param map
	 * @return String
	 */
	public static <T> String mapToJson(Map<String, T> map) {
		 Gson gson = new Gson();
		 String jsonStr = gson.toJson(map);
		 return jsonStr;
	}

}
