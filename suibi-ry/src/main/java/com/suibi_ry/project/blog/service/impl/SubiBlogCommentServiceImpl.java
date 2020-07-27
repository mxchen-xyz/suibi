package com.suibi_ry.project.blog.service.impl;

import java.util.List;

import com.suibi_ry.project.blog.mapper.SubiBlogCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.suibi_ry.project.blog.domain.SubiBlogComment;
import com.suibi_ry.project.blog.service.ISubiBlogCommentService;

/**
 * 评论Service业务层处理
 *
 * @author mxchen
 * @date 2020-07-19
 */
@Service
public class SubiBlogCommentServiceImpl implements ISubiBlogCommentService {
    @Autowired(required = false)
    private SubiBlogCommentMapper subiBlogCommentMapper;

    /**
     * 查询评论
     *
     * @param commentId 评论ID
     * @return 评论
     */
    @Override
    public SubiBlogComment selectSubiBlogCommentById(Long commentId) {
        return subiBlogCommentMapper.selectSubiBlogCommentById(commentId);
    }

    /**
     * 查询评论列表
     *
     * @param subiBlogComment 评论
     * @return 评论
     */
    @Override
    public List<SubiBlogComment> selectSubiBlogCommentList(SubiBlogComment subiBlogComment) {
        return subiBlogCommentMapper.selectSubiBlogCommentList(subiBlogComment);
    }

    /**
     * 新增评论
     *
     * @param subiBlogComment 评论
     * @return 结果
     */
    @Override
    public int insertSubiBlogComment(SubiBlogComment subiBlogComment) {
        return subiBlogCommentMapper.insertSubiBlogComment(subiBlogComment);
    }

    /**
     * 修改评论
     *
     * @param subiBlogComment 评论
     * @return 结果
     */
    @Override
    public int updateSubiBlogComment(SubiBlogComment subiBlogComment) {
        return subiBlogCommentMapper.updateSubiBlogComment(subiBlogComment);
    }

    /**
     * 批量删除评论
     *
     * @param commentIds 需要删除的评论ID
     * @return 结果
     */
    @Override
    public int deleteSubiBlogCommentByIds(Long[] commentIds) {
        return subiBlogCommentMapper.deleteSubiBlogCommentByIds(commentIds);
    }

    /**
     * 删除评论信息
     *
     * @param commentId 评论ID
     * @return 结果
     */
    @Override
    public int deleteSubiBlogCommentById(Long commentId) {
        return subiBlogCommentMapper.deleteSubiBlogCommentById(commentId);
    }
}
