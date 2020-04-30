package com.app.practice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.practice.model.User;

public class UserRowMapper implements RowMapper<User> {

	/*public User mapRowToEmployee(ResultSet resultSet, int rowNum) throws SQLException {
	    return new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age"));
	            .id(resultSet.getLong("id"))
	            .firstName(resultSet.getString("first_name"))
	            .lastName(resultSet.getString("last_name"))
	            .yearlyIncome(resultSet.getLong("yearly_income"))
	            .build();
	  }*/

	@Override
	public User mapRow(ResultSet resultSet, int arg1) throws SQLException {
		User u = new User();
		u.setId(resultSet.getInt("id"));
		u.setName(resultSet.getString("name"));
		u.setAge(resultSet.getInt("age"));
		return u;
	}
}
