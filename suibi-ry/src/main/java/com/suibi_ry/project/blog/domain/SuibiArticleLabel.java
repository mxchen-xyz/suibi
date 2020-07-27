package com.suibi_ry.project.blog.domain;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * TODO
 * 文章与标签关联对象
 * @author mxchen
 * @version V1.0
 * @Package com.suibi_ry.project.blog.domain
 * @date 2020/7/21 16:20
 */
public class SuibiArticleLabel {
    /** 文章ID */
    private Long articleId;

    /** 标签id */
    private Long labelId;

    public void setArticleId(Long articleId)
    {
        this.articleId = articleId;
    }

    public Long getArticleId()
    {
        return articleId;
    }
    public void setLabelId(Long labelId)
    {
        this.labelId = labelId;
    }

    public Long getLabelId()
    {
        return labelId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("articleId", getArticleId())
                .append("labelId", getLabelId())
                .toString();
    }
}
