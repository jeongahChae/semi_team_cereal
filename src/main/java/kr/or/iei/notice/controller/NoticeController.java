package kr.or.iei.notice.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.or.iei.FileUtil;
import kr.or.iei.member.model.vo.NoticeFile;
import kr.or.iei.notice.model.service.NoticeService;
import kr.or.iei.notice.model.vo.Notice;
import kr.or.iei.notice.model.vo.NoticeListData;

@Controller
@RequestMapping(value = "/notice")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	@Value("${file.root}")
	private String root;
	@Autowired
	private FileUtil fileUtil;
	
	@GetMapping(value = "/list")
	public String noticeList(Model model, int reqPage) {
		NoticeListData nld = noticeService.selectNoticeList(reqPage);
		model.addAttribute("noticeList",nld.getNoticeList());
		model.addAttribute("pageNavi",nld.getPageNavi());
		return "notice/noticeList";
	}
	
	@GetMapping(value = "/view")
	public String noticeView(int noticeNo, Model model) {
		Notice n = noticeService.selectOneNotice(noticeNo);
		model.addAttribute("n", n);
		return "notice/noticeView";
	}
	
	@GetMapping(value = "/writeFrm")
	public String noticeWriteFrm() {
		return "notice/writeFrm";
	}
	
	@PostMapping(value = "/write")
	public String noticeWrite(Notice n, MultipartFile[] upfile, Model model) {
		ArrayList<NoticeFile> fileList = null;
		if(!upfile[0].isEmpty()) {
			fileList = new ArrayList<NoticeFile>();
			String savepath = root+"notice/";
			for(MultipartFile file : upfile) {
				String filename = file.getOriginalFilename();
				String filepath = fileUtil.getFilepath(savepath, filename);
				File uploadFile = new File(savepath+filepath);
				try {
					file.transferTo(uploadFile);
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				NoticeFile nf = new NoticeFile();
				nf.setFilename(filename);
				nf.setFilepath(filepath);
				fileList.add(nf);
			}
		}
		int result = noticeService.insertNotice(n,fileList);
		if((fileList == null && result == 1) || (fileList != null && result == (fileList.size()+1))) {
			
		}else {
			
		}
		return "redirect:/notice/list?reqPage=1";
	}
	
	@GetMapping(value = "/delete")
	public String deleteNotice(int noticeNo, Model model) {
		List list = noticeService.deleteNotice(noticeNo);
		if(list != null) {
			String savepath = root+"notice/";
			for(Object obj : list) {
				NoticeFile file = (NoticeFile)obj;
				File delfile = new File(savepath+file.getFilepath());
				delfile.delete();
			}
			return "redirect:/notice/list?reqPage=1";
		}else {
			return "redirect:/notice/view?noticeNo="+noticeNo;
		}
	}
}














