package service;

import exception.MemberDuplicateException;
import java.time.LocalDateTime;
import vo.Member;
import vo.RegisterRequest;

public class MemberRegisterService {
	private MemberDao memberDao;

	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public Long register(RegisterRequest request) {
		Member existMember = memberDao.selectByEmail(request.getEmail());
		if (existMember != null) {
			throw new MemberDuplicateException("이미 존재하는 이메일입니다. " + request.getEmail());
		}
		Member newMember = new Member(request.getEmail(), request.getPassword(), request.getName(), LocalDateTime.now());
		memberDao.insert(newMember);
		return newMember.getId();
	}
}
