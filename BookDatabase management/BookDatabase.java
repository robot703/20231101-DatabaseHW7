import java.sql.*;
import java.util.Scanner;

public class BookDatabase {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://192.168.200.85:4567/madang";
    static final String USER = "minjae";
    static final String PASSWORD = "1234";

    public static void main(String[] args) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement statement = connection.createStatement();

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1. 삽입 | 2. 삭제 | 3. 검색 | 4. 출력 | 5. 종료");
                System.out.print("원하는 기능을 선택하세요: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // 데이터 삽입
                        System.out.print("Book ID: ");
                        int bookid = scanner.nextInt();
                        System.out.print("Book Name: ");
                        String bookname = scanner.next();
                        System.out.print("Publisher: ");
                        String publisher = scanner.next();
                        System.out.print("Price: ");
                        int price = scanner.nextInt();

                        String insertQuery = "INSERT INTO Book (bookid, bookname, publisher, price) VALUES (" +
                                bookid + ", '" + bookname + "', '" + publisher + "', " + price + ")";
                        statement.executeUpdate(insertQuery);
                        System.out.println("데이터가 성공적으로 삽입되었습니다.");
                        break;

                    case 2:
                        // 데이터 삭제
                        System.out.print("삭제할 Book ID: ");
                        int deleteId = scanner.nextInt();
                        String deleteQuery = "DELETE FROM Book WHERE bookid=" + deleteId;
                        statement.executeUpdate(deleteQuery);
                        System.out.println("데이터가 성공적으로 삭제되었습니다.");
                        break;

                    case 3:
                        // 데이터 검색
                        System.out.print("검색할 Book ID: ");
                        int searchId = scanner.nextInt();
                        String selectQuery = "SELECT * FROM Book WHERE bookid=" + searchId;
                        ResultSet resultSet = statement.executeQuery(selectQuery);
                        while (resultSet.next()) {
                            System.out.println("Book ID: " + resultSet.getInt("bookid") +
                                    ", Book Name: " + resultSet.getString("bookname") +
                                    ", Publisher: " + resultSet.getString("publisher") +
                                    ", Price: " + resultSet.getInt("price"));
                        }
                        resultSet.close();
                        break;

                    case 4:
                        // 데이터 출력
                        String outputQuery = "SELECT * FROM Book";
                        ResultSet outputResultSet = statement.executeQuery(outputQuery);
                        while (outputResultSet.next()) {
                            System.out.println("Book ID: " + outputResultSet.getInt("bookid") +
                                    ", Book Name: " + outputResultSet.getString("bookname") +
                                    ", Publisher: " + outputResultSet.getString("publisher") +
                                    ", Price: " + outputResultSet.getInt("price"));
                        }
                        outputResultSet.close();
                        break;

                    case 5:
                        // 종료
                        statement.close();
                        connection.close();
                        scanner.close();
                        System.out.println("프로그램을 종료합니다.");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("올바른 선택이 아닙니다. 다시 선택해주세요.");
                        break;
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
