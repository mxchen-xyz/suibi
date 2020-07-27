package com.suibi_ry.project.blog.controller;


import com.suibi_ry.common.utils.poi.ExcelUtil;
import com.suibi_ry.framework.aspectj.lang.annotation.Log;
import com.suibi_ry.framework.aspectj.lang.enums.BusinessType;
import com.suibi_ry.framework.web.domain.AjaxResult;
import com.suibi_ry.framework.web.page.TableDataInfo;
import com.suibi_ry.project.blog.domain.SubiBlogComment;
import com.suibi_ry.project.blog.service.ISubiBlogCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.suibi_ry.framework.web.controller.BaseController;

import java.util.List;

/**
 * 评论Controller
 *
 * @author mxchen
 * @date 2020-07-19
 */
@RestController
@RequestMapping("/blog/comment")
public class SubiBlogCommentController extends BaseController {
    @Autowired
    private ISubiBlogCommentService subiBlogCommentService;

    /**
     * 查询评论列表
     */
    @PreAuthorize("@ss.hasPermi('blog:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(SubiBlogComment subiBlogComment) {
        startPage();
        List<SubiBlogComment> list = subiBlogCommentService.selectSubiBlogCommentList(subiBlogComment);
        return getDataTable(list);
    }

    /**
     * 导出评论列表
     */
    @PreAuthorize("@ss.hasPermi('blog:comment:export')")
    @Log(title = "评论", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SubiBlogComment subiBlogComment) {
        List<SubiBlogComment> list = subiBlogCommentService.selectSubiBlogCommentList(subiBlogComment);
        ExcelUtil<SubiBlogComment> util = new ExcelUtil<SubiBlogComment>(SubiBlogComment.class);
        return util.exportExcel(list, "comment");
    }

    /**
     * 获取评论详细信息
     */
    @PreAuthorize("@ss.hasPermi('blog:comment:query')")
    @GetMapping(value = "/{commentId}")
    public AjaxResult getInfo(@PathVariable("commentId") Long commentId) {
        return AjaxResult.success(subiBlogCommentService.selectSubiBlogCommentById(commentId));
    }

    /**
     * 新增评论
     */
    @PreAuthorize("@ss.hasPermi('blog:comment:add')")
    @Log(title = "评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SubiBlogComment subiBlogComment) {
        return toAjax(subiBlogCommentService.insertSubiBlogComment(subiBlogComment));
    }

    /**
     * 修改评论
     */
    @PreAuthorize("@ss.hasPermi('blog:comment:edit')")
    @Log(title = "评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SubiBlogComment subiBlogComment) {
        return toAjax(subiBlogCommentService.updateSubiBlogComment(subiBlogComment));
    }

    /**
     * 删除评论
     */
    @PreAuthorize("@ss.hasPermi('blog:comment:remove')")
    @Log(title = "评论", businessType = BusinessType.DELETE)
    @DeleteMapping("/{commentIds}")
    public AjaxResult remove(@PathVariable Long[] commentIds) {
        return toAjax(subiBlogCommentService.deleteSubiBlogCommentByIds(commentIds));
    }

}
