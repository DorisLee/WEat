package edu.pitt.domain;

import edu.pitt.utilities.DbUtilities;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Liu
 */
public class Comment {
    private int commentID; 
    private String resultID;
    private int userID;
    private int groupID;
    private String commentText;
    
     /**
     * Comment constructor 1 : Retrieve data for provided commentID from MySQL
     * WEat.Comment table; Use retrieved data to populate corresponding
     * properties of the Comment object.
     * @param commentID
     */
    public Comment(int commentID) {
        String sql = "SELECT * FROM WEat.Comment  ";
        sql += " WHERE commentID = " + commentID  + " ;" ;
        DbUtilities db = new DbUtilities();
        try {
            ResultSet rs = db.getResultSet(sql);
            while (rs.next()) {
                this.commentID = Integer.parseInt(rs.getString("commentID"));
                this.resultID = rs.getString("resultID");
                this.userID = Integer.parseInt(rs.getString("userID"));
                this.groupID = Integer.parseInt(rs.getString("groupID"));
                this.commentText = rs.getString("commentText");
            }
        } catch (SQLException e) {
            System.out.println("Cannot construct Comment with commentID");
            e.printStackTrace();
        }
    }

    /**
     * Comment constructor 2 : Update appropriate properties of the Result
     * object; Insert a record into MySQL WEat.Result table.
     *
     * @param resultID
     * @param userID
     * @param groupID
     * @param commentText
     */
    public Comment(String resultID, int userID, int groupID, String commentText) {
        this.resultID = resultID;
        this.userID = userID;
        this.groupID = groupID;
        this.commentText = commentText;

        String sql = "INSERT INTO WEat.Comment ";
        sql += " (resultID,userID,groupID,commentText) ";
        sql += " VALUES ";
        sql += "('" + this.resultID + "', ";
        sql += this.userID + ", ";
        sql += this.groupID + ", ";
        sql += "'" + this.commentText + "');";

        DbUtilities db = new DbUtilities();
        db.executeQuery(sql);
    }
    
    public String toString()
    {
        String  s = userID + "said: " + commentText + "\n";
        return s;
    } 
}