package com.mugua.phone.tool.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.youguu.core.pojo.Response;
import com.youguu.core.util.HttpUtil;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * Created by lenovo on 2020/1/20.
 */
@SwaggerDefinition(
        tags = {
                @Tag(name = "v1.0", description = "用户API")
        }
)
@Api(value = "ToolAction")
@Path(value = "/tool")
@Controller("toolAction")
public class ToolAction {

    @GET
    @Path(value = "/phoneSelect")
    @Produces("text/html;charset=UTF-8")
    @ApiOperation(value = "手机号码归属查询", notes = "", author = "更新于 2019-07-22")
    @ApiResponses(value = {
            @ApiResponse(code = "0000", message = "请求成功"),
            @ApiResponse(code = "0001", message = "失败")

    })
    public String phoneSelect(@ApiParam(value = "手机号码") @QueryParam("phone") String phone){
        String url = "http://mobsec-dianhua.baidu.com/dianhua_api/open/location?tel=" + phone;
        Response<String> msg = HttpUtil.sendGet(url, null, "UTF-8");
        JSONObject json = new JSONObject();
        json.put("status","0000");
        json.put("message","未知");
        try{
            if(msg!=null && msg.getT()!=null){
                String res = msg.getT();
                JSONObject jsRes = JSON.parseObject(res);
                JSONObject phoneMess = jsRes.getJSONObject("response").getJSONObject(phone);
                if(phoneMess!=null){
                    json.put("message",phoneMess.getString("location"));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return json.toJSONString();
    }
}
