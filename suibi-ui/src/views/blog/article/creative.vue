<template>
  <div class="app-container">
    <el-form :model="markdownForm" :rules="rules" :inline="true" label-width="68px">
      <el-form-item label="标 题" prop="title">
        <el-input v-model="markdownForm.title" size="small"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="mini" @click="handleRelease">发布</el-button>
        <el-button type="primary" size="mini" @click="handleDrafts">存为草稿</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">取消</el-button>
      </el-form-item>
      <br>
      <el-form-item label="分类" prop="type">

        <el-select v-model="markdownForm.typeName"  placeholder="请选择分类" size="small">
          <el-option
            v-for="type in typeList"
            :key="type.typeId"
            :label = "type.typeName"
            :value ="type.typeId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="标签" prop="Label">

        <el-select v-model="markdownForm.labelName" placeholder="请选择标签" :multiple="true" :multiple-limit="3" clearable size="medium">

          <el-option
            v-for="label in lableList"
            :key="label.labelId"
            :label = "label.labelName"
            :value ="label.labelId"
          >
            <span :color="label.labelColor">{{ label.labelName }}</span>
          </el-option>

        </el-select>
      </el-form-item>
    </el-form>
    <mavon-editor ref="md" @imgAdd="imgAdd" @imgDel="imgDel" v-model="markdownForm.contentMarkdown"
                  :codeStyle="markdown.codeStyle" :toolbars="markdown.toolbars"/>
    <br>
  </div>
</template>

<script>
  import Type from "@/api/blog/type";
  import Label from "@/api/blog/label";
  export default {
    name: "Creative",
    data() {
      return {
        markdown: {
          codeStyle: 'atom-one-dark',
          toolbars: {
            bold: true, // 粗体
            italic: true, // 斜体
            header: true, // 标题
            underline: true, // 下划线
            strikethrough: true, // 中划线
            mark: true, // 标记
            superscript: true, // 上角标
            subscript: true, // 下角标
            quote: true, // 引用
            ol: true, // 有序列表
            ul: true, // 无序列表
            link: true, // 链接
            imagelink: true, // 图片链接
            code: true, // code
            table: true, // 表格
            fullscreen: true, // 全屏编辑
            readmodel: true, // 沉浸式阅读
            htmlcode: true, // 展示html源码
            help: true, // 帮助
            /* 1.3.5 */
            undo: true, // 上一步
            redo: true, // 下一步
            trash: true, // 清空
            save: true, // 保存（触发events中的save事件）
            /* 1.4.2 */
            navigation: true, // 导航目录
            /* 2.1.8 */
            alignleft: true, // 左对齐
            aligncenter: true, // 居中
            alignright: true, // 右对齐
            /* 2.2.1 */
            subfield: true, // 单双栏模式
            preview: false, // 预览
          }
        },
        markdownForm: {
          articleId: null,
          typeName: 1000,
          labelName:undefined,
          articleContent: undefined,
          contentMarkdown: '',
          contentHtml: null,
        },
        rules: {
          title: [
            {required: true, message: '请输入标题', trigger: 'blur'},
            {min: 1, max: 80, message: '长度在1到80个字符', trigger: 'blur'}
          ],
          Label: [
            { required: true, message: "标签至少一个", trigger: "blur" }
          ]
        },
        queryTypeParams:{
          pageNum: 1,
          pageSize: 10,
          typeName: undefined,
          orderNum: undefined,
          typeStatus: undefined,
        },
        queryLabelParams:{
          pageNum: 1,
          pageSize: 10,
          labelName: undefined,
          labelColor: undefined,
          labelStatus: undefined,
        },
        typeList:[],
        lableList:[],
        lastSaveTime: null,
        timer: null
      }
    },
    created() {
      this.init();
      this.getArticle()
    },
    mounted() {
      this.timer = setInterval(this.intervalSave, 2 * 60 * 1000);
    },
    beforeDestroy() {
      clearInterval(this.timer);
    },
    methods:{
      init(){
        Type.listType(this.queryTypeParams).then(response => {
          this.typeList = response.rows;
        })
        Label.listLabel(this.queryLabelParams).then(response => {
          this.lableList = response.rows;
        })
      },
      getArticle(){  //获取文章内容
        //先判断路由是否携带文章ID
        if(this.$route.params && this.$route.params.id){

        }
      },
      handleRelease(){

      },
      handleDrafts(){

      },
      resetQuery(){

      },
      intervalSave(){ //自动保存
      },
      imgAdd(pos, file){ //添加图片，pos为位置

      },
      imgDel(pos,url){ //删除图片，并不是修改就会触发，仅支持工具栏操作

      }

    }

  }
</script>

<style lang="scss" scoped>
  .v-note-panel.shadow{
    min-height: 440px;  //设置编辑框最小高度
  }
</style>
