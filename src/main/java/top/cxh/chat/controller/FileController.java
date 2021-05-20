package top.cxh.chat.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import top.cxh.chat.utils.Config;


@Controller
public class FileController {

	/**
	 * 文件上传
	 * @param request
	 * @param path
	 * @param account
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadFiles(HttpServletRequest request,
			@RequestParam("path") String path,
			@RequestParam("account") String account) throws IOException {
		String fileNames = "";
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag = false;
		
		MultipartHttpServletRequest mReq = (MultipartHttpServletRequest) request;
		
		Iterator<String> files = mReq.getFileNames();
		
		MultipartFile file = null;
		OutputStream outStream = null;
		while(files.hasNext()) {
			try {
				file = mReq.getFile(files.next());
				byte[] bytes = file.getBytes();
				String oldName = file.getOriginalFilename();
				String fileName = account + "-" + new Date().getTime() + (int)(Math.random() * 10000) + oldName.substring(oldName.lastIndexOf("."),oldName.length());
				fileNames += fileName + "@";
				String filePath = Config.storagePath + "\\" + path.replaceAll(",", "\\") ;
				File f = new File(filePath);
				if(!f.exists()) {
					f.mkdirs();
				}
				File targetFile = new File(filePath + "\\" + fileName);
				outStream = new FileOutputStream(targetFile);
				outStream.write(bytes);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(!fileNames.equals("")) {
			map.put("fileName", fileNames.substring(0,fileNames.length()-1));
		}
		outStream.close();
		flag = true;
		map.put("flag", flag);
		return map;
	}
	
}
