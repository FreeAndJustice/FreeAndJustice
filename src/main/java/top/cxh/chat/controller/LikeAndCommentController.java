package top.cxh.chat.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import top.cxh.chat.bean.CommentCircle;
import top.cxh.chat.bean.LikeCircle;
import top.cxh.chat.bean.Msg;
import top.cxh.chat.service.CommentCircleService;
import top.cxh.chat.service.LikeCircleService;

@Controller
@RequestMapping("/circle")
public class LikeAndCommentController {

	@Autowired
	LikeCircleService likeCircleService;
	
	@Autowired
	CommentCircleService commentCircleService;
	
	/**
	 * 添加点赞记录
	 * @param likeCircle
	 * @return
	 */
	@RequestMapping(value="/addLikeCircle",method=RequestMethod.POST)
	@ResponseBody
	Msg addLikeCircle(LikeCircle likeCircle) {
		Msg msg = new Msg();
		boolean flag = likeCircleService.isLikeCircle(likeCircle.getAccount(), likeCircle.getCircleId());
		if(flag) {
			flag = likeCircleService.addLikeCircle(likeCircle);
			if(flag) {
				msg.setCode(100);
				msg.setMsg("成功");
			}else {
				msg.setCode(200);
				msg.setMsg("成功");
			}
		}else {
			msg.setCode(300);
			msg.setMsg("已经点过了");
		}
		return msg;
	}
	
	/**
	 * 删除点赞
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delLikeCircle/{id}",method=RequestMethod.GET)
	@ResponseBody
	Msg delLikeCircle(@PathParam("id") Integer id) {
		boolean flag = likeCircleService.delLikeCircle(id);
		if(flag) {
			return Msg.success();
		}else {
			return Msg.fail();
		}
	}
	
	/**
	 * 添加评论记录
	 * @param commentCircle
	 * @return
	 */
	@RequestMapping(value="/addCommentCircle",method=RequestMethod.POST)
	@ResponseBody
	Msg addCommentCircle(CommentCircle commentCircle) {
		boolean flag = commentCircleService.addCommentCircle(commentCircle);
		if(flag) {
			return Msg.success();
		}else {
			return Msg.fail();
		}
	}
	
	/**
	 * 删除评论
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delCommentCircle/{id}",method=RequestMethod.GET)
	@ResponseBody
	Msg delCommentCircle(@PathParam("id") Integer id) {
		boolean flag = commentCircleService.delCommentCircle(id);
		if(flag) {
			return Msg.success();
		}else {
			return Msg.fail();
		}
	}
	
}
