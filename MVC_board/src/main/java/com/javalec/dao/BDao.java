package com.javalec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.javalec.dto.BDto;
import com.javalec.template.Constant;

/**
 * 
 * 
 * */
public class BDao {
	DataSource dataSource=null;
	JdbcTemplate template=null;

	public BDao() {

		try {
			
			Context context=new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
	
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		template = Constant.template;
		
	}

public ArrayList<BDto> list() {
	
	
		String query="select bId,bName,bTitle,bContent,bDate,bHit,bGroup,bStep,bIndent from mvc_board order by bGroup desc, bStep asc"; 
						// 데이터를 가져올때는 query(다수의?) 	//데이터를 가져올 커맨트 객체
		return (ArrayList<BDto>)template.query(query, new BeanPropertyRowMapper<BDto>(BDto.class));
		
		
//		ArrayList<BDto> dtos = new ArrayList<BDto>();
//		
//		Connection connection=null;
//		PreparedStatement preparedStatement=null;
//		ResultSet resultSet=null;
//		String query="select bId,bName,bTitle,bContent,bDate,bHit,bGroup,bStep,bIndent from mvc_board order by bGroup desc, bStep asc"; 
//		try{
//			connection = dataSource.getConnection();
//			preparedStatement = connection.prepareStatement(query);
//			resultSet = preparedStatement.executeQuery();
//			
//			while(resultSet.next()) {
//				
//				int bId = resultSet.getInt("bId");
//				String bName =resultSet.getString("bName");
//				String bTitle = resultSet.getString("bTitle");
//				String bContent = resultSet.getString("bContent");
//				Timestamp bDate = resultSet.getTimestamp("bDate");
//				int bHit = resultSet.getInt("bHit");
//				int bGroup = resultSet.getInt("bGroup");
//				int bStep = resultSet.getInt("bStep");
//				int bIndent = resultSet.getInt("bIndent");
//				
//				BDto dto = new BDto(bId,bName,bTitle,bContent,bDate,bHit,bGroup,bStep,bIndent);
//				dtos.add(dto);
//			}
//
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(resultSet != null) resultSet.close();
//				if(preparedStatement != null) preparedStatement.close();
//				if(connection != null) resultSet.close();
//				
//			}catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
//
//		return dtos;
	}

	public void write(final String bName,final String bTitle,final String bContent) {
		
		
		String query="insert into mvc_board (bId,bName,bTitle,bContent,bHit,bGroup,bStep,bIndent) values(mvc_board_seq.nextval, ?,?,?,0,mvc_board_seq.currval,0,0)";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				
				ps.setString(1, bName);
				ps.setString(2, bTitle);
				ps.setString(3, bContent);
				
			}
		});
		
//		template.update(new PreparedStatementCreator() {
//			
//			@Override
//			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//				String query="insert into mvc_board (bId,bName,bTitle,bContent,bHit,bGroup,bStep,bIndent) values(mvc_board_seq.nextval, ?,?,?,0,mvc_board_seq.currval,0,0)";
//				PreparedStatement pstmt = con.prepareStatement(query);
//				pstmt.setString(1, bName);
//				pstmt.setString(2, bTitle);
//				pstmt.setString(3, bContent);
//				return pstmt;
//			}
//		});
		
		// 이 방법도 되는디?
		// 쿼리에 업데이트 하는경우  template.update(query, new PreparedStatementSetter() 
		
//  template.update(new PreparedStatementCreator() 둘다 사용가능
		
//		template.update(new PreparedStatementCreator() {
//			
//			@Override
//			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//
//				PreparedStatement psmt = con.prepareStatement(query);
//				psmt.setString(1, bName);
//				
//				return null;
//			}
//		});
		
		

		
		
		
//		Connection connection=null;
//		PreparedStatement preparedStatement=null;
//		ResultSet resultSet=null;
//		String query="insert into mvc_board (bId,bName,bTitle,bContent,bHit,bGroup,bStep,bIndent) values(mvc_board_seq.nextval, ?,?,?,0,mvc_board_seq.currval,0,0)";
//		
//		
//		try {
//			
//			connection = dataSource.getConnection();
//			preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1, bName);
//			preparedStatement.setString(2, bTitle);
//			preparedStatement.setString(3, bContent);
//			int n = preparedStatement.executeUpdate();
//			
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(resultSet != null) resultSet.close();
//				if(preparedStatement != null) preparedStatement.close();
//				if(connection != null) connection.close();
//			}catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
		
	}
	
	public BDto contentView(String strID) {
		
		bHit(strID);
		
		String query = "select * from mvc_board where bId="+ strID;
						// 하나의 객체데이터 가져올떄 queryForObject
		return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));
		
		
