package com.oprosi.restservice.Repository;

import com.oprosi.restservice.entities.Opros;
import com.oprosi.restservice.entities.Vopros;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.sql.ResultSet;

@Service
public class OprosService
{
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void saveOpros(Opros opros)
    {
        String sql = "INSERT INTO opros(title, createdat, endat, active) VALUES (?,?,?,?)";

        jdbcTemplate.update(sql, opros.getTitle(), Timestamp.valueOf(opros.getCreatedat()), Timestamp.valueOf(opros.getEndat()), opros.isActive());
    }


    public List<Opros> getAllOprosOrderedby(int orderedby)
    {


        String sql = "select * from opros ORDER BY title";
        if(orderedby == 2) sql = "select * from opros ORDER BY createdat";
        else if(orderedby == 3) sql = "select * from opros ORDER BY endat";
        else if(orderedby == 4) sql = "select * from opros ORDER BY active";


        List<Opros> oprosi = jdbcTemplate.query(sql,
                new RowMapper<Opros>()
                {
                    public Opros mapRow(ResultSet rs, int rowNum) throws SQLException
                    {
                        Opros opros = new Opros
                        (
                                rs.getString("title"),
                                rs.getTimestamp("createdat").toLocalDateTime(),
                                rs.getTimestamp("endat").toLocalDateTime(),
                                rs.getBoolean("active")
                        );
                        opros.setId(rs.getLong("id"));
                        return opros;
                    }
                });
        return oprosi;
    }

    public void updateOpros(Opros opros)
    {
        String sql = "UPDATE opros SET title = ?, createdat = ?, endat = ?, active = ? WHERE id = ?";
        jdbcTemplate.update(sql, opros.getTitle(), Timestamp.valueOf(opros.getCreatedat()), Timestamp.valueOf(opros.getEndat()), opros.isActive(), opros.getId());
    }

    public void deleteOpros(long id)
    {
        String sql = "DELETE FROM opros WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


    public void saveVopros(Vopros vopros)
    {
        String sql = "INSERT INTO vopros(opros_id, poradok, description) VALUES (?,?,?)";

        jdbcTemplate.update(sql, vopros.getOpros().getId(), vopros.getPoradok(), vopros.getDescription());
    }


    public List<Vopros> getAllVopros(long opros_id)
    {
        String sql = "SELECT * from vopros ORDER BY CASE WHEN poradok >= 0 THEN 1 ELSE 2 END, poradok";

            
        //WHEN poradok>0

        List<Vopros> voprosi = jdbcTemplate.query(sql,
                new RowMapper<Vopros>()
                {
                    public Vopros mapRow(ResultSet rs, int rowNum) throws SQLException
                    {
                        Vopros vopros = new Vopros
                        (
                                new Opros(rs.getLong("opros_id")),
                                rs.getLong("poradok"),
                                rs.getString("description")
                        );
                        vopros.setId(rs.getLong("id"));
                        return vopros;
                    }
                });
        return voprosi;
    }

    public void deleteVopros(long id)
    {
        String sql = "DELETE FROM vopros WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
