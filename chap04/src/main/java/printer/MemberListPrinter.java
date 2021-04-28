package printer;

import java.util.Collection;
import service.MemberDao;
import vo.Member;

public class MemberListPrinter {
	private MemberDao memberDao;
	private MemberPrinter memberPrinter;

	public MemberListPrinter(MemberDao memberDao, MemberPrinter memberPrinter) {
		this.memberDao = memberDao;
		this.memberPrinter = memberPrinter;
	}

	public void printAll() {
		Collection<Member> members = memberDao.selectAll();
		members.forEach(member -> memberPrinter.print(member));
		System.out.println();
	}
}
