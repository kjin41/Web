package com.ssafy.hw.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class FileDownLoadView extends AbstractView{

	public FileDownLoadView() {
		setContentType("application/download; charset=UTF-8");
	}
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ServletContext ctx=getServletContext();
		String realPath=ctx.getRealPath("/upload");
		
		Map<String, String> fileInfo=(Map<String, String>) model.get("downloadFile");
		
		String saveFolder=(String) fileInfo.get("sfolder");
		String originalFile=(String) fileInfo.get("ofile");
		String saveFile=(String) fileInfo.get("sfile");
		File file=new File(realPath+File.separator+saveFolder, saveFile);
		
		response.setContentType(getContentType());
		response.setContentLength((int) file.length());
		
		String header=request.getHeader("User-Agent");
		boolean isIE=header.indexOf("MSIE")>-1 || header.indexOf("Trident")>-1;
		String fileName=null;
		if (isIE) {
			fileName=URLEncoder.encode(originalFile, "UTF-8").replaceAll("\\+", "%20");
		} else {
			fileName=new String(originalFile.getBytes("UTF-8"), "ISO-8859-1");
		}
		response.setHeader("Content-Disposition", "attatchment; filename=\""+fileName+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream out=response.getOutputStream();
		FileInputStream fis=null;
		
		fis=new FileInputStream(file);
		FileCopyUtils.copy(fis, out);
		fis.close();
		out.flush();
//		try {
//            fis = new FileInputStream(file);
//            FileCopyUtils.copy(fis, out);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if(fis != null) {
//                try { 
//                    fis.close(); 
//                }catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
	}
	

}
