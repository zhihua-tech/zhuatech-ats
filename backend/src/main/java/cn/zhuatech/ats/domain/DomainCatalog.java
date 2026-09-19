/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ats.domain;

import org.springframework.stereotype.Component;
import java.util.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Component
public class DomainCatalog {
    private final Map<String, WorkflowAction> actions = new LinkedHashMap<>();

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public DomainCatalog() {
        actions.put("SCREEN", new WorkflowAction("SCREEN", "通过筛选", List.of("新申请"), "待面试", "OPERATOR"));
        actions.put("INTERVIEW", new WorkflowAction("INTERVIEW", "面试通过", List.of("待面试"), "待决策", "OPERATOR"));
        actions.put("OFFER", new WorkflowAction("OFFER", "发放 Offer", List.of("待决策"), "待入职", "ADMIN"));
        actions.put("HIRE", new WorkflowAction("HIRE", "确认入职", List.of("待入职"), "已入职", "ADMIN"));
        actions.put("REJECT", new WorkflowAction("REJECT", "淘汰候选人", List.of("新申请","待面试","待决策","待入职"), "已淘汰", "OPERATOR"));
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String systemName() { return "知华科技招聘管理 ATS"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String scene() { return "招聘需求、职位发布、人才库、筛选、面试、Offer、背调与入职协同"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String initialStatus() { return "新申请"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String partyLabel() { return "候选人/用人部门"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String amountLabel() { return "预算薪资"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String quantityLabel() { return "候选人数"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String dueLabel() { return "计划入职日"; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<ModuleDefinition> modules() {
        return List.of(
            new ModuleDefinition("REQUISITION", "招聘需求", "管理编制、预算、审批与招聘优先级"),
            new ModuleDefinition("JOB", "职位发布", "维护职位画像并同步内外部招聘渠道"),
            new ModuleDefinition("TALENT_POOL", "人才库", "沉淀候选人标签、来源和人才关系"),
            new ModuleDefinition("APPLICATION", "候选申请", "管理简历、隐私同意与应聘记录"),
            new ModuleDefinition("SCREENING", "智能筛选", "执行必备条件、评分卡和重复简历识别"),
            new ModuleDefinition("INTERVIEW", "面试协同", "编排面试轮次、面试官与结构化评价"),
            new ModuleDefinition("OFFER", "Offer 管理", "控制薪酬方案、审批、签署和有效期"),
            new ModuleDefinition("BACKGROUND", "背景调查", "管理授权、调查项、异常与复核结论"),
            new ModuleDefinition("ONBOARDING", "入职协同", "向 EHR、IT、行政推送待办和入职资料"),
            new ModuleDefinition("ANALYTICS", "招聘分析", "分析渠道、周期、转化、成本和质量")
        );
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Map<String, WorkflowAction> actions() { return Collections.unmodifiableMap(actions); }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record ModuleDefinition(String code, String name, String description) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record WorkflowAction(String code, String label, List<String> from, String to, String requiredRole) {}
}
