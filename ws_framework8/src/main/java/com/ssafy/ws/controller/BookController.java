package com.ssafy.ws.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.ws.model.BookDto;
import com.ssafy.ws.model.FileInfoDto;
import com.ssafy.ws.model.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {

private static final Logger logger=LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/register")
	public String insert() {
		return "/book/regist";
	}
	
	@PostMapping("/register")
	public String insert(BookDto bookDto, HttpSession session, Model model, 
			@RequestParam("upfile") MultipartFile[] files, RedirectAttributes redirectAttributes) throws Exception {
		
//		FileUpload 관련 설정.
		logger.debug("MultipartFile.isEmpty : {}", files[0].isEmpty());
		if (!files[0].isEmpty()) {
			String realPath = servletContext.getRealPath("/upload");	// 실제경로
//			String realPath = servletContext.getRealPath("/resources/img");
			String today = new SimpleDateFormat("yyMMdd").format(new Date());	// 211021
			String saveFolder = realPath + File.separator + today;
			logger.debug("저장 폴더 : {}", saveFolder);
			File folder = new File(saveFolder);
			if (!folder.exists())
				folder.mkdirs();
			List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
			for (MultipartFile mfile : files) {
				FileInfoDto fileInfoDto = new FileInfoDto();
				String originalFileName = mfile.getOriginalFilename();
				if (!originalFileName.isEmpty()) {
					String saveFileName = UUID.randomUUID().toString()
							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
					fileInfoDto.setSaveFolder(today);
					fileInfoDto.setOriginFile(originalFileName);
					fileInfoDto.setSaveFile(saveFileName);
					logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
					mfile.transferTo(new File(folder, saveFileName));
				}
				fileInfos.add(fileInfoDto);
			}
			bookDto.setFileInfoDtos(fileInfos);
		}
		
		
		bookService.insert(bookDto);
		session.setAttribute("article", bookDto);
		redirectAttributes.addAttribute("msg", "책 등록 완료");
		
		
		return "redirect:/book/detail";
		
	}
	
	@GetMapping("/list")
	public String list(Model model, @RequestParam Map<String, String> map) throws Exception {
		String spp=map.get("spp");
		map.put("spp", spp!=null? spp:"10");
		model.addAttribute("key", map.get("key"));
		model.addAttribute("word", map.get("word"));
		model.addAttribute("col", map.get("col"));
		model.addAttribute("sort", map.get("sort"));
		model.addAttribute("articlelist", bookService.search(map));
		model.addAttribute("navigation", bookService.makePageNavigation(map));
		return "/book/list";
	}
	
	@GetMapping("/detail")
	public String detail() throws Exception {
		return "/book/detail";
	}
	
	@GetMapping(value="/download")
	public ModelAndView downloadFile(@RequestParam("sfolder") String sfolder, @RequestParam("ofile") String ofile,
		@RequestParam("sfile") String sfile, HttpSession session) {
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		fileInfo.put("sfolder", sfolder);
		fileInfo.put("ofile", ofile);
		fileInfo.put("sfile", sfile);
		return new ModelAndView("fileDownLoadView", "downloadFile", fileInfo);
	}
}
