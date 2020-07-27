package com.suibi_ry.project.blog.domain;

import com.suibi_ry.framework.aspectj.lang.annotation.Excel;
import com.suibi_ry.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * TODO
 *
 * @author mxchen
 * @version V1.0
 * @Package com.suibi_ry.project.blog.domain
 * @date 2020/7/19 23:30
 */
public class SuibiBlogArticle extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 博文ID
     */
    private Long articleId;

    /**
     * 创作者ID
     */
    private Long userId;

    /**
     * 博文标题
     */
    @Excel(name = "博文标题")
    private String articleTitle;

    /**
     * 博文内容
     */
    @Excel(name = "博文内容")
    private String articleContent;

    /**
     * 博文浏览量
     */
    @Excel(name = "博文浏览量")
    private Long articleView;

    /**
     * 博文评论数
     */
    @Excel(name = "博文评论数")
    private Long articleCount;

    /**
     * 博文点赞数
     */
    @Excel(name = "博文点赞数")
    private Long articleLike;

    /**
     * 发布状态，未发布：0 ，已发布 ： 1
     */
    @Excel(name = "发布状态")
    private String articleStatus;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleView(Long articleView) {
        this.articleView = articleView;
    }

    public Long getArticleView() {
        return articleView;
    }

    public void setArticleCount(Long articleCount) {
        this.articleCount = articleCount;
    }

    public Long getArticleCount() {
        return articleCount;
    }

    public void setArticleLike(Long articleLike) {
        this.articleLike = articleLike;
    }

    public Long getArticleLike() {
        return articleLike;
    }

    public void setArticleStatus(String articleStatus) {
        this.articleStatus = articleStatus;
    }

    public String getArticleStatus() {
        return articleStatus;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("articleId", getArticleId())
                .append("userId", getUserId())
                .append("articleTitle", getArticleTitle())
                .append("articleContent", getArticleContent())
                .append("articleView", getArticleView())
                .append("articleCount", getArticleCount())
                .append("articleLike", getArticleLike())
                .append("articleStatus", getArticleStatus())
                .append("delFlag", getDelFlag())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}


