package kr.or.iei;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class FileUtil {
	
	public String getFilepath(String savepath, String filename) {//경로와 파일명을 받아와서 중복되지 않은 파일명 만들기
		//예시) filename : test.txt
			//test        .txt
		String onlyFilename = filename.substring(0, filename.lastIndexOf(".")); //확장자를 제외한 파일이름 : 뒤부터 점을 만나면 앞에서부터 글씨전달요청 → test // substring : 복사개념, 원본 건드리지 않음
		String extention = filename.substring(filename.lastIndexOf("."));		//점부터 끝까지 → .txt
		//실제로 업로드할 파일명을 저장할 변수
		String filepath = null;
		//파일명이 중복되면 붙일 숫자
		int count = 0;
		while(true) {
			if(count == 0) {
				filepath = onlyFilename+extention;//=filename
			} else {
				filepath = onlyFilename+"_"+count+extention;
			}
			File checkFile = new File(savepath+filepath);	//파일에 접촉
			if(!checkFile.exists()) {//.exists() : 존재하면 true, 없으면 false
				break;//존재하지 않으면 while문을 나가랏
			}
			count++;//text_1로 바뀔 수 있도록 ++해줌
		}
		return filepath;
	}

	//파일 다운로드하기
	public void downloadFile(String savepath, String filename, String filepath, HttpServletResponse response) {
		String downFile = savepath+filepath;	//업로드해둔 실제 파일명
		try {
			//파일을 객체로 읽어오기 위한 스트림 생성
			FileInputStream fis = new FileInputStream(downFile);	//방향 : 프로그램 기준(파일에서 읽어와야 함)
			//속도개선을 위한 보조스트림 생성
			BufferedInputStream bis = new BufferedInputStream(fis);
			
			//읽어온 파일을 사용자에게 내보낼 스트림 생성(캐치추가)
			ServletOutputStream sos = response.getOutputStream();
			//속도 개선을 위한 보조스트림 생성
			BufferedOutputStream bos = new BufferedOutputStream(sos);
			
			//파일명 처리(파일명이 깨지지 않도록, 크롬기준(브라우저마다 방법이 다름))
			String resFilename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
			
			//파일 다운로드를 위한 HTTP 헤더 설정 
			response.setContentType("application/octet-stream");	//내가 되돌려줄 건 파일이라고 알려주는 과정
			response.setHeader("Content-Disposition", "attachment;filename="+resFilename);	//파일명 설정(att;filename=으로 들어와야 파일명으로 다운로드가 진행됨)
			
			//파일을 읽어온 뒤 전송하는 코드
			while(true) {
				int data = bis.read();	//데이터 읽어오기
				if(data != -1) {		//읽어오는 중
					bos.write(data);
				} else {				//읽어오기 끝
					break;
				}
			}
			bis.close();
			bos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
