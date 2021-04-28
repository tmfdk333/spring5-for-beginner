package service;

import exception.MemberNotFoundException;
import vo.Member;

public class MemberChangePasswordService {
	private MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void changePassword(String email, String oldPassword, String newPassword) {
		Member existMember = memberDao.selectByEmail(email);
		if (existMember == null) {
			throw new MemberNotFoundException();
		}
		existMember.changePassword(oldPassword, newPassword);
		memberDao.update(existMember);
	}
}
