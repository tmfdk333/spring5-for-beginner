package printer;

import org.springframework.beans.factory.annotation.Autowired;
import service.MemberDao;
import vo.Member;

public class MemberInfoPrinter2 {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MemberPrinter memberPrinter;

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
