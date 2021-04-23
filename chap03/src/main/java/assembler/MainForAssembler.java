package assembler;

import assembler.Assembler;
import exception.MemberDuplicateException;
import exception.MemberNotFoundException;
import exception.WrongPasswordException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import service.MemberChangePasswordService;
import service.MemberRegisterService;
import vo.RegisterRequest;

public class MainForAssembler {
	private static Assembler assembler = new Assembler();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("명령어를 입력하세요");
			String command = br.readLine();

			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다");
				break;
			}

			if (command.startsWith("new ")) {
				processNewCommand(command.split(" "));
				continue;
			} else if (command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			}
			printHelp();
		}
	}

	private static void processNewCommand(String[] arg) {
		if (arg.length != 5) {
			// printHelp();
			return;
		}
		RegisterRequest registerRequest = new RegisterRequest();
		registerRequest.setEmail(arg[1]);
		registerRequest.setName(arg[2]);
		registerRequest.setPassword(arg[3]);
		registerRequest.setConfirmPassword(arg[4]);

		if (!registerRequest.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치하지 않습니다.");
			return;
		}

		MemberRegisterService memberRegisterService = assembler.getMemberRegisterService();

		try {
			memberRegisterService.register(registerRequest);
			System.out.println("회원이 등록되었습니다.\n");
		} catch (MemberDuplicateException e) {
			System.out.println("이미 존재하는 회원입니다.\n");
		}
	}

	private static void processChangeCommand(String[] arg) {
		if (arg.length != 4) {
			// printHelp();
			return;
		}

		MemberChangePasswordService memberChangePasswordService = assembler.getMemberChangePasswordService();

		try {
			memberChangePasswordService.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("암호가 변경되었습니다.\n");
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 회원입니다.\n");
		} catch (WrongPasswordException e) {
			System.out.println("비밀번호가 일치하지 않습니다.\n");
		}
	}

	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요");
		System.out.println("명령어 사용법: ");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 현재비밀번호 변경비밀번호");
		System.out.println();
	}
}
