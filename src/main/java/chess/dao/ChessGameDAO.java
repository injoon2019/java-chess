package chess.dao;

import chess.controller.WebChessGame;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Color;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChessGameDAO {

    private final Gson gson = new Gson();

    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:3306"; // MySQL 서버 주소
        String database = "java_chess"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "root"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager
                .getConnection("jdbc:mysql://" + server + "/" + database + option, userName,
                    password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    // 드라이버 연결해제
    public void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void addGame(WebChessGame chessGame) throws SQLException {
        String query = "INSERT INTO chess_game (turn, finished, board) VALUES (?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, chessGame.getTurn());
        pstmt.setBoolean(2, chessGame.isOver());
        pstmt.setString(3, gson.toJson(chessGame.getChessBoardMap()));
        pstmt.executeUpdate();
    }

    public int recentGame() throws SQLException {
        String query = "SELECT MAX(id) FROM chess_game";
        Statement statement = getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            return rs.getInt("MAX(id)");
        }
        return -1;
    }

    public void saveGame(int id, WebChessGame chessGame) throws SQLException {
        String query = "UPDATE chess_game SET (turn, finished, board) = (?, ?, ?) WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, chessGame.getTurn());
        pstmt.setBoolean(2, chessGame.isOver());
        pstmt.setString(3, gson.toJson(chessGame.getChessBoardMap()));
        pstmt.setInt(4, id);
        pstmt.executeUpdate();
    }

    public WebChessGame loadGame(int id) throws SQLException {
        String query = "SELECT * FROM chess_game WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        return new WebChessGame(
            gson.fromJson(rs.getString("board"), ChessBoard.class),
            gson.fromJson(rs.getString("turn"), Color.class)
        );
    }
}
