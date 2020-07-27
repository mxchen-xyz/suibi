<template>

  <div class="app-container">

    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">

      <el-form-item label="标签名字" prop="labelName">

        <el-input

          v-model="queryParams.labelName"

          placeholder="请输入标签名字"

          clearable

          size="small"

          @keyup.enter.native="handleQuery"

        />

      </el-form-item>

      <el-form-item label="标签状态" prop="labelStatus">

        <el-select v-model="queryParams.labelStatus" placeholder="请选择标签状态" clearable size="small">

          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />

        </el-select>

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

          v-hasPermi="['blog:label:add']"

        >新增</el-button>

      </el-col>
      <el-col :span="1.5">

        <el-button

          type="danger"

          icon="el-icon-delete"

          size="mini"

          :disabled="multiple"

          @click="handleDelete"

          v-hasPermi="['blog:label:remove']"

        >删除</el-button>

      </el-col>

    </el-row>



    <el-table v-loading="loading" :data="labelList" @selection-change="handleSelectionChange">

      <el-table-column type="selection" width="55" align="center" />

      <el-table-column label="标签Id" align="center" prop="labelId" />

      <el-table-column label="标签名字" align="center" prop="labelName" />

      <el-table-column label="标签颜色代码" align="center" prop="labelColor" />

      <el-table-column label="标签状态" align="center" width="100">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.labelStatus"
            active-value="0"
            inactive-value="1"
            @change="handleStatusChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>

      <el-table-column label="备注" align="center" prop="remark" />

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">

        <template slot-scope="scope">
          <el-button
            type="text"
            size="small"
            icon="el-icon-view"
            @click="handlePreview(scope.row)"
          >预览</el-button>
          <el-button

            size="mini"

            type="text"

            icon="el-icon-edit"

            @click="handleUpdate(scope.row)"

            v-hasPermi="['blog:label:edit']"

          >修改</el-button>

          <el-button

            size="mini"

            type="text"

            icon="el-icon-delete"

            @click="handleDelete(scope.row)"

            v-hasPermi="['blog:label:remove']"

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



    <!-- 添加或修改博客标签对话框 -->

    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>

      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="标签名字" prop="labelName">

          <el-input v-model="form.labelName" placeholder="请输入标签名字" />

        </el-form-item>

        <el-form-item label="标签颜色" prop="labelColor">

          <el-input v-model="form.labelColor" placeholder="请输入标签颜色代码" />
          <el-color-picker v-model="form.labelColor" size="small" class="color_pos"/>
        </el-form-item>

        <el-form-item label="标签状态">

          <el-radio-group v-model="form.labelStatus">

            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>

          </el-radio-group>
        </el-form-item>
        <el-form-item label="标签备注">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"></el-input>
        </el-form-item>

      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="success" @click="randomColor">随机颜色</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>

    </el-dialog>
    <!-- 预览界面 -->
    <el-dialog :title="preview.title" :visible.sync="preview.open" width="500px" append-to-body>
      <el-tag
        :color="preview.data.labelColor"
        :key="preview.data.labelName"
        closable>
        <span style="color: black">{{preview.data.labelName}}</span>
      </el-tag>
    </el-dialog>
  </div>

</template>



<script>

  import Lable from "@/api/blog/label";



  export default {

    name: "Label",

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
        //字典数据
        statusOptions:[],

        // 博客标签表格数据

        labelList: [],

        // 弹出层标题

        title: "",

        // 是否显示弹出层

        open: false,

        // 查询参数

        queryParams: {

          pageNum: 1,

          pageSize: 10,

          labelName: undefined,

          labelColor: undefined,

          labelStatus: undefined,

        },
        // 预览参数
        preview: {
          open: false,
          title: "标签预览",
          data: {}
        },

        // 表单参数

        form: {},

        // 表单校验

        rules: {

          labelName: [

            { required: true, message: "标签名字不能为空", trigger: "blur" }

          ],

        }

      };

    },

    created() {

      this.getList();
      this.getDicts("blog_label_status").then(response => {
        this.statusOptions = response.data;
      });

    },

    methods: {

      /** 查询博客标签列表 */

      getList() {

        this.loading = true;

        Lable.listLabel(this.queryParams).then(response => {

          this.labelList = response.rows;

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

          labelId: undefined,

          labelName: undefined,

          labelColor: "#0000FF",

          labelStatus: "0",

          createTime: undefined,

          updateTime: undefined,

          remark: undefined

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

        this.ids = selection.map(item => item.labelId)

        this.single = selection.length!=1

        this.multiple = !selection.length

      },

      /** 新增按钮操作 */

      handleAdd() {

        this.reset();

        this.open = true;

        this.title = "添加博客标签";

      },
      handlePreview(row){
        Lable.getLabel(row.labelId).then(response => {
          this.preview.data = response.data;
          console.log(this.preview.data)
          this.preview.open = true;
        });
      },
      /** 修改按钮操作 */

      handleUpdate(row) {

        this.reset();

        const labelId = row.labelId || this.ids

        Lable.getLabel(labelId).then(response => {

          this.form = response.data;

          this.open = true;

          this.title = "修改博客标签";

        });

      },
      /**随机颜色按钮*/
      randomColor:function(){
        let r = Math.floor(Math.random() * 255);
        let g = Math.floor(Math.random() * 255);
        let b = Math.floor(Math.random() * 255);
        this.form.labelColor ="#"+ ((1<<24)+(r << 16)+(g << 8 )+ b).toString(16).slice(1);//rgb转换16进制
      },
      // 分类状态修改
      handleStatusChange(row) {
        console.log("要修改成的状态："+row.labelStatus)
        let text = row.labelStatus === "0" ? "使用" : "停用";
        this.$confirm('确认要"' + text + '""' + row.labelName + '"这个标签吗?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return Lable.changeLabelStatus(row.labelId, row.labelStatus);
        }).then(() => {
          console.log("当前状态："+row.labelStatus)
          this.msgSuccess(text + "成功");
        }).catch(function() {
          row.labelStatus = row.labelStatus === "0" ? "1" : "0";
        });
      },

      /** 提交按钮 */

      submitForm: function() {

        this.$refs["form"].validate(valid => {

          if (valid) {

            if (this.form.labelId != undefined) {

              Lable.updateLabel(this.form).then(response => {

                if (response.code === 200) {

                  this.msgSuccess("修改成功");

                  this.open = false;

                  this.getList();

                }

              });

            } else {

              Lable.addLabel(this.form).then(response => {

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

        const labelIds = row.labelId || this.ids;

        this.$confirm('是否确认删除博客标签编号为"' + labelIds + '"的数据项?', "警告", {

          confirmButtonText: "确定",

          cancelButtonText: "取消",

          type: "warning"

        }).then(function() {

          return Lable.delLabel(labelIds);

        }).then(() => {

          this.getList();

          this.msgSuccess("删除成功");

        }).catch(function() {});

      },

      /** 导出按钮操作 */

      handleExport() {

        const queryParams = this.queryParams;

        this.$confirm('是否确认导出所有博客标签数据项?', "警告", {

          confirmButtonText: "确定",

          cancelButtonText: "取消",

          type: "warning"

        }).then(function() {

          return Lable.exportLabel(queryParams);

        }).then(response => {

          this.download(response.msg);

        }).catch(function() {});

      }

    }

  };

</script>
<style lang="scss" scoped>
  .color_pos{
    position: absolute;
    right: 6px;
    top: 2px;
  }
</style>
