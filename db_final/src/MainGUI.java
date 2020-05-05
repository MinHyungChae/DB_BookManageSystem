import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;
import java.text.SimpleDateFormat;

public class MainGUI implements ActionListener {

   private JFrame frmBookManagementSystem;
   private JPanel panel, temp;
   private JLabel lblBookManagementSystem, lblLoginStatus;
   private JTable bookTable, memberTable;
   private JButton btnLogin, btnAddMember, btnAddBook, btnReservation, btnBorrow, btnReturn, btnBest, btnExit;
   
   private int worker_id; //needed to insert into admin_member, admin_book
   private int mi; //member index, needed to insert into admin_member
   private int bn; //book index, needed to insert into admin_book
   private String location; //needed to insert into admin_book
   
   //민형 추가 start
   String book_category[] = { "name","author","genre", "location", "book_num", "lender_number" };
   //책이름, 저자, 장르, 위치, 책번호(book_id), 대여자 번호(member_id), 등록한 worker 번호(worker_num)
   String member_category[] = { "name", "phone num", "address", "member id" };
   //멤버 이름, 폰번호, 주소, 멤버 id
   DefaultTableModel book_cat_model;
   DefaultTableModel mem_cat_model;
   JScrollPane tbl_sp, tbl_sp2; //tbl_sp : book_cat_model, tbl_sp2 : mem_cat_model
   
   Connection conn = null;
   PreparedStatement pstm = null;
   ResultSet rs = null;
   
