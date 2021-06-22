package com.lenovo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lenovo.utils.HttpDiao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RuquestController {
    //跳转
    @RequestMapping("toView")
    public String toView(String name) {
        return name;
    }


    @ResponseBody
    @RequestMapping("getUrl")
    public String getUrl(String Fruit) {
        System.out.println(Fruit);
        if (Fruit.equals("1"))
            return "1";
        else if (Fruit.equals("2"))
            return "2";
        return "请选择正确的按钮";

    }

    //查询答案 和 查必需槽
    @ResponseBody
    @RequestMapping("getAnswerSlot")
    public String getAnswerSlot(Integer status, String subintentCode, String slotKey1, String slotValue1, String slotKey2, String slotValue2,
                                String slotKey3, String slotValue3) {
        String jsonObject = null;
        String url = "";
        JSONObject json = new JSONObject();
        JSONObject queryJson = new JSONObject();
        JSONArray actionsDone = new JSONArray();
        JSONArray slotInfos = new JSONArray();
        JSONArray stepDone = new JSONArray();
        JSONArray keywords = new JSONArray();

        if (status == 1)   //查必须槽
        {  //system__to_agent
            queryJson.put("actionsDone", actionsDone);
            queryJson.put("domainCode", "system");
            queryJson.put("intentCode", "system_assistance");
            queryJson.put("slotInfos", slotInfos);
            queryJson.put("stepDone", stepDone);
            queryJson.put("subintentCode", subintentCode);
            queryJson.put("userId", "5bb133fa-3795-42da-bcdb-4c2f5b3ebc93");


            json.put("businessLine", "mobile");
            json.put("language", "en");
            json.put("password", "ccagent_kg_2018");
            json.put("query", "");
            json.put("queryJson", queryJson);
            json.put("serviceType", "query_necessary_slots");
            json.put("username", "ccagent");
        } else {
            json.put("businessLine", "mobile");
            json.put("language", "en");
            json.put("username", "ccagent");
            json.put("password", "ccagent_kg_2018");

            if (status == 2) //查询答案（除fact域）
            {
                actionsDone.add("reboot the phone");
                actionsDone.add("factory data reset");
                queryJson.put("actionsDone", actionsDone);

                stepDone.add("reboot_the_phone");
                queryJson.put("stepDone", stepDone);

                queryJson.put("domainCode", "trouble_shooting");
                queryJson.put("intentCode", "ts_power_and_charging");
                queryJson.put("subintentCode", subintentCode);
                keywords.add("bla");
                keywords.add("bla2");
                queryJson.put("keywords", keywords);
                queryJson.put("language", "en");
                JSONObject jsonObject1 = new JSONObject();
                JSONObject jsonObject2 = new JSONObject();
                jsonObject1.put("slotKey", slotKey1);
                jsonObject1.put("slotValue", slotValue1);
                jsonObject2.put("slotKey", slotKey2);
                jsonObject2.put("slotValue", slotValue2);
                slotInfos.add(jsonObject1);
                slotInfos.add(jsonObject2);
                queryJson.put("slotInfos", slotInfos);
                queryJson.put("query", "Blablabla");
                queryJson.put("userId", "4ec6ffa2-0778-44c3-b547-2b09715cb9f4");
                queryJson.put("version", "1");

                json.put("serviceType", "how_to");
                json.put("query", "xxxxxx");
                json.put("queryJson", queryJson);

            }
            if (status == 3) //查询fact答案（新接口）
              {
                json.put("query", "");
                json.put("serviceType", "fact");
                queryJson.put("actionsDone", actionsDone);
                queryJson.put("domainCode", "fact");
                queryJson.put("intentCode", "fact_parameter");
                queryJson.put("keywords", keywords);
                JSONObject jsonObject1 = new JSONObject();
                JSONObject jsonObject2 = new JSONObject();
                JSONObject jsonObject3 = new JSONObject();
                jsonObject1.put("slotKey", slotKey1);
                jsonObject1.put("slotValue", slotValue1);
                jsonObject2.put("slotKey", slotKey2);
                jsonObject2.put("slotValue", slotValue2);
                jsonObject3.put("slotKey", slotKey3);
                jsonObject3.put("slotValue", slotValue3);
                slotInfos.add(jsonObject1);
                slotInfos.add(jsonObject2);
                slotInfos.add(jsonObject3);
                queryJson.put("slotInfos", slotInfos);
                queryJson.put("stepDone", stepDone);
                queryJson.put("subintentCode", subintentCode);
                queryJson.put("userId", "5bb133fa-3795-42da-bcdb-4c2f5b3ebc93");
            }
            if (status==4)  //新版查询答案调用示例
            {
             json.put("subintentCode",subintentCode);
                JSONObject jsonObject1 = new JSONObject();
                JSONObject jsonObject2 = new JSONObject();
                jsonObject1.put("slotKey", slotKey1);
                jsonObject1.put("slotValue", slotValue1);
                jsonObject2.put("slotKey", slotKey2);
                jsonObject2.put("slotValue", slotValue2);
                slotInfos.add(jsonObject1);
                slotInfos.add(jsonObject2);
                json.put("slotInfos", slotInfos);
                actionsDone.add("reboot the phone");
                actionsDone.add("factory data reset");
                json.put("actionsDone",actionsDone);
                stepDone.add("reboot_the_phone");
                stepDone.add("factory_data_reset");
                json.put("stepDone",stepDone);
            }

        }
        // url="http://10.110.130.238:8080/kgserver";
        url = "http://10.110.147.194:8016/moli_kgservice/kgserver";
        jsonObject = HttpDiao.doPost(url, json);
        System.out.println(jsonObject);
        return jsonObject;
    }

    //OS/Product等归一化
    @ResponseBody
    @RequestMapping("getNormalizationByType")
    public String getNormalizationByType(String slotEntity, String slotCode, String slotKey, String slotValue) {
        String jsonObject = null;
        String url = "";
        JSONObject json = new JSONObject();
        JSONArray slotInfos = new JSONArray();

        json.put("businessLine", "mobile");
        json.put("slotEntity", slotEntity);
        json.put("slotCode", slotCode);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("slotKey", slotKey);
        jsonObject1.put("slotValue", slotValue);
        slotInfos.add(jsonObject1);
        json.put("slotInfos", slotInfos);
        json.put("language", "en");
        json.put("username", "ccagent");
        json.put("password", "ccagent_kg_2018");
        url = "http://10.110.147.194:8016/moli_kgservice/normalization/getNormalizationByType";
        jsonObject = HttpDiao.doPost(url, json);
        System.out.println(jsonObject);
        return jsonObject;
    }

    //Backword 和 推断-subintent
    @ResponseBody
    @RequestMapping("getBackwardSubintentInfo")
    public String getBackwardSubintentInfo(String attributeCode1, String attributeCode2, String attributeCode3, String contextCode1, String contextValue1,
                                           String contextCode2, String contextValue2, String contextCode3, String contextValue3, String contextCode4, String contextValue4) {
        String url = "";
        JSONObject json = new JSONObject();
        json.put("businessLine", "mobile");
        json.put("language", "en");
        json.put("userName", "ccagent");
        json.put("passWord", "ccagent_kg_2018");
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();
        JSONObject jsonObject4 = new JSONObject();
        JSONArray subintentAttributeInfos = new JSONArray();
        if (!attributeCode1.equals("")) {
            jsonObject1.put("attributeCode", attributeCode1);
            jsonObject1.put("attributeShownName", "XXXXX");
            jsonObject1.put("attributeTypeName", "feature");

            jsonObject2.put("attributeCode", attributeCode2);
            jsonObject2.put("attributeShownName", "XXXXX");
            jsonObject2.put("attributeTypeName", "attribute");

            jsonObject3.put("attributeCode", attributeCode3);
            jsonObject3.put("attributeShownName", "XXXXX");
            jsonObject3.put("attributeTypeName", "component");
            subintentAttributeInfos.add(jsonObject1);
            subintentAttributeInfos.add(jsonObject2);
            subintentAttributeInfos.add(jsonObject3);
            json.put("subintentAttributeInfos", subintentAttributeInfos);
        }
        if (!contextCode1.equals("")) {
            jsonObject1.put("contextCode", contextCode1);
            jsonObject1.put("contextValue", contextValue1);

            jsonObject2.put("contextCode", contextCode2);
            jsonObject2.put("contextValue", contextValue2);

            jsonObject3.put("contextCode", contextCode3);
            jsonObject3.put("contextValue", contextValue3);

            jsonObject4.put("contextCode", contextCode4);
            jsonObject4.put("contextValue", contextValue4);
            subintentAttributeInfos.add(jsonObject1);
            subintentAttributeInfos.add(jsonObject2);
            subintentAttributeInfos.add(jsonObject3);
            subintentAttributeInfos.add(jsonObject4);
            json.put("subintentAttributeInfos", subintentAttributeInfos);
        }
        url = "http://10.110.147.195:8019/moli_kgservice/subintentInfo/getBackwardSubintentInfo";
        return "";
    }

    //Forward和subintent-正向推断
    @ResponseBody
    @RequestMapping("getForwardSubintentInfo")
    public String getForwardSubintentInfo(int status, String subintentCode) {
        String url = "";
        JSONObject json = new JSONObject();
        json.put("businessLine", "mobile");
        json.put("language", "en");
        json.put("domainCode", "fact");
        json.put("subintentCode", subintentCode);
        json.put("userName", "ccagent");
        json.put("passWord", "ccagent_kg_2018");
        url = "http://10.110.147.195:8019/moli_kgservice/subintentInfo/getForwardSubintentInfo";
        return "";
    }

    //推断-context
    @ResponseBody
    @RequestMapping("attributeValueNormalization")
    public String attributeValueNormalization(int status, String contextValue1, String contextValue2) {
        String url = "";
        JSONObject json = new JSONObject();
        json.put("businessLine", "mobile");
        JSONArray subintentAttributeInfos = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        jsonObject1.put("contextValue", contextValue1);
        jsonObject2.put("contextValue", contextValue2);
        subintentAttributeInfos.add(jsonObject1);
        subintentAttributeInfos.add(jsonObject2);
        json.put("subintentAttributeInfos", subintentAttributeInfos);
        json.put("language", "en");
        json.put("userName", "ccagent");
        json.put("passWord", "ccagent_kg_2018");
        url = "/subintentInfo/attributeValueNormalization";
        return "";
    }

    //查询机型系列（归一化二查）和 查询某一系列全部机型
    @ResponseBody
    @RequestMapping("getRelatedProductByNodeId")
    public String getRelatedProductByNodeId(int status, String slotKey, String slotValue) {
        String url = "";
        JSONObject json = new JSONObject();
        json.put("businessLine", "mobile");
        json.put("recognizedLevelId", "4");
        json.put("recognizedId", "moto_droid");
        JSONArray slotInfos = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("slotKey", slotKey);
        jsonObject.put("slotValue", slotValue);
        slotInfos.add(jsonObject);
        json.put("slotInfos", slotInfos);

        json.put("language", "en");
        json.put("userName", "ccagent");
        json.put("passWord", "ccagent_kg_2018");
        url = "http://10.110.147.194:8016/moli_kgservice/normalization/getRelatedProductByNodeId";
        return "";
    }

    //根据itemeCode查产品图片
    @ResponseBody
    @RequestMapping("getImg")
    public String getImg(String itemcode) {
        String url = "";
        JSONObject json = new JSONObject();
        json.put("businessLine", "mobile");
        json.put("serviceType", "itemcode_info");
        json.put("query", "");
        json.put("language", "en");
        json.put("userName", "ccagent");
        json.put("passWord", "ccagent_kg_2018");
        JSONObject queryJson = new JSONObject();
        queryJson.put("itemcode", itemcode);
        json.put("queryJson", queryJson);
        url = "http://10.110.147.194:8016/moli_kgservice/kgserver";
        return "";
    }

}
