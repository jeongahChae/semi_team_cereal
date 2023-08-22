package kr.or.iei.aboutUs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.aboutUs.dao.AboutUsDao;

@Service
public class AboutUsService {

	@Autowired
	private AboutUsDao aboutUsDao;

	public List selectAllStore() {
		List list = aboutUsDao.selectAllStore();
		return list;
	}
}
