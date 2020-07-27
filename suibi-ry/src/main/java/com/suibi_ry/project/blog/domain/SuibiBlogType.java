package com.suibi_ry.project.blog.domain;

import com.suibi_ry.framework.aspectj.lang.annotation.Excel;
import com.suibi_ry.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 博客类型对象 suibi_blog_type
 * 
 * @author suibi_ry
 * @date 2020-07-17
 */
public class SuibiBlogType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类型ID */
    private Long typeId;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String typeName;

    @Excel(name="文章数")
    private String articleNums;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer orderNum;

    /** 类型状态（0显示 1隐藏） */
    @Excel(name = "类型状态", readConverterExp = "0=显示,1=隐藏")
    private String typeStatus;

    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getArticleNums() {
        return articleNums;
    }

    public void setArticleNums(String articleNums) {
        this.articleNums = articleNums;
    }

    public String getTypeName()
    {
        return typeName;
    }
    public void setOrderNum(Integer orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum() 
    {
        return orderNum;
    }
    public void setTypeStatus(String typeStatus) 
    {
        this.typeStatus = typeStatus;
    }

    public String getTypeStatus() 
    {
        return typeStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("typeId", getTypeId())
            .append("articleNums",getArticleNums())
            .append("typeName", getTypeName())
            .append("orderNum", getOrderNum())
            .append("typeStatus", getTypeStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
