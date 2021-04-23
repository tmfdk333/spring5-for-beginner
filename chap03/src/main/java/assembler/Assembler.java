package assembler;

import service.MemberChangePasswordService;
import service.MemberDao;
import service.MemberRegisterService;

public class Assembler {
	private MemberDao memberDao;
	private MemberRegisterService memberRegisterService;
	private MemberChangePasswordService memberChangePasswordService;

	public Assembler() {
		memberDao = new MemberDao();
		memberRegisterService = new MemberRegisterService(memberDao);
		memberChangePasswordService = new MemberChangePasswordService();
		memberChangePasswordService.setMemberDao(memberDao);
	}

	public MemberDao getMemberDao() {
		return memberDao;
	}

	public MemberRegisterService getMemberRegisterService() {
		return memberRegisterService;
	}

	public MemberChangePasswordService getMemberChangePasswordService() {
		return memberChangePasswordService;
	}
}
