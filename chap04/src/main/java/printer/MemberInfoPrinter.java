package printer;

import service.MemberDao;
import vo.Member;

public class MemberInfoPrinter {
	private MemberDao memberDao;
	private MemberPrinter memberPrinter;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void setMemberPrinter(MemberPrinter memberPrinter) {
		this.memberPrinter = memberPrinter;
	}

	public void printInfo(String email) {
		Member member = memberDao.selectByEmail(email);
		if (member == null) {
			System.out.println("데이터 없음");
			return;
		}
		memberPrinter.print(member);
		System.out.println();
	}
}
