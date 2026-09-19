/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ats.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class HiringDecisionReleaseService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Result assess(Request request) {
        var blockers = new ArrayList<String>();
        var actions = new ArrayList<String>();
        if (request.requisitionId() == null || request.requisitionId().isBlank()) blockers.add("招聘需求编号不能为空");
        if (!request.candidateConsent()) blockers.add("候选人隐私授权缺失");
        if (request.backgroundCheckRequired() && !request.backgroundCheckPassed()) blockers.add("必要背调未通过");
        if (!request.headcountAvailable()) blockers.add("招聘编制不足");
        if (!request.decisionMakerSeparated()) blockers.add("面试决策与薪酬审批未职责分离");
        if (!request.auditReady()) blockers.add("招聘决策审计证据不完整");
        if (!request.interviewPanelComplete()) actions.add("补齐面试小组评分");
        if (!request.compensationApproved()) actions.add("完成薪酬方案审批");
        if (!request.retentionPolicyAccepted()) actions.add("确认候选人数据留存策略");
        if (!request.offerTemplateApproved()) actions.add("审批 Offer 模板");
        var decision = !blockers.isEmpty() ? Decision.BLOCKED : actions.isEmpty() ? Decision.RELEASE : Decision.HR_REVIEW;
        return new Result(decision, List.copyOf(blockers), List.copyOf(actions));
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public enum Decision { RELEASE, HR_REVIEW, BLOCKED }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(String requisitionId, boolean candidateConsent, boolean interviewPanelComplete,
                          boolean backgroundCheckRequired, boolean backgroundCheckPassed,
                          boolean compensationApproved, boolean headcountAvailable,
                          boolean decisionMakerSeparated, boolean retentionPolicyAccepted,
                          boolean offerTemplateApproved, boolean auditReady) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Result(Decision decision, List<String> blockers, List<String> actions) {}
}
