package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.MemberChangePasswordService;
import service.MemberDao;
import service.MemberRegisterService;

@Configuration
public class AppCtx {
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}

	@Bean
	public MemberRegisterService memberRegisterService() {
		return new MemberRegisterService(memberDao());
	}

	@Bean
	public MemberChangePasswordService memberChangePasswordService() {
		MemberChangePasswordService memberChangePasswordService = new MemberChangePasswordService();
		memberChangePasswordService.setMemberDao(memberDao());
		return memberChangePasswordService;
	}
}
