<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="评论用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入评论用户ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评论博文ID" prop="articleId">
        <el-input
          v-model="queryParams.articleId"
          placeholder="请输入评论博文ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评论内容" prop="commentContext">
        <el-input
          v-model="queryParams.commentContext"
          placeholder="请输入评论内容"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="点赞数" prop="commentLike">
        <el-input
          v-model="queryParams.commentLike"
          placeholder="请输入点赞数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="父评论ID" prop="parentCommentId">
        <el-input
          v-model="queryParams.parentCommentId"
          placeholder="请输入父评论ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评论日期" prop="commentDate">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.commentDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择评论日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['blog:comment:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['blog:comment:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['blog:comment:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['blog:comment:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="commentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="评论ID" align="center" prop="commentId" />
      <el-table-column label="评论用户ID" align="center" prop="userId" />
      <el-table-column label="评论博文ID" align="center" prop="articleId" />
      <el-table-column label="评论内容" align="center" prop="commentContext" />
      <el-table-column label="点赞数" align="center" prop="commentLike" />
      <el-table-column label="父评论ID" align="center" prop="parentCommentId" />
      <el-table-column label="评论日期" align="center" prop="commentDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.commentDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['blog:comment:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['blog:comment:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改评论对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="评论用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入评论用户ID" />
        </el-form-item>
        <el-form-item label="评论博文ID" prop="articleId">
          <el-input v-model="form.articleId" placeholder="请输入评论博文ID" />
        </el-form-item>
        <el-form-item label="评论内容" prop="commentContext">
          <el-input v-model="form.commentContext" placeholder="请输入评论内容" />
        </el-form-item>
        <el-form-item label="点赞数" prop="commentLike">
          <el-input v-model="form.commentLike" placeholder="请输入点赞数" />
        </el-form-item>
        <el-form-item label="父评论ID" prop="parentCommentId">
          <el-input v-model="form.parentCommentId" placeholder="请输入父评论ID" />
        </el-form-item>
        <el-form-item label="评论日期" prop="commentDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.commentDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择评论日期">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listComment, getComment, delComment, addComment, updateComment, exportComment } from "@/api/blog/comment";

export default {
  name: "Comment",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 评论表格数据
      commentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: undefined,
        articleId: undefined,
        commentContext: undefined,
        commentLike: undefined,
        parentCommentId: undefined,
        commentDate: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "评论用户ID不能为空", trigger: "blur" }
        ],
        articleId: [
          { required: true, message: "评论博文ID不能为空", trigger: "blur" }
        ],
        commentContext: [
          { required: true, message: "评论内容不能为空", trigger: "blur" }
        ],
        parentCommentId: [
          { required: true, message: "父评论ID不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询评论列表 */
    getList() {
      this.loading = true;
      listComment(this.queryParams).then(response => {
        this.commentList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        commentId: undefined,
        userId: undefined,
        articleId: undefined,
        commentContext: undefined,
        commentLike: undefined,
        parentCommentId: undefined,
        commentDate: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.commentId)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加评论";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const commentId = row.commentId || this.ids
      getComment(commentId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改评论";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.commentId != undefined) {
            updateComment(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addComment(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const commentIds = row.commentId || this.ids;
      this.$confirm('是否确认删除评论编号为"' + commentIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delComment(commentIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有评论数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportComment(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>