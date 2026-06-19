package com.app.dao_impl;


import com.app.dao.ClaimDao;
import com.app.enums.ClaimStatus;
import com.app.exception.ResourceNotFoundException;
import com.app.model.Claim;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClaimDaoImpl implements ClaimDao {

    private final JdbcTemplate jdbcTemplate;

    public ClaimDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Claim> mapper(){

        return (rs, num) -> {

            return new Claim(
                    rs.getInt("id"),
                    rs.getDouble("amount"),
                    ClaimStatus.valueOf(rs.getString("status")));
        };
    }

    @Override
    public void insert(Claim claim) {

        String sql = "insert into claim(amount,status) values(?,?)";

        jdbcTemplate.update(
                sql,
                claim.getAmount(),
                claim.getStatus().toString()
        );

        System.out.println("Claim Added");
    }

    @Override
    public List<Claim> getAll() {

        String sql = "select * from claim";

        return jdbcTemplate.query(sql, mapper());
    }

    @Override
    public Claim getById(int id) {

        String sql = "select * from claim where id=?";

        return jdbcTemplate.queryForObject(sql, mapper(), id);
    }

    @Override
    public void deleteById(int id) throws ResourceNotFoundException {

        String sql = "delete from claim where id=?";

        int numRow = jdbcTemplate.update(sql, id);

        if(numRow == 0) throw new ResourceNotFoundException("Invalid Claim Id");

        System.out.println("Claim Deleted");
    }

    @Override
    public void update(Claim claim) {

        String sql = "update claim set amount=?, status=? where id=?";

        jdbcTemplate.update(
                sql,
                claim.getAmount(),
                claim.getStatus().toString(),
                claim.getId()
        );

        System.out.println("Claim Updated");
    }
}