//		BDto dto = null;
//		Connection connection=null;
//		PreparedStatement preparedStatement=null;
//		ResultSet resultSet = null;
//		int Id = Integer.parseInt(strID);
//		String query = "select * from mvc_board where bId=?";
//		
//		try {
//			
//			connection = dataSource.getConnection();
//			preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setInt(1, Id);
//			resultSet = preparedStatement.executeQuery();
//			
//			while(resultSet.next()) {
//				
//				int bId = resultSet.getInt("bId");
//				String bName = resultSet.getString("bName");
//				String bTitle = resultSet.getString("bTitle");
//				String bContent = resultSet.getString("bContent");
//				Timestamp bDate = resultSet.getTimestamp("bDate");
//				int bHit = resultSet.getInt("bHit");
//				int bGroup = resultSet.getInt("bGroup");
//				int bStep = resultSet.getInt("bStep");
//				int bIndent = resultSet.getInt("bIndent");
//				dto = new BDto(bId,bName,bTitle,bContent,bDate,bHit,bGroup,bStep,bIndent);	
//			}
//
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//		
//			try {
//				if(resultSet != null) resultSet.close();
//				if(preparedStatement != null) preparedStatement.close();
//				if(connection != null) connection.close();
//			}catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		
//
//		return dto;
	}

	// form에서 숫자 넘어올떄는 문자로 넘어오는건가???
	public void modify(final String bId,final String bName,final String bTitle,final String bContent) {
		
		String query = "update mvc_board set bName=?, bTitle=?, bContent=? where bId=?";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {

				ps.setString(1, bName);
				ps.setString(2, bTitle);
				ps.setString(3, bContent);
				ps.setInt(4, Integer.parseInt(bId));
			}
		});
		
//		Connection con=null;
//		PreparedStatement ps = null;
//		
//		String query = "update mvc_board set bName=?, bTitle=?, bContent=? where bId=?";
//				
//		try {
//			con = dataSource.getConnection();
//			ps = con.prepareStatement(query);
//			ps.setString(1, bName);
//			ps.setString(2, bTitle);
//			ps.setString(3, bContent);
//			ps.setInt(4, Integer.parseInt(bId));
//			int n = ps.executeUpdate();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			if(ps !=null)
//				try {
//					ps.close();
//					if(con !=null) con.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		}
	
	}
	
	public void delete(final String bId) {
		
		String query = "delete from mvc_board where bId=?";
		template.update(query,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {

				ps.setInt(1, Integer.parseInt(bId));
			}
		});
		
//		Connection con = null;
//		PreparedStatement ps = null;
//		String query = "delete from mvc_board where bId=?";
//		
//		try {
//			
//			con = dataSource.getConnection();
//			ps = con.prepareStatement(query);
//			ps.setInt(1, Integer.parseInt(bId));
//			int rn = ps.executeUpdate();
//			
//			
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(ps != null) ps.close();
//				if(con != null) con.close();
//			}catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
		
	}
	
	
	public void reply(String bId, final String bName, final String bTitle, final String bContent, final String bGroup, final String bStep, final String bIndent) {
		
		reshape(bGroup,bStep); //새로 답글 달릴 글 제외한 같은 그룹의 나머지 글들을 아래로 밀어주기
		
		String query = "insert into mvc_board (bId,bName,bTitle,bContent,bGroup,bStep,bIndent) values (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?)";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bName);
				ps.setString(2, bTitle);
				ps.setString(3, bContent);
				ps.setInt(4, Integer.parseInt(bGroup));
				ps.setInt(5, Integer.parseInt(bStep)+1);	// 답글달릴글을 본문글보다 한칸씩 증가시키기
				ps.setInt(6, Integer.parseInt(bIndent)+1);	// 답글달릴글을 본문글보다 한칸씩 증가시키기
				
			}
		});

		
