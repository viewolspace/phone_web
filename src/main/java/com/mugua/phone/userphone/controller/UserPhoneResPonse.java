package com.mugua.phone.userphone.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by lenovo on 2019/7/22.
 */
@ApiModel
public class UserPhoneResPonse {
    @ApiModelProperty("status")
    private String status;

    @ApiModelProperty("返回消息")
    private String message;



    @ApiModelProperty("result")
    private UserPhoneVo result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public UserPhoneVo getResult() {
        return result;
    }

    public void setResult(UserPhoneVo result) {
        this.result = result;
    }

    @ApiModel
    static class UserPhoneVo {
        @ApiModelProperty("userId")
        private Integer userId;

        @ApiModelProperty("通讯录json格式")
        private String content;

        @ApiModelProperty("数量")
        private int num;

        @ApiModelProperty("时间")
        private Date cTime;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public Date getcTime() {
            return cTime;
        }

        public void setcTime(Date cTime) {
            this.cTime = cTime;
        }
    }
}
