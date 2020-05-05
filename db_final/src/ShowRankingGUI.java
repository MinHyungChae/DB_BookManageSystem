import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ShowRankingGUI {

	private JFrame frame;
	
	private JLabel BookRankingLabel;
	private String BookRankingCategory[] = { "Book Name", "Author" };
	private DefaultTableModel BookRankingModel;
	private JTable BookRankingTable;
	private JScrollPane BookRankingScrollPane;
	
	private JLabel GenreRankingLabel;
	private String GenreRankingCategory[] = { "Genre", "Total Count" };
	private DefaultTableModel GenreRankingModel;
	private JTable GenreRankingTable;
	private JScrollPane GenreRankingScrollPane;
	
	private JLabel BookByGenreRankingLabel;
	private JLabel InputGenreLabel;
	private JTextField genreTextField;
	private JButton btnSubmit;
	private JLabel genreStatusLabel;
	private String BookByGenreRankingCategory[] = { "Book Name", "Author" };
	private DefaultTableModel BookByGenreRankingModel;
	private JTable BookByGenreRankingTable;
	private JScrollPane BookByGenreScrollPane;
	
	private Connection conn = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ShowRankingGUI() {
		initialize();
		showRanking();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Best Ranking Books");
		frame.setBounds(100, 100, 400, 467);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		BookRankingLabel = new JLabel("Top 5 Books");
		BookRankingLabel.setBounds(19, 27, 94, 16);
		frame.getContentPane().add(BookRankingLabel);
		
		BookRankingModel = new DefaultTableModel(BookRankingCategory, 0);
		BookRankingTable = new JTable(BookRankingModel);
		BookRankingScrollPane = new JScrollPane(BookRankingTable);
		BookRankingScrollPane.setBounds(19, 55, 362, 105);
		frame.getContentPane().add(BookRankingScrollPane);
		
		GenreRankingLabel = new JLabel("Top 3 Genres");
		GenreRankingLabel.setBounds(19, 180, 94, 16);
		frame.getContentPane().add(GenreRankingLabel);
		
		GenreRankingModel = new DefaultTableModel(GenreRankingCategory, 0);
		GenreRankingTable = new JTable(GenreRankingModel);
		GenreRankingScrollPane = new JScrollPane(GenreRankingTable);
		GenreRankingScrollPane.setBounds(19, 208, 362, 70);
		frame.getContentPane().add(GenreRankingScrollPane);
		
		BookByGenreRankingLabel = new JLabel("Top 3 Books by Genre");
		BookByGenreRankingLabel.setBounds(17, 297, 149, 16);
		frame.getContentPane().add(BookByGenreRankingLabel);
		
		InputGenreLabel = new JLabel("Input Genre: ");
		InputGenreLabel.setBounds(19, 325, 94, 16);
		frame.getContentPane().add(InputGenreLabel);
		
		genreTextField = new JTextField();
		genreTextField.setBounds(102, 319, 117, 26);
		frame.getContentPane().add(genreTextField);
		genreTextField.setColumns(10);
		
		btnSubmit = new JButton("SUBMIT");
		btnSubmit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnSubmitActionPerformed();
				}
			}
		});
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnSubmit) {
					btnSubmitActionPerformed();
				}
			}
		});
		btnSubmit.setBounds(219, 319, 90, 29);
		frame.getContentPane().add(btnSubmit);
		
		genreStatusLabel = new JLabel("");
		genreStatusLabel.setForeground(Color.RED);
		genreStatusLabel.setBounds(178, 297, 131, 16);
		frame.getContentPane().add(genreStatusLabel);

		BookByGenreRankingModel = new DefaultTableModel(BookByGenreRankingCategory, 0);
		BookByGenreRankingTable = new JTable(BookByGenreRankingModel);
		BookByGenreScrollPane = new JScrollPane(BookByGenreRankingTable);
		BookByGenreScrollPane.setBounds(19, 353, 362, 70);
		frame.getContentPane().add(BookByGenreScrollPane);
	}
	
	private void btnSubmitActionPerformed() {
		try {
			refresh();
			String BookByGenreRankingQuery = "SELECT BOOK_NAME, AUTHOR FROM BOOK NATURAL JOIN PREFERENCE WHERE GENRE = '"+genreTextField.getText()+"' ORDER BY RENTAL_COUNT DESC";
			//System.out.println(BookByGenreRankingQuery);
			
			conn = DBConnection.getConnection();
			
			//Book By Genre Ranking
			if(genreTextField.getText().equals(""))
				genreStatusLabel.setText("Input Genre");
			else {
				pstm = conn.prepareStatement(BookByGenreRankingQuery);
				rs = pstm.executeQuery();
				
				int bestCount = 0;
				while(bestCount < 3) {
					rs.next();
					String book_name = rs.getString(1);
					String author = rs.getString(2);
					
					Vector<Object> BookByGenreRankingVector = new Vector();
					BookByGenreRankingVector.add(book_name);
					BookByGenreRankingVector.add(author);
				
					BookByGenreRankingModel.addRow(BookByGenreRankingVector);
					bestCount++;
				}
			}
		} catch(SQLException sqle) {
			genreStatusLabel.setText("Not Found Genre");
		}
	}
	
	public void showRanking() {
		try {
			conn = DBConnection.getConnection();

			//Book Ranking
			String BookRankingQuery = "SELECT BOOK_NAME, AUTHOR FROM BOOK NATURAL JOIN PREFERENCE ORDER BY RENTAL_COUNT DESC";
			pstm = conn.prepareStatement(BookRankingQuery);
			rs = pstm.executeQuery();
			
			int bestCount = 0;
			while(bestCount < 5) {
				rs.next();
				String book_name = rs.getString(1);
				String author = rs.getString(2);
				
				Vector<Object> BookRankingVector = new Vector();
				BookRankingVector.add(book_name);
				BookRankingVector.add(author);
				
				BookRankingModel.addRow(BookRankingVector);
				bestCount++;
			}
			
			//Genre Ranking
			String GenreRankingQuery = "SELECT GENRE, SUM(RENTAL_COUNT) FROM PREFERENCE GROUP BY GENRE ORDER BY SUM(RENTAL_COUNT) DESC";
			pstm = conn.prepareStatement(GenreRankingQuery);
			rs = pstm.executeQuery();
			
			bestCount = 0;
			while(bestCount < 3) {
				rs.next();
				String genre = rs.getString(1);
				int total = rs.getInt(2);
				
				Vector<Object> GenreRankingVector = new Vector();
				GenreRankingVector.add(genre);
				GenreRankingVector.add(total);
				
				GenreRankingModel.addRow(GenreRankingVector);
				bestCount++;
			}
			
			
		} catch(SQLException sqle) {
			System.out.println("SQLs");
			sqle.printStackTrace();
		} finally { 
			try {
				if(rs != null) { rs.close(); }
				if(pstm != null) { pstm.close(); }
				if(conn != null) {	conn.close(); }
			} catch(Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	private void refresh() {
		DefaultTableModel model = (DefaultTableModel) BookByGenreRankingTable.getModel();
		model.setNumRows(0);
	}
}
