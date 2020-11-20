package kr.co.kh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HaksaProject {

	public static void main(String[] args) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String protocol = null;
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "khbclass", "dkdlxl");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		while (true) {
			System.out.println("======학사관리======");
			System.out.println("1. 등록");
			System.out.println("2. 찾기");
			System.out.println("3. 삭제");
			System.out.println("4. 전체출력");
			System.out.println("============");
			System.out.println("0. 종료");
			System.out.println("번호를 선택해 주세요..");

			try {
				protocol = input.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (protocol.equals("1")) {// 등록
				System.out.println("======메뉴선택======");
				System.out.println("1. 학생");
				System.out.println("2. 교수");
				System.out.println("3. 관리자");
				System.out.println("4. 이전메뉴");
				System.out.println("번호를 선택해 주세요..");
				try {
					protocol = input.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (protocol.equals("1")) {// 학생등록
					System.out.println("학생등록");
					System.out.println("나이 : ");
					try {
						String age1 = input.readLine();
						System.out.println("이름 : ");
						String irum = input.readLine();
						System.out.println("학번 : ");
						String hakbun1 = input.readLine();
						int cnt = 0;
						try {
							Statement stmt = conn.createStatement();
							int age = Integer.parseInt(age1);
							int hakbun = Integer.parseInt(hakbun1);
							String sql = "insert into student2(no, age,irum,hakbun)values(student2_no.nextval," + age
									+ ",'" + irum + "'," + hakbun + ")";
							cnt = stmt.executeUpdate(sql);
							System.out.println(cnt + "건 학생이 등록되었습니다.");
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (protocol.equals("2")) {
					System.out.println("교수등록");
					System.out.println("나이 : ");
					try {
						String age1 = input.readLine();
						System.out.println("이름 : ");
						String irum = input.readLine();
						System.out.println("과목 : ");
						String subject = input.readLine();
						int age = Integer.parseInt(age1);
						int cnt = 0;
						try {
							Statement stmt = conn.createStatement();
							String sql = "insert into professor(no,age,irum,subject) values (professor_no.nextval,"
									+ age + ",'" + irum + "','" + subject + "')";
							cnt = stmt.executeUpdate(sql);
							System.out.println(cnt + "건 교수님이 등록되었습니다.");
						} catch (SQLException e) {
							e.printStackTrace();
						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (protocol.equals("3")) {
					System.out.println("관리자등록");
					System.out.println("나이 : ");
					try {
						String age1 = input.readLine();
						System.out.println("이름 : ");
						String irum = input.readLine();
						System.out.println("부서 : ");
						String part = input.readLine();
						int cnt = 0;
						try {
							Statement stmt = conn.createStatement();
							int age = Integer.parseInt(age1);
							String sql = "insert into manager(no,age,irum,part) values (manager_no.nextval," + age
									+ ",'" + irum + "','" + part + "')";
							cnt = stmt.executeUpdate(sql);
							System.out.println(cnt + "건의 관리자가 등록되었습니다.");
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (protocol.equals("4")) {
					continue;
				}
				System.out.println("계속하시려면 1, 종료 하시려면 0을 입력해 주세요..");
				try {
					String cnt = input.readLine();
					if (cnt.equals("1")) {
						continue;
					} else {
						System.exit(0);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} // 등록
			else if (protocol.equals("2")) {// 찾기
			} // 찾기
			else if (protocol.equals("3")) {// 삭제
			} // 삭제
			else if (protocol.equals("4")) {// 전체출력
				try {
					Statement stmt = conn.createStatement();
					String sql = "select no,age,irum,hakbun from student2";
					ResultSet rs = stmt.executeQuery(sql); // rs는 테이블을 가르킨다
					while (rs.next()) {
						int no = rs.getInt("no");
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
						int no = rs.getInt("no");
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
						int no = rs.getInt("no");
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
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} // 전체출력
			else if (protocol.equals("0")) {// 검색
				System.out.println("학사프로그램을 종료합니다.");
				System.exit(0);
			} // 검색
		} // while
	}
}
