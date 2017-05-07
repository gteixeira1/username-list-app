package com.intertec.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RestrictedWordRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> getAllRestrictedWords(){
        String select = "SELECT WORD FROM RESTRICTED_WORDS";
        List<String> result = jdbcTemplate.queryForList(select, String.class);
        return result;
    }

    public List<String> findRestrictedWordByWord(String word){
        String select = "SELECT WORD FROM RESTRICTED_WORDS WHERE WORD = ?";
        List<String> result = jdbcTemplate.queryForList(select, String.class, word);
        return result;
    }
}
