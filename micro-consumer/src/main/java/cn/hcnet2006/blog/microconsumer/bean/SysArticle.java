package cn.hcnet2006.blog.microconsumer.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(value = {"handler"})
public class SysArticle implements Serializable {
    private Long id;

    private String articleName;

    private String articleImgUrl;

    private String articleIntroUrl;

    private String articleContentUrl;

    private String createBy;

    private Date createTime;

    private String lastUpdateBy;

    private Date lastUpdateTime;

    private Byte delFlag;
    private Long deptId;
    private Long userId;
    private Long typeId;
    private List<SysDept> deptList;
    private List<SysType> typeList;
    private List<SysUser> userList;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleImgUrl() {
        return articleImgUrl;
    }

    public void setArticleImgUrl(String articleImgUrl) {
        this.articleImgUrl = articleImgUrl;
    }

    public String getArticleIntroUrl() {
        return articleIntroUrl;
    }

    public void setArticleIntroUrl(String articleIntroUrl) {
        this.articleIntroUrl = articleIntroUrl;
    }

    public String getArticleContentUrl() {
        return articleContentUrl;
    }

    public void setArticleContentUrl(String articleContentUrl) {
        this.articleContentUrl = articleContentUrl;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public List<SysDept> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<SysDept> deptList) {
        this.deptList = deptList;
    }

    public List<SysType> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<SysType> typeList) {
        this.typeList = typeList;
    }

    public List<SysUser> getUserList() {
        return userList;
    }

    public void setUserList(List<SysUser> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", articleName=").append(articleName);
        sb.append(", articleImgUrl=").append(articleImgUrl);
        sb.append(", articleIntroUrl=").append(articleIntroUrl);
        sb.append(", articleContentUrl=").append(articleContentUrl);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", lastUpdateBy=").append(lastUpdateBy);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}