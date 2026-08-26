/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ats.controller;
import cn.zhuatech.ats.common.ApiResponse; import cn.zhuatech.ats.service.EnterpriseAtsService; import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/enterprise/ats") public class EnterpriseAtsController {
 private final EnterpriseAtsService service; public EnterpriseAtsController(EnterpriseAtsService service){this.service=service;}
 @PostMapping("/screen-candidate") ApiResponse<?> execute(@Valid @RequestBody EnterpriseAtsService.ScreeningRequest request){return ApiResponse.ok(service.screen(request));}
}

