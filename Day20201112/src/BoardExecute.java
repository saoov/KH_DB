import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BoardExecute {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		Connection conn = null;
		while (true) {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "khbclass", "dkdlxl");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		
			System.out.println("=======게시판작성=======");
			System.out.println("R:등록 S:검색 D:삭제 U:수정 L:목록");

			char protocol = input.next().charAt(0); // 정한 약속(protocol)대로 입력 받는다
			if (protocol == 'r' || protocol == 'R') {// 등록
				System.out.print("제목|내용 : ");
				String titleContent = input.next();
				int indexI = titleContent.indexOf("|");
				String title = titleContent.substring(0, indexI);
				String content = titleContent.substring(indexI + 1);
				System.out.print("작성자 입력 : ");
				String author = input.next();
				System.out.print("날짜 입력 : ");
				String nal = input.next();
				System.out.print("조회수 : ");
				int readcount = input.nextInt();
				try {
					// 3.준비(Statement) 3-1 공간을 준비 3-2 쿼리 준비
					// Statement는 sql(문장)을 가리키는 역할
					Statement stmt = conn.createStatement();// 공간준비
					String sql = "INSERT INTO board (no,title,content,author,nal,readcount) VALUES(board_no.nextval, '"+ title + "', '" + content + "','" + author + "','" + nal + "'," + readcount + ")";
					// 4.실행(execute)
					int cnt = stmt.executeUpdate(sql);
					System.out.println(cnt + "건 게시글이 등록되었습니다.");
					stmt.close(); // Statement 연결 해제
					conn.close(); // Connection 연결 해제
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} // 등록
			else if (protocol == 'S' || protocol == 's') {// 검색
				System.out.print("찾는 게시글 제목을 입력 : ");
				String titleSearch = input.next();
				System.out.print("번호\t제목\t내용\t작성자\t날짜\t\t조회수\n");
				try {
					Statement stmt = conn.createStatement();
					String sql = "SELECT NO,TITLE,CONTENT,AUTHOR,NAL,READCOUNT FROM BOARD WHERE TITLE='" + titleSearch+ "'";
					ResultSet rs = stmt.executeQuery(sql);
					rs = stmt.executeQuery(sql);
					int readcount =0;
					while (rs.next()) {
						int no = rs.getInt("no");
						String title = rs.getString("title");
						String content = rs.getString("content");
						String author = rs.getString("author");
						String nal = rs.getString("nal");
						readcount = rs.getInt("readcount");
						System.out.println(
								no + "\t" + title + "\t" + content + "\t" + author + "\t" + nal + "\t" + readcount);
						readcount++;
					}
					stmt = conn.createStatement();
					sql = "UPDATE board SET readcount="+readcount+" where title = '"+titleSearch+"'";
					int cnt = stmt.executeUpdate(sql);
					
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} // 검색
			else if (protocol == 'D' || protocol == 'd') {// 삭제
				System.out.print("삭제할 게시글 제목을 입력 : ");
				String titleDelete = input.next();
				
				try {
					//3. 준비
					//3.1 공간을 준비한다.
					Statement stmt = conn.createStatement();
					//3.2 쿼리를 준비한다.
					String sql = "DELETE FROM board where title='"+titleDelete+"'";
					int cnt = stmt.executeUpdate(sql);
					System.out.println(cnt+"건 게시글이 삭제되었습니다.");
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} // 삭제
			else if (protocol == 'U' || protocol == 'u') {// 수정
				System.out.print("변경할 게시글 제목 입력 : ");
				String titleSearch = input.next();
				String sql = null;
				try {
					Statement stmt = conn.createStatement();
					sql = "SELECT TITLE, CONTENT, AUTHOR, NAL, READCOUNT FROM BOARD WHERE TITLE='"+titleSearch+"'"; 
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println("===변경하기 전 게시글입니다===");
					System.out.print("제목\t내용\t작성자\t날짜\t\t조회수\n");
					while(rs.next()) {
						String title = rs.getString("title");
						String content = rs.getString("content");
						String author = rs.getString("author");
						String nal = rs.getString("nal");
						int readcount = rs.getInt("readcount");
						System.out.println(
								title + "\t" + content + "\t" + author + "\t" + nal + "\t" + readcount);
					}
					System.out.println("정말로 변경하시겠습니까?(y/n)");
					char option = input.next().charAt(0);
					if(option=='Y'||option=='y') {
						System.out.print("제목|내용 : ");
						String titleContent = input.next();
						int indexI = titleContent.indexOf("|");
						String titleUpdate = titleContent.substring(0, indexI);
						String contentUpdate = titleContent.substring(indexI + 1);
						System.out.print("작성자 입력 : ");
						String authorUpdate = input.next();
						System.out.print("날짜 입력 : ");
						String nalUpdate = input.next();
						System.out.print("조회수 : ");
						int readcountUpdate = input.nextInt();
						stmt = conn.createStatement();
						sql = "UPDATE board SET TITLE='"+titleUpdate+"', content='"+contentUpdate+"', author='"+authorUpdate+"', nal='"+nalUpdate+"', readcount='"+readcountUpdate+"' where title='"+titleSearch+"'";
						int cnt = stmt.executeUpdate(sql);
						System.out.println(cnt+"건 게시글이 수정되었습니다.");
						stmt.close();
						conn.close();
					} else {
						stmt.close();
						conn.close();
						continue;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} // 수정
			else if (protocol == 'L' || protocol == 'l') {// 목록(전체출력)
				System.out.println("===게시판 전체 출력===");
				System.out.print("번호\t제목\t내용\t작성자\t날짜\t\t조회수\n");
				//3.1 공간 준비
				try {
					Statement stmt = conn.createStatement();
					//3.2 쿼리 준비
					String sql = "SELECT * FROM board";
					ResultSet rs = stmt.executeQuery(sql);
					while(rs.next()) {
						int no = rs.getInt("no");
						String title = rs.getString("title");
						String content = rs.getString("content");
						String author = rs.getString("author");
						String nal = rs.getString("nal");
						int readcount = rs.getInt("readcount");
						System.out.println(
								no + "\t" + title + "\t" + content + "\t" + author + "\t" + nal + "\t" + readcount);
					}
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // 목록
		} // while
	}

}
