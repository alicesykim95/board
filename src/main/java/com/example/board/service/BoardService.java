package com.example.board.service;

import com.example.board.dto.FileDto;
import com.example.board.mapper.BoardMapper;
import com.example.board.vo.BoardVo;
import com.example.board.vo.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    // 게시글 전체 리스트 for 화면
    public List<BoardVo> selectBoardList(Criteria criteria) throws Exception {
        return boardMapper.selectBoardList(criteria);
    }

    // 게시글 전체 리스트 for RestApi
    public List<BoardVo> selectBoardListNone() throws Exception {
        return boardMapper.selectBoardListNone();
    }

    // 게시글 전체 리스트 갯수 for 페이징
    public int totalRecordCount() throws Exception {
        return boardMapper.totalRecordCount();
    }

    // 게시글 전체 리스트 체크 박스 삭제
    public void deleteBoardList(int boardNum) throws Exception {
        boardMapper.deleteBoardList(boardNum);
    }

    // 게시글 작성
    public int insertBoard(BoardVo bdv) throws Exception {
        return boardMapper.insertBoard(bdv);
    }

    // 게시글 상세
    public BoardVo getBoardDetail(int boardNum) throws Exception {
        boardMapper.updateHitCount(boardNum);
        BoardVo board = boardMapper.getBoardDetail(boardNum);
        return board;
    }

    // 게시글 수정
    public int updateBoard(BoardVo bdv) throws Exception {
        return boardMapper.updateBoard(bdv);
    }

    // 게시글 삭제
    public int deleteBoard(int boardNum) throws Exception {
        return boardMapper.deleteBoard(boardNum);
    }


    // 파일 업로드
    public void fileUpload(Long boardNum, MultipartFile file, String userId) throws Exception {
        assert file != null;
        String originName = file.getOriginalFilename();
        String filePath = "C:\\file_repo/";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss").withZone(ZoneOffset.UTC);
        String savedName = formatter + "_" + originName;
        file.transferTo(new File(filePath + savedName));

        FileDto fd = new FileDto(originName, savedName, filePath, userId, boardNum);

        boardMapper.uploadFile(fd);
    }


}
