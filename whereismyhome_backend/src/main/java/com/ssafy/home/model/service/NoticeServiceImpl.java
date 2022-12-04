package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.dto.Notice;
import com.ssafy.home.dto.User_Notice;
import com.ssafy.home.model.mapper.NoticeMapper;
import com.ssafy.home.util.PagingVO;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeMapper noticeMapper;
	
	@Override
	public int add(Notice notice) throws SQLException {
		return noticeMapper.add(notice);
	}

	@Override
	public int edit(Notice notice) throws SQLException {
		return noticeMapper.edit(notice);
	}

	@Override
	public int delete(int noticeIdx) throws SQLException {
		return noticeMapper.delete(noticeIdx);
	}

	@Override
	public int countNotice() {
		return noticeMapper.countNotice();
	}

	@Override
	public List<User_Notice> list(PagingVO vo) throws SQLException {
		return noticeMapper.list(vo);
	}

	@Override
	public User_Notice view(int noticeIdx) throws SQLException {
		return noticeMapper.view(noticeIdx);
	}
}
