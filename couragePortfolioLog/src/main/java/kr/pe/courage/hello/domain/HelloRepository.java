package kr.pe.courage.hello.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HelloRepository {
	public List<HelloVO> selectHelloList();
}
