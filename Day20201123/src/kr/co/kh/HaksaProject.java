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
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		while (true) {
			try {
				conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql?characterEncoding=utf8","root","");
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
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
					e.printStackTrace();
				}
				if (protocol.equals("1")) {// 학생등록
					try {
						System.out.println("학생등록");
						System.out.println("나이 : ");
						String age1 = input.readLine();
						System.out.println("이름 : ");
						String irum = input.readLine();
						System.out.println("학번 : ");
						String hakbun1 = input.readLine();
						int cnt = 0;
						try {
							stmt = conn.createStatement();
							int age = Integer.parseInt(age1);
							int hakbun = Integer.parseInt(hakbun1);
							String sql = "insert into student(age,irum,hakbun)values("+age+ ",'" + irum + "'," + hakbun + ")";
							cnt = stmt.executeUpdate(sql);
							System.out.println(cnt + "건 학생이 등록되었습니다.");
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						} finally {
							try {
								stmt.close();
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (protocol.equals("2")) {
					try {
						System.out.println("교수등록");
						System.out.println("나이 : ");
						String age1 = input.readLine();
						System.out.println("이름 : ");
						String irum = input.readLine();
						System.out.println("과목 : ");
						String subject = input.readLine();
						int age = Integer.parseInt(age1);
						int cnt = 0;
						try {
							stmt = conn.createStatement();
							String sql = "insert into professor(no,age,irum,subject) values (professor_no.nextval,"
									+ age + ",'" + irum + "','" + subject + "')";
							cnt = stmt.executeUpdate(sql);
							System.out.println(cnt + "건 교수님이 등록되었습니다.");
						} catch (SQLException e) {
							e.printStackTrace();
						} finally {
							try {
								stmt.close();
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (protocol.equals("3")) {
					try {
						System.out.println("관리자등록");
						System.out.println("나이 : ");
						String age1 = input.readLine();
						System.out.println("이름 : ");
						String irum = input.readLine();
						System.out.println("부서 : ");
						String part = input.readLine();
						int cnt = 0;
						try {
							stmt = conn.createStatement();
							int age = Integer.parseInt(age1);
							String sql = "insert into manager(no,age,irum,part) values (manager_no.nextval," + age
									+ ",'" + irum + "','" + part + "')";
							cnt = stmt.executeUpdate(sql);
							System.out.println(cnt + "건의 관리자가 등록되었습니다.");
						} catch (SQLException e) {
							e.printStackTrace();
						} finally {
							try {
								stmt.close();
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
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
				System.out.println("찾을 대상 : ");
				System.out.println("1. 학생 2.교수 3.관리자");
				String gubun = null;
				try {
					gubun = input.readLine();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if (gubun.equals("1")) {// 학생 찾기
					try {
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
					} catch (IOException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						try {
							stmt.close();
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				} // 학생 찾기
				else if (gubun.equals("2")) { // 교수 찾기
					try {
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

					} catch (IOException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						try {
							stmt.close();
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				} // 교수 찾기
				else if (gubun.equals("3")) { // 관리자 찾기
					try {
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
					} catch (IOException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						try {
							stmt.close();
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				} // 관리자 찾기
			} // 찾기
			else if (protocol.equals("3")) {// 삭제
				System.out.println("삭제할 대상 : ");
				System.out.println("1. 학생 2.교수 3.관리자");
				String gubun = null;
				try {
					gubun = input.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (gubun.equals("1")) {// 학생 삭제
					try {
						System.out.println("삭제할 학생 이름을 입력하세요.");
						String deleteName = input.readLine();
						stmt = conn.createStatement();
						String sql = "DELETE FROM student2 where irum='" + deleteName + "'";
						int cnt = stmt.executeUpdate(sql);
						System.out.println(cnt + "건의 학생정보가 삭제되었습니다.");
					} catch (IOException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						try {
							stmt.close();
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}

				} // 학생삭제
				else if (gubun.equals("2")) {// 교수 삭제
					try {
						System.out.println("삭제할 교수 이름을 입력하세요.");
						String deleteName = input.readLine();
						stmt = conn.createStatement();
						String sql = "DELETE FROM professor where irum='" + deleteName + "'";
						int cnt = stmt.executeUpdate(sql);
						System.out.println(cnt + "건의 교수정보가 삭제되었습니다.");
					} catch (IOException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						try {
							stmt.close();
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				} // 교수삭제
				else if (gubun.equals("3")) {// 관리자 삭제
					try {
						System.out.println("삭제할 관리자 이름을 입력하세요.");
						String deleteName = input.readLine();
						stmt = conn.createStatement();
						String sql = "DELETE FROM manager where irum='" + deleteName + "'";
						int cnt = stmt.executeUpdate(sql);
						System.out.println(cnt + "건의 관리자정보가 삭제되었습니다.");
					} catch (IOException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						try {
							stmt.close();
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				} // 관리자 삭제

			} // 삭제
			else if (protocol.equals("4")) {// 전체출력
				try {
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
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						stmt.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} // 전체출력
			else if (protocol.equals("0")) {// 검색
				System.out.println("학사프로그램을 종료합니다.");
				System.exit(0);
			} // 검색
		} // while
	}
}
