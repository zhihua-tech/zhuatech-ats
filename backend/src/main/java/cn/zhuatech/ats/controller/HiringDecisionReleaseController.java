/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ats.controller;

import cn.zhuatech.ats.common.ApiResponse;
import cn.zhuatech.ats.service.HiringDecisionReleaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/ats")
public class HiringDecisionReleaseController {
    private final HiringDecisionReleaseService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public HiringDecisionReleaseController(HiringDecisionReleaseService service) { this.service = service; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/hiring-decision-release")
    public ApiResponse<?> assess(@RequestBody HiringDecisionReleaseService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
