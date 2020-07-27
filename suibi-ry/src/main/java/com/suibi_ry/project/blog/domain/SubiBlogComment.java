package com.suibi_ry.project.blog.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.suibi_ry.framework.aspectj.lang.annotation.Excel;
import com.suibi_ry.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 评论对象 subi_blog_comment
 * 
 * @author mxchen
 * @date 2020-07-19
 */
public class SubiBlogComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评论ID */
    private Long commentId;

    /** 评论用户ID */
    @Excel(name = "评论用户ID")
    private Long userId;

    /** 评论博文ID */
    @Excel(name = "评论博文ID")
    private Long articleId;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String commentContext;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Long commentLike;

    /** 父评论ID */
    @Excel(name = "父评论ID")
    private Long parentCommentId;

    /** 评论日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "评论日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date commentDate;

    public void setCommentId(Long commentId) 
    {
        this.commentId = commentId;
    }

    public Long getCommentId() 
    {
        return commentId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setArticleId(Long articleId) 
    {
        this.articleId = articleId;
    }

    public Long getArticleId() 
    {
        return articleId;
    }
    public void setCommentContext(String commentContext) 
    {
        this.commentContext = commentContext;
    }

    public String getCommentContext() 
    {
        return commentContext;
    }
    public void setCommentLike(Long commentLike) 
    {
        this.commentLike = commentLike;
    }

    public Long getCommentLike() 
    {
        return commentLike;
    }
    public void setParentCommentId(Long parentCommentId) 
    {
        this.parentCommentId = parentCommentId;
    }

    public Long getParentCommentId() 
    {
        return parentCommentId;
    }
    public void setCommentDate(Date commentDate) 
    {
        this.commentDate = commentDate;
    }

    public Date getCommentDate() 
    {
        return commentDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("commentId", getCommentId())
            .append("userId", getUserId())
            .append("articleId", getArticleId())
            .append("commentContext", getCommentContext())
            .append("commentLike", getCommentLike())
            .append("parentCommentId", getParentCommentId())
            .append("commentDate", getCommentDate())
            .toString();
    }
}
