package com.suibi_ry.project.blog.domain;

import com.suibi_ry.framework.aspectj.lang.annotation.Excel;
import com.suibi_ry.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * TODO
 * 博客标签对象
 * @author mxchen
 * @version V1.0
 * @Package com.suibi_ry.project.blog.domain
 * @date 2020/7/21 16:17
 */
public class SuibiBlogLabel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 标签Id */
    private Long labelId;

    /** 标签名字 */
    @Excel(name = "标签名字")
    private String labelName;

    /** 标签颜色代码 */
    @Excel(name = "标签颜色代码")
    private String labelColor;

    /** 标签状态（0 :正常 1：停用） */
    @Excel(name = "标签状态", readConverterExp = "0=,:=正常,1=：停用")
    private String labelStatus;

    public void setLabelId(Long labelId)
    {
        this.labelId = labelId;
    }

    public Long getLabelId()
    {
        return labelId;
    }
    public void setLabelName(String labelName)
    {
        this.labelName = labelName;
    }

    public String getLabelName()
    {
        return labelName;
    }
    public void setLabelColor(String labelColor)
    {
        this.labelColor = labelColor;
    }

    public String getLabelColor()
    {
        return labelColor;
    }
    public void setLabelStatus(String labelStatus)
    {
        this.labelStatus = labelStatus;
    }

    public String getLabelStatus()
    {
        return labelStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("labelId", getLabelId())
                .append("labelName", getLabelName())
                .append("labelColor", getLabelColor())
                .append("labelStatus", getLabelStatus())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
