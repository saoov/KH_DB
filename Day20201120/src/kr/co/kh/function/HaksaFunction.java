package kr.co.kh.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HaksaFunction {

	public static String register(String protocol, BufferedReader input, Statement stmt, Connection conn, int cnt)
			throws IOException, SQLException {
		System.out.println("======메뉴선택======");
		System.out.println("1. 학생");
		System.out.println("2. 교수");
		System.out.println("3. 관리자");
		System.out.println("4. 이전메뉴");
		System.out.println("번호를 선택해 주세요..");
		try {
			protocol = input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (protocol.equals("1")) {// 학생등록
			System.out.println("학생등록");
			System.out.println("나이 : ");
			String age1 = input.readLine();
			System.out.println("이름 : ");
			String irum = input.readLine();
			System.out.println("학번 : ");
			String hakbun1 = input.readLine();
			cnt = 0;
			stmt = conn.createStatement();
			int age = Integer.parseInt(age1);
			int hakbun = Integer.parseInt(hakbun1);
			String sql = "insert into student2(no, age,irum,hakbun)values(student2_no.nextval," + age + ",'" + irum
					+ "'," + hakbun + ")";
			cnt = stmt.executeUpdate(sql);
			System.out.println(cnt + "건 학생이 등록되었습니다.");

		} else if (protocol.equals("2")) {
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

		} else if (protocol.equals("3")) {
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
		return protocol;
	}

	public static void search(BufferedReader input, Statement stmt, Connection conn) throws IOException, SQLException {
		System.out.println("찾을 대상 : ");
		System.out.println("1. 학생 2.교수 3.관리자");
		String gubun = null;
			gubun = input.readLine();
		if (gubun.equals("1")) {// 학생 찾기
				System.out.println("찾을 학생이름을 입력하세요.");
				String searchName = input.readLine();
				stmt = conn.createStatement();
				String sql = "SELECT no,age,irum,hakbun from student2 where irum='" + searchName + "'";
				int op = stmt.executeUpdate(sql);
				ResultSet rs = stmt.executeQuery(sql);
				if (op != 0) {
					while (rs.next()) {
						int age = rs.getInt("age");
						String irum = rs.getString("irum");
						int hakbun = rs.getInt("hakbun");
						System.out.print("나이:" + age + "\t" + "이름:" + irum + "\t" + "학번:" + hakbun + "\n");
					}
				} else {
					System.out.println("찾는 학생이 없습니다.");
				}
		} // 학생 찾기
		else if (gubun.equals("2")) { // 교수 찾기
				System.out.println("찾을 교수이름을 입력하세요.");
				String searchName = input.readLine();
				stmt = conn.createStatement();
				String sql = "SELECT no,age,irum,subject from professor where irum='" + searchName + "'";
				int op = stmt.executeUpdate(sql);
				ResultSet rs = stmt.executeQuery(sql);
				if (op != 0) {
					while (rs.next()) {
						int age = rs.getInt("age");
						String irum = rs.getString("irum");
						String subject = rs.getString("subject");
						System.out.print("나이:" + age + "\t" + "이름:" + irum + "\t" + "과목:" + subject + "\n");
					}
				} else {
					System.out.println("찾는 교수님이 없습니다.");
				}

		} // 교수 찾기
		else if (gubun.equals("3")) { // 관리자 찾기
				System.out.println("찾을 관리자이름을 입력하세요.");
				String searchName = input.readLine();
				stmt = conn.createStatement();
				String sql = "SELECT no,age,irum,part from manager where irum='" + searchName + "'";
				int op = stmt.executeUpdate(sql);
				ResultSet rs = stmt.executeQuery(sql);
				if (op != 0) {
					while (rs.next()) {
						int age = rs.getInt("age");
						String irum = rs.getString("irum");
						String part = rs.getString("part");
						System.out.print("나이:" + age + "\t" + "이름:" + irum + "\t" + "부서:" + part + "\n");
					}
				} else {
					System.out.println("찾는 관리자가 없습니다.");
				}
		} // 관리자 찾기
	}

	public static void delete(BufferedReader input, Statement stmt, Connection conn, int cnt) throws SQLException, IOException {
		System.out.println("삭제할 대상 : ");
		System.out.println("1. 학생 2.교수 3.관리자");
		String gubun = null;
			gubun = input.readLine();
		if (gubun.equals("1")) {// 학생 삭제
				System.out.println("삭제할 학생 이름을 입력하세요.");
				String deleteName = input.readLine();
				stmt = conn.createStatement();
				String sql = "DELETE FROM student2 where irum='" + deleteName + "'";
				cnt = stmt.executeUpdate(sql);
				System.out.println(cnt + "건의 학생정보가 삭제되었습니다.");

		} // 학생삭제
		else if (gubun.equals("2")) {// 교수 삭제
				System.out.println("삭제할 교수 이름을 입력하세요.");
				String deleteName = input.readLine();
				stmt = conn.createStatement();
				String sql = "DELETE FROM professor where irum='" + deleteName + "'";
				cnt = stmt.executeUpdate(sql);
				System.out.println(cnt + "건의 교수정보가 삭제되었습니다.");
		} // 교수삭제
		else if (gubun.equals("3")) {// 관리자 삭제
				System.out.println("삭제할 관리자 이름을 입력하세요.");
				String deleteName = input.readLine();
				stmt = conn.createStatement();
				String sql = "DELETE FROM manager where irum='" + deleteName + "'";
				cnt = stmt.executeUpdate(sql);
				System.out.println(cnt + "건의 관리자정보가 삭제되었습니다.");
			}

	}

	public static void list(Statement stmt, Connection conn) throws SQLException {
			stmt = conn.createStatement();
			String sql = "select no,age,irum,hakbun from student2";
			ResultSet rs = stmt.executeQuery(sql); // rs는 테이블을 가르킨다
			while (rs.next()) {
				int age = rs.getInt("age");
				String irum = rs.getString("irum");
				int hakbun = rs.getInt("hakbun");
				System.out.print("이름 :");
				System.out.print(irum + "\t");
				System.out.print("나이 :");
				System.out.print(age + "\t");
				System.out.print("학번 :");
				System.out.print(hakbun + "\n");
			} // 학생 전체출력
			stmt = conn.createStatement();
			sql = "select no,age,irum,subject from professor";
			rs = stmt.executeQuery(sql); // rs는 테이블을 가르킨다
			while (rs.next()) {
				int age = rs.getInt("age");
				String irum = rs.getString("irum");
				String subject = rs.getString("subject");
				System.out.print("이름 :");
				System.out.print(irum + "\t");
				System.out.print("나이 :");
				System.out.print(age + "\t");
				System.out.print("과목 :");
				System.out.print(subject + "\n");
			} // 교수 전체출력
			stmt = conn.createStatement();
			sql = "select no,age,irum,part from manager";
			rs = stmt.executeQuery(sql); // rs는 테이블을 가르킨다
			while (rs.next()) {
				int age = rs.getInt("age");
				String irum = rs.getString("irum");
				String part = rs.getString("part");
				System.out.print("이름 :");
				System.out.print(irum + "\t");
				System.out.print("나이 :");
				System.out.print(age + "\t");
				System.out.print("부서 :");
				System.out.print(part + "\n");
			} // 관리자 전체출력
			stmt = conn.createStatement();
			sql = "select s.no as 번호,s.age as 나이,s.irum as 이름,s.hakbun as 학번,p.age as 교수나이,p.irum as 교수이름,p.subject as 과목,m.age as 관리자나이,m.irum as 관리자이름,m.part as 부서 from (student2 s full outer join professor p on s.no=p.no) full outer join manager m on s.no=m.no ORDER BY 번호 ASC";
			rs = stmt.executeQuery(sql); // rs는 테이블을 가르킨다
			System.out.println("======학사전체출력======");
			while (rs.next()) {
				int sage = rs.getInt("나이");
				String sirum = rs.getString("이름");
				int shakbun = rs.getInt("학번");
				int page = rs.getInt("교수나이");
				String pirum = rs.getString("교수이름");
				String psubject = rs.getString("과목");
				int mage = rs.getInt("관리자나이");
				String mirum = rs.getString("관리자이름");
				String mpart = rs.getString("부서");
				System.out.print(sage + "\t" + sirum + "\t" + shakbun + "\t" + page + "\t" + pirum + "\t"
						+ psubject + "\t" + mage + "\t" + mirum + "\t" + mpart + "\n");
			} // 전체출력
	}

}
