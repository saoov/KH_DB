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

		
			System.out.println("=======�Խ����ۼ�=======");
			System.out.println("R:��� S:�˻� D:���� U:���� L:���");

			char protocol = input.next().charAt(0); // ���� ���(protocol)��� �Է� �޴´�
			if (protocol == 'r' || protocol == 'R') {// ���
				System.out.print("����|���� : ");
				String titleContent = input.next();
				int indexI = titleContent.indexOf("|");
				String title = titleContent.substring(0, indexI);
				String content = titleContent.substring(indexI + 1);
				System.out.print("�ۼ��� �Է� : ");
				String author = input.next();
				System.out.print("��¥ �Է� : ");
				String nal = input.next();
				System.out.print("��ȸ�� : ");
				int readcount = input.nextInt();
				try {
					// 3.�غ�(Statement) 3-1 ������ �غ� 3-2 ���� �غ�
					// Statement�� sql(����)�� ����Ű�� ����
					Statement stmt = conn.createStatement();// �����غ�
					String sql = "INSERT INTO board (no,title,content,author,nal,readcount) VALUES(board_no.nextval, '"+ title + "', '" + content + "','" + author + "','" + nal + "'," + readcount + ")";
					// 4.����(execute)
					int cnt = stmt.executeUpdate(sql);
					System.out.println(cnt + "�� �Խñ��� ��ϵǾ����ϴ�.");
					stmt.close(); // Statement ���� ����
					conn.close(); // Connection ���� ����
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} // ���
			else if (protocol == 'S' || protocol == 's') {// �˻�
				System.out.print("ã�� �Խñ� ������ �Է� : ");
				String titleSearch = input.next();
				System.out.print("��ȣ\t����\t����\t�ۼ���\t��¥\t\t��ȸ��\n");
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

			} // �˻�
			else if (protocol == 'D' || protocol == 'd') {// ����
				System.out.print("������ �Խñ� ������ �Է� : ");
				String titleDelete = input.next();
				
				try {
					//3. �غ�
					//3.1 ������ �غ��Ѵ�.
					Statement stmt = conn.createStatement();
					//3.2 ������ �غ��Ѵ�.
					String sql = "DELETE FROM board where title='"+titleDelete+"'";
					int cnt = stmt.executeUpdate(sql);
					System.out.println(cnt+"�� �Խñ��� �����Ǿ����ϴ�.");
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} // ����
			else if (protocol == 'U' || protocol == 'u') {// ����
				System.out.print("������ �Խñ� ���� �Է� : ");
				String titleSearch = input.next();
				String sql = null;
				try {
					Statement stmt = conn.createStatement();
					sql = "SELECT TITLE, CONTENT, AUTHOR, NAL, READCOUNT FROM BOARD WHERE TITLE='"+titleSearch+"'"; 
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println("===�����ϱ� �� �Խñ��Դϴ�===");
					System.out.print("����\t����\t�ۼ���\t��¥\t\t��ȸ��\n");
					while(rs.next()) {
						String title = rs.getString("title");
						String content = rs.getString("content");
						String author = rs.getString("author");
						String nal = rs.getString("nal");
						int readcount = rs.getInt("readcount");
						System.out.println(
								title + "\t" + content + "\t" + author + "\t" + nal + "\t" + readcount);
					}
					System.out.println("������ �����Ͻðڽ��ϱ�?(y/n)");
					char option = input.next().charAt(0);
					if(option=='Y'||option=='y') {
						System.out.print("����|���� : ");
						String titleContent = input.next();
						int indexI = titleContent.indexOf("|");
						String titleUpdate = titleContent.substring(0, indexI);
						String contentUpdate = titleContent.substring(indexI + 1);
						System.out.print("�ۼ��� �Է� : ");
						String authorUpdate = input.next();
						System.out.print("��¥ �Է� : ");
						String nalUpdate = input.next();
						System.out.print("��ȸ�� : ");
						int readcountUpdate = input.nextInt();
						stmt = conn.createStatement();
						sql = "UPDATE board SET TITLE='"+titleUpdate+"', content='"+contentUpdate+"', author='"+authorUpdate+"', nal='"+nalUpdate+"', readcount='"+readcountUpdate+"' where title='"+titleSearch+"'";
						int cnt = stmt.executeUpdate(sql);
						System.out.println(cnt+"�� �Խñ��� �����Ǿ����ϴ�.");
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

			} // ����
			else if (protocol == 'L' || protocol == 'l') {// ���(��ü���)
				System.out.println("===�Խ��� ��ü ���===");
				System.out.print("��ȣ\t����\t����\t�ۼ���\t��¥\t\t��ȸ��\n");
				//3.1 ���� �غ�
				try {
					Statement stmt = conn.createStatement();
					//3.2 ���� �غ�
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
			} // ���
		} // while
	}

}
