package com.api.book.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
	
	//public final String UPLOAD_DIR="E:\\SpringBootProject\\bootrestexample\\src\\main\\resources\\static\\image";
	public final String UPLOAD_DIR=new ClassPathResource("static/image/").getFile().getAbsolutePath();
	
	
	public FileUploadHelper() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}


	public boolean uploadFile(MultipartFile file) {
		boolean f=false;
		try {
			InputStream is=file.getInputStream();
			byte data[]=new byte[is.available()];
			is.read(data);
			//write to file
			FileOutputStream fos=new FileOutputStream(UPLOAD_DIR+File.separator+file.getOriginalFilename());
			fos.write(data);
			fos.flush();
			fos.close();
			f=true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}

}
