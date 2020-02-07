package com.mugua.phone.userphone.controller;

/**
 * Created by lenovo on 2019/7/23.
 */

import com.alibaba.fastjson.JSONObject;
import com.mugua.phone.pojo.User;
import com.mugua.phone.pojo.UserPhone;
import com.mugua.phone.service.IUserPhoneService;
import com.mugua.phone.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;


@SwaggerDefinition(
        tags = {
                @Tag(name = "v1.0", description = "用户API")
        }
)
@Api(value = "UserPhoneAction")
@Path(value = "/userPhone")
@Controller("userPhoneAction")
public class UserPhoneAction {

    @Resource
    private IUserService userService;

    @Resource
    private IUserPhoneService userPhoneService;

    @POST
    @Path(value = "/uploadPhoneAddress")
    @Produces("text/html;charset=UTF-8")
    @ApiOperation(value = "上传通讯录", notes = "", author = "更新于 2019-07-22")
    @ApiResponses(value = {
            @ApiResponse(code = "0000", message = "请求成功"),
            @ApiResponse(code = "0001", message = "失败")

    })
    public String updateUser(@ApiParam(value = "通讯录JSON格式", required = true) @FormParam("address") String address,
                             @ApiParam(value = "用户ID", required = true) @HeaderParam("userId") int userId) {

        JSONObject json = new JSONObject();

        User user = userService.getUser(userId);

        if (user == null) {
            json.put("status", "0001");
            json.put("message", "用户不存在");
            return json.toJSONString();
        }

        UserPhone userPhone = new UserPhone();
        userPhone.setUserId(userId);
        userPhone.setContent(address);
        userPhoneService.addUserPhone(userPhone);

        json.put("status", "0000");

        json.put("message", "ok");


        return json.toJSONString();
    }


    @GET
    @Path(value = "/getUserPhone")
    @Produces("text/html;charset=UTF-8")
    @ApiOperation(value = "获取云通讯录,空表示还没有上传数据", notes = "", author = "更新于 2019-07-22")
    @ApiResponses(value = {
            @ApiResponse(code = "0000", message = "请求成功", response = UserPhoneResPonse.class),
            @ApiResponse(code = "0001", message = "失败")

    })
    public String getUserPhone(@ApiParam(value = "userId", required = true) @HeaderParam("userId") int userId) {

        UserPhoneResPonse resPonse = new UserPhoneResPonse();

        try {
            UserPhone userPhone = userPhoneService.getUserPhone(userId);
            if (userPhone != null) {
                UserPhoneResPonse.UserPhoneVo userPhoneVo = new UserPhoneResPonse.UserPhoneVo();
                userPhoneVo.setContent(userPhone.getContent());
                userPhoneVo.setUserId(userPhone.getUserId());
                userPhoneVo.setcTime(userPhone.getcTime());
                userPhoneVo.setNum(userPhone.getNum());
                resPonse.setResult(userPhoneVo);
            }
            resPonse.setStatus("0000");
            resPonse.setMessage("ok");

        } catch (Exception e) {
            resPonse.setStatus("0002");
            resPonse.setMessage("系统异常");
            e.printStackTrace();
        }
        return JSONObject.toJSONString(resPonse);
    }


    @GET
    @Path(value = "/listUserPhone")
    @Produces("text/html;charset=UTF-8")
    @ApiOperation(value = "获取云通讯录", notes = "", author = "更新于 2019-07-22")
    @ApiResponses(value = {
            @ApiResponse(code = "0000", message = "请求成功", response = UserPhoneResPonse.class),
            @ApiResponse(code = "0001", message = "失败")

    })
    public String listUserPhone(@ApiParam(value = "userId", required = true) @HeaderParam("userId") int userId,
                                @ApiParam(value = " 最大id 第一次请求传0就可以") @QueryParam("maxId") @DefaultValue("0") int maxId,
                                @ApiParam(value = "每页数量") @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

        UserPhoneListResPonse resPonse = new UserPhoneListResPonse();

        try {
            List<UserPhone> userPhones = userPhoneService.listUserPhone(userId, maxId, pageSize);
            if (userPhones != null) {
                List<UserPhoneListResPonse.UserPhoneVo> UserPhoneVos = new ArrayList<UserPhoneListResPonse.UserPhoneVo>();
                for (UserPhone userPhone : userPhones) {
                    UserPhoneListResPonse.UserPhoneVo userPhoneVo = new UserPhoneListResPonse.UserPhoneVo();
                    userPhoneVo.setContent(userPhone.getContent());
                    userPhoneVo.setUserId(userPhone.getUserId());
                    userPhoneVo.setcTime(userPhone.getcTime());
                    userPhoneVo.setNum(userPhone.getNum());
                    UserPhoneVos.add(userPhoneVo);
                }
                resPonse.setResult(UserPhoneVos);

            }
            resPonse.setStatus("0000");
            resPonse.setMessage("ok");

        } catch (Exception e) {
            resPonse.setStatus("0002");
            resPonse.setMessage("系统异常");
            e.printStackTrace();
        }
        return JSONObject.toJSONString(resPonse);
    }
}
