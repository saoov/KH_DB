package kr.co.kh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberExecuterMe {
	public static String session;
	static {
		session = null;
	}

	public static void main(String[] args) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		Connection conn = null;
		String protocol = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "jsa", "dkdlxl");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		while (true) {
			System.out.println("R:회원가입 L:회원목록 S:ID찾기 D:회원탈퇴 E:회원수정 I:로그인 O:로그아웃");
			try {
				protocol = input.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (protocol.equalsIgnoreCase("r")) {// 회원가입
				if(session==null) {
				try {
					System.out.println("아이디입력 : ");
					String id = input.readLine();
					System.out.println("패스워드입력 : ");
					String pw = input.readLine();
					System.out.println("주소 입력");
					String addr = input.readLine();
					System.out.println("전화번호입력 : ");
					String tel = input.readLine();
					Statement stmt = conn.createStatement();
					String sql = "INSERT INTO member (id,pw,addr,tel) VALUES ('" + id + "','" + pw + "','" + addr
							+ "','" + tel + "')";
					int cnt = stmt.executeUpdate(sql);
					System.out.println(cnt + "명 회원이 등록되었습니다.");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				} else {
					System.out.println("로그인되어있습니다.");
					continue;
				}
			} // 회원가입종료
			else if (protocol.equalsIgnoreCase("l")) {// 회원목록
				System.out.print("회원아이디\t\t" + "회원패스워드\t" + "회원주소\t" + "회원전화번호\t\n");
				ResultSet rs = null;
				try {
					Statement stmt = conn.createStatement();
					String sql = "SELECT id,pw,addr,tel from member";
					rs = stmt.executeQuery(sql);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					while (rs.next()) {
						String listId = rs.getString("id");
						String listPw = rs.getString("pw");
						String listAddr = rs.getString("addr");
						String listTel = rs.getString("tel");
						System.out.print(listId + "\t" + listPw + "\t" + listAddr + "\t" + listTel + "\n");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} // 회원목록종료
			else if (protocol.equalsIgnoreCase("s")) {// ID찾기
				System.out.println("찾는 ID를 입력 : ");
				ResultSet rs = null;
				try {
					String searchInputID = input.readLine();
					Statement stmt = conn.createStatement();
					String sql = "SELECT ID,PW,ADDR,TEL FROM MEMBER WHERE ID='" + searchInputID + "'";
					rs = stmt.executeQuery(sql);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				try {
					System.out.print("회원아이디\t\t" + "회원패스워드\t" + "회원주소\t" + "회원전화번호\t\n");
					while (rs.next()) {
						String searchId = rs.getString("id");
						String searchPw = rs.getString("pw");
						String searchAddr = rs.getString("addr");
						String searchTel = rs.getString("tel");
						System.out.println(searchId + "\t" + searchPw + "\t" + searchAddr + "\t" + searchTel + "\n");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} // ID찾기종료
			else if (protocol.equalsIgnoreCase("d")) {// 회원탈퇴
				Statement stmt = null;
				if (session != null) {
					try {
						stmt = conn.createStatement();
						String sql = "DELETE FROM MEMBER WHERE ID='" + session + "'";
						int cnt = stmt.executeUpdate(sql);
						System.out.println("회원 탈퇴되었습니다.");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("로그인 되어있지 않습니다.");
				}

			} // 회원탈퇴종료
			else if (protocol.equalsIgnoreCase("e")) {// 회원수정
				Statement stmt = null;
				String sql;
				try {
					stmt = conn.createStatement();
					sql = "SELECT id,pw,addr,tel from member where id='" + session + "'";
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) {
						String updateId = rs.getString("id");
						String updatePw = rs.getString("pw");
						String updateAddr = rs.getString("addr");
						String updateTel = rs.getString("tel");
						System.out.println(updateId + "\t" + updatePw + "\t" + updateAddr + "\t" + updateTel + "\n");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (session != null) {
					try {
						System.out.println("아이디입력 : ");
						String id = input.readLine();
						System.out.println("패스워드입력 : ");
						String pw = input.readLine();
						System.out.println("주소 입력");
						String addr = input.readLine();
						System.out.println("전화번호입력 : ");
						String tel = input.readLine();
						sql = "UPDATE MEMBER SET ID='" + id + "', pw='" + pw + "', addr='" + addr + "', tel='" + tel
								+ "' where id = '" + session + "'";
						int cnt = stmt.executeUpdate(sql);
						System.out.println(cnt + "명의 회원이 수정되었습니다.");
					} catch (IOException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("로그인되어있지 않습니다.");
				}

			} // 회원수정종료
			else if (protocol.equalsIgnoreCase("i")) {// 로그인
				
				try {
					System.out.println("아이디입력 : ");
					String loginId = input.readLine();
					System.out.println("패스워드입력 : ");
					String loginPw = input.readLine();
					Statement stmt = conn.createStatement();
					String sql = "SELECT id,pw,addr,tel FROM MEMBER WHERE (ID,PW) IN (SELECT ID, PW FROM MEMBER WHERE ID='"
							+ loginId + "' AND pw='" + loginPw + "')";
					int cnt = stmt.executeUpdate(sql);
					if (cnt != 0) {
						System.out.println("로그인되었습니다.");
						session = loginId;
					} else {
						System.out.println("회원정보가 올바르지 않습니다.");
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} // 로그인종료
			else if (protocol.equalsIgnoreCase("o")) {// 로그아웃
				if (session != null) {
					System.out.println("로그아웃되었습니다.");
					session = null;
				} else {
					System.out.println("로그인 되어있지 않습니다.");
				}
			} // 로그아웃종료
		} // while
	}// main

}
