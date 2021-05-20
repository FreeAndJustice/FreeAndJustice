package top.cxh.chat.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.GlobalEventExecutor;
import top.cxh.chat.bean.GroupUser;
import top.cxh.chat.bean.MsgRecord;
import top.cxh.chat.bean.UserInfo;
import top.cxh.chat.service.GroupUserService;
import top.cxh.chat.service.MsgRecordService;
import top.cxh.chat.service.UserInfoService;
import top.cxh.chat.utils.Beans;
import top.cxh.chat.utils.Config;

public class ChatServer {
	
	private int port;
	
	public ChatServer(int port) {
		this.port = port;
	}
	
	/**
	 * 
	 */
	public void run() {
		
		//创建两个线程组
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap sb = new ServerBootstrap();
			
			sb.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 128)
			.childOption(ChannelOption.SO_KEEPALIVE, true)
			.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel sc) throws Exception {
					
					

					ChannelPipeline pipeline = sc.pipeline();
					pipeline.addLast(new HttpServerCodec());
					pipeline.addLast(new ChunkedWriteHandler());
					pipeline.addLast(new HttpObjectAggregator(1024 * 64));
					pipeline.addLast(new WebSocketServerProtocolHandler("/chat"));
					
					//加入自己的业务处理
					pipeline.addLast(new ChatServerHandler());
					
				}
			});
			ChannelFuture channelFuture = sb.bind(port).sync();
			
			//监听关闭
			channelFuture.channel().closeFuture().sync();
		}catch(Exception e) {
			e.printStackTrace();
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
		
		
	}



	public static void main(String[] args) {
		new ChatServer(7000).run();
	}
	
}

class ChatServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{
	
	//定义一个channel组
	private static ChannelGroup cg = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	
	/**
	 * 连接建立时首先被执行
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		//获得当前连接的channel
		Channel c = ctx.channel();
		Set<Entry<String,Channel>> cs = Config.onlineUsers.entrySet();
		for(Entry<String,Channel> channel : cs) {
			if(channel.getValue() == c) {
				Config.onlineUsers.remove(channel.getKey());
				break;
			}
		}
		//当前连接加入组
		cg.add(c);
	}

	/**
	 * channel处于活动状态，处理连接功能
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel c = ctx.channel();
		System.out.println("客户端【" + c.remoteAddress() + "】连接\n");
	}
	
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		Channel c = ctx.channel();
		String res = msg.text();
		System.out.println(res);
		JSONObject param = JSONObject.parseObject(res);
		int msgCode = param.getInteger("msgCode");
		
		JSONObject data = new JSONObject();
		data.put("msgCode", msgCode);
		if(msgCode == 100) {//连接建立，请求注册为在线
			String fromAccount = param.getString("fromAccount");
			if(Config.onlineUsers.containsKey(fromAccount)) {
				Channel oldC = Config.onlineUsers.get(fromAccount);
				if(oldC.isActive()) {//原先连接还在，处理强制下线
					System.out.println("原链接还在");
				}
			}
			System.out.println("原链接不在了");
			Config.onlineUsers.put(fromAccount, c);
			System.out.println(Config.onlineUsers);
		}else if(msgCode == 101) {//聊天消息转发
			String toAccount = param.getString("toAccount");
			if(Config.onlineUsers.containsKey(toAccount)) {//好友是否在线
				Channel channel = Config.onlineUsers.get(toAccount);
				if(channel.isActive()) {//通道是否还在
					for(Channel c1 : cg) {//遍历找到该通道
						if(c1 == channel) {//找到通道用此通道进行转发
							TextWebSocketFrame tsf = new TextWebSocketFrame(param.toJSONString());
							c1.writeAndFlush(tsf);
							return;
						}
					}
				}
			}
			//不在线将消息写入数据库
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			MsgRecord mr = new MsgRecord(param.getString("context"),param.getString("fromAccount"),toAccount,"chat","self",sdf.parse(param.getString("createDate")));
			Beans.getBean("msgRecordService", MsgRecordService.class).addMsgRecord(mr);
		}else if(msgCode == 102) {//申请消息转发
			
		}else if(msgCode == 103) {//心跳消息
			System.out.println(param.getString("createDate") + "  用户【" + param.getString("fromAccount") + "】发送心跳包,地址：【" + c.remoteAddress() +"】");
		}else if(msgCode == 104) {//群聊消息
			try {
				String toAccount = param.getString("toAccount");
				String fromAccount = param.getString("fromAccount");
				UserInfo ui = Beans.getBean("userInfoService", UserInfoService.class).getUserInfoByAccount(fromAccount);
				List<GroupUser> groupUsers = Beans.getBean("groupUserService", GroupUserService.class).getGroupUsers(toAccount);
				for(GroupUser gu : groupUsers) {//遍历群成员，发送群消息
					boolean flag = false;
					String recvAccount = gu.getAccount();
					if(!recvAccount.equals(fromAccount)) {//不给自己发
						if(Config.onlineUsers.containsKey(recvAccount)) {//好友是否在线
							Channel channel = Config.onlineUsers.get(recvAccount);
							if(channel.isActive()) {//通道是否还在
								for(Channel c1 : cg) {//遍历找到该通道
									if(c1 == channel) {//找到通道用此通道进行转发
										param.put("user", ui);
										param.put("role", gu.getGroupRole());
										TextWebSocketFrame tsf = new TextWebSocketFrame(param.toJSONString());
										c1.writeAndFlush(tsf);
										flag = false;
									}
								}
							}else {
								flag = true;
							}
						}else {
							flag = true;
						}
						if(flag) {
							//未发送写入数据库....
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							MsgRecord mr = new MsgRecord(param.getString("context")+"#@#"+toAccount,param.getString("fromAccount"),recvAccount,"chat","group",sdf.parse(param.getString("createDate")));
							Beans.getBean("msgRecordService", MsgRecordService.class).addMsgRecord(mr);
						}
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * channel处于不活动状态，可在此处理离线功能
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Channel c = ctx.channel();
		Set<Entry<String,Channel>> cs = Config.onlineUsers.entrySet();
		for(Entry<String,Channel> channel : cs) {
			if(channel.getValue() == c) {
				Config.onlineUsers.remove(channel.getKey());
				break;
			}
		}
		System.out.println(Config.onlineUsers);
		System.out.println("客户端【" + c.remoteAddress() + "】断开连接\n");
	}

	/**
	 * 断开连接触发
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		cg.remove(ctx.channel());
	}

	/**
	 * 异常
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println(cause);
		Channel c = ctx.channel();
		c.close();
		cg.remove(ctx.channel());
	}

	
}

