package com.shopping.controller;

import com.shopping.entity.Board;
import com.shopping.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService ;

    @GetMapping(value = "/list")
    public String SelectAll(Model model) {

        List<Board> boardList = boardService.SelectAll();

        model.addAttribute("list", boardList) ;
        return "/board/boardList" ;

    }

    @GetMapping(value = "/insert") // 게시물 등록
    public String doGetInsert(Model model){

        System.out.println("게시물 등록");
        return "/board/boardInsert" ;

    }

    @PostMapping(value = "/insert") // 게시물 등록
    public String doPostInsert(Board board){

        System.out.println("board : " + board);

        int cnt = -999 ;

        cnt = boardService.Insert(board) ;

        System.out.println("cnt : " + cnt) ;

        if (cnt == 1) {
            return "redirect:/board/list";
        }else {
            return "/board/boardInsert";
        }
    }

    @GetMapping(value = "/detail/{no}")
    public String SelectOne(@PathVariable("no") Integer no, Model model){  // 상세 보기

        Board board = boardService.SelectOne(no);
        model.addAttribute("board", board) ;
        return "/board/boardDetail" ;
    }

    @GetMapping(value = "/update/{no}")  // 수정하기
    public String doGetUpdate(@PathVariable("no") Integer no, Model model){  // get 방식으로 해당 폼으로 이동하기

        Board board = boardService.SelectOne(no);
        model.addAttribute("board", board) ;
        return "/board/boardUpdate" ;

    }

    @GetMapping(value = "/delete/{no}")
    public String Delete(@PathVariable("no") Integer no){  // 삭제 하기

        int cnt = -999 ;
        cnt = boardService.Delete(no) ;
        return "redirect:/board/list" ;
    }

    @GetMapping(value = "/update")
    public String doPostUpdate(Board board){  // 폼 양식 수정하고 수정 버튼 클릭

        // @Valid를 사용하여 유효성 검사도 하면 좋습니다.

        System.out.println("board : " + board);

        int cnt = -999 ;

        cnt = boardService.Update(board) ;

        System.out.println("cnt : " + cnt) ;

        if (cnt == 1) {
            return "redirect:/board/list";
        }else {
            return "/board/boardUpdate";
        }
    }

}
