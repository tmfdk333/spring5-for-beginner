package config;

import exception.MemberDuplicateException;
import exception.MemberNotFoundException;
import exception.WrongPasswordException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import printer.MemberInfoPrinter;
import printer.VersionPrinter;
import service.MemberChangePasswordService;
import printer.MemberListPrinter;
import service.MemberRegisterService;
import vo.RegisterRequest;

public class MainForConfig {
	private static ApplicationContext atx = null;

	public static void main(String[] args) throws IOException {
//		atx = new AnnotationConfigApplicationContext(AppCtx.class);
//		atx = new AnnotationConfigApplicationContext(AppCtx1.class, AppCtx2.class);
		atx = new AnnotationConfigApplicationContext(AppCtxImport2.class);

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
			} else if (command.trim().equals("list")) {
				processListCommand();
				continue;
			} else if (command.startsWith("info ")) {
				processInfoCommand(command.split(" "));
				continue;
			} else if (command.trim().equals("version")) {
				processVersionCommand();
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

		MemberRegisterService memberRegisterService = atx.getBean("memberRegisterService", MemberRegisterService.class);

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

		MemberChangePasswordService memberChangePasswordService = atx.getBean("memberChangePasswordService", MemberChangePasswordService.class);

		try {
			memberChangePasswordService.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("암호가 변경되었습니다.\n");
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 회원입니다.\n");
		} catch (WrongPasswordException e) {
			System.out.println("비밀번호가 일치하지 않습니다.\n");
		}
	}

	private static void processListCommand() {
		MemberListPrinter memberListPrinter = atx.getBean("memberListPrinter", MemberListPrinter.class);
		memberListPrinter.printAll();
	}

	private static void processInfoCommand(String[] arg) {
		if (arg.length != 2) {
			// printHelp();
			return;
		}
		MemberInfoPrinter memberInfoPrinter = atx.getBean("memberInfoPrinter", MemberInfoPrinter.class);
		memberInfoPrinter.printInfo(arg[1]);
	}

	private static void processVersionCommand() {
		VersionPrinter versionPrinter = atx.getBean("versionPrinter", VersionPrinter.class);
		versionPrinter.printVersion();
	}

	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요");
		System.out.println("명령어 사용법: ");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 현재비밀번호 변경비밀번호");
		System.out.println("list");
		System.out.println("info 이메일");
		System.out.println("version");
		System.out.println();
	}
}
