package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import printer.MemberInfoPrinter;
import printer.MemberInfoPrinter2;
import printer.MemberListPrinter;
import printer.MemberPrinter;
import printer.VersionPrinter;
import service.MemberChangePasswordService;
import service.MemberDao;
import service.MemberRegisterService;

@Configuration
public class AppCtx2 {
	@Autowired
	private MemberDao memberDao;

	@Autowired
	private MemberPrinter memberPrinter;

	@Bean
	public MemberRegisterService memberRegisterService() {
		return new MemberRegisterService(memberDao);
	}

	@Bean
	public MemberChangePasswordService memberChangePasswordService() {
		MemberChangePasswordService memberChangePasswordService = new MemberChangePasswordService();
		memberChangePasswordService.setMemberDao(memberDao);
		return memberChangePasswordService;
	}

	@Bean
	public MemberListPrinter memberListPrinter() {
		return new MemberListPrinter(memberDao, memberPrinter);
	}

	@Bean
	public MemberInfoPrinter2 memberInfoPrinter2() {
		MemberInfoPrinter2 memberInfoPrinter2 = new MemberInfoPrinter2();
		return memberInfoPrinter2;
	}

	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(0);
		versionPrinter.setMinorVersion(1);
		return versionPrinter;
	}
}
