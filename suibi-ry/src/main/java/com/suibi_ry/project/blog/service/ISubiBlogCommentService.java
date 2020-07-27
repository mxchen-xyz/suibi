package com.suibi_ry.project.blog.service;

import java.util.List;
import com.suibi_ry.project.blog.domain.SubiBlogComment;

/**
 * 评论Service接口
 * 
 * @author mxchen
 * @date 2020-07-19
 */
public interface ISubiBlogCommentService 
{
    /**
     * 查询评论
     * 
     * @param commentId 评论ID
     * @return 评论
     */
    public SubiBlogComment selectSubiBlogCommentById(Long commentId);

    /**
     * 查询评论列表
     * 
     * @param subiBlogComment 评论
     * @return 评论集合
     */
    public List<SubiBlogComment> selectSubiBlogCommentList(SubiBlogComment subiBlogComment);

    /**
     * 新增评论
     * 
     * @param subiBlogComment 评论
     * @return 结果
     */
    public int insertSubiBlogComment(SubiBlogComment subiBlogComment);

    /**
     * 修改评论
     * 
     * @param subiBlogComment 评论
     * @return 结果
     */
    public int updateSubiBlogComment(SubiBlogComment subiBlogComment);

    /**
     * 批量删除评论
     * 
     * @param commentIds 需要删除的评论ID
     * @return 结果
     */
    public int deleteSubiBlogCommentByIds(Long[] commentIds);

    /**
     * 删除评论信息
     * 
     * @param commentId 评论ID
     * @return 结果
     */
    public int deleteSubiBlogCommentById(Long commentId);
}