   Person[] libPerson;
   int countPerson;
   Book[] libBook;
   int countBook;  
   
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               MainGUI window = new MainGUI();
               window.frmBookManagementSystem.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the application.
    */
   public MainGUI() {
      initialize();
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {
      frmBookManagementSystem = new JFrame();
      frmBookManagementSystem.setTitle("Book Management System");
      frmBookManagementSystem.setBounds(100, 100, 1141, 300);
      frmBookManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      panel = new JPanel(null);
      frmBookManagementSystem.getContentPane().add(panel, BorderLayout.CENTER);
      panel.setLayout(null);
      
      lblBookManagementSystem = new JLabel("Book Management System");
      lblBookManagementSystem.setBounds(29, 18, 172, 28);
      panel.add(lblBookManagementSystem);
      
      btnLogin = new JButton("Login");
      btnLogin.setBounds(29, 58, 172, 29);
      btnLogin.addActionListener(this);
      panel.add(btnLogin);
      
      btnAddMember = new JButton("Add Member");
      btnAddMember.setBounds(29, 89, 172, 29);
      btnAddMember.addActionListener(this);
      panel.add(btnAddMember);
      btnAddMember.setVisible(false);
      
      btnAddBook = new JButton("Add Book");
      btnAddBook.setBounds(29, 117, 172, 29);
      btnAddBook.addActionListener(this);
      panel.add(btnAddBook);
      btnAddBook.setVisible(false);
      
      btnReservation = new JButton("Check Reservation");
      btnReservation.addActionListener(this);
      btnReservation.setBounds(29, 146, 172, 29);
      panel.add(btnReservation);
      btnReservation.setVisible(false);
      
      btnBest = new JButton("Best");
      btnBest.setBounds(29, 174, 172, 29);
      btnBest.addActionListener(this);
      panel.add(btnBest);
      btnBest.setVisible(false);
      
      btnBorrow = new JButton("Borrow");
      btnBorrow.setBounds(29, 202, 87, 29);
      btnBorrow.addActionListener(this);
      panel.add(btnBorrow);
      btnBorrow.setVisible(false);
      
      btnReturn = new JButton("Return");
      btnReturn.setBounds(108, 202, 93, 29);
      btnReturn.addActionListener(this);
      panel.add(btnReturn);
      btnReturn.setVisible(false);
      
      btnExit = new JButton("Exit");
      btnExit.setBounds(73, 233, 79, 29);
      btnExit.addActionListener(this);
      panel.add(btnExit);
      
      book_cat_model = new DefaultTableModel(book_category, 0);
      bookTable = new JTable(book_cat_model);
      tbl_sp = new JScrollPane(bookTable);
      tbl_sp.setBounds(241, 6, 567, 266);
      panel.add(tbl_sp);
   
      mem_cat_model = new DefaultTableModel(member_category, 0);
      memberTable = new JTable(mem_cat_model);
      tbl_sp2 = new JScrollPane(memberTable);
      tbl_sp2.setBounds(820, 6, 311, 266);
      panel.add(tbl_sp2);
      
      lblLoginStatus = new JLabel("");
      lblLoginStatus.setBounds(51, 89, 133, 29);
      panel.add(lblLoginStatus);
      
      libPerson = new Person[100];
      libBook = new Book[100];      
      countPerson = countBook = 0;
   }
   
   public void actionPerformed(ActionEvent iEvent)
   {
      if(iEvent.getSource() == btnLogin)
      {
         String id = JOptionPane.showInputDialog("아이디를 입력하세요");
         String pw = JOptionPane.showInputDialog("비밀번호를 입력하세요");
         String query ="SELECT worker_id, password from worker where worker_id = ";
         
         try {
            conn = DBConnection.getConnection();
            pstm = conn.prepareStatement(query+id);
            rs = pstm.executeQuery();
            int pw_int = Integer.parseInt(pw);
            while(rs.next()) {
               if(pw_int == rs.getInt(2)) {
                  System.out.println("Login Success! User: "+id);
                  worker_id = Integer.parseInt(id);
                        
                  btnAddMember.setVisible(true);
                  btnAddBook.setVisible(true);
                  btnReservation.setVisible(true);
                  btnBorrow.setVisible(true);
                  btnReturn.setVisible(true);
                  btnBest.setVisible(true);
                  
                 refresh();
                  //성공
               }
               else {
                  lblLoginStatus.setForeground(Color.RED);
                  lblLoginStatus.setText("Login Denied!");
                  //실패 
               }
            }
         } catch (SQLException e) {
            e.printStackTrace();
         } finally { //DB연결 종료
            try {
               if(rs != null) { rs.close(); }
               if(pstm != null) { pstm.close(); }
               if(conn != null) { conn.close(); }
            } catch(Exception e) {
               throw new RuntimeException(e.getMessage());
            }
         }
      }
      else if(iEvent.getSource() == btnAddMember)
        {
            String member_name = JOptionPane.showInputDialog("이름을 입력하세요");
            String member_phone_num = JOptionPane.showInputDialog("핸드폰 번호를 입력하세요");
            String address = JOptionPane.showInputDialog("주소를 입력하세요");
            
            int member_index = 0;
            
            System.out.println("btnAddMember success : "+member_name + member_phone_num + address);
            /*
             * String book_category[] = {"name","author","genre", "location", "book_num", "lender_number", "worker_num"};
                  책이름, 저자, 장르, 위치, 책번호(book_id), 대여자 번호(member_id), 등록한 worker 번호(worker_num)
             * 
             * member table attributes
             * member_id, member_name, m_phone_num, address
             * 
             * admin_worker table attributes
             * worker_id, member_id, register_date
             * */
                
            /* 민형
             * 여기서 입력 받은 member_name, member_phone_num, address 를 DB 에 저장
             * */
            try {
               String query = "SELECT COUNT(*)  FROM MEMBER";                   
                conn = DBConnection.getConnection();
                pstm = conn.prepareStatement(query);
                rs = pstm.executeQuery();
                
                while(rs.next()) {
                   System.out.println(rs.getInt(1));
                   member_index = rs.getInt(1)+1;
                      System.out.println("MEMBER COUNT" + member_index);
                }
                pstm.close();
            }catch(SQLException sqle) {
               System.out.println("SELECT count 문에서 예외 발생");
               sqle.printStackTrace();
            } 
            
            //INSERT INTO MEMBER
            try {
               String query = "INSERT INTO MEMBER (MEMBER_ID,MEMBER_NAME,M_PHONE_NUM,ADDRESS) ";
               query = query + "VALUES (?, ?, ?, ?)";
             conn = DBConnection.getConnection();
             pstm = conn.prepareStatement(query);
             mi = member_index;
             
             System.out.println("member id :" + mi + " member_name : " + member_name + " m_phone_num : "+ member_phone_num + " address : " + address);
             
              pstm.setInt(1, mi);
              pstm.setString(2, member_name);
                pstm.setString(3, member_phone_num);
                pstm.setString(4, address);
                int i = pstm.executeUpdate();
                System.out.println("Insert 쿼리 수행" + i);
                pstm.close();
         }catch(SQLException sqle) {
            System.out.println("INSERT문에서 예외 발생");
            sqle.printStackTrace();
         } 
            //INSERT INTO ADMIN_MEMBER
            try {
               java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
               
               String query = "INSERT INTO ADMIN_MEMBER (WORKER_ID,MEMBER_ID,REGISTER_DATE)";
               query = query + "VALUES (?, ?, ?)";
               conn = DBConnection.getConnection();
               pstm = conn.prepareStatement(query);
               
               pstm.setInt(1, worker_id);
               pstm.setInt(2, mi);
               pstm.setDate(3, date);
               System.out.println("add member worker id :" +worker_id + " member id : " + mi);
               int i = pstm.executeUpdate();
                System.out.println("Insert 쿼리 수행" + i);
                pstm.close();
            } catch(SQLException sqle) {
               System.out.println("INSERT문에서 예외 발생");
               sqle.printStackTrace();
            }    
            refresh();
         }
         else if(iEvent.getSource() == btnAddBook)
         {
            String book_name = JOptionPane.showInputDialog("책 제목을 입력하세요");
            String book_auth = JOptionPane.showInputDialog("저자를 입력하세요");   
            String book_genre = JOptionPane.showInputDialog("장르를 입력하세요");
            location = JOptionPane.showInputDialog("책을 놓을 위치를 입력하세요\n현재 장르별 위치\nliterature 100\n essay 200\n writing 300\n novel 400\n travel 500\n");
            
            int bookIndex = 0;
            
            System.out.println("btnAddBook success : " + book_name + book_auth + book_genre);
            /*
             * String member_category[] = {"name", "phone num", "address", "member id"};
               //멤버 이름, 폰번호, 주소, 멤버 id
             * book table attributes
             * book_num, genre, book_name, author
             * 
             * 
             * reservation table attributes
             * member_id, book_num, genre, reserve_date
             * 
             * admin_book table attributes
             * worker_id, book_num, genre, location, isAvailable
             * */
            try {
               String query = "SELECT COUNT(*)  FROM BOOK";                   
               conn = DBConnection.getConnection();
               pstm = conn.prepareStatement(query);
               rs = pstm.executeQuery();
               
               while(rs.next()) {
                  System.out.println(rs.getInt(1));
                  bookIndex = rs.getInt(1)+1;
                  System.out.println("BOOK COUNT" + bookIndex);
               }
               pstm.close();     
            } catch(SQLException sqle) {
               System.out.println("SELECT count 문에서 예외 발생");
               sqle.printStackTrace();
            }
            
            //INSERT INTO BOOK
            try {
               String query = "INSERT INTO BOOK (BOOK_NUM, GENRE, BOOK_NAME, AUTHOR) ";
               query = query + "VALUES (?, ?, ?, ?)";
               bn = bookIndex;
               conn = DBConnection.getConnection();
               pstm = conn.prepareStatement(query);
                
               System.out.println("boook_num : "+ bn + " genre : " + book_genre + " book_name : " + book_name + " author : " + book_auth);
             
               pstm.setInt(1, bn);
               pstm.setString(2, book_genre);
               pstm.setString(3, book_name);
               pstm.setString(4, book_auth);
               
               int i = pstm.executeUpdate();
               System.out.println("Insert 쿼리 수행" + i);
               pstm.close();
            } catch(SQLException sqle) {
               System.out.println("INSERT문에서 예외 발생");
               sqle.printStackTrace();
            } 
           
            /* 민형
             * 여기서 입력 받은 book_name, book_auth, book_genre
             * */
            
            //INSERT INTO ADMIN_BOOK
            try {
               String query = "INSERT INTO ADMIN_BOOK (WORKER_ID, BOOK_NUM, GENRE, LOCATION, ISAVAILABLE) ";
               query = query + "VALUES (?, ?, ?, ?, ?)";
               
               conn = DBConnection.getConnection();
               pstm = conn.prepareStatement(query);
                
               boolean book_status = true;
               
               System.out.println("worker id: "+worker_id+" book num: "+bn+ " genre : " + book_genre + " location: " +location+" available? "+book_status);
             
               pstm.setInt(1, worker_id);
               pstm.setInt(2, bn);
               pstm.setString(3, book_genre);
               pstm.setString(4, location);
              pstm.setBoolean(5, book_status);
               int i = pstm.executeUpdate();
               System.out.println("Insert 쿼리 수행" + i);
               pstm.close();
            } catch(SQLException sqle) {
               System.out.println("INSERT문에서 예외 발생");
               sqle.printStackTrace();
            } 
            refresh();
         }
      else if(iEvent.getSource() == btnReservation)
      {
         String book_num = JOptionPane.showInputDialog("책 번호를 입력하세요");
          
         Connection conn = null;
         PreparedStatement pstm = null;
         ResultSet rs = null;

         String query = "SELECT * from book natural left outer join reservation where book_num =";
         
        try {
           conn = DBConnection.getConnection();
           pstm = conn.prepareStatement(query+book_num);
           rs = pstm.executeQuery();
        
           java.util.Date utilDate = new java.util.Date();
           java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
           while(rs.next()) {
              if(rs.getDate(6) != null){
                 sqlDate = rs.getDate(6);
                 JOptionPane.showMessageDialog(null,book_num + "번책이 이미 예약중입니다!\n"+rs.getString(3)+"\n"+rs.getString(4)+"\n"+rs.getString(2)+"\n예약일자: "+sqlDate+"\n예약자 번호: " + rs.getInt(5), "오류",JOptionPane.ERROR_MESSAGE);
                 
                 System.out.println(rs.getString(3)+"\n"+rs.getString(4)+"\n"+rs.getString(2)+"\n"+sqlDate);
                 System.out.println("예약자 번호:" + rs.getInt(5));
              }
              else {
                 System.out.println("예약가능");
                 String id_input = JOptionPane.showInputDialog("예약하는 회원의 회원번호: ");
                 int id_input_i = Integer.parseInt(id_input);
                 int book_num_i = Integer.parseInt(book_num);
                 Calendar now = Calendar.getInstance();
                 Date date = new Date(System.currentTimeMillis());
                 SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
                 String current = sf.format(date);
                 
                 String reserve_query = "insert into reservation values(" + id_input_i + "," + book_num_i+", '"+rs.getString(2)+"', TO_DATE('"+current +"', 'YYYYMMDD'))";
                 JOptionPane.showMessageDialog(null,"'"+rs.getString(3)+"'"+" 예약 성공!","성공",JOptionPane.PLAIN_MESSAGE);
                 pstm = conn.prepareStatement(reserve_query);
                 pstm.executeUpdate();   
              }
           }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        finally { //DB연결 종료
           try {
              if(rs != null) { rs.close(); }
              if(pstm != null) { pstm.close(); }
              if(conn != null) {   conn.close(); }
           } catch(Exception e) {
              throw new RuntimeException(e.getMessage());
           }
        
        }
         System.out.println("btnReservation success :" + book_num);
      }
      else if(iEvent.getSource() == btnBest)
      {
         new ShowRankingGUI();
         System.out.println("btnBest success :");
      }      
      else if(iEvent.getSource() == btnBorrow)
      {
    	  int book = bookTable.getSelectedRow();
          int person = memberTable.getSelectedRow();
          if(book_cat_model.getValueAt(book, 5)!=null){
              JOptionPane.showMessageDialog(temp, "이미 대여자가 존재합니다.");
              return;
            }
          String bookname = (String) book_cat_model.getValueAt(book, 0);
          int mem_id = (Integer) mem_cat_model.getValueAt(person, 3);
          int b_num = (Integer) book_cat_model.getValueAt(book, 4);
          String genre = (String) book_cat_model.getValueAt(book, 2);
          Date date = new Date(System.currentTimeMillis());
		  SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		  String current = sf.format(date);

          String query ="UPDATE admin_book SET isAvailable=0 WHERE book_num = ?";
          String query2 ="INSERT INTO rental values(?, ?, ?, TO_DATE(?, 'YYYYMMDD'))";
          try {
        	  conn = DBConnection.getConnection();
        	  pstm = conn.prepareStatement(query);
        	  pstm.setInt(1, b_num);
        	  rs = pstm.executeQuery();
        	  pstm = conn.prepareStatement(query2);
        	  pstm.setInt(1, mem_id);
        	  pstm.setInt(2, b_num);
        	  pstm.setString(3, genre);
        	  pstm.setString(4, current);
        	  rs = pstm.executeQuery();
          } catch (SQLException e) {
        	  e.printStackTrace();
          } finally { 
        	  try {
        		  if(rs != null) { rs.close(); }
        		  if(pstm != null) { pstm.close(); }
        		  if(conn != null) { conn.close(); }
        	  } catch(Exception e) {
        		  throw new RuntimeException(e.getMessage());
        	  }
          }
          borrow(b_num);
          System.out.println("btnBorrow success :");
         
      }
      else if(iEvent.getSource() == btnReturn)
      {
    	  
    	  int book = bookTable.getSelectedRow();
    	  
          if(book_cat_model.getValueAt(book, 5)==null){
            JOptionPane.showMessageDialog(temp, "빌린책만 반납할 수 있습니다.");
            return;
          }
          
          
          String personname = (String) book_cat_model.getValueAt(book, 2);
          //Return(personname, (String) book_cat_model.getValueAt(book, 0));
          
          String query ="UPDATE admin_book SET isAvailable=1 WHERE book_num = ?";
          String query2 = "DELETE FROM RENTAL WHERE MEMBER_ID = ? and BOOK_NUM = ?";

          try {
        	  conn = DBConnection.getConnection();
        	  pstm = conn.prepareStatement(query);
        	  pstm.setInt(1, (Integer) book_cat_model.getValueAt(book, 4));
        	  rs = pstm.executeQuery();
        	  pstm = conn.prepareStatement(query2);
        	  pstm.setInt(1,(Integer) book_cat_model.getValueAt(book, 5));
        	  pstm.setInt(2,(Integer) book_cat_model.getValueAt(book, 4));
        	  rs = pstm.executeQuery();
          } catch (SQLException e) {
        	  e.printStackTrace();
          } finally { 
        	  try {
        		  if(rs != null) { rs.close(); }
        		  if(pstm != null) { pstm.close(); }
        		  if(conn != null) { conn.close(); }
        	  } catch(Exception e) {
        		  throw new RuntimeException(e.getMessage());
        	  }
          }
         System.out.println("btnReturn success : ");
         refresh();
      }      else if(iEvent.getSource() == btnExit)
      {
         System.out.println("btnExit success : ");
         System.exit(0);
      }
   }
   /*
   public void Return(String personname, String bookname)
   {
	   for (int i = 0; i < countBook; i++) {
		   if(libBook[i].getName().equals(bookname)) {
			   libBook[i].setPersonname((Integer) null);
			   return;
	      }
	   }
   }
   */
   public void borrow(int book_num) {
	   int prefer = 0;
	   /*
	   for(int i=0; i<countBook; i++) {
		   if(libBook[i].getName().equals(bookname)) {
			   book_num = libBook[i].getNum();
			   libBook[i].setPersonname(personname);
			   break;
		   }
	   }
	   */
	   String query = "SELECT rental_count FROM preference WHERE book_num= ?";
	   try {
     	  conn = DBConnection.getConnection();
     	  pstm = conn.prepareStatement(query);
     	  pstm.setInt(1, book_num);
     	  rs = pstm.executeQuery();
     	  while(rs.next()) {
     		 prefer = rs.getInt(1);
     	 }
     	  System.out.println(prefer);
     	  prefer++;
     	  String query2 = "UPDATE preference SET rental_count= ? WHERE book_num = ?";
     	  pstm = conn.prepareStatement(query2);
     	  pstm.setInt(1, prefer);
     	  pstm.setInt(2, book_num);
     	  rs = pstm.executeQuery();
       } catch (SQLException e) {
     	  e.printStackTrace();
       } finally { 
     	  try {
     		  if(rs != null) { rs.close(); }
     		  if(pstm != null) { pstm.close(); }
     		  if(conn != null) { conn.close(); }
     	  } catch(Exception e) {
     		  throw new RuntimeException(e.getMessage());
     	  }
       }
	   refresh();
   }
     
   public void refresh()  {
      String BookTableRefreshQuery = "SELECT BOOK_NAME, AUTHOR, GENRE, LOCATION, BOOK_NUM, MEMBER_ID FROM BOOK NATURAL JOIN ADMIN_BOOK NATURAL LEFT OUTER JOIN RENTAL";
      String MemberTableRefreshQuery = "SELECT MEMBER_NAME, M_PHONE_NUM, ADDRESS, MEMBER_ID FROM MEMBER";
    		  
      book_cat_model.setNumRows(0);
      mem_cat_model.setNumRows(0);
      
      conn = DBConnection.getConnection();
      
      //Book Table Refresh
       try {
          pstm = conn.prepareStatement(BookTableRefreshQuery);
           rs = pstm.executeQuery();
           
           while(rs.next()) {
              String book_name = rs.getString(1);
              String author = rs.getString(2);
              String genre = rs.getString(3);
              String location = rs.getString(4);
              int book_num = rs.getInt(5);
              int mem_id = rs.getInt(6);
              
              
              Vector<Object> BookTableVector = new Vector();
              BookTableVector.add(book_name);
              BookTableVector.add(author);
              BookTableVector.add(genre);
              BookTableVector.add(location);
              BookTableVector.add(book_num);
              if (mem_id != 0){
            	  BookTableVector.add(mem_id);  
              }
              
              book_cat_model.addRow(BookTableVector);
           }
           bookTable.setModel(book_cat_model);
           panel.invalidate();
         } catch(SQLException sqle) {
            System.out.println("Book Table Refresh하는데 예외 발생");
            sqle.printStackTrace();
         }
       
       //Member Table Refresh
       try {
          pstm = conn.prepareStatement(MemberTableRefreshQuery);
           rs = pstm.executeQuery();
           
           while(rs.next()) {
              String member_name = rs.getString(1);
              String m_phone_num = rs.getString(2);
              String address = rs.getString(3);
              int member_id = rs.getInt(4);
              
              Vector<Object> MemberTableVector = new Vector();
              MemberTableVector.add(member_name);
              MemberTableVector.add(m_phone_num);
              MemberTableVector.add(address);
              MemberTableVector.add(member_id);
              
              mem_cat_model.addRow(MemberTableVector);
              bookTable.setModel(book_cat_model);
           }
           memberTable.setModel(mem_cat_model);
           panel.invalidate();
         } catch(SQLException sqle) {
            System.out.println("Member Table Refresh하는데 예외 발생");
            sqle.printStackTrace();
         }
   }
}