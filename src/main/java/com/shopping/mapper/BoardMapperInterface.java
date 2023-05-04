package com.shopping.mapper;

import com.shopping.entity.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper // 스프링이 이 객체를 자동으로 생성하고, sql 구문을 분석해서 처리해 줍니다.
public interface BoardMapperInterface {

    @Select("select * from boards")
    List<Board> SelectAll() ;

    // no 컬럼은 auto_increment 옵션이다.
    @Insert("insert into boards(writer, subject, description) values(#{board.writer}, #{board.subject}, #{board.description})")
    int Insert(@Param("board") final Board bean) ;

    @Select("select * from boards where no = #{no}") // 게시물 상세 보기
    Board SelectOne(@Param("no") Integer no) ;

    @Update("update boards set writer=#{board.writer}, subject=#{board.subject}, description=#{board.description} where no=#{board.no} ") // 게시물 수정 하기
    int Update(@Param("board") final Board bean) ;

    @Delete("delete from boards where no = #{no}") // 게시물 삭제 하기
    int Delete(@Param("no") Integer no) ;

}
