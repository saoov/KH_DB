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
			System.out.println("======�л����======");
			System.out.println("1. ���");
			System.out.println("2. ã��");
			System.out.println("3. ����");
			System.out.println("4. ��ü���");
			System.out.println("============");
			System.out.println("0. ����");
			System.out.println("��ȣ�� ������ �ּ���..");
			try {
				protocol = input.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (protocol.equals("1")) {// ���
				System.out.println("======�޴�����======");
				System.out.println("1. �л�");
				System.out.println("2. ����");
				System.out.println("3. ������");
				System.out.println("4. �����޴�");
				System.out.println("��ȣ�� ������ �ּ���..");
				try {
					protocol = input.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (protocol.equals("1")) {// �л����
					try {
						System.out.println("�л����");
						System.out.println("���� : ");
						String age1 = input.readLine();
						System.out.println("�̸� : ");
						String irum = input.readLine();
						System.out.println("�й� : ");
						String hakbun1 = input.readLine();
						int cnt = 0;
						try {
							stmt = conn.createStatement();
							int age = Integer.parseInt(age1);
							int hakbun = Integer.parseInt(hakbun1);
							String sql = "insert into student(age,irum,hakbun)values("+age+ ",'" + irum + "'," + hakbun + ")";
							cnt = stmt.executeUpdate(sql);
							System.out.println(cnt + "�� �л��� ��ϵǾ����ϴ�.");
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
						System.out.println("�������");
						System.out.println("���� : ");
						String age1 = input.readLine();
						System.out.println("�̸� : ");
						String irum = input.readLine();
						System.out.println("���� : ");
						String subject = input.readLine();
						int age = Integer.parseInt(age1);
						int cnt = 0;
						try {
							stmt = conn.createStatement();
							String sql = "insert into professor(no,age,irum,subject) values (professor_no.nextval,"
									+ age + ",'" + irum + "','" + subject + "')";
							cnt = stmt.executeUpdate(sql);
							System.out.println(cnt + "�� �������� ��ϵǾ����ϴ�.");
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
						System.out.println("�����ڵ��");
						System.out.println("���� : ");
						String age1 = input.readLine();
						System.out.println("�̸� : ");
						String irum = input.readLine();
						System.out.println("�μ� : ");
						String part = input.readLine();
						int cnt = 0;
						try {
							stmt = conn.createStatement();
							int age = Integer.parseInt(age1);
							String sql = "insert into manager(no,age,irum,part) values (manager_no.nextval," + age
									+ ",'" + irum + "','" + part + "')";
							cnt = stmt.executeUpdate(sql);
							System.out.println(cnt + "���� �����ڰ� ��ϵǾ����ϴ�.");
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
				System.out.println("����Ͻ÷��� 1, ���� �Ͻ÷��� 0�� �Է��� �ּ���..");
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
			} // ���
			else if (protocol.equals("2")) {// ã��
				System.out.println("ã�� ��� : ");
				System.out.println("1. �л� 2.���� 3.������");
				String gubun = null;
				try {
					gubun = input.readLine();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if (gubun.equals("1")) {// �л� ã��
					try {
						System.out.println("ã�� �л��̸��� �Է��ϼ���.");
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
								System.out.print("����:" + age + "\t" + "�̸�:" + irum + "\t" + "�й�:" + hakbun + "\n");
							}
						} else {
							System.out.println("ã�� �л��� �����ϴ�.");
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
				} // �л� ã��
				else if (gubun.equals("2")) { // ���� ã��
					try {
						System.out.println("ã�� �����̸��� �Է��ϼ���.");
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
								System.out.print("����:" + age + "\t" + "�̸�:" + irum + "\t" + "����:" + subject + "\n");
							}
						} else {
							System.out.println("ã�� �������� �����ϴ�.");
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
				} // ���� ã��
				else if (gubun.equals("3")) { // ������ ã��
					try {
						System.out.println("ã�� �������̸��� �Է��ϼ���.");
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
								System.out.print("����:" + age + "\t" + "�̸�:" + irum + "\t" + "�μ�:" + part + "\n");
							}
						} else {
							System.out.println("ã�� �����ڰ� �����ϴ�.");
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
				} // ������ ã��
			} // ã��
			else if (protocol.equals("3")) {// ����
				System.out.println("������ ��� : ");
				System.out.println("1. �л� 2.���� 3.������");
				String gubun = null;
				try {
					gubun = input.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (gubun.equals("1")) {// �л� ����
					try {
						System.out.println("������ �л� �̸��� �Է��ϼ���.");
						String deleteName = input.readLine();
						stmt = conn.createStatement();
						String sql = "DELETE FROM student2 where irum='" + deleteName + "'";
						int cnt = stmt.executeUpdate(sql);
						System.out.println(cnt + "���� �л������� �����Ǿ����ϴ�.");
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

				} // �л�����
				else if (gubun.equals("2")) {// ���� ����
					try {
						System.out.println("������ ���� �̸��� �Է��ϼ���.");
						String deleteName = input.readLine();
						stmt = conn.createStatement();
						String sql = "DELETE FROM professor where irum='" + deleteName + "'";
						int cnt = stmt.executeUpdate(sql);
						System.out.println(cnt + "���� ���������� �����Ǿ����ϴ�.");
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
				} // ��������
				else if (gubun.equals("3")) {// ������ ����
					try {
						System.out.println("������ ������ �̸��� �Է��ϼ���.");
						String deleteName = input.readLine();
						stmt = conn.createStatement();
						String sql = "DELETE FROM manager where irum='" + deleteName + "'";
						int cnt = stmt.executeUpdate(sql);
						System.out.println(cnt + "���� ������������ �����Ǿ����ϴ�.");
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
				} // ������ ����

			} // ����
			else if (protocol.equals("4")) {// ��ü���
				try {
					stmt = conn.createStatement();
					String sql = "select no,age,irum,hakbun from student2";
					ResultSet rs = stmt.executeQuery(sql); // rs�� ���̺��� ����Ų��
					while (rs.next()) {
						int age = rs.getInt("age");
						String irum = rs.getString("irum");
						int hakbun = rs.getInt("hakbun");
						System.out.print("�̸� :");
						System.out.print(irum + "\t");
						System.out.print("���� :");
						System.out.print(age + "\t");
						System.out.print("�й� :");
						System.out.print(hakbun + "\n");
					} // �л� ��ü���
					stmt = conn.createStatement();
					sql = "select no,age,irum,subject from professor";
					rs = stmt.executeQuery(sql); // rs�� ���̺��� ����Ų��
					while (rs.next()) {
						int age = rs.getInt("age");
						String irum = rs.getString("irum");
						String subject = rs.getString("subject");
						System.out.print("�̸� :");
						System.out.print(irum + "\t");
						System.out.print("���� :");
						System.out.print(age + "\t");
						System.out.print("���� :");
						System.out.print(subject + "\n");
					} // ���� ��ü���
					stmt = conn.createStatement();
					sql = "select no,age,irum,part from manager";
					rs = stmt.executeQuery(sql); // rs�� ���̺��� ����Ų��
					while (rs.next()) {
						int age = rs.getInt("age");
						String irum = rs.getString("irum");
						String part = rs.getString("part");
						System.out.print("�̸� :");
						System.out.print(irum + "\t");
						System.out.print("���� :");
						System.out.print(age + "\t");
						System.out.print("�μ� :");
						System.out.print(part + "\n");
					} // ������ ��ü���
					stmt = conn.createStatement();
					sql = "select s.no as ��ȣ,s.age as ����,s.irum as �̸�,s.hakbun as �й�,p.age as ��������,p.irum as �����̸�,p.subject as ����,m.age as �����ڳ���,m.irum as �������̸�,m.part as �μ� from (student2 s full outer join professor p on s.no=p.no) full outer join manager m on s.no=m.no ORDER BY ��ȣ ASC";
					rs = stmt.executeQuery(sql); // rs�� ���̺��� ����Ų��
					System.out.println("======�л���ü���======");
					while (rs.next()) {
						int sage = rs.getInt("����");
						String sirum = rs.getString("�̸�");
						int shakbun = rs.getInt("�й�");
						int page = rs.getInt("��������");
						String pirum = rs.getString("�����̸�");
						String psubject = rs.getString("����");
						int mage = rs.getInt("�����ڳ���");
						String mirum = rs.getString("�������̸�");
						String mpart = rs.getString("�μ�");
						System.out.print(sage + "\t" + sirum + "\t" + shakbun + "\t" + page + "\t" + pirum + "\t"
								+ psubject + "\t" + mage + "\t" + mirum + "\t" + mpart + "\n");
					} // ��ü���
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
			} // ��ü���
			else if (protocol.equals("0")) {// �˻�
				System.out.println("�л����α׷��� �����մϴ�.");
				System.exit(0);
			} // �˻�
		} // while
	}
}
