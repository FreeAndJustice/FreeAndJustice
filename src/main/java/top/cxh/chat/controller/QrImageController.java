package top.cxh.chat.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import top.cxh.chat.bean.Msg;
import top.cxh.chat.bean.QrImage;
import top.cxh.chat.service.QrImageService;
import top.cxh.chat.utils.Config;
import top.cxh.chat.utils.qr.CreateQR;

@Controller
@RequestMapping("/qr")
public class QrImageController {

	@Autowired
	QrImageService qrImageService;
	
	@RequestMapping(value = "/createQR", method = RequestMethod.POST)
	@ResponseBody
	Msg createQR(@RequestParam("imageName") String imageName,
						@RequestParam("account") String account,
						@RequestParam("name") String name) throws IOException {
		Msg msg = new Msg();
		List<QrImage> qrImage = qrImageService.getQrImage(account);
		boolean flag = false;
		if(qrImage.size() > 0) {//二维码存在
			QrImage qi = qrImage.get(0);
			File f = new File(Config.storagePath + "\\userImage\\" + qi.getQrName());
			if(f.exists() && f.isFile()) {//文件还在
				flag = false;
			}else {//文件已经不再了
				flag = true;
			}
		}else {//二维码不存在
			flag = true;
		}
		if(flag) {//需要重新创建
			String imagePath = Config.storagePath + "\\userImage\\" + imageName;
			String outPath = Config.storagePath + "\\userImage";
			String fileName = account + "-QR-" + new Date().getTime() + ".png";
			Map<String,Object> map = new HashMap<String,Object>();
			JSONObject json = new JSONObject();
			json.put("account", account);
			json.put("name", name);
			flag = CreateQR.Encode_QR_CODE(json.toJSONString(), imagePath, outPath, fileName);
			if(flag) {
				QrImage qi = new QrImage(account,fileName);
				qrImageService.addQrImage(qi);
			}
			map.put("flag", flag);
			map.put("fileName", fileName);
			msg.setCode(100);
			msg.add("data", map);
		}else {//不需要重新创建
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("flag", true);
			map.put("fileName", qrImage.get(0).getQrName());
			msg.setCode(100);
			msg.add("data", map);
		}
		return msg;
	}
	
}
