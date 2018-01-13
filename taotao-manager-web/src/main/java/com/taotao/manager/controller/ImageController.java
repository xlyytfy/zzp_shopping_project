package com.taotao.manager.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.pojo.PicUploadResult;

@Controller
@RequestMapping("/pic/upload")
public class ImageController {
	
	@Value("${TAOTAO_IMAGE_URL}")
	private String TAOTAO_IMAGE_URL;
	
	//声明可用的文件类型
	private String[] TYPE = { ".jpg", ".jpeg", ".png", ".bmp", ".gif" };
	
	//使用Jackson工具类把对象转换为json数据
	private static final ObjectMapper mapper = new ObjectMapper();
	
	
	@RequestMapping(method = RequestMethod.POST,produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String upload(MultipartFile uploadFile) throws Exception{
		PicUploadResult picUploadResult = new PicUploadResult();
		//默认设置上传失败
		picUploadResult.setError(1);
		
		//默认判断标识为fasle
		boolean flag = false;
		
		//1:文件类型判断
		for (String suffix : TYPE) {
			//如果文件类型匹配
			if(StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(), suffix)){
				flag = true;
				break;
			}
		}
		
		if(!flag){
			//如果类型不存在则返回错误
			return mapper.writeValueAsString(picUploadResult);
		}
		
		//重置标识
		flag = false;
		
		//2：文件内容判读
		try {
			BufferedImage image = ImageIO.read(uploadFile.getInputStream());
			if(null != image){
				picUploadResult.setHeight(String.valueOf(image.getHeight()));
				picUploadResult.setWidth(String.valueOf(image.getWidth()));
				flag = true;
			}
		} catch (Exception e) {
			//如果catch异常或者image = null flag = false
		}
		
		if(!flag){
			//没有获取到宽和高
			return mapper.writeValueAsString(picUploadResult);
		}
		
		//执行文件上传
		//初始化服务器连接配置信息
		//ClientGlobal.init("classpath:tracker.properties");
		ClientGlobal.init(System.getProperty("user.dir")+"/src/main/resources/tracker.properties");
		//创建TrackerClient
		TrackerClient trackerClient = new TrackerClient();
		//创建trackerServer
		TrackerServer trackerServer = trackerClient.getConnection();
		//创建storageServer
		StorageServer storageServer = null;
		//创建StorageClient
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		/**
		 * file_buff:文件字节流
		 * file_ext_name：文件后缀名
		 * meta_list：null
		 */
		String[] file = storageClient.upload_file(uploadFile.getBytes(),StringUtils.substringAfterLast(uploadFile.getOriginalFilename(), "."), null);
		
		//拼接UR地址：http://192.168.37.161/group1/M00/00/00/wKgloVpQP8eACKiaAAM8-p7VfAU696_big.jpg
		picUploadResult.setUrl(TAOTAO_IMAGE_URL+file[0]+"/"+file[1]);
		//设置上传成功
		picUploadResult.setError(0);
		return mapper.writeValueAsString(picUploadResult);
	}
	
	
	/**
	 * 图片上传
	 * @return
	 * @throws MyException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	/*@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public PicUploadResult upload(MultipartFile uploadFile) throws Exception{
		PicUploadResult picUploadResult = new PicUploadResult();
		//默认设置上传失败
		picUploadResult.setError(1);
		
		//默认判断标识为fasle
		boolean flag = false;
		
		//1:文件类型判断
		for (String suffix : TYPE) {
			//如果文件类型匹配
			if(StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(), suffix)){
				flag = true;
				break;
			}
		}
		
		if(!flag){
			//如果类型不存在则返回错误
			return picUploadResult;
		}
		
		//重置标识
		flag = false;
		
		//2：文件内容判读
		try {
			BufferedImage image = ImageIO.read(uploadFile.getInputStream());
			if(null != image){
				picUploadResult.setHeight(String.valueOf(image.getHeight()));
				picUploadResult.setWidth(String.valueOf(image.getWidth()));
				flag = true;
			}
		} catch (Exception e) {
			//如果catch异常或者image = null flag = false
		}
		
		if(!flag){
			//没有获取到宽和高
			return picUploadResult;
		}
		
		//执行文件上传
		//初始化服务器连接配置信息
		//ClientGlobal.init("classpath:tracker.properties");
		ClientGlobal.init(System.getProperty("user.dir")+"/src/main/resources/tracker.properties");
		//创建TrackerClient
		TrackerClient trackerClient = new TrackerClient();
		//创建trackerServer
		TrackerServer trackerServer = trackerClient.getConnection();
		//创建storageServer
		StorageServer storageServer = null;
		//创建StorageClient
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		*//**
		 * file_buff:文件字节流
		 * file_ext_name：文件后缀名
		 * meta_list：null
		 *//*
		String[] file = storageClient.upload_file(uploadFile.getBytes(),StringUtils.substringAfterLast(uploadFile.getOriginalFilename(), "."), null);
		
		//拼接UR地址：http://192.168.37.161/group1/M00/00/00/wKgloVpQP8eACKiaAAM8-p7VfAU696_big.jpg
		picUploadResult.setUrl(TAOTAO_IMAGE_URL+file[0]+"/"+file[1]);
		//设置上传成功
		picUploadResult.setError(0);
		return picUploadResult;
	}*/

}
