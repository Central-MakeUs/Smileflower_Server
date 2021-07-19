package com.smileflower.santa.profile.repository;


import com.smileflower.santa.profile.model.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Repository
public class ProfileJdbcRepository implements ProfileRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Profile> findByEmail(String email) {
        String query = "select * from user where emailId =?";
        String param = email;
        List<Profile> user = this.jdbcTemplate.query(query,
                (rs, rowNum) -> new Profile(
                        rs.getLong("userIdx"),
                        new Email(rs.getString("emailId")),
                        null,
                        rs.getString("userImageUrl"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("updatedAt").toLocalDateTime(),
                        rs.getString("name")),
                param);

        return ofNullable(user.isEmpty() ? null : user.get(0));
    }

    @Override
    public Optional<String> findNameByIdx(Long userIdx) {
        String query = "select name from user where userIdx =?";
        String name = this.jdbcTemplate.queryForObject(query,new Object[]{userIdx},String.class);

        return ofNullable(name==null ? null : name);
    }

    @Override
    public Optional<List<Flag>> findFlagsByIdx(Long userIdx) {
        String query = "select flag.*, mountain.name, COUNT(flag.mountainIdx) As cnt from flag Left JOIN mountain ON flag.mountainIdx = mountain.mountainIdx group by flag.mountainIdx where userIdx =?";
        Object[] param = new Object[]{userIdx};
        List<Flag> flags = this.jdbcTemplate.query(query,param,(rs,rowNum) -> new Flag(
                rs.getLong("flagIdx"),
                rs.getLong("userIdx"),
                rs.getLong("mountainIdx"),
                rs.getTimestamp("createAt").toLocalDateTime(),
                rs.getTimestamp("updatedAt").toLocalDateTime(),
                rs.getString("status"),
                rs.getString("pictureUrl")
        ));
        return ofNullable(flags.isEmpty() ? null : flags);
    }

    @Override
    public Optional<List<Picture>> findPicturesByIdx(Long userIdx) {
        String query = "select * from picture where userIdx =?";
        Object[] param = new Object[]{userIdx};
        List<Picture> pictures = this.jdbcTemplate.query(query,param,(rs,rowNum) -> new Picture(
                rs.getLong("pictureIdx"),
                rs.getLong("userIdx"),
                rs.getString("imageUrl"),
                rs.getTimestamp("createAt").toLocalDateTime(),
                rs.getTimestamp("updatedAt").toLocalDateTime(),
                rs.getString("status")
        ));
        return ofNullable(pictures.isEmpty() ? null : pictures);
    }

    @Override
    public int createPicture(Long userIdx,String imageUrl) {
        String query = "insert into picture (userIdx, imageUrl) VALUES (?,?)";
        Object[] params = new Object[]{userIdx,imageUrl};
        this.jdbcTemplate.update(query, params);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    @Override
    public int findFlagCountByIdx(Long userIdx) {
        return 0;
    }

    @Override
    public boolean deleteFlagByIdx(Long flagIdx) {
        String query = "delete from flag where flagIdx = ?";
        Object[] params = new Object[]{flagIdx};
        int changedCnt = this.jdbcTemplate.update(query,params);
        return changedCnt==1 ? true : false;
    }

    @Override
    public int report(Long flagIdx, Long userIdx) {
        String query = "insert into picture (userIdx, flagIdx) VALUES (?,?)";
        Object[] params = new Object[]{userIdx,flagIdx};
        this.jdbcTemplate.update(query, params);
        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    @Override
    public int reportCountByIdx(Long flagIdx) {
        return 0;
    }

    @Override
    public int updateImageUrlByEmail(String email, String filename) {
        String query = "update user set userImageUrl = ? where emailId = ? ";
        Object[] params = new Object[]{filename, email};
        return this.jdbcTemplate.update(query,params);
    }

    @Override
    public int deleteImageUrlByEmail(String email) {
        String query = "update user set userImageUrl = null where emailId = ? ";
        String param = email;
        return this.jdbcTemplate.update(query,param);
    }
}
