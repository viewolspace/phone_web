package com.chenghe.parttime.phone.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2019/7/22.
 */
@ApiModel
public class UserPhoneListResPonse {
    @ApiModelProperty("status")
    private String status;

    @ApiModelProperty("返回消息")
    private String message;



    @ApiModelProperty("result")
    private List<UserPhoneVo> result;

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



    public List<UserPhoneVo> getResult() {
        return result;
    }

    public void setResult(List<UserPhoneVo> result) {
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
