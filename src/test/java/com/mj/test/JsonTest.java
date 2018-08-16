package com.ying.test;

import com.ying.general.port.model.Port;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * @author zejun
 * @date 2018/7/24 10:54
 */
public class JsonTest {
    @SuppressWarnings({ "static-access", "deprecation", "unchecked" })



    public static void main(String[] args) throws UnsupportedEncodingException {
        String JsonContext = new Util().ReadFile("C:\\Users\\zejun\\Desktop\\port.json");
        JSONArray jsonArray = JSONArray.fromObject(JsonContext);

        int size = jsonArray.size();
        System.out.println("Size: " + size);
        for(int  i = 0; i < 1; i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Port port = new Port();
            port.setPortCode(jsonObject.get("code").toString());
            port.setPortEN(jsonObject.get("enname").toString());
            port.setPortCN(jsonObject.get("cnname").toString());
            port.setCountryCode(jsonObject.get("countryCode").toString());
            port.setCountryEN(jsonObject.get("encountryname").toString());
            port.setCountryCN(jsonObject.get("cncountryname").toString());
        }

    }
}
