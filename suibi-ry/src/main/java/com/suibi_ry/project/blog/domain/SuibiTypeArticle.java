package com.suibi_ry.project.blog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * TODO
 *类型与文章关联
 * @author mxchen
 * @version V1.0
 * @Package com.suibi_ry.project.blog.domain
 * @date 2020/7/21 16:02
 */
public class SuibiTypeArticle {
    //一个类型对应多个ID
    //类型Id
    private Long typeId;
    //文章Id
    private Long articleId;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("typeId", getTypeId())
                .append("articleId", getArticleId())
                .toString();
    }
}
