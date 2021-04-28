package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import printer.MemberInfoPrinter;
import printer.MemberListPrinter;
import printer.MemberPrinter;
import printer.VersionPrinter;
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

	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}

	@Bean
	public MemberListPrinter memberListPrinter() {
		return new MemberListPrinter(memberDao(), memberPrinter());
	}

	@Bean
	public MemberInfoPrinter memberInfoPrinter() {
		MemberInfoPrinter memberInfoPrinter = new MemberInfoPrinter();
		memberInfoPrinter.setMemberDao(memberDao());
		memberInfoPrinter.setMemberPrinter(memberPrinter());
		return memberInfoPrinter;
	}

	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(0);
		versionPrinter.setMinorVersion(1);
		return versionPrinter;
	}
}
