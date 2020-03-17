package cn.hcnet2006.blog.hcnetwebsite.bean;

import java.io.Serializable;
import java.util.Date;

public class SysVideo implements Serializable {
    private Long id;

    private String videoImgUrl;

    private String videoContentUrl;

    private String videoIntroUrl;

    private Date createTime;

    private String createBy;

    private Date lastUpdateTime;

    private String lastUpdateBy;

    private Byte delFlag;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVideoImgUrl() {
        return videoImgUrl;
    }

    public void setVideoImgUrl(String videoImgUrl) {
        this.videoImgUrl = videoImgUrl;
    }

    public String getVideoContentUrl() {
        return videoContentUrl;
    }

    public void setVideoContentUrl(String videoContentUrl) {
        this.videoContentUrl = videoContentUrl;
    }

    public String getVideoIntroUrl() {
        return videoIntroUrl;
    }

    public void setVideoIntroUrl(String videoIntroUrl) {
        this.videoIntroUrl = videoIntroUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", videoImgUrl=").append(videoImgUrl);
        sb.append(", videoContentUrl=").append(videoContentUrl);
        sb.append(", videoIntroUrl=").append(videoIntroUrl);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", lastUpdateBy=").append(lastUpdateBy);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}