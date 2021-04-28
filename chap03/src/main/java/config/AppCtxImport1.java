package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import printer.MemberPrinter;
import service.MemberDao;
import vo.Member;

@Configuration
@Import(AppCtx2.class)
public class AppCtxImport1 {
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}

	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
}
