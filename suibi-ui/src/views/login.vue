<template>
  <div class="login-container">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on" label-position="left">

      <div class="title-container">
        <h3 class="title">SuiBi后台管理入口</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          ref="username"
          v-model="loginForm.username"
          placeholder="Username"
          name="username"
          style="width: 85%"
          type="text"
          tabindex="1"
          auto-complete="off"
        />
        <!--auto-complete="on" 默认。规定启用自动完成功能。 off 	规定禁用自动完成功能。 -->
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="loginForm.password"
          :type="passwordType"
          placeholder="密码"
          style="width: 85%"
          name="password"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native="handleLogin"
        />
        <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>
      <!--验证码-->
      <el-form-item prop="code">
        <span class="svg-container">
          <svg-icon icon-class="validCode" />
        </span>
        <el-input
          ref="code"
          v-model="loginForm.code"
          auto-complete="on"
          placeholder="验证码"
          style="width: 58%"
          type="text"
          tabindex="3"
          @keyup.enter.native="handleLogin"
        />
        <div class="login-code">
          <img :src="codeUrl" @click="getCode" />
        </div>
      </el-form-item>
      <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;">记住密码</el-checkbox>
      <el-button
        :loading="loading"
        type="primary"
        style="width:100%;margin-bottom:30px;"
        @click.native.prevent="handleLogin">
        <span v-if="!loading">登 录</span>
        <span v-else>登 录 中...</span>
      </el-button>


    </el-form>
  </div>
</template>

<script>
  import { getCodeImg } from "@/api/login"
  import Cookies from "js-cookie";
  import { encrypt, decrypt } from '@/utils/jsencrypt'
  export default {
    name: 'Login',
    data() {
      return {
        codeUrl: "",
        cookiePassword: "",
        loginForm: {
          username: "",
          password: "",
          rememberMe: false,
          code: "",
          uuid: ""
        },
        loginRules: {
          username: [{ required: true, trigger: "blur", message: "用户名不能为空" }
          ],
          password: [{ required: true, trigger: "blur", message: "密码不能为空" }
          ],
          code: [{ required: true, trigger: "change", message: "验证码不能为空" }]
        },
        loading: false,
        passwordType: 'password',
        redirect: undefined
      }
    },
    watch: {
      $route: {
        handler: function(route) {
          this.redirect = route.query && route.query.redirect
        },
        immediate: true
      }
    },
    created() {
      this.getCode();
      this.getCookie();
    },
    methods: {
      getCode() {
        getCodeImg().then(res => {
          this.codeUrl = "data:image/gif;base64," + res.img;
          this.loginForm.uuid = res.uuid;
          console.log("uuid:"+this.loginForm.uuid)
        });
      },
      getCookie() {
        console.log("已进入getCookie")
        const username = Cookies.get("username");
        console.log(username)
        const password = Cookies.get("password");
        console.log(password)
        const rememberMe = Cookies.get('rememberMe')
        console.log(rememberMe)
        this.loginForm = {
          username: username === undefined ? this.loginForm.username : username,
          password: password === undefined ? this.loginForm.password : decrypt(password),
          rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
        };
      },
      showPwd() {
        if (this.passwordType === 'password') {
          this.passwordType = ''
        } else {
          this.passwordType = 'password'
        }
        this.$nextTick(() => {
          this.$refs.password.focus()
        })
      },
      handleLogin() {
        console.log("已经进入handleLogin")
        console.log("uuid:"+this.loginForm.uuid)
        this.$refs.loginForm.validate(valid => {
          if (valid) {
            this.loading = true
            if (this.loginForm.rememberMe) {
              Cookies.set("username", this.loginForm.username, { expires: 30 });
              Cookies.set("password", encrypt(this.loginForm.password), { expires: 30 });
              Cookies.set('rememberMe', this.loginForm.rememberMe, { expires: 30 });
            } else {
              Cookies.remove("password");
              Cookies.remove('rememberMe');
            }
            console.log("loginForm"+this.loginForm)
            this.$store.dispatch('Login', this.loginForm).then(() => {
              this.$router.push({ path: this.redirect || '/' })
              this.loading = false
            }).catch(() => {
              this.loading = false
              this.getCode();
            })
          }
        })
      }
    }
  }
</script>

<style lang="scss">
  /* 修复input 背景不协调 和光标变色 */
  /* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

  $bg:#283443;
  $light_gray:#fff;
  $cursor: #fff;

  @supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
    .login-container .el-input input {
      color: $cursor;
    }
  }

  /* reset element-ui css */
  .login-container {
    .el-input {
      display: inline-block;
      height: 47px;
      input {
        background: transparent;
        border: 0px;
        -webkit-appearance: none;
        border-radius: 0px;
        padding: 12px 5px 12px 15px;
        color: $light_gray;
        height: 47px;
        caret-color: $cursor;

        &:-webkit-autofill {
          box-shadow: 0 0 0px 1000px $bg inset !important;
          -webkit-text-fill-color: $cursor !important;
        }
      }
    }

    .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: #454545;
    }
  }
</style>

<style lang="scss" scoped>
  $bg:#2d3a4b;
  $dark_gray:#889aa4;
  $light_gray:#eee;

  .login-container {
    min-height: 100%;
    width: 100%;
    background-image: url("../assets/image/login-background.jpg");
    background-size: cover;
    overflow: hidden;

    .login-form {
      position: relative;
      width: 400px;
      max-width: 100%;
      /*padding: 80px 35px 0;*/
      top:50%;
      left:50%;
      margin-top:206px;/*注意这里必须是DIV高度的一半*/
      margin-left:-200px;/*这里是DIV宽度的一半*/
      overflow: hidden;
    }

    .svg-container {
      padding: 6px 5px 6px 15px;
      color: $dark_gray;
      vertical-align: middle;
      width: 30px;
      display: inline-block;
    }

    .title-container {
      position: relative;

      .title {
        font-size: 26px;
        color: $light_gray;
        margin: 0px auto 40px auto;
        text-align: center;
        font-weight: bold;
      }
    }
    .login-code {
      width: 32%;
      display: inline-block;
      img {
        cursor: pointer;
        vertical-align: middle;
        margin: 1px 0 0 10px;
      }
    }
    .show-pwd {
      position: absolute;
      right: 10px;
      top: 7px;
      font-size: 16px;
      color: #889aa4;
      cursor: pointer;
      user-select: none;
    }
  }
</style>
