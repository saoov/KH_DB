package kr.co.kh.obj;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Register {

	private String protocol;
	private BufferedReader input;
	private Statement stmt;
	private Connection conn;
	private int cnt;
	
	public void showMenu() throws IOException {
		System.out.println("======메뉴선택======");
		System.out.println("1. 학생");
		System.out.println("2. 교수");
		System.out.println("3. 관리자");
		System.out.println("4. 이전메뉴");
		System.out.println("번호를 선택해 주세요..");
			protocol = input.readLine();
	}
	
	public void registerStudent() throws IOException, SQLException {
		System.out.println("학생등록");
		System.out.println("나이 : ");
		String age1 = input.readLine();
		System.out.println("이름 : ");
		String irum = input.readLine();
		System.out.println("학번 : ");
		String hakbun1 = input.readLine();
		stmt = conn.createStatement();
		int age = Integer.parseInt(age1);
		int hakbun = Integer.parseInt(hakbun1);
		String sql = "insert into student2(no, age,irum,hakbun)values(student2_no.nextval," + age + ",'" + irum+ "'," + hakbun + ")";
		cnt = stmt.executeUpdate(sql);
		System.out.println(cnt + "건 학생이 등록되었습니다.");
	}
	
	public void registerProfessor() throws IOException, SQLException {
		System.out.println("교수등록");
		System.out.println("나이 : ");
		String age1 = input.readLine();
		System.out.println("이름 : ");
		String irum = input.readLine();
		System.out.println("과목 : ");
		String subject = input.readLine();
		int age = Integer.parseInt(age1);
		cnt = 0;
		stmt = conn.createStatement();
		String sql = "insert into professor(no,age,irum,subject) values (professor_no.nextval," + age + ",'" + irum
				+ "','" + subject + "')";
		cnt = stmt.executeUpdate(sql);
		System.out.println(cnt + "건 교수님이 등록되었습니다.");
	}
	
	public void registerManager() throws IOException, SQLException {
		System.out.println("관리자등록");
		System.out.println("나이 : ");
		String age1 = input.readLine();
		System.out.println("이름 : ");
		String irum = input.readLine();
		System.out.println("부서 : ");
		String part = input.readLine();
		cnt = 0;
		stmt = conn.createStatement();
		int age = Integer.parseInt(age1);
		String sql = "insert into manager(no,age,irum,part) values (manager_no.nextval," + age + ",'" + irum + "','"
				+ part + "')";
		cnt = stmt.executeUpdate(sql);
		System.out.println(cnt + "건의 관리자가 등록되었습니다.");
	}
	
	
}

/*

	
	if (protocol.equals("1")) {// 학생등록
		

	} else if (protocol.equals("2")) {
		

	} else if (protocol.equals("3")) {
		

	}
	return protocol;
}
*/