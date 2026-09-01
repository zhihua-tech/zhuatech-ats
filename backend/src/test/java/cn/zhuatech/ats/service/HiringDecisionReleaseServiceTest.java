/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ats.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class HiringDecisionReleaseServiceTest {
    private final HiringDecisionReleaseService service = new HiringDecisionReleaseService();

    @Test void releasesGovernedHiringDecision() {
        var result = service.assess(new HiringDecisionReleaseService.Request("REQ-100", true, true, false,
                true, true, true, true, true, true, true));
        assertThat(result.decision()).isEqualTo(HiringDecisionReleaseService.Decision.RELEASE);
    }

    @Test void routesIncompleteOperationalWorkToHrReview() {
        var result = service.assess(new HiringDecisionReleaseService.Request("REQ-101", true, false, false,
                true, false, true, true, false, false, true));
        assertThat(result.actions()).hasSize(4);
        assertThat(result.decision()).isEqualTo(HiringDecisionReleaseService.Decision.HR_REVIEW);
    }

    @Test void blocksPrivacyBackgroundAndControlFailures() {
        var result = service.assess(new HiringDecisionReleaseService.Request("", false, false, true,
                false, false, false, false, false, false, false));
        assertThat(result.blockers()).hasSize(6);
        assertThat(result.decision()).isEqualTo(HiringDecisionReleaseService.Decision.BLOCKED);
    }
}
