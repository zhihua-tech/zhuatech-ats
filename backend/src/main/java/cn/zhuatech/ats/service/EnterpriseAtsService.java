/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ats.service;
import jakarta.validation.Valid; import jakarta.validation.constraints.*; import org.springframework.stereotype.Service; import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service public class EnterpriseAtsService {
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public ScreeningResult screen(@Valid ScreeningRequest r){
  Set<String> actual=new HashSet<>(); r.skills().forEach(s->actual.add(s.toLowerCase(Locale.ROOT))); List<String> missing=r.mustHaveSkills().stream().filter(s->!actual.contains(s.toLowerCase(Locale.ROOT))).toList();
  List<String> blockers=new ArrayList<>(); if(!r.privacyConsent()) blockers.add("候选人隐私授权缺失"); if(r.yearsExperience()<r.minimumYears()) blockers.add("工作年限不满足"); if(!missing.isEmpty()) blockers.add("缺少必备技能: "+String.join(",",missing));
  int score=Math.min(100,Math.max(0,r.resumeScore()*40/100+r.assessmentScore()*30/100+r.interviewScore()*30/100));
  String decision=!blockers.isEmpty()?"REJECTED":score>=70?"SHORTLISTED":"MANUAL_REVIEW";
  return new ScreeningResult(r.candidateNo(),score,missing,blockers,decision);
 }
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record ScreeningRequest(@NotBlank String candidateNo,@Email @NotBlank String email,boolean privacyConsent,@Min(0) int yearsExperience,@Min(0) int minimumYears,@NotNull Set<@NotBlank String> skills,@NotNull Set<@NotBlank String> mustHaveSkills,@Min(0) @Max(100) int resumeScore,@Min(0) @Max(100) int assessmentScore,@Min(0) @Max(100) int interviewScore){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record ScreeningResult(String candidateNo,int totalScore,List<String> missingSkills,List<String> blockers,String decision){}
}

