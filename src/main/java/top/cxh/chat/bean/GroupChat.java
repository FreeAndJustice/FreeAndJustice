package top.cxh.chat.bean;

import java.util.Date;

public class GroupChat {
    private String groupId;

    private String groupName;

    private String groupImage;

    private Date createDate;
    
    

    public GroupChat(String groupId, String groupName, Date createDate) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.createDate = createDate;
	}

	public GroupChat() {
		super();
	}

	public GroupChat(String groupId, String groupName, String groupImage, Date createDate) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupImage = groupImage;
		this.createDate = createDate;
	}

	public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(String groupImage) {
        this.groupImage = groupImage == null ? null : groupImage.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}