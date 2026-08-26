/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ats;
import org.junit.jupiter.api.Test; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.context.SpringBootTest; import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc; import org.springframework.http.MediaType; import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic; import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc class EnterpriseAtsApiTests { @Autowired MockMvc mvc;

 @Test void qualifiedCandidateIsShortlisted() throws Exception {mvc.perform(post("/api/enterprise/ats/screen-candidate").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content("""
 {"candidateNo":"C-001","email":"candidate@example.com","privacyConsent":true,"yearsExperience":6,"minimumYears":3,"skills":["Java","MySQL","Vue"],"mustHaveSkills":["Java","MySQL"],"resumeScore":85,"assessmentScore":90,"interviewScore":80}
 """)).andExpect(status().isOk()).andExpect(jsonPath("$.data.totalScore").value(85)).andExpect(jsonPath("$.data.decision").value("SHORTLISTED"));}
 @Test void consentAndMustHaveSkillsAreEnforced() throws Exception {mvc.perform(post("/api/enterprise/ats/screen-candidate").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content("""
 {"candidateNo":"C-002","email":"candidate2@example.com","privacyConsent":false,"yearsExperience":1,"minimumYears":3,"skills":["Vue"],"mustHaveSkills":["Java"],"resumeScore":90,"assessmentScore":90,"interviewScore":90}
 """)).andExpect(status().isOk()).andExpect(jsonPath("$.data.decision").value("REJECTED")).andExpect(jsonPath("$.data.blockers.length()").value(3));}
}

