package com.ssafy.hw.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.hw.dto.FileInfoDto;
import com.ssafy.hw.dto.ProductDto;
import com.ssafy.hw.dto.UserDto;
import com.ssafy.hw.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController extends HttpServlet {
	
	private Logger logger=LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/list")
	private String listArticle(@RequestParam Map<String, String> map, HttpSession session, Model model) {
		UserDto userDto=(UserDto) session.getAttribute("userinfo");
		if (userDto!=null) {
			try {
				List<ProductDto> list=productService.listArticle(map);
				model.addAttribute("articlelist", list);
				return "/product/listview";
				
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("error", "목록 얻기 중 문제 발생");
				return "/error/error";
			}
			
		} else {
			model.addAttribute("msg", "로그인 후 이용해 주세요.");
			return "/index";
		}
	}

	
	@GetMapping("/register")
	private String registArticle() {
		return "/product/regist";
	}
	
	@PostMapping("/register")
	private String registArticle(ProductDto productDto, Model model, HttpSession session, HttpServletResponse response,
			@RequestParam("upfile") MultipartFile[] files, RedirectAttributes redirectAttributes) {
		UserDto userDto=(UserDto) session.getAttribute("userinfo");
		if (userDto!=null) {
			try {
//				FileUpload
//				System.out.println(files[0].isEmpty());
				logger.debug("MultipartFile.isEmpty: {}", files[0].isEmpty());
				if (!files[0].isEmpty()) {
					String realPath=servletContext.getRealPath("/upload");
					String today=new SimpleDateFormat("yyMMdd").format(new Date());
					String saveFolder=realPath+File.separator+today;
					logger.debug("저장 폴더: {}", saveFolder);
					File folder=new File(saveFolder);
					if (!folder.exists()) {
						folder.mkdirs();
					}
					List<FileInfoDto> fileInfos=new ArrayList<FileInfoDto>();
					for (MultipartFile mfile: files) {
						FileInfoDto fileInfoDto=new FileInfoDto();
						String originalFileName=mfile.getOriginalFilename();
						if (!originalFileName.isEmpty()) {
							String saveFileName=UUID.randomUUID().toString()+originalFileName.substring(originalFileName.lastIndexOf('.'));
							fileInfoDto.setSaveFolder(today);
							fileInfoDto.setOriginFile(originalFileName);
							fileInfoDto.setSaveFile(saveFileName);
							logger.debug("원본 파일 이름: {}, 실제 저장 파일 이름: {}", mfile.getOriginalFilename(), saveFileName);
							mfile.transferTo(new File(folder, saveFileName));
						}
						fileInfos.add(fileInfoDto);
					}
					productDto.setFileInfos(fileInfos);
				}
				
				
				productService.registerArticle(productDto);
				// 사용자별로 마지막 등록된거 따로 저장 : 주로 쿠키를 안씀
//				Cookie cookie=new Cookie(userDto.getId(), productDto.getNo());	
				// 사용자 상관없이 마지막으로 등록된 상품.
				Cookie cookie=new Cookie("product_no", productDto.getNo());	
				cookie.setMaxAge(60*60*24*365*40);
				cookie.setPath("/");
				response.addCookie(cookie);

				
				
				
				redirectAttributes.addAttribute("msg", "상품이 등록되었습니다.");
				return "redirect:/product/list?key=&word=";
				
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("error", "상품 등록 중 문제 발생");
				return "/error/error";
			} 
		} else {
			model.addAttribute("msg", "로그인 후 이용해 주세요.");
			return "/index";
		}
	}
	
	@GetMapping("/download")
	public ModelAndView downloadFile(@RequestParam("sfolder") String sfolder, @RequestParam("ofile") String ofile, 
			@RequestParam("sfile") String sfile, HttpSession session) {
		UserDto userDto=(UserDto) session.getAttribute("userinfo");
		if (userDto!=null) {
			Map<String, Object> fileInfo=new HashMap<String, Object>();
			fileInfo.put("sfolder", sfolder);
			fileInfo.put("ofile", ofile);
			fileInfo.put("sfile", sfile);
			return new ModelAndView("fileDownLoadView", "downloadFile", fileInfo);
		} else {
//			model.addAttribute("msg", "로그인 후 이용해 주세요.");
			return new ModelAndView("/index");	// redirect:/
			
		}
	}
	
	@GetMapping("/lastproduct")
	public String lastProduct(@CookieValue(value="product_no", required=false) String no, HttpSession session, HttpServletRequest request, Model model) {
		UserDto userDto = (UserDto) session.getAttribute("userinfo");
		if (userDto!=null) {
			try {
				ProductDto lastProductDto=productService.getArticle(no);
    			model.addAttribute("lastproduct", lastProductDto);
    			return "/product/lastproduct";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("error", "마지막 상품 보기 중 문제 발생");
				return "/error/error";
			}
		} else {
			model.addAttribute("msg", "로그인 후 이용해 주세요.");
			return "/index";
		}
	}

	@GetMapping("/update")
	public String updateArticle(@RequestParam("no") String no, Model model, HttpSession session) {
		UserDto userDto=(UserDto) session.getAttribute("userinfo");
		if (userDto!=null) {
			try {
				ProductDto productDto=productService.getArticle(no);
    			model.addAttribute("product", productDto);
				return "/product/update";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("error", "상품 등록 중 문제 발생");
				return "/error/error";
			}
		} else {
			model.addAttribute("msg", "로그인 후 이용해 주세요.");
			return "/index";
		}
	}
	
	@PostMapping("/update")
	public String updateArticle(ProductDto productDto, HttpSession session, Model model) {
		UserDto userDto=(UserDto) session.getAttribute("userinfo");
		if (userDto!=null) {
			try {
				productService.updateArticle(productDto);
				return "redirect:/product/list?key=&word=";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("error", "상품 등록 중 문제 발생");
				return "/error/error";
			}
		} else {
			model.addAttribute("msg", "로그인 후 이용해 주세요.");
			return "/index";
		}
	}
	
	@GetMapping("/delete")
	public String deleteArticle(@RequestParam("no") String no, HttpSession session, Model model) {
		UserDto userDto=(UserDto) session.getAttribute("userinfo");
		if (userDto!=null) {
			try {
				productService.deleteArticle(no, servletContext.getRealPath("/upload"));
				return "redirect:/product/list?key=&word=";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("error", "상품 삭제 중 문제 발생");
				return "/error/error";
			}
		} else {
			model.addAttribute("msg", "로그인 후 이용해 주세요.");
			return "/index";
		}
	}
	

}
