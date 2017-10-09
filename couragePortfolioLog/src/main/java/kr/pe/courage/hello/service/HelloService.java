package kr.pe.courage.hello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.courage.hello.domain.HelloRepository;
import kr.pe.courage.hello.domain.HelloVO;

@Service("helloService")
public class HelloService {
	@Autowired
	private HelloRepository helloRepository;
	
	public List<HelloVO> retrieveHelloList() {
		return helloRepository.selectHelloList();
	}
}
