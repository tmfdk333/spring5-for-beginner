package service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import vo.Member;

public class MemberDao {
	private static long nextId = 0;

	private Map<String, Member> mapDatabase = new HashMap<>();

	public Member selectByEmail(String email) {
		return mapDatabase.get(email);
	}

	public void insert(Member member) {
		member.setId(++nextId);
		mapDatabase.put(member.getEmail(), member);
	}

	public void update(Member member) {
		mapDatabase.put(member.getEmail(), member);
	}

	public Collection<Member> selectAll() {
		return mapDatabase.values();
	}
}