//		Connection con=null;
//		PreparedStatement ps = null;
//		String query = "insert into mvc_board (bId,bName,bTitle,bContent,bGroup,bStep,bIndent) values (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?)";
//							
//		
//		try {
//			
//			con = dataSource.getConnection();
//			ps = con.prepareStatement(query);
//			ps.setString(1, bName);
//			ps.setString(2, bTitle);
//			ps.setString(3, bContent);
//			ps.setInt(4, Integer.parseInt(bGroup));
//			ps.setInt(5, Integer.parseInt(bStep)+1);	// 답글달릴글을 본문글보다 한칸씩 증가시키기
//			ps.setInt(6, Integer.parseInt(bIndent)+1);	// 답글달릴글을 본문글보다 한칸씩 증가시키기
//			int rn = ps.executeUpdate();
//			
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(ps != null) ps.close();
//				if(con != null) con.close();
//			}catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
		
	}
	
	public BDto reply_view(String strId) {
		
		String query = "select * from mvc_board where bId="+strId;
		return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));
		
		
		
//		Connection con=null;
//		PreparedStatement ps = null;
//		ResultSet resultSet = null;
//		String query = "select * from mvc_board where bId=?";
//		BDto dto=null;
//		
//		try {
//			
//			con = dataSource.getConnection();
//			ps = con.prepareStatement(query);
//			ps.setInt(1, Integer.parseInt(strId));
//			resultSet = ps.executeQuery();
//			
//			while(resultSet.next()) {
//				
//				int bId = resultSet.getInt("bId");
//				String bName = resultSet.getString("bName");
//				String bTitle = resultSet.getString("bTitle");
//				String bContent = resultSet.getString("bContent");
//				Timestamp bDate = resultSet.getTimestamp("bDate");
//				int bHit = resultSet.getInt("bHit");
//				int bGroup = resultSet.getInt("bGroup");
//				int bStep = resultSet.getInt("bStep");
//				int bIndent = resultSet.getInt("bIndent");
//				dto = new BDto(bId,bName,bTitle,bContent,bDate,bHit,bGroup,bStep,bIndent);	
//			}
//			
//			
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(resultSet != null) resultSet.close();
//				if(ps != null) ps.close();
//				if(con != null) con.close();
//			}catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		
//		return dto;
	}
	
	public void reshape(final String group, final String step) {
		
		String query="update mvc_board set bStep=bStep+1 where bGroup = ? and bStep > ?";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				
				ps.setInt(1, Integer.parseInt(group));
				ps.setInt(2, Integer.parseInt(step));
				
			}
		});
		
		
//		Connection con=null;
//		PreparedStatement ps= null;
//		String query="update mvc_board set bStep=bStep+1 where bGroup = ? and bStep > ?";
//		try {
//			
//			con = dataSource.getConnection();
//			ps = con.prepareStatement(query);
//			ps.setInt(1, Integer.parseInt(group));
//			ps.setInt(2, Integer.parseInt(step));
//			
//			int rn = ps.executeUpdate();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(ps != null) ps.close();
//				if(con != null) con.close();
//			}catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
	}

	public void bHit(final String bHit) {
		
		
		String query = "update mvc_board set bHit = bHit+1 where bId=?";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, Integer.parseInt(bHit));
			}
		});
		
		 
//		Connection con=null;
//		PreparedStatement ps=null;
//		
//		String query = "update mvc_board set bHit = bHit+1 where bId=?";
//
//		try {
//			
//			con = dataSource.getConnection();
//			ps = con.prepareStatement(query);
//			ps.setString(1, bHit);
//			int n = ps.executeUpdate();
//	
//		}catch (Exception e) {
//			
//		}finally {
//			try {
//				if(ps != null) ps.close();
//				if(con != null) con.close();
//			}catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		
//	}
//	
	}
}

