package top.cxh.chat.bean;

import java.util.Date;
import java.util.List;

public class Circle {
    private String circleId;

    private String context;

    private Date createDate;

    private String account;
    
    private UserInfo userInfo;
    
    private List<CircleImage> circleImages;
    
    private List<LikeCircle> likeCircle;
    
    private List<CommentCircle> commentCircle;
    

    @Override
	public String toString() {
		return "Circle [circleId=" + circleId + ", context=" + context + ", createDate=" + createDate + ", account="
				+ account + ", circleImages=" + circleImages + "]";
	}

	public Circle() {
		super();
	}

	public Circle(String circleId, String context, Date createDate, String account) {
		super();
		this.circleId = circleId;
		this.context = context;
		this.createDate = createDate;
		this.account = account;
	}

	public String getCircleId() {
        return circleId;
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId == null ? null : circleId.trim();
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

	public List<CircleImage> getCircleImages() {
		return circleImages;
	}

	public void setCircleImages(List<CircleImage> circleImages) {
		this.circleImages = circleImages;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<CommentCircle> getCommentCircle() {
		return commentCircle;
	}

	public void setCommentCircle(List<CommentCircle> commentCircle) {
		this.commentCircle = commentCircle;
	}

	public List<LikeCircle> getLikeCircle() {
		return likeCircle;
	}

	public void setLikeCircle(List<LikeCircle> likeCircle) {
		this.likeCircle = likeCircle;
	}
}