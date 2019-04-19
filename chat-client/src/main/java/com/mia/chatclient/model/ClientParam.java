package com.mia.chatclient.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ClientParam
 * @Description TODO
 * @Author mia.he
 * @Date 2019/4/19
 * @Version 1.0
 */
@Data
public class ClientParam {
    @ApiModelProperty("内容")
    private String clientContent;
    @ApiModelProperty("服务器")
    private String serverName;
    @ApiModelProperty("端口")
    private int serverPort;
}
