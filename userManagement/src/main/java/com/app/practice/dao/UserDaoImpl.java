package com.app.practice.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.practice.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
    JdbcTemplate jdbcTemplate;

	@Override
	public int add(User user) {
		 /*String sqlQuery = "insert into employees(first_name, last_name, yearly_income) " +
                 "values (?, ?, ?)";
		 return jdbcTemplate.update(sqlQuery, user.getName(), user.getAge());*/
		 
		return jdbcTemplate.update("insert into user_tbl (name, age) values(?,?)", user.getName(), user.getAge());
		
	}

	@Override
	public int edit(User user, int UserId) {
		String sql = "update user_tbl set name = ?, age = ? where id = ?";
		return jdbcTemplate.update(sql, new Object[] { user.getName(), user.getAge(),UserId });
	}

	@Override
	public String delete(int userId) {
		String sqlQuery = "delete from user_tbl where id = ?";
		if(jdbcTemplate.update(sqlQuery, userId) > 0)
			return "User Deleted..";
		return "User Not Deleted..";
	}

	@Override
	public User find(int userId) {
		 String sqlQuery = "select id, name, age from user_tbl where id = ?";
		 return jdbcTemplate.queryForObject(sqlQuery,new Object[] {userId }, new UserRowMapper());
	}

	@Override
	public List<User> findAll() {
		List<User> userList = (List<User>) jdbcTemplate.query("select * from user_tbl",
				new UserRowMapper());
		return userList;
		
		/*return jdbcTemplate.query(
                "select * from user_tbl",
                (rs, rowNum) ->
                        new User(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("age")
                        )
        );*/
	}
 
	
	/*@Override
    public int count() {
        return jdbcTemplate
                .queryForObject("select count(*) from books", Integer.class);
    }*/
/*

    @Override
    public int update(Book book) {
        return jdbcTemplate.update(
                "update books set price = ? where id = ?",
                book.getPrice(), book.getId());
    }


    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(
                "delete books where id = ?",
                id);
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query(
                "select * from books",
                (rs, rowNum) ->
                        new Book(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getBigDecimal("price")
                        )
        );
    }

    // jdbcTemplate.queryForObject, populates a single object
    @Override
    public Optional<Book> findById(Long id) {
        return jdbcTemplate.queryForObject(
                "select * from books where id = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new Book(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getBigDecimal("price")
                        ))
        );
    }

    @Override
    public List<Book> findByNameAndPrice(String name, BigDecimal price) {
        return jdbcTemplate.query(
                "select * from books where name like ? and price <= ?",
                new Object[]{"%" + name + "%", price},
                (rs, rowNum) ->
                        new Book(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getBigDecimal("price")
                        )
        );
    }

    @Override
    public String getNameById(Long id) {
        return jdbcTemplate.queryForObject(
                "select name from books where id = ?",
                new Object[]{id},
                String.class
        );
    }
    
    */

}
