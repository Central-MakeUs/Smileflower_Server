package com.smileflower.santa.profile.repository;


import com.smileflower.santa.profile.model.domain.*;
import com.smileflower.santa.profile.model.dto.FlagResponse;
import com.smileflower.santa.profile.model.dto.FlagsForMapResponse;
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
    public Optional<Profile> findByIdx(Long userIdx) {
        String query = "select * from user where userIdx =?";
        Long param = userIdx;
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
    public String findNameByIdx(Long userIdx) {
        String query = "select name from user where userIdx =?";
        String name = this.jdbcTemplate.queryForObject(query,new Object[]{userIdx},String.class);

        return name;
    }

    @Override
    public List<FlagResponse> findFlagsByIdx(Long userIdx) {
        String query = "SELECT a.flagIdx, a.userIdx, a.mountainIdx, a.createdAt, a.pictureUrl, b.cnt, b.name from flag a left join (Select ANY_VALUE(f.userIdx) as userIdx, ANY_VALUE(f.mountainIdx) as mountainIdx, COUNT(f.mountainIdx) as cnt, m.name  from flag f LEFT JOIN mountain m ON f.mountainIdx = m.mountainIdx group by f.mountainIdx) b on a.mountainIdx = b.mountainIdx where a.useridx = ?";
        Object[] param = new Object[]{userIdx};
        List<FlagResponse> flags = this.jdbcTemplate.query(query,param,(rs,rowNum) -> new FlagResponse(
                rs.getLong("flagIdx"),
                rs.getLong("userIdx"),
                rs.getLong("mountainIdx"),
                rs.getTimestamp("createAt").toLocalDateTime(),
                rs.getString("pictureUrl"),
                rs.getInt("cnt"),
                rs.getString("name")
        ));
        return flags;
    }

    @Override
    public List<FlagsForMapResponse> findFlagsForMapByIdx(Long userIdx) {
        String query = "Select ANY_VALUE(f.userIdx) as userIdx, ANY_VALUE(f.mountainIdx) as mountainIdx, COUNT(f.mountainIdx) as cnt, m.name, m.imageUrl, m.lat, m.lng, m.address from flag f LEFT JOIN mountain m ON f.mountainIdx = m.mountainIdx where f.useridx = ? group by f.mountainIdx";
        Object[] param = new Object[]{userIdx};
        List<FlagsForMapResponse> flags = this.jdbcTemplate.query(query,param,(rs,rowNum) -> new FlagsForMapResponse(
                rs.getLong("userIdx"),
                rs.getLong("mountainIdx"),
                rs.getString("imageUrl"),
                rs.getDouble("lat"),
                rs.getDouble("lng"),
                rs.getInt("cnt"),
                rs.getString("address")
        ));
        return flags;
    }

    @Override
    public List<Picture> findPicturesByIdx(Long userIdx) {
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
        return pictures;
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
        return this.jdbcTemplate.queryForObject("SELECT COUNT(*) FROM flag WHERE userIdx = ?",new Object[]{userIdx}, Integer.class);
    }

    @Override
    public boolean deleteFlagByIdx(Long flagIdx) {
        String query = "delete from flag where flagIdx = ?";
        Object[] params = new Object[]{flagIdx};
        int changedCnt = this.jdbcTemplate.update(query,params);
        return changedCnt==1 ? true : false;
    }

    @Override
    public boolean deletePictureByIdx(Long flagIdx) {
        String query = "delete from picture where pictureIdx = ?";
        Object[] params = new Object[]{flagIdx};
        int changedCnt = this.jdbcTemplate.update(query,params);
        return changedCnt==1 ? true : false;
    }

    @Override
    public Long report(Long flagIdx, Long userIdx) {
        String query = "insert into picture (userIdx, flagIdx) VALUES (?,?)";
        Object[] params = new Object[]{userIdx,flagIdx};
        this.jdbcTemplate.update(query, params);
        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,Long.class);
    }

    @Override
    public int reportCountByIdx(Long flagIdx) {
        return this.jdbcTemplate.queryForObject("SELECT COUNT(*) FROM report WHERE flagIdx = ?",new Object[]{flagIdx}, Integer.class);
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
