package kr.or.iei.notice.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.or.iei.FileUtil;
import kr.or.iei.notice.model.vo.NoticeFile;
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
		model.addAttribute("btn", 0);
		return "notice/noticeList";
	}
	
	@GetMapping(value = "/view")
	public String noticeView(int noticeNo, Model model) {
		Notice n = noticeService.selectOneNotice(noticeNo);
		model.addAttribute("n", n);
		model.addAttribute("btn", 0);
		return "notice/noticeView";
	}
	
	@GetMapping(value = "/writeFrm")
	public String noticeWriteFrm(Model model) {
		model.addAttribute("btn", 0);
		return "notice/noticeWriteFrm";
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
		model.addAttribute("btn", 0);
		return "redirect:/notice/list?reqPage=1";
	}
	
	@GetMapping(value = "/filedown")
	public void filedown(NoticeFile file, HttpServletResponse response) {
		String savepath = root+"notice/";
		fileUtil.downloadFile(savepath,file.getFilename(),file.getFilepath(),response);
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
			model.addAttribute("btn", 0);
			return "redirect:/notice/view?noticeNo="+noticeNo;
		}
	}
	
	@GetMapping(value = "/updateFrm")
	public String updateFrm(int noticeNo, Model model) {
		Notice n = noticeService.getNotice(noticeNo);
		model.addAttribute("n", n);
		return "notice/noticeUpdateFrm";
	}
	
	@PostMapping(value = "/update")
	public String update(Notice n, MultipartFile[] addfile, int[] delFileNo, Model model) {
		ArrayList<NoticeFile> fileList = null;
		String savepath = root+"notice/";
		if(!addfile[0].isEmpty()) {
			fileList = new ArrayList<NoticeFile>();
			for(MultipartFile file : addfile) {
				String filename = file.getOriginalFilename();
				String filepath = fileUtil.getFilepath(savepath, filename);
				File upfile = new File(savepath+filepath);
				try {
					file.transferTo(upfile);
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				NoticeFile noticeFile = new NoticeFile();
				noticeFile.setFilename(filename);
				noticeFile.setFilepath(filepath);
				noticeFile.setNoticeNo(n.getNoticeNo());
				fileList.add(noticeFile);
			}
		}
		List list = noticeService.updateNotice(n,fileList,delFileNo);
		if(list != null) {
			model.addAttribute("btn",0);
			return "/notice/view?noticeNo="+n.getNoticeNo();
		}else {
			//error났을경우
			return "/";
		}
	}
	
}














