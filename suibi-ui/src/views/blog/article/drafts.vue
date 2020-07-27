<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="博文标题" prop="articleTitle">
        <el-input
          v-model="queryParams.articleTitle"
          placeholder="请输入博文标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table v-loading="loading" :data="articleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="博文标题" align="center" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <router-link :to="{path:'/article/creative'}" class="link-type">
            <span>{{scope.row.articleTitle}}</span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime"/>
      <el-table-column label="发布状态" align="center" :formatter="statusFormat" prop="articleStatus"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['blog:article:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['blog:article:remove']"
          >删除
          </el-button>
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

    <!-- 添加或修改博客文章对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="博文标题" prop="articleTitle">
          <el-input v-model="form.articleTitle" placeholder="请输入博文标题"/>
        </el-form-item>
        <el-form-item label="发布状态">
          <el-radio-group v-model="form.articleStatus">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}
            </el-radio>
          </el-radio-group>
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
  import article from "@/api/blog/article";

  export default {
    name: "Drafts",
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
        // 博客文章表格数据
        articleList: [],
        //发布状态字典数据
        statusOptions: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          articleTitle: undefined,
          articleContent: undefined,
          articleStatus: 0,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          articleTitle: [
            {required: true, message: "博文标题不能为空", trigger: "blur"}
          ],
          articleContent: [
            {required: true, message: "博文内容不能为空", trigger: "blur"}
          ],
        }
      };
    },
    created() {
      this.getList();
      this.getDicts("blog_article_status").then(response => {
        this.statusOptions = response.data;
      });
    },
    methods: {
      // 发布状态字典翻译
      statusFormat(row, column) {
        return this.selectDictLabel(this.statusOptions, row.articleStatus);
      },
      /** 查询博客文章列表 */
      getList() {
        this.loading = true;
        article.listArticle(this.queryParams).then(response => {
          this.articleList = response.rows;
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
          articleId: undefined,
          userId: undefined,
          articleTitle: undefined,
          articleContent: undefined,
          articleView: undefined,
          articleCount: undefined,
          articleLike: undefined,
          articleStatus: "0",
          delFlag: undefined,
          createTime: undefined,
          updateTime: undefined
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
        this.ids = selection.map(item => item.articleId)
        this.single = selection.length != 1
        this.multiple = !selection.length
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const articleId = row.articleId || this.ids
        article.getArticle(articleId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改博客文章";
        });
      },
      /** 提交按钮 */
      submitForm: function () {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.articleId != undefined) {
              article.updateArticle(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                }
              }).then(() => {
                this.$router.push("/article/creative");
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const articleIds = row.articleId || this.ids;
        this.$confirm('是否确认删除博客文章编号为"' + articleIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return article.delArticle(articleIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function () {
        });
      }
    }
  };
</script>
