package cn.hcnet2006.blog.hcnetwebsite.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(value = {"handler"})
public class SysDeptArticle implements Serializable {
    private Long deptId;

    private Long articleId;

    private String id;

    private Date createTime;

    private String createBy;

    private Date lastUpdateTime;

    private String lastUdpateBy;

    private Byte delFlag;

    private static final long serialVersionUID = 1L;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getLastUdpateBy() {
        return lastUdpateBy;
    }

    public void setLastUdpateBy(String lastUdpateBy) {
        this.lastUdpateBy = lastUdpateBy;
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
        sb.append(", deptId=").append(deptId);
        sb.append(", articleId=").append(articleId);
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", lastUdpateBy=").append(lastUdpateBy);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}