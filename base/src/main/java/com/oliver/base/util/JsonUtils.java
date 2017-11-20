package com.oliver.base.util;//package com.oliver.baselibrary.util;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParseException;
//import com.google.gson.JsonParser;
//import com.haiersmart.utilslib.data.StringUtil;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class JsonUtils {
//
//   /** 获取Gson实例 **/
//   private static Gson getInstance() {
//      GsonBuilder gsonBuilder = new GsonBuilder();
//      gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
//      gsonBuilder.registerTypeAdapter(Object.class, new NaturalDeserializer());
//      Gson gson = gsonBuilder.create();
//      return gson;
//   }
//
//   /**
//    * 解析集合
//    *
//    * @param json
//    *            数据
//    * @param beanClass
//    *            泛型
//    * @return 泛型集合
//    */
//   public static <T> List<T> getBeanList(String json, Class<T> beanClass) {
//      return getBeanList(json, null, beanClass);
//   }
//
//   /**
//    * 解析集合
//    *
//    * @param json
//    * @param key
//    *            json数据key
//    * @param beanClass
//    *            泛型
//    * @return 泛型集合
//    */
//   public static <T> List<T> getBeanList(String json, String key, Class<T> beanClass) {
//      JsonParser parser = new JsonParser();
//      Gson gson = getInstance();
//      JsonElement contentElement = null;
//      List<T> elementList = new ArrayList<T>();
////    try {
////       @SuppressWarnings("unused")
////       JSONObject object = new JSONObject(json);
////    } catch (JSONException e1) {
////       return elementList;
////    }
//      if (StringUtil.isBlank(key) || StringUtil.isBlank(json)) {
//         contentElement = parser.parse(json).getAsJsonArray();
//      } else {
//         JsonObject jsonObject = parser.parse(json).getAsJsonObject();
//         contentElement = jsonObject.get(key);
//      }
//      if (StringUtil.isBlank(contentElement))
//         return elementList;
//      if (contentElement.isJsonArray()) {
//         JsonArray jsonArray = contentElement.getAsJsonArray();
//         try {
//            for (int i = 0; i < jsonArray.size(); i++) {
//               JsonElement jsonObj = jsonArray.get(i);
//               T entity = gson.fromJson(jsonObj, beanClass);
//               elementList.add(entity);
//            }
//         } catch (ClassCastException e) {
//         } catch (JsonParseException e) {
//         }
//         return elementList;
//      }
//      return elementList;
//   }
//
//   /**
//    * 解析单个bean
//    *
//    * @param json
//    * @param clazz
//    * @return 泛型
//    */
//   public static <T> T getBean(String json, final Class<T> clazz) {
//      return getBean(json, null, clazz);
//   }
//
//   /**
//    * 解析单个bean
//    *
//    * @param json
//    * @param key
//    *            解析的json数据key
//    * @param clazz
//    * @return 泛型
//    */
//   @SuppressWarnings("unchecked")
//   public static <T> T getBean(String json, String key, final Class<T> clazz) {
//      JsonParser parser = new JsonParser();
//      Gson gson = getInstance();
//      JsonElement contentElement = null;
//      try {
//         @SuppressWarnings("unused")
//         JSONObject object = new JSONObject(json);
//      } catch (JSONException e1) {
//         return null;
//      }
//      if (StringUtil.isBlank(key)) {
//         contentElement = parser.parse(json).getAsJsonObject();
//      } else {
//         JsonObject jsonObject = parser.parse(json).getAsJsonObject();
//         contentElement = jsonObject.get(key);
//      }
//      if (StringUtil.isBlank(contentElement))
//         return null;
//      if (contentElement.isJsonPrimitive()) {
//         if (clazz == String.class) {
//            return (T) contentElement.getAsString();
//         } else if (clazz == Integer.class) {
//            return (T) Integer.valueOf(contentElement.getAsString());
//         } else if (clazz == Number.class) {
//            return (T) contentElement.getAsNumber();
//         }
//      } else if (contentElement.isJsonObject()) {
//         JsonObject contentObj = contentElement.getAsJsonObject();
//         T content = null;
//         try {
//            content = gson.fromJson(contentObj, clazz);
//         } catch (ClassCastException e) {
//         } catch (JsonParseException e) {
//            System.out.println(e.getStackTrace().toString());
//         }
//         return content;
//      }
//      return null;
//   }
//
//   /**
//    * 根据key 解析相关字段
//    *
//    * @param json
//    * @param key
//    * @return
//    */
//   public static Object getObjectByKey(String json, String key, String targetkey) {
//      JsonParser parser = new JsonParser();
//      JsonElement jsonElement;
//      try {
//         JsonObject jsonObject = parser.parse(json).getAsJsonObject();
//         jsonElement = jsonObject.get(key);
//         return jsonElement.getAsJsonObject().get(targetkey).toString();
//      } catch (Exception e) {
//         return null;
//      }
//   }
//
//   /** JSONObject生成Json字符串 **/
//   public static String getJSONStringByJSONObject(JSONObject jsonobject) {
//      String s;
//      if (jsonobject == null)
//         s = "";
//      else
//         s = jsonobject.toString();
//      return s;
//   }
//
//   /** 解析JSONObject Json字符串 **/
//   public static JSONObject getJSONObjectByJSONString(String str) {
//      str = str.replace("\ufeff", "");
//      JSONObject jsonobject;
//      if (str != null)
//         try {
//            jsonobject = new JSONObject(str);
//         } catch (JSONException _ex) {
//            jsonobject = null;
//         }
//      else
//         jsonobject = null;
//      return jsonobject;
//   }
//
//   /** 解析JSONArray Json字符串 **/
//   public static JSONArray getJSONArrayByJSONString(String str) {
//      JSONArray jsonarray;
//      if (str != null)
//         try {
//            jsonarray = new JSONArray(str);
//         } catch (JSONException _ex) {
//            jsonarray = null;
//         }
//      else
//         jsonarray = null;
//      return jsonarray;
//   }
//}
