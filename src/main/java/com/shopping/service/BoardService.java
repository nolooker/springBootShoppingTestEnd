package com.shopping.service;

import com.shopping.entity.Board;
import com.shopping.mapper.BoardMapperInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapperInterface boardMapperInterface ;

    public List<Board> SelectAll(){
        return boardMapperInterface.SelectAll();
    }

    public int Insert(Board board) {
        return boardMapperInterface.Insert(board) ;
    }

    public int Update(Board board) {
        return boardMapperInterface.Update(board) ;
    }

    public int Delete(Integer no) {
        return boardMapperInterface.Delete(no) ;
    }

    public Board SelectOne(Integer no) {
        return boardMapperInterface.SelectOne(no) ;
    }
}